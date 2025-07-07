package aoc.twentytwentyfour.eleven;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {

    private static final File SAMPLE_INPUT = new File("src/test/resources/day11sample.txt");

    private Day11 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day11(SAMPLE_INPUT);
    }

    @Test
    void part1SampleInput() {
        assertEquals(55312L, underTest.part1());
    }

    @Test
    void part2SampleInput() {
        assertEquals(65601038650482L, underTest.part2());
    }

    @Test
    void canParseInput() {
        StoneLine expected = new StoneLine(List.of(
                new Stone(125),
                new Stone(17)
        ));

        assertEquals(expected, Day11.parseInput(SAMPLE_INPUT));
    }
}