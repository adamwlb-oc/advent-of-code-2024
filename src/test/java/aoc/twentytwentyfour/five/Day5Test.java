package aoc.twentytwentyfour.five;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {

    private static final File SAMPLE_INPUT = new File("src/test/resources/day5sample.txt");

    private Day5 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day5(SAMPLE_INPUT);
    }

    @Test
    public void canParseInput() {
        List<OrderingRule> orderingRules = List.of(
                new OrderingRule(47, 53),
                new OrderingRule(97, 13),
                new OrderingRule(97, 61),
                new OrderingRule(97, 47),
                new OrderingRule(75, 29),
                new OrderingRule(61, 13),
                new OrderingRule(75, 53),
                new OrderingRule(29, 13),
                new OrderingRule(97, 29),
                new OrderingRule(53, 29),
                new OrderingRule(61, 53),
                new OrderingRule(97, 53),
                new OrderingRule(61, 29),
                new OrderingRule(47, 13),
                new OrderingRule(75, 47),
                new OrderingRule(97, 75),
                new OrderingRule(47, 61),
                new OrderingRule(75, 61),
                new OrderingRule(47, 29),
                new OrderingRule(75, 13),
                new OrderingRule(53, 13)
        );
        List<Update> updates = List.of(
                new Update(List.of(75, 47, 61, 53, 29)),
                new Update(List.of(97, 61, 53, 29, 13)),
                new Update(List.of(75, 29, 13)),
                new Update(List.of(75, 97, 47, 61, 53)),
                new Update(List.of(61, 13, 29)),
                new Update(List.of(97, 13, 75, 29, 47))
        );
        PrinterInstructions expected = new PrinterInstructions(orderingRules, updates);

        assertEquals(expected, underTest.printerInstructions());
    }

    @Test
    public void sampleInputPart1() {
        assertEquals(143, underTest.part1());
    }

    @Test
    public void sampleInputPart2() {
        assertEquals(123, underTest.part2());
    }
}