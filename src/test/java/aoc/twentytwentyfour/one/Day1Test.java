package aoc.twentytwentyfour.one;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {

    private Day1 underTest;
    private LocationIdLists input;

    @BeforeEach
    void setup() {
        underTest = new Day1();
        File sampleInput = new File("src/test/resources/day1sample.txt");
        input = new LocationIdLists(sampleInput);
    }

    @Test
    public void part1SampleInput() {
        int answer = underTest.part1(input);
        assertEquals(11, answer);
    }

    @Test
    public void part2SampleInput() {
        int answer = underTest.part2(input);
        assertEquals(31, answer);
    }

    @Test
    public void getSimilarityScoreForANumber() {
        List<Integer> searchList = List.of(4, 3, 5, 3, 9, 3);
        int base = 3;

        assertEquals(9, underTest.getSimilarityScore(base, searchList));
    }
}