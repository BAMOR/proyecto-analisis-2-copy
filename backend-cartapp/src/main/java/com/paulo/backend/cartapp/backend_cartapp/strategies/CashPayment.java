package com.paulo.backend.cartapp.backend_cartapp.strategies;

public class CashPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Pagando $" + amount + " en efectivo");
    }

    @Override
    public String toString() {
        return "CashPayment{}";
    }
}
