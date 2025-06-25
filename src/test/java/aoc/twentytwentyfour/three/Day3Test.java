package aoc.twentytwentyfour.three;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {

    private Day3 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day3();
    }

    @Test
    void extractsMulInstructions() {
        String memoryChunk = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul";
        assertEquals(List.of(new Mul("mul(2,4)"), new Mul("mul(5,5)")), Day3.getMulInstructions(memoryChunk));
    }

    @Test
    void part1SampleInput() {
        File sampleInput = new File("src/test/resources/day3sample.txt");
        Memory memory = new Memory(sampleInput);

        assertEquals(161, underTest.part1(memory));
    }

    @Test
    void extractsDoInstructionIndicies() {
        String memoryChunk = "undo()?mul(8,5))";
        assertEquals(List.of(2), Day3.getDoIndicies(memoryChunk));
    }

    @Test
    void extractsDontInstructionsIndicies() {
        String memoryChunk = "7]!^don't()_mul";
        assertEquals(List.of(4), Day3.getDontIndicies(memoryChunk));
    }

    @Test
    void extractsMulsWithIndicies() {
        String memoryChunk = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul";
        assertEquals(Map.of(1, "mul(2,4)", 29, "mul(5,5)"), Day3.getMulsWithIndicies(memoryChunk));
    }

    @Test
    void aggregatesInstructionsInOrder() {
        File sampleInput = new File("src/test/resources/day3sample2.txt");
        String input = new Memory(sampleInput).asString();

        Map<Integer, String> expected = Map.of(1, "mul(2,4)",
                20, "don't()",
                28, "mul(5,5)",
                48, "mul(11,8)",
                59, "do()",
                64, "mul(8,5)");

        assertEquals(expected, Day3.aggregateInstructions(input));
    }

    @Test
    void extractsEnabledMulInstructions() {
        SortedMap<Integer, String> instructions = new TreeMap<>(Map.of(1, "mul(2,4)",
                20, "don't()",
                28, "mul(5,5)",
                48, "mul(11,8)",
                59, "do()",
                64, "mul(8,5)"));

        assertEquals(List.of(new Mul("mul(2,4)"), new Mul("mul(8,5)")), Day3.extractEnabledMuls(instructions));
    }

    @Test
    void part2SampleInput() {
        File sampleInput = new File("src/test/resources/day3sample2.txt");
        Memory memory = new Memory(sampleInput);

        assertEquals(48, underTest.part2(memory));
    }
}