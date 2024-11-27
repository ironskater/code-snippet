package codesnippet.design_pattern.strategy.functional;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

@FunctionalInterface
public interface UnaryDiscounter extends UnaryOperator<BigDecimal> {

    static UnaryDiscounter identity() {

        return amount -> amount;
    }

    default UnaryDiscounter combine(UnaryDiscounter other) {

        return amount -> {

            BigDecimal step1 = other.apply(amount);

            return this.apply(step1);
        };
    }
}
