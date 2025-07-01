package aoc.twentytwentyfour.seven;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record Equation(long testValue, List<Integer> operands) {

    static long evaluate(long operand1, long operand2, Operator operation) {
        return switch (operation) {
            case ADD -> operand1 + operand2;
            case MULTIPLY -> operand1 * operand2;
            case CONCATENATION -> Long.parseLong(String.valueOf(operand1) + operand2);
        };
    }

    @Override
    public String toString() {
        return testValue + ": " + operands.stream().map(Objects::toString).collect(Collectors.joining(" "));
    }

    public boolean canBeTrue(List<Operator> operators) {
        return generateOperatorPermutations(operators, getNumberOfOperators()).stream()
                .anyMatch(permutation -> {
                    long result = operands.getFirst();
                    for (int i = 0; i < permutation.size(); i++) {
                        result = evaluate(result, operands.get(i + 1), permutation.get(i));
                    }
                    return result == testValue;
                });
    }

    List<List<Operator>> generateOperatorPermutations(List<Operator> operators, int length) {
        if (length == 0) {
            return List.of(new ArrayList<>());
        }
        List<List<Operator>> result = new ArrayList<>();
        for (Operator op : operators) {
            for (List<Operator> subPermutation : generateOperatorPermutations(operators, length - 1)) {
                List<Operator> newPermutation = new ArrayList<>(subPermutation);
                newPermutation.add(op);
                result.add(newPermutation);
            }
        }
        return result;
    }

    int getNumberOfOperators() {
        return operands.size() - 1;
    }
}
