package com.example.order_service;


public class Product {
    private Long id;
    private String name;
    private double price;

    public Product() {} // Default constructor for RestTemplate

    public Long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}