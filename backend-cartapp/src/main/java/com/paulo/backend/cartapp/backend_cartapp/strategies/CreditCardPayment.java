// backend-cartapp/src/main/java/com/paulo/backend/cartapp/backend_cartapp/strategies/CreditCardPayment.java
package com.paulo.backend.cartapp.backend_cartapp.strategies;

public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Pagando $" + amount + " con tarjeta de crédito " + cardNumber);
    }
}