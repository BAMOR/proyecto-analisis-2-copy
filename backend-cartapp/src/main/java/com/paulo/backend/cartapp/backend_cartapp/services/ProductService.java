package com.paulo.backend.cartapp.backend_cartapp.services;

import java.util.List;
import com.paulo.backend.cartapp.backend_cartapp.models.entities.Product;

public interface ProductService {
    List<Product> findAll();
    Product save(Product product);
    void deleteById(Long id);
    Product update(Long id, Product product);
}