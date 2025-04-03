package com.example.order_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestParam Long userId, @RequestParam Long productId) {
        logger.info("Received order request for userId: {} and productId: {}", userId, productId);
        return orderService.createOrder(userId, productId);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        logger.info("Received request to fetch order with ID: {}", id);
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        logger.info("Received request to fetch all orders");
        return orderService.getAllOrders();
    }
}