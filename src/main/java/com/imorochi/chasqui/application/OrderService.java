package com.imorochi.chasqui.application;

import com.imorochi.chasqui.domain.document.Order;
import com.imorochi.chasqui.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(final Order order) {
        orderRepository.save(order);
    }

    public Order findById(final Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

}
