package com.paulo.backend.cartapp.backend_cartapp.services;

import com.paulo.backend.cartapp.backend_cartapp.models.entities.ShoppingCart;
import java.util.List;





public interface ShoppingCartService {
List<ShoppingCart> getCart(String userId);
    ShoppingCart addToCart(String userId, Long productId, Integer quantity);
    ShoppingCart updateItem(Long itemId, Integer quantity);
    void removeFromCart(Long itemId);
    void clearCart(String userId);
}
