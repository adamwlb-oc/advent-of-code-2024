package aoc.twentytwentyfour.runner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RunnerTest {

    @Test
    public void formatsResultString() {
        assertEquals("Day 32 part 12 result: 12345", Runner.getResultString(12345, 32, 12));
    }
}