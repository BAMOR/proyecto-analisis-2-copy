package com.paulo.backend.cartapp.backend_cartapp.services;

import com.paulo.backend.cartapp.backend_cartapp.models.entities.Product;
import com.paulo.backend.cartapp.backend_cartapp.models.entities.ShoppingCart;
import com.paulo.backend.cartapp.backend_cartapp.repositories.ProductRepository;
import com.paulo.backend.cartapp.backend_cartapp.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ShoppingCart> getCart(String userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public ShoppingCart addToCart(String userId, Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        List<ShoppingCart> cartItems = cartRepository.findByUserId(userId);
        for (ShoppingCart item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return cartRepository.save(item);
            }
        }

        ShoppingCart newItem = new ShoppingCart();
        newItem.setUserId(userId);
        newItem.setProduct(product);
        newItem.setQuantity(quantity);
        return cartRepository.save(newItem);
    }

    @Override
    @Transactional
    public ShoppingCart updateItem(Long itemId, Integer quantity) {
        ShoppingCart item = cartRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Ítem no encontrado"));
        item.setQuantity(quantity);
        return cartRepository.save(item);
    }

    @Override
    @Transactional
    public void removeFromCart(Long itemId) {
        cartRepository.deleteById(itemId);
    }

    @Override
    @Transactional
    public void clearCart(String userId) {
        cartRepository.deleteByUserId(userId);
    }
}