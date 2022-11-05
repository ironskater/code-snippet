package codesnippet.enums;

import java.util.function.DoubleBinaryOperator;

public enum Operation {

    PLUS("+", (l, r) -> l + r)
    ;

    private final String symbol;
    private final DoubleBinaryOperator doubleBinaryOperator;

    private Operation(final String symbol, final DoubleBinaryOperator doubleBinaryOperator) {

        this.symbol = symbol;
        this.doubleBinaryOperator = doubleBinaryOperator;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public double operate(double x, double y) {
        return doubleBinaryOperator.applyAsDouble(x, y);
    }
}
