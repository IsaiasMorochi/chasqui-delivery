package com.imorochi.chasqui.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imorochi.chasqui.domain.document.Customer;
import com.imorochi.chasqui.domain.graphQL.SlashGraphQlResultCustomer;
import com.imorochi.chasqui.infrastructure.config.SlashGraphQlProperties;
import com.imorochi.chasqui.infrastructure.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerService {
    private final SlashGraphQlProperties slashGraphQlProperties;
    private static final String CUSTOMER_QUERY = "query { queryCustomer { username } }";

    public CustomerService(SlashGraphQlProperties slashGraphQlProperties) {
        this.slashGraphQlProperties = slashGraphQlProperties;
    }

    public List<Customer> getCustomers() throws Exception {
        ResponseEntity<String> responseEntity = RestTemplateUtils.query(slashGraphQlProperties.getHostname(), CUSTOMER_QUERY);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SlashGraphQlResultCustomer slashGraphQlResult = objectMapper.readValue(responseEntity.getBody(), SlashGraphQlResultCustomer.class);
            log.debug("slashGraphQlResult={}", slashGraphQlResult);
            return slashGraphQlResult.getData().getQueryCustomer();
        } catch (JsonProcessingException e) {
            throw new Exception("An error was encountered processing responseEntity=" + responseEntity.getBody(), e);
        }
    }
}
