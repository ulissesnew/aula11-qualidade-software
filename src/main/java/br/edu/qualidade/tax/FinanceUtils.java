package br.edu.qualidade.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Objects;

/**
 * Refatoração de uma classe com múltiplos if/else para o padrão Strategy.
 */
public final class FinanceUtils {
    private static final BigDecimal ICMS_RATE = new BigDecimal("0.17");
    private static final BigDecimal IPI_STANDARD_RATE = new BigDecimal("0.05");
    private static final BigDecimal IPI_HIGH_VALUE_RATE = new BigDecimal("0.10");
    private static final BigDecimal IPI_THRESHOLD = new BigDecimal("10000");

    private final Map<TaxType, TaxStrategy> strategies;

    public FinanceUtils() {
        strategies = Map.of(
                TaxType.ICMS, amount -> amount.multiply(ICMS_RATE),
                TaxType.IPI, this::calculateIpi);
    }

    public BigDecimal calculateTax(BigDecimal amount, TaxType type) {
        validateAmount(amount);
        TaxStrategy strategy = strategies.get(Objects.requireNonNull(type, "type must not be null"));
        return toCurrency(strategy.calculate(amount));
    }

    private BigDecimal calculateIpi(BigDecimal amount) {
        BigDecimal rate = amount.compareTo(IPI_THRESHOLD) > 0 ? IPI_HIGH_VALUE_RATE : IPI_STANDARD_RATE;
        return amount.multiply(rate);
    }

    private static void validateAmount(BigDecimal amount) {
        Objects.requireNonNull(amount, "amount must not be null");
        if (amount.signum() < 0) {
            throw new IllegalArgumentException("amount must not be negative");
        }
    }

    private static BigDecimal toCurrency(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
