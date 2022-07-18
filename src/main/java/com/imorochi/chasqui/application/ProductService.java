package com.imorochi.chasqui.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imorochi.chasqui.application.search.SearchCustom;
import com.imorochi.chasqui.application.search.SearchRequestDTO;
import com.imorochi.chasqui.domain.document.Product;
import com.imorochi.chasqui.domain.helper.Indices;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);
    private final RestHighLevelClient client;
//    private final ProductRepository productRepository;

    public ProductService(RestHighLevelClient client) {
        this.client = client;
    }

    public Boolean save(final Product product) {
//        productRepository.save(product);
        try {
            final String vehicleAsString = MAPPER.writeValueAsString(product);

            final IndexRequest request = new IndexRequest(Indices.PRODUCT_INDEX);
            request.id(String.valueOf(product.getProductId()));
            request.source(vehicleAsString, XContentType.JSON);

            final IndexResponse response = client.index(request, RequestOptions.DEFAULT);

            return response != null && response.status().equals(RestStatus.OK);
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    public Product findById(final Integer id) {
//        return productRepository.findById(id).orElse(null);
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
            LOG.error(e.getMessage(), e);
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
            LOG.error("Failed to build search request");
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

            return products;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

}
