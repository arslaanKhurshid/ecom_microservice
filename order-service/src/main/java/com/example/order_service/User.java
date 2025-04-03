package com.example.order_service;


public class User {
    private Long id;
    private String name;

    public User() {} // Default constructor for RestTemplate

    public Long getId() { return id; }
    public String getName() { return name; }
}