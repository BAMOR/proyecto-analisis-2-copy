package com.paulo.backend.cartapp.backend_cartapp.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paulo.backend.cartapp.backend_cartapp.models.entities.Product;
import com.paulo.backend.cartapp.backend_cartapp.repositories.ProductRepository;



@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
     
        return (List<Product>) repository.findAll();
    }
    @Override
    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Product update(Long id, Product productDetails) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setDescription(productDetails.getDescription());
            return repository.save(product);
        }
        throw new RuntimeException("Producto no encontrado con id: " + id);
    }
    // ultima verion
    
    

}
