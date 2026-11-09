package br.edu.qualidade.tax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class FinanceUtilsTest {
    private final FinanceUtils financeUtils = new FinanceUtils();

    @Test
    void calculatesIcms() {
        assertEquals(new BigDecimal("170.00"), financeUtils.calculateTax(new BigDecimal("1000.00"), TaxType.ICMS));
    }

    @Test
    void calculatesStandardIpiAtThreshold() {
        assertEquals(new BigDecimal("500.00"), financeUtils.calculateTax(new BigDecimal("10000.00"), TaxType.IPI));
    }

    @Test
    void calculatesHighValueIpiAboveThreshold() {
        assertEquals(new BigDecimal("1000.01"), financeUtils.calculateTax(new BigDecimal("10000.10"), TaxType.IPI));
    }

    @Test
    void rejectsNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> financeUtils.calculateTax(new BigDecimal("-1"), TaxType.ICMS));
    }

    @Test
    void rejectsNullArguments() {
        assertThrows(NullPointerException.class, () -> financeUtils.calculateTax(null, TaxType.ICMS));
        assertThrows(NullPointerException.class, () -> financeUtils.calculateTax(BigDecimal.ONE, null));
    }
}
