package br.edu.qualidade.discount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Objects;

/**
 * Refatoração de um cálculo de desconto condicionado à forma de pagamento.
 */
public final class DiscountCalculator {
    private static final BigDecimal PIX_DISCOUNT = new BigDecimal("0.10");
    private static final BigDecimal CASH_DISCOUNT = new BigDecimal("0.05");
    private static final BigDecimal NO_DISCOUNT = BigDecimal.ZERO;

    private final Map<PaymentMethod, DiscountStrategy> strategies = Map.of(
            PaymentMethod.PIX, subtotal -> subtractDiscount(subtotal, PIX_DISCOUNT),
            PaymentMethod.CASH, subtotal -> subtractDiscount(subtotal, CASH_DISCOUNT),
            PaymentMethod.CREDIT_CARD, subtotal -> subtotal);

    public BigDecimal calculateFinalPrice(BigDecimal subtotal, PaymentMethod paymentMethod) {
        validateSubtotal(subtotal);
        DiscountStrategy strategy = strategies.get(Objects.requireNonNull(paymentMethod, "paymentMethod must not be null"));
        return toCurrency(strategy.apply(subtotal));
    }

    private static BigDecimal subtractDiscount(BigDecimal subtotal, BigDecimal discountRate) {
        return subtotal.subtract(subtotal.multiply(discountRate));
    }

    private static void validateSubtotal(BigDecimal subtotal) {
        Objects.requireNonNull(subtotal, "subtotal must not be null");
        if (subtotal.signum() < 0) {
            throw new IllegalArgumentException("subtotal must not be negative");
        }
    }

    private static BigDecimal toCurrency(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
