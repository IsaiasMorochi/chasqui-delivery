package com.imorochi.chasqui.application.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imorochi.chasqui.application.search.SearchCustom;
import com.imorochi.chasqui.application.search.SearchRequestDTO;
import com.imorochi.chasqui.domain.document.Product;
import com.imorochi.chasqui.domain.graphQL.SlashGraphQlResultProduct;
import com.imorochi.chasqui.infrastructure.utils.helper.Indices;
import com.imorochi.chasqui.infrastructure.config.SlashGraphQlProperties;
import com.imorochi.chasqui.infrastructure.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class ProductService {

    private static final String PRODUCT_QUERY = "query { queryProduct { productId category { categoryId categoryName } productName productPrice productPhotoUrl } }";

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final SlashGraphQlProperties slashGraphQlProperties;
    private final RestHighLevelClient client;
    private final ElasticsearchClient elasticsearchClient;

    public ProductService(SlashGraphQlProperties slashGraphQlProperties, RestHighLevelClient client, ElasticsearchClient elasticsearchClient) {
        this.slashGraphQlProperties = slashGraphQlProperties;
        this.client = client;
        this.elasticsearchClient = elasticsearchClient;
    }

    public Boolean save(final Product product) {
        try {
            co.elastic.clients.elasticsearch.core.IndexRequest<Product> request = co.elastic.clients.elasticsearch.core.IndexRequest.of(op -> op.index(Indices.PRODUCT_INDEX)
                    .id(String.valueOf(product.getProductId()))
                    .document(product)
            );
            co.elastic.clients.elasticsearch.core.IndexResponse response = elasticsearchClient.index(request);
            log.info("[IndexResponse] [save]: UUID: {}, source: {}", product.getProductId(), request.toString());
            return true;
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    public Product findById(final Integer id) {
        try {
            final GetResponse documentFields = client.get(
                    new GetRequest(Indices.PRODUCT_INDEX, String.valueOf(id)),
                    RequestOptions.DEFAULT
            );
            if (documentFields == null || documentFields.isSourceEmpty()) {
                return null;
            }

            return MAPPER.readValue(documentFields.getSourceAsString(), Product.class);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public List<Product> search(final SearchRequestDTO dto) {
        final SearchRequest request = SearchCustom.buildSearchRequest(
                Indices.PRODUCT_INDEX,
                dto
        );

        return searchInternal(request);
    }

    private List<Product> searchInternal(final SearchRequest request) {
        if (request == null) {
            log.error("Failed to build search request");
            return Collections.emptyList();
        }

        try {
            final SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            final SearchHit[] searchHits = response.getHits().getHits();
            final List<Product> products = new ArrayList<>(searchHits.length);

            for (SearchHit hit : searchHits) {
                products.add(MAPPER.readValue(hit.getSourceAsString(), Product.class)
                );
            }
            co.elastic.clients.elasticsearch.core.SearchResponse s = new co.elastic.clients.elasticsearch.core.SearchResponse.Builder<Product>().build();
            co.elastic.clients.elasticsearch.core.SearchRequest r = new co.elastic.clients.elasticsearch.core.SearchRequest.Builder().build();

            s.aggregations().get("max");

            return products;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<Product> getAllProducts() throws Exception {
        ResponseEntity<String> responseEntity = RestTemplateUtils.query(slashGraphQlProperties.getHostname(), PRODUCT_QUERY);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SlashGraphQlResultProduct slashGraphQlResult = objectMapper.readValue(responseEntity.getBody(), SlashGraphQlResultProduct.class);
            log.debug("slashGraphQlResult={}", slashGraphQlResult);
            return slashGraphQlResult.getData().getQueryProduct();
        } catch (JsonProcessingException e) {
            throw new Exception("An error was encountered processing responseEntity=" + responseEntity.getBody(), e);
        }
    }

}
