package codesnippet.design_pattern.strategy.functional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Stream;

public class App
{
    public static void main(String[] args) {

        Discounter christmasDiscounter = Discounter.christmasDiscounter();

        System.out.println(christmasDiscounter.applyDiscount(BigDecimal.valueOf(100)));

        UnaryDiscounter udiscounter1 = value -> value.multiply(BigDecimal.valueOf(0.5));
        UnaryDiscounter udiscounter2 = value -> value.multiply(BigDecimal.valueOf(0.4));

        System.out.println(udiscounter1.combine(udiscounter2).apply(BigDecimal.valueOf(100)));

        // by stream
        UnaryDiscounter combined = Stream.of(udiscounter1, udiscounter2)
            .reduce(discounter -> discounter, UnaryDiscounter::combine);

        System.out.println(combined.apply(BigDecimal.valueOf(100)));

        // empty stream
        UnaryDiscounter empty = new ArrayList<UnaryDiscounter>().stream()
            .reduce(discounter -> discounter, UnaryDiscounter::combine);

        System.out.println(empty.apply(BigDecimal.valueOf(100)));

        // by parallel stream
        UnaryDiscounter udiscounter3 = value -> value.multiply(BigDecimal.valueOf(0.3));

        UnaryDiscounter parallelCombined = Stream.of(udiscounter1, udiscounter2, udiscounter3)
            .parallel()
            .reduce(
                UnaryDiscounter.identity(),
                UnaryDiscounter::combine,
                UnaryDiscounter::combine
            );

        System.out.println(parallelCombined.apply(BigDecimal.valueOf(100)));
    }
}
