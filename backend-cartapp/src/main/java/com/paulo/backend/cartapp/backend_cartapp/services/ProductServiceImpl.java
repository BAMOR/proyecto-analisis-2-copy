package com.paulo.backend.cartapp.backend_cartapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paulo.backend.cartapp.backend_cartapp.factory.DatabasePermission;
import com.paulo.backend.cartapp.backend_cartapp.factory.PermissionFactory;
import com.paulo.backend.cartapp.backend_cartapp.models.entities.Product;
import com.paulo.backend.cartapp.backend_cartapp.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    // 👇 Rol fijo para pruebas (en producción vendría del usuario autenticado)
    private static final String CURRENT_USER_ROLE = "admin"; // ← Puedes cambiar a "admin" para probar escritura

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        DatabasePermission permission = PermissionFactory.getPermission(CURRENT_USER_ROLE);
        if (!permission.canRead()) {
            throw new RuntimeException("No tienes permisos de lectura");
        }
        return (List<Product>) repository.findAll();
    }

    @Override
    @Transactional
    public Product save(Product product) {
        DatabasePermission permission = PermissionFactory.getPermission(CURRENT_USER_ROLE);
        if (!permission.canWrite()) {
            throw new RuntimeException("No tienes permisos de escritura");
        }
        return repository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        DatabasePermission permission = PermissionFactory.getPermission(CURRENT_USER_ROLE);
        if (!permission.canWrite()) {
            throw new RuntimeException("No tienes permisos de escritura");
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Product update(Long id, Product productDetails) {
        DatabasePermission permission = PermissionFactory.getPermission(CURRENT_USER_ROLE);
        if (!permission.canWrite()) {
            throw new RuntimeException("No tienes permisos de escritura");
        }

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
}