package com.example.order_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private static final double TAX_RATE = 0.1;
    private final List<Order> orders = new ArrayList<>(); // In-memory storage
    private long nextId = 1; // Simple ID generator

    @Autowired
    private RestTemplate restTemplate;

    public Order createOrder(Long userId, Long productId) {
        logger.info("Creating order for userId: {} and productId: {}", userId, productId);

        String userUrl = "http://localhost:8080/users/" + userId;
        User user = restTemplate.getForObject(userUrl, User.class);
        if (user == null) {
            logger.error("User with ID {} not found", userId);
            return null;
        }

        String productUrl = "http://localhost:8080/products/" + productId;
        Product product = restTemplate.getForObject(productUrl, Product.class);
        if (product == null) {
            logger.error("Product with ID {} not found", productId);
            return null;
        }

        double price = product.getPrice();
        double tax = price * TAX_RATE;
        double totalCost = price + tax;

        logger.info("Computed total cost: {}", totalCost);

        Order order = new Order(nextId++, userId, productId, user.getName(), product.getName(), totalCost, "PLACED");
        orders.add(order); // Store the order
        return order;
    }

    public Order getOrderById(Long id) {
        logger.info("Fetching order with ID: {}", id);
        Optional<Order> order = orders.stream().filter(o -> o.getId().equals(id)).findFirst();
        if (order.isPresent()) {
            return order.get();
        } else {
            logger.warn("Order with ID {} not found", id);
            return null;
        }
    }

    public List<Order> getAllOrders() {
        logger.info("Fetching all orders, total count: {}", orders.size());
        return new ArrayList<>(orders); // Return a copy to avoid external modification
    }
}