package aoc.twentytwentyfour.ten;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {

    private static final File SAMPLE_INPUT = new File("src/test/resources/day10sample.txt");

    private Day10 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day10(SAMPLE_INPUT);
    }

    @Test
    void sampleInputPart1() {
        assertEquals(36, underTest.part1());
    }

    @Test
    void sampleInputPart2() {
        assertEquals(81, underTest.part2());
    }

    @Test
    void canParseInput() {
        TopographicMap expected = new TopographicMap(List.of(
                List.of(8, 9, 0, 1, 0, 1, 2, 3),
                List.of(7, 8, 1, 2, 1, 8, 7, 4),
                List.of(8, 7, 4, 3, 0, 9, 6, 5),
                List.of(9, 6, 5, 4, 9, 8, 7, 4),
                List.of(4, 5, 6, 7, 8, 9, 0, 3),
                List.of(3, 2, 0, 1, 9, 0, 1, 2),
                List.of(0, 1, 3, 2, 9, 8, 0, 1),
                List.of(1, 0, 4, 5, 6, 7, 3, 2)
        ));
        assertEquals(expected, Day10.parseInput(SAMPLE_INPUT));
    }

}