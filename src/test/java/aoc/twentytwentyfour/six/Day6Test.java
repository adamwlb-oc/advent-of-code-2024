package aoc.twentytwentyfour.six;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    private static final File SAMPLE_INPUT = new File("src/test/resources/day6sample.txt");

    private Day6 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day6(SAMPLE_INPUT);
    }

    @Test
    public void canParseInput() {
        LabMap expectedLabMap = new LabMap(List.of(
                List.of('.', '.', '.', '.', '#', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', '#'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', '.'),
                List.of('.', '.', '#', '.', '.', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '#', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', '.'),
                List.of('.', '#', '.', '.', '^', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '#', '.'),
                List.of('#', '.', '.', '.', '.', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '#', '.', '.', '.')
        ));

        assertEquals(expectedLabMap, underTest.labMap());
    }

    @Test
    public void sampleInputPart1() {
        assertEquals(41, underTest.part1());
    }

    @Test
    public void sampleInputPart2() {
        assertEquals(6, underTest.part2());
    }
}