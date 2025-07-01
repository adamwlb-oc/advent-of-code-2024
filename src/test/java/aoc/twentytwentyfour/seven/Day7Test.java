package aoc.twentytwentyfour.seven;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {

    private static final File SAMPLE_INPUT = new File("src/test/resources/day7sample.txt");

    private Day7 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day7(SAMPLE_INPUT);
    }

    @Test
    void canParseInput() {
        List<Equation> expected = List.of(
                new Equation(190, List.of(10, 19)),
                new Equation(3267, List.of(81, 40, 27)),
                new Equation(83, List.of(17, 5)),
                new Equation(156, List.of(15, 6)),
                new Equation(7290, List.of(6, 8, 6, 15)),
                new Equation(161011, List.of(16, 10, 13)),
                new Equation(192, List.of(17, 8, 14)),
                new Equation(21037, List.of(9, 7, 18, 13)),
                new Equation(292, List.of(11, 6, 16, 20))
        );

        assertEquals(expected, Day7.parseInput(SAMPLE_INPUT));
    }

    @Test
    void sampleInputPart1() {
        assertEquals(3749, underTest.part1());
    }

    @Test
    void sampleInputPart2() {
        assertEquals(11387, underTest.part2());
    }

}