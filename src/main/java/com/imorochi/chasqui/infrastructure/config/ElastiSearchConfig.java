package com.imorochi.chasqui.infrastructure.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpHeaders;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.imorochi.chasqui.domain.repository")
@ComponentScan(basePackages = {"com.imorochi.chasqui"})
public class ElastiSearchConfig extends AbstractElasticsearchConfiguration {

    @Value("${elasticsearch.url}")
    public String host;

    @Value("${elasticsearch.user}")
    public String user;

    @Value("${elasticsearch.password}")
    public String password;

    @Override
    public RestHighLevelClient elasticsearchClient() {

        HttpHeaders compatibilityHeaders = new HttpHeaders();
        compatibilityHeaders.add("Accept", "application/vnd.elasticsearch+json;compatible-with=7");
        compatibilityHeaders.add("Content-Type", "application/vnd.elasticsearch+json;compatible-with=7");

        final ClientConfiguration config = ClientConfiguration.builder()
                .connectedTo(host)
                .withBasicAuth(user, password)
                .withDefaultHeaders(compatibilityHeaders)
                .build();

        return RestClients.create(config).rest();
    }
}
