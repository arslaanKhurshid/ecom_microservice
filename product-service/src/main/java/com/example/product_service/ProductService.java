package com.example.product_service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    private final List<Product> products = Arrays.asList(
            new Product(1L, "Laptop", 999.99),
            new Product(2L, "Phone", 499.99)
    );

    public Product getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}