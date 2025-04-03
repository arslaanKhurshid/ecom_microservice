package com.example.order_service;

public class Order {
    private Long id;
    private Long userId;
    private Long productId;
    private String userName;
    private String productName;
    private double totalCost;
    private String status;

    public Order(Long id, Long userId, Long productId, String userName, String productName, double totalCost, String status) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.userName = userName;
        this.productName = productName;
        this.totalCost = totalCost;
        this.status = status;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getProductId() { return productId; }
    public String getUserName() { return userName; }
    public String getProductName() { return productName; }
    public double getTotalCost() { return totalCost; }
    public String getStatus() { return status; }
}