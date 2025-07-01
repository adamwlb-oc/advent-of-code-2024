package aoc.twentytwentyfour.seven;

import org.junit.jupiter.api.Test;

import static aoc.twentytwentyfour.seven.Operator.ADD;
import static aoc.twentytwentyfour.seven.Operator.CONCATENATION;
import static aoc.twentytwentyfour.seven.Operator.MULTIPLY;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OperatorTest {

    @Test
    void testToString() {
        assertEquals("+", ADD.toString());
        assertEquals("*", MULTIPLY.toString());
        assertEquals("||", CONCATENATION.toString());
    }
}