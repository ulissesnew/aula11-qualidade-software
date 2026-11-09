package br.edu.qualidade.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class DiscountCalculatorTest {
    private final DiscountCalculator calculator = new DiscountCalculator();

    @Test
    void appliesPixDiscount() {
        assertEquals(new BigDecimal("90.00"), calculator.calculateFinalPrice(new BigDecimal("100.00"), PaymentMethod.PIX));
    }

    @Test
    void appliesCashDiscount() {
        assertEquals(new BigDecimal("95.00"), calculator.calculateFinalPrice(new BigDecimal("100.00"), PaymentMethod.CASH));
    }

    @Test
    void keepsCreditCardSubtotal() {
        assertEquals(new BigDecimal("100.00"), calculator.calculateFinalPrice(new BigDecimal("100.00"), PaymentMethod.CREDIT_CARD));
    }

    @Test
    void acceptsZeroSubtotal() {
        assertEquals(BigDecimal.ZERO.setScale(2), calculator.calculateFinalPrice(BigDecimal.ZERO, PaymentMethod.PIX));
    }

    @Test
    void rejectsInvalidArguments() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateFinalPrice(new BigDecimal("-1"), PaymentMethod.PIX));
        assertThrows(NullPointerException.class, () -> calculator.calculateFinalPrice(null, PaymentMethod.PIX));
        assertThrows(NullPointerException.class, () -> calculator.calculateFinalPrice(BigDecimal.ONE, null));
    }
}
