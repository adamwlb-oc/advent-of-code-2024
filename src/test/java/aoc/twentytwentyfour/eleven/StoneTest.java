package aoc.twentytwentyfour.eleven;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StoneTest {

    private static Stream<Arguments> getEvenDigitsTestCases() {
        return Stream.of(
                Arguments.of(new Stone(1), false),
                Arguments.of(new Stone(11), true),
                Arguments.of(new Stone(20), true),
                Arguments.of(new Stone(100), false),
                Arguments.of(new Stone(224), false),
                Arguments.of(new Stone(301), false),
                Arguments.of(new Stone(1234), true),
                Arguments.of(new Stone(47644), false),
                Arguments.of(new Stone(198543), true)
        );
    }

    @Test
    void zeroStoneBecomesOne() {
        Stone zero = new Stone(0);
        List<Stone> result = zero.blink();
        assertEquals(1, result.size());
        assertEquals(1, result.getFirst().number());
    }

    @ParameterizedTest
    @MethodSource("getEvenDigitsTestCases")
    void identifyStonesWithEvenDigits(Stone stone, boolean hasEvenDigits) {
        assertEquals(hasEvenDigits, stone.isEvenNumberDigits());
    }

    @Test
    void getLeftDigits() {
        assertEquals(12, new Stone(1234).getLeftDigits());
    }

    @Test
    void getRightDigits() {
        assertEquals(34, new Stone(1234).getRightDigits());
    }

    @Test
    void splitsToTwoStones() {
        Stone split = new Stone(1234);
        List<Stone> expected = List.of(
                new Stone(12),
                new Stone(34)
        );

        assertEquals(expected, split.blink());
    }

    @Test
    void elseMultipliedBy2024() {
        Stone other = new Stone(111);
        List<Stone> result = other.blink();
        assertEquals(1, result.size());
        assertEquals(224664, result.getFirst().number());
    }

    @Test
    void simpleToString() {
        assertEquals("12345", new Stone(12345).toString());
    }

}