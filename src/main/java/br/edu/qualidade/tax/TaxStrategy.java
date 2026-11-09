package br.edu.qualidade.tax;

import java.math.BigDecimal;

@FunctionalInterface
public interface TaxStrategy {
    BigDecimal calculate(BigDecimal amount);
}
