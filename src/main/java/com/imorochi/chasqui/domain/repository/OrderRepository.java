package com.imorochi.chasqui.domain.repository;

import com.imorochi.chasqui.domain.document.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrderRepository extends ElasticsearchRepository<Order, Integer> {
}
