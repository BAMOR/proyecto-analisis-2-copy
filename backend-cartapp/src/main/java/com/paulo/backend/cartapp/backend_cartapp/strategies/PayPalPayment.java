// backend-cartapp/src/main/java/com/paulo/backend/cartapp/backend_cartapp/strategies/PayPalPayment.java
package com.paulo.backend.cartapp.backend_cartapp.strategies;

public class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Pagando $" + amount + " con PayPal a " + email);
    }
}