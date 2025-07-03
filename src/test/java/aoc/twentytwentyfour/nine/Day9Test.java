package aoc.twentytwentyfour.nine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static aoc.twentytwentyfour.nine.Block.emptyBlock;
import static aoc.twentytwentyfour.nine.Block.fileBlock;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {

    private static final File SAMPLE_INPUT = new File("src/test/resources/day9sample.txt");

    private Day9 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day9(SAMPLE_INPUT);
    }

    @Test
    void canParseInput() {
        DiskMap expected = new DiskMap(List.of(
                fileBlock(0), fileBlock(0),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(1), fileBlock(1), fileBlock(1),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(2),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(3), fileBlock(3), fileBlock(3),
                emptyBlock(),
                fileBlock(4), fileBlock(4),
                emptyBlock(),
                fileBlock(5), fileBlock(5), fileBlock(5), fileBlock(5),
                emptyBlock(),
                fileBlock(6), fileBlock(6), fileBlock(6), fileBlock(6),
                emptyBlock(),
                fileBlock(7), fileBlock(7), fileBlock(7),
                emptyBlock(),
                fileBlock(8), fileBlock(8), fileBlock(8), fileBlock(8),
                fileBlock(9), fileBlock(9)
        ));

        assertEquals(expected, Day9.parseInput(SAMPLE_INPUT));
    }

    @Test
    void sampleInputPart1() {
        assertEquals(1928, underTest.part1());
    }

    @Test
    void sampleInputPart2() {
        assertEquals(2858, underTest.part2());
    }
}