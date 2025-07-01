package aoc.twentytwentyfour.seven;

import java.util.List;

public enum Operator {
    ADD("+"),
    MULTIPLY("*"),
    CONCATENATION("||");

    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public static List<Operator> part1Operations() {
        return List.of(ADD, MULTIPLY);
    }

    public static List<Operator> part2Operations() {
        return List.of(ADD, MULTIPLY, CONCATENATION);
    }

    @Override
    public String toString() {
        return symbol;
    }
}
