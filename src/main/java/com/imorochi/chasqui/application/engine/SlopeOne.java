package com.imorochi.chasqui.application.engine;

import com.imorochi.chasqui.domain.document.Customer;
import com.imorochi.chasqui.domain.document.Product;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class SlopeOne {

    private SlopeOne() { }

    private static final Map<Product, Map<Product, Double>> diff = new HashMap<>();
    private static final Map<Product, Map<Product, Integer>> freq = new HashMap<>();

    public static Map<Customer, HashMap<Product, Double>> slopeOne(Map<Customer, HashMap<Product, Double>> data, List<Product> Products) {
        buildDifferencesMatrix(data);
        return predict(data, Products);
    }

    private static void buildDifferencesMatrix(Map<Customer, HashMap<Product, Double>> data) {
        for (HashMap<Product, Double> customer : data.values()) {
            for (Map.Entry<Product, Double> productEntry : customer.entrySet()) {
                if (!diff.containsKey(productEntry.getKey())) {
                    diff.put(productEntry.getKey(), new HashMap<>());
                    freq.put(productEntry.getKey(), new HashMap<>());
                }

                for (Map.Entry<Product, Double> productEntryInner : customer.entrySet()) {
                    int oldCount = 0;

                    if (freq.get(productEntry.getKey()).containsKey(productEntryInner.getKey())) {
                        oldCount = freq.get(productEntry.getKey()).get(productEntryInner.getKey());
                    }

                    double oldDiff = 0.0;

                    if (diff.get(productEntry.getKey()).containsKey(productEntryInner.getKey())) {
                        oldDiff = diff.get(productEntry.getKey()).get(productEntryInner.getKey());
                    }

                    double observedDiff = productEntry.getValue() - productEntryInner.getValue();

                    freq.get(productEntry.getKey()).put(productEntryInner.getKey(), oldCount + 1);
                    diff.get(productEntry.getKey()).put(productEntryInner.getKey(), oldDiff + observedDiff);
                }
            }
        }

        for (Product j : diff.keySet()) {
            for (Product i : diff.get(j).keySet()) {
                double oldValue = diff.get(j).get(i);
                int count = freq.get(j).get(i);
                diff.get(j).put(i, oldValue / count);
            }
        }

        log.debug("diff={}", diff);
        log.debug("freq={}", freq);
        log.debug("data={}", data);
    }

    private static Map<Customer, HashMap<Product, Double>> predict(Map<Customer, HashMap<Product, Double>> data, List<Product> products) {
        Map<Customer, HashMap<Product, Double>> outputData = new HashMap<>();
        HashMap<Product, Double> uPred = new HashMap<>();
        HashMap<Product, Integer> uFreq = new HashMap<>();

        for (Product productDiff : diff.keySet()) {
            uFreq.put(productDiff, 0);
            uPred.put(productDiff, 0.0);
        }

        for (Map.Entry<Customer, HashMap<Product, Double>> customerMapEntry : data.entrySet()) {
            for (Product product : customerMapEntry.getValue().keySet()) {
                for (Product productDiff : diff.keySet()) {
                    try {
                        double predictedValue = diff.get(productDiff).get(product) + customerMapEntry.getValue().get(product);
                        double finalValue = predictedValue * freq.get(productDiff).get(product);
                        uPred.put(productDiff, uPred.get(productDiff) + finalValue);
                        uFreq.put(productDiff, uFreq.get(productDiff) + freq.get(productDiff).get(product));
                    } catch (NullPointerException e) {
                        log.warn("A null pointer error was encountered processing product={} and productDiff={}", product, productDiff);
                    }
                }
            }

            HashMap<Product, Double> clean = new HashMap<>();

            for (Product product : uPred.keySet()) {
                if (uFreq.get(product) > 0) {
                    clean.put(product, uPred.get(product) / uFreq.get(product));
                }
            }

            for (Product product : products) {
                if (customerMapEntry.getValue().containsKey(product)) {
                    clean.put(product, customerMapEntry.getValue().get(product));
                } else if (!clean.containsKey(product)) {
                    clean.put(product, -1.0);
                }
            }

            outputData.put(customerMapEntry.getKey(), clean);
        }

        log.info("outputData={}", outputData);
        return outputData;
    }
}
