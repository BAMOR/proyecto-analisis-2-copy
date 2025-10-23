package com.paulo.backend.cartapp.backend_cartapp;

import com.paulo.backend.cartapp.backend_cartapp.strategies.PaymentContext;
import com.paulo.backend.cartapp.backend_cartapp.strategies.CashPayment;
import com.paulo.backend.cartapp.backend_cartapp.strategies.CreditCardPayment;
import com.paulo.backend.cartapp.backend_cartapp.strategies.PayPalPayment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;



import static org.junit.jupiter.api.Assertions.*;

class PaymentContextTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void executePayment_CashStrategy_PrintsCorrectMessage() {
        PaymentContext context = new PaymentContext();
        context.setPaymentStrategy(new CashPayment());
        context.executePayment(100.0);

        String output = outContent.toString();
        assertTrue(output.contains("Pagando $100.0 en efectivo"));
    }

    @Test
    void executePayment_CreditCardStrategy_PrintsCorrectMessage() {
        PaymentContext context = new PaymentContext();
        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        context.executePayment(250.0);

        String output = outContent.toString();
        assertTrue(output.contains("tarjeta de crédito 1234-5678-9012-3456"));
        assertTrue(output.contains("Pagando $250.0"));
    }

    @Test
    void executePayment_PayPalStrategy_PrintsCorrectMessage() {
        PaymentContext context = new PaymentContext();
        context.setPaymentStrategy(new PayPalPayment("user@example.com"));
        context.executePayment(75.5);

        String output = outContent.toString();
        assertTrue(output.contains("PayPal a user@example.com"));
        assertTrue(output.contains("Pagando $75.5"));
    }

    @Test
    void executePayment_NoStrategySet_ThrowsException() {
        PaymentContext context = new PaymentContext();

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            context.executePayment(100.0);
        });

        assertEquals("No se ha establecido una estrategia de pago", exception.getMessage());
    }
}