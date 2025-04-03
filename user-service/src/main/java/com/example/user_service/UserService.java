package com.example.user_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final List<User> users = Arrays.asList(
            new User(1L, "Alice"),
            new User(2L, "Bob")
    );
    @Autowired
    private RestTemplate restTemplate;

    public User getUserById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Order> getUserOrders(Long userId) {
        String orderUrl = "http://localhost:8080/orders";
        Order[] allOrders = restTemplate.getForObject(orderUrl, Order[].class);

        // Filter orders for the user
        List<Order> userOrders = Arrays.stream(allOrders)
                .filter(order -> order.getUserId().equals(userId))
                .collect(Collectors.toList());

        // Intentional error: Calculate average order value without checking if list is empty
        double totalCost = userOrders.stream().mapToDouble(Order::getTotalCost).sum();
        double averageCost = totalCost / userOrders.size(); // Will throw ArithmeticException if size is 0

        // This line won't execute if exception occurs, but included for realism
        System.out.println("Average order cost for user " + userId + ": " + averageCost);

        return userOrders;
    }


}









//    // Intentional bug: Incorrect cast that will cause a runtime exception
//    Order[] allOrders = (Order[]) response; // ClassCastException will occur here