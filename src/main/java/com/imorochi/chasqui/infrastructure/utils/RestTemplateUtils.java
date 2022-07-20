package com.imorochi.chasqui.infrastructure.utils;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public final class RestTemplateUtils {

    private RestTemplateUtils() { }

    private static final String MEDIA_TYPE_GRAPH_QL = "application/graphql";
    private static final String GRAPH_QL_URI = "/graphql";

    public static ResponseEntity<String> query(String hostname, String query) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MEDIA_TYPE_GRAPH_QL));

        HttpEntity<String> httpEntity = new HttpEntity<>(query, headers);
        return restTemplate.exchange(hostname + GRAPH_QL_URI, HttpMethod.POST, httpEntity, String.class);
    }
}
