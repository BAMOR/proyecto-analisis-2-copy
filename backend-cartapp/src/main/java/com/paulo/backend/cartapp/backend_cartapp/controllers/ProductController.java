package com.paulo.backend.cartapp.backend_cartapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.paulo.backend.cartapp.backend_cartapp.models.entities.Product;
import com.paulo.backend.cartapp.backend_cartapp.services.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/products") // ← Esto aplica a todos los métodos
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> list() {
        return service.findAll();
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.save(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        try {
            Product updated = service.update(id, product);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}