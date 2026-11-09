package br.edu.qualidade.discount;

import java.math.BigDecimal;

@FunctionalInterface
public interface DiscountStrategy {
    BigDecimal apply(BigDecimal subtotal);
}
