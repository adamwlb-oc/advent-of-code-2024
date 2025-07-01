package aoc.twentytwentyfour.seven;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static aoc.twentytwentyfour.seven.Operator.ADD;
import static aoc.twentytwentyfour.seven.Operator.CONCATENATION;
import static aoc.twentytwentyfour.seven.Operator.MULTIPLY;
import static aoc.twentytwentyfour.seven.Operator.part1Operations;
import static aoc.twentytwentyfour.seven.Operator.part2Operations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EquationTest {

    private static Stream<Arguments> getEquationsWithResults() {
        return Stream.of(
                Arguments.of(new Equation(190, List.of(10, 19)), true),
                Arguments.of(new Equation(3267, List.of(81, 40, 27)), true),
                Arguments.of(new Equation(83, List.of(17, 5)), false),
                Arguments.of(new Equation(156, List.of(15, 6)), false),
                Arguments.of(new Equation(7290, List.of(6, 8, 6, 15)), false),
                Arguments.of(new Equation(161011, List.of(16, 10, 13)), false),
                Arguments.of(new Equation(192, List.of(17, 8, 14)), false),
                Arguments.of(new Equation(21037, List.of(9, 7, 18, 13)), false),
                Arguments.of(new Equation(292, List.of(11, 6, 16, 20)), true)
        );
    }

    private static Stream<Arguments> getEquationsWithResultsWithConcatenation() {
        return Stream.of(
                Arguments.of(new Equation(190, List.of(10, 19)), true),
                Arguments.of(new Equation(3267, List.of(81, 40, 27)), true),
                Arguments.of(new Equation(83, List.of(17, 5)), false),
                Arguments.of(new Equation(156, List.of(15, 6)), true),
                Arguments.of(new Equation(7290, List.of(6, 8, 6, 15)), true),
                Arguments.of(new Equation(161011, List.of(16, 10, 13)), false),
                Arguments.of(new Equation(192, List.of(17, 8, 14)), true),
                Arguments.of(new Equation(21037, List.of(9, 7, 18, 13)), false),
                Arguments.of(new Equation(292, List.of(11, 6, 16, 20)), true)
        );
    }

    private static Stream<Arguments> getOperandsWithOperation() {
        return Stream.of(
                Arguments.of(1, 2, MULTIPLY, 2),
                Arguments.of(34, 21, ADD, 55),
                Arguments.of(78, 21, MULTIPLY, 1638),
                Arguments.of(897, 231, ADD, 1128),
                Arguments.of(12, 345, CONCATENATION, 12345),
                Arguments.of(345, 678, CONCATENATION, 345678)
        );
    }

    private static Stream<Arguments> getEquationsWithOperatorSlots() {
        return Stream.of(
                Arguments.of(new Equation(190, List.of(10, 19)), 1),
                Arguments.of(new Equation(3267, List.of(81, 40, 27)), 2)
        );
    }

    private static Stream<Arguments> getEquationsWithOperatorCombos() {
        return Stream.of(
                Arguments.of(new Equation(190, List.of(10, 19)), List.of(
                                List.of(ADD),
                                List.of(MULTIPLY)
                        )
                ),
                Arguments.of(new Equation(3267, List.of(81, 40, 27)), List.of(
                                List.of(ADD, MULTIPLY),
                                List.of(MULTIPLY, ADD),
                                List.of(ADD, ADD),
                                List.of(MULTIPLY, MULTIPLY)
                        )
                )
        );
    }


    private static Stream<Arguments> getEquationsWithOperatorCombosWithConcatenation() {
        return Stream.of(
                Arguments.of(new Equation(190, List.of(10, 19)), List.of(
                                List.of(ADD),
                                List.of(MULTIPLY),
                                List.of(CONCATENATION)
                        )
                ),
                Arguments.of(new Equation(3267, List.of(81, 40, 27)), List.of(
                                List.of(ADD, MULTIPLY),
                                List.of(MULTIPLY, ADD),
                                List.of(ADD, ADD),
                                List.of(MULTIPLY, MULTIPLY),
                                List.of(ADD, CONCATENATION),
                                List.of(CONCATENATION, ADD),
                                List.of(CONCATENATION, CONCATENATION),
                                List.of(MULTIPLY, CONCATENATION),
                                List.of(CONCATENATION, MULTIPLY)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getOperandsWithOperation")
    void evaluatesTwoOperands(int operand1, int operand2, Operator operation, int result) {
        assertEquals(result, Equation.evaluate(operand1, operand2, operation));
    }

    @ParameterizedTest
    @MethodSource("getEquationsWithOperatorSlots")
    void getsNumberOfOperatorsForEquation(Equation equation, int numberOfOperators) {
        assertEquals(numberOfOperators, equation.getNumberOfOperators());
    }

    @ParameterizedTest
    @MethodSource("getEquationsWithOperatorCombos")
    void buildsSetOfPossibleOperatorCombosForEquation(Equation equation, List<List<Operator>> combos) {
        List<List<Operator>> allOperatorCombos = equation.generateOperatorPermutations(part1Operations(), equation.getNumberOfOperators());
        assertTrue(combos.size() == allOperatorCombos.size() && combos.containsAll(allOperatorCombos) && allOperatorCombos.containsAll(combos));
    }

    @ParameterizedTest
    @MethodSource("getEquationsWithOperatorCombosWithConcatenation")
    void buildsSetOfPossibleOperatorCombosForEquationWithConcatenation(Equation equation, List<List<Operator>> combos) {
        List<List<Operator>> allOperatorCombos = equation.generateOperatorPermutations(part2Operations(), equation.getNumberOfOperators());
        assertTrue(combos.size() == allOperatorCombos.size() && combos.containsAll(allOperatorCombos) && allOperatorCombos.containsAll(combos));
    }

    @ParameterizedTest
    @MethodSource("getEquationsWithResults")
    void evaluatesIfCouldBeTrue(Equation equation, boolean canBeTrue) {
        assertEquals(canBeTrue, equation.canBeTrue(Operator.part1Operations()));
    }

    @ParameterizedTest
    @MethodSource("getEquationsWithResultsWithConcatenation")
    void evaluatesIfCouldBeTrueWithConcatenation(Equation equation, boolean canBeTrue) {
        assertEquals(canBeTrue, equation.canBeTrue(Operator.part2Operations()));
    }

    @Test
    void testToString() {
        assertEquals("190: 10 19", new Equation(190, List.of(10, 19)).toString());
    }
}