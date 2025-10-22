// backend-cartapp/src/main/java/com/paulo/backend/cartapp/backend_cartapp/strategies/PaymentContext.java
package com.paulo.backend.cartapp.backend_cartapp.strategies;

public class PaymentContext {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executePayment(double amount) {
        if (strategy == null) {
            throw new RuntimeException("No se ha establecido una estrategia de pago");
        }
        strategy.pay(amount);
    }
}