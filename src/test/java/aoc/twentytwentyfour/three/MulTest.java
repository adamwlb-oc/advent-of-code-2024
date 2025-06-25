package aoc.twentytwentyfour.three;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MulTest {

    @Test
    void executesMulInstruction() {
        String instruction = "mul(3,5)";

        assertEquals(15, new Mul(instruction).evaluate());
    }

    @ParameterizedTest
    @MethodSource("getInvalidMulArgs")
    void invalidMulThrowsIllegalArgumentException(String instruction) {
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Mul(instruction));
        assertEquals("Invalid mul instruction " + instruction, e.getMessage());
    }

    @Test
    void toStringFormatsCorrectly() {
        Mul mul = new Mul("mul(3,5)");
        assertEquals("mul(3,5)", mul.toString());
    }

    private static Stream<Arguments> getInvalidMulArgs() {
        return Stream.of(
                Arguments.of("mul(4*"),
                Arguments.of("mul(6,9!"),
                Arguments.of("?(12,34)"),
                Arguments.of("mul ( 2 , 4 )")
        );
    }
}