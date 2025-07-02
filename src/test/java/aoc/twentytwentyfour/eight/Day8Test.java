package aoc.twentytwentyfour.eight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {

    private static final File SAMPLE_INPUT = new File("src/test/resources/day8sample.txt");

    private Day8 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day8(SAMPLE_INPUT);
    }

    @Test
    void part1SampleInput() {
        assertEquals(14, underTest.part1());
    }

    @Test
    void part2SampleInput() {
        assertEquals(34, underTest.part2());
    }

    @Test
    void canParseInput() {
        FrequencyMap expected = new FrequencyMap(List.of(
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '0', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '0', '.', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '0', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '0', '.', '.', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', 'A', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', 'A', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', 'A', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.')
        ));
        assertEquals(expected, Day8.getFrequencyMapFor(SAMPLE_INPUT));
    }

}