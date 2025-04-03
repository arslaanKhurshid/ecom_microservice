package com.example.user_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        logger.info("Fetching user with ID: {}", id);
        User user = userService.getUserById(id);
        if (user == null) {
            logger.error("User with ID {} not found", id);
        }
        return user;
    }
    @GetMapping("/{id}/orders")
    public List<Order> getUserOrders(Long userId) {
        try {

            return userService.getUserOrders(userId);
        } catch (Exception e) {
            logger.error("Error fetching user orders for userId {}: {}", userId, e.getMessage(), e);
            throw e; // Re-throw so Spring handles it
        }
    }

}