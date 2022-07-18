package com.imorochi.chasqui.infrastructure.controller;

import com.imorochi.chasqui.application.OrderService;
import com.imorochi.chasqui.domain.document.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public void save(@RequestBody final Order order) {
        orderService.save(order);
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable final Integer id) {
        return orderService.findById(id);
    }
}
