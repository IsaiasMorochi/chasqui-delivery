package com.imorochi.chasqui.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gitlab.johnjvester.randomizer.RandomGenerator;
import com.imorochi.chasqui.application.engine.SlopeOne;
import com.imorochi.chasqui.domain.document.Customer;
import com.imorochi.chasqui.domain.document.Product;
import com.imorochi.chasqui.domain.document.Rating;
import com.imorochi.chasqui.domain.document.Recommendation;
import com.imorochi.chasqui.domain.graphQL.GraphQlDataRating;
import com.imorochi.chasqui.domain.graphQL.SlashGraphQlResultRating;
import com.imorochi.chasqui.infrastructure.config.SlashGraphQlProperties;
import com.imorochi.chasqui.infrastructure.utils.RestTemplateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RecommendationService {

    private final ProductService productService;
    private final CustomerService customerService;
    private final SlashGraphQlProperties slashGraphQlProperties;

    public RecommendationService(ProductService productService, CustomerService customerService, SlashGraphQlProperties slashGraphQlProperties) {
        this.productService = productService;
        this.customerService = customerService;
        this.slashGraphQlProperties = slashGraphQlProperties;
    }

    private static final String RATING_QUERY = "query RatingQuery { queryRating { id score by { username } about { productId category { categoryId categoryName } productName productPrice productPhotoUrl  }  } }";
//    private static final String RATING_QUERY = "query RatingQuery { queryRating { id, score, by { username }, about { name } } }";

    public Recommendation recommend() throws Exception {
        ResponseEntity<String> responseEntity = RestTemplateUtils.query(slashGraphQlProperties.getHostname(), RATING_QUERY);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SlashGraphQlResultRating slashGraphQlResult = objectMapper.readValue(responseEntity.getBody(), SlashGraphQlResultRating.class);
            log.debug("slashGraphQlResult={}", slashGraphQlResult);

            return makeRecommendation(slashGraphQlResult.getData());
        } catch (JsonProcessingException e) {
            throw new Exception("An error was encountered processing responseEntity=" + responseEntity.getBody(), e);
        }
    }

    private Recommendation makeRecommendation(GraphQlDataRating ratings) throws Exception {
        if (ratings == null || CollectionUtils.isEmpty(ratings.getQueryRating())) {
            throw new Exception("No ratings found to process");
        }

        Map<Customer, HashMap<Product, Double>> data = new HashMap<>();

        for (Rating rating : ratings.getQueryRating()) {
            if (data.containsKey(rating.getBy())) {
                if (!data.get(rating.getBy()).containsKey(rating.getAbout())) {
                    data.get(rating.getBy()).put(rating.getAbout(), computeRating(rating.getScore()));
                } else {
                    log.warn("Multiple ratings found skipping");
                }
            } else {
                HashMap<Product, Double> customerRating = new HashMap<>();
                customerRating.put(rating.getAbout(), computeRating(rating.getScore()));
                data.put(rating.getBy(), customerRating);
            }
        }

        log.debug("data={}", data);

        Map<Customer, HashMap<Product, Double>> projectedData = SlopeOne.slopeOne(data, productService.getAllProducts());

        RandomGenerator<Customer> randomGenerator = new RandomGenerator<>();
        List<Customer> randomCustomers = randomGenerator.randomize(customerService.getCustomers(), Integer.valueOf("1"));
        log.info("Using randomCustomer={}", randomCustomers.get(0));

        Recommendation recommendation = new Recommendation();
        recommendation.setMatchedCustomer(randomCustomers.get(0));
        recommendation.setResultsMap(projectedData.get(recommendation.getMatchedCustomer()));
        recommendation.setRatingsMap(data.get(recommendation.getMatchedCustomer()));
        recommendation.setRecommendationsMap(createRecommendationsMap(recommendation.getRatingsMap(), recommendation.getResultsMap()));
        return recommendation;
    }

    private HashMap<Product, Double> createRecommendationsMap(HashMap<Product, Double> ratingsMap, HashMap<Product, Double> resultsMap) {
        HashMap<Product, Double> recommendationsMap = new HashMap<>();

        for (Map.Entry<Product, Double> pair : resultsMap.entrySet()) {
            boolean add = true;

            if (ratingsMap.containsKey(pair.getKey()) && ratingsMap.get(pair.getKey()).equals(pair.getValue())) {
                add = false;
            }

            if (add) {
                recommendationsMap.put(pair.getKey(), pair.getValue());
            }
        }

        return recommendationsMap;
    }

    /**
     * Simple example: converts a (double) score which should have a range of 0 - 5 into a (double) range from 0.0 - 1.0.
     *
     * @param score (double) score to analyze
     * @return (double)
     */
    private double computeRating(double score) {
        switch ((int) score) {
            case 5 : return 1.0d;
            case 4 : return 0.8d;
            case 3 : return 0.6d;
            case 2 : return 0.4d;
            case 1 : return 0.2d;
            case 0 :
            default:
                return 0.0d;
        }
    }
}
