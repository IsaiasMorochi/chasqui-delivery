package com.imorochi.chasqui.domain.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recommendation {
    private Customer matchedCustomer;
    private HashMap<Product, Double> recommendationsMap;
    private HashMap<Product, Double> ratingsMap;
    private HashMap<Product, Double> resultsMap;
}
