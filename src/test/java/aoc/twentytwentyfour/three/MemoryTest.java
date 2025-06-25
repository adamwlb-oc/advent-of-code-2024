package aoc.twentytwentyfour.three;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemoryTest {
    private static final File SAMPLE_INPUT = new File("src/test/resources/day3sample.txt");
    private static final File SAMPLE_INPUT_WITH_LINE_BREAK = new File("src/test/resources/day3samplewithlinebreak.txt");

    @Test
    void canParseInput() {
        Memory memory = new Memory(SAMPLE_INPUT);
        assertEquals("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))", memory.asString());
    }

    @Test
    void canParseInputWithMultipleLines() {
        Memory memory = new Memory(SAMPLE_INPUT_WITH_LINE_BREAK);
        assertEquals("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))", memory.asString());
    }
}