package aoc.twentytwentyfour.four;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinatePairTest {

    @Test
    void validToString() {
        assertEquals("(1, 3)", new CoordinatePair(1, 3).toString());
    }
}