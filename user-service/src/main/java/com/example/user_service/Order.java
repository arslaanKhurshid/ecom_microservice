package com.example.user_service;

public class Order {
    private Long id;
    private Long userId;
    private Long productId;
    private String userName;
    private String productName;
    private double totalCost;
    private String status;

    public Order() {} // Default constructor for RestTemplate

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getProductId() { return productId; }
    public String getUserName() { return userName; }
    public String getProductName() { return productName; }
    public double getTotalCost() { return totalCost; }
    public String getStatus() { return status; }
}