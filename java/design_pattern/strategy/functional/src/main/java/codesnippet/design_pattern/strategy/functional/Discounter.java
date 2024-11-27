package codesnippet.design_pattern.strategy.functional;

import java.math.BigDecimal;

@FunctionalInterface
public interface Discounter {

    BigDecimal applyDiscount(BigDecimal amount);

    static Discounter christmasDiscounter() {

        return amount -> amount.multiply(BigDecimal.valueOf(0.9));
    }

    static Discounter newYearDiscounter() {

        return amount -> amount.multiply(BigDecimal.valueOf(0.8));
    }

    static Discounter blackFridayDiscounter() {

        return amount -> amount.multiply(BigDecimal.valueOf(0.7));
    }
}
