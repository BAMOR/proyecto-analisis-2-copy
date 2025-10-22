package com.paulo.backend.cartapp.backend_cartapp.controllers;

import com.paulo.backend.cartapp.backend_cartapp.models.entities.ShoppingCart;
import com.paulo.backend.cartapp.backend_cartapp.services.ShoppingCartService;
import com.paulo.backend.cartapp.backend_cartapp.strategies.CashPayment;
import com.paulo.backend.cartapp.backend_cartapp.strategies.CreditCardPayment;
import com.paulo.backend.cartapp.backend_cartapp.strategies.PayPalPayment;
import com.paulo.backend.cartapp.backend_cartapp.strategies.PaymentContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;

    private static final String DEFAULT_USER_ID = "default_user";

    @GetMapping
    public List<ShoppingCart> getCart() {
        return cartService.getCart(DEFAULT_USER_ID);
    }

    @PostMapping("/add")
    public ShoppingCart addToCart(@RequestParam Long productId,
            @RequestParam(defaultValue = "1") Integer quantity) {
        return cartService.addToCart(DEFAULT_USER_ID, productId, quantity);
    }

    @PutMapping("/item/{itemId}")
    public ShoppingCart updateItem(@PathVariable Long itemId,
            @RequestParam Integer quantity) {
        return cartService.updateItem(itemId, quantity);
    }

    @DeleteMapping("/item/{itemId}")
    public void removeFromCart(@PathVariable Long itemId) {
        cartService.removeFromCart(itemId);
    }

    @DeleteMapping("/clear")
    public void clearCart() {
        cartService.clearCart(DEFAULT_USER_ID);
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam String paymentMethod,
            @RequestParam(defaultValue = "default_user") String userId) {
        // Obtener total del carrito
        double total = cartService.getCart(userId).stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        PaymentContext context = new PaymentContext();

        switch (paymentMethod.toLowerCase()) {
            case "efectivo":
                context.setPaymentStrategy(new CashPayment());
                break;
            case "tarjetacredito":
                context.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
                break;
            case "paypal":
                context.setPaymentStrategy(new PayPalPayment("user@example.com"));
                break;
            default:
                throw new RuntimeException("Método de pago no soportado");
        }

        context.executePayment(total);

        // Vaciar carrito después del pago
        cartService.clearCart(userId);

        return "Pago procesado exitosamente. Total: $" + total;
    }
}