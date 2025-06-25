package aoc.twentytwentyfour.two;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    private Day2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day2();
    }

    @Test
    void levelsAllIncreasingIsSafe() {
        List<Integer> increasingLevels = List.of(1, 2, 3, 4, 5);

        assertTrue(Day2.levelsAllIncreasing(increasingLevels));
        assertTrue(Day2.reportIsSafe(increasingLevels));
    }

    @Test
    void levelsAllDecreasingIsSafe() {
        List<Integer> decreasingLevels = List.of(5, 4, 3, 2, 1);

        assertTrue(Day2.levelsAllDecreasing(decreasingLevels));
        assertTrue(Day2.reportIsSafe(decreasingLevels));
    }

    @Test
    void levelsIncreasingAndDecreasingIsUnsafe() {
        List<Integer> mixedLevels = List.of(1, 2, 3, 2, 1);

        assertFalse(Day2.reportIsSafe(mixedLevels));
    }

    @Test
    void levelsWithNoChangeIsUnsafe() {
        List<Integer> staticLevels = List.of(1, 1, 1, 1, 1);

        assertFalse(Day2.reportIsSafe(staticLevels));
    }

    @Test
    void differencesBetweenOneAndThreeInclusiveIsSafe() {
        List<Integer> validDifferences = List.of(1, 2, 4, 7, 8);

        assertTrue(Day2.levelsChangeIsSafe(validDifferences));
        assertTrue(Day2.reportIsSafe(validDifferences));
    }

    @Test
    void differencesMoreThanThreeIsUnsafe() {
        List<Integer> invalidDifferences = List.of(1, 5, 6, 7);

        assertFalse(Day2.levelsChangeIsSafe(invalidDifferences));
        assertFalse(Day2.reportIsSafe(invalidDifferences));
    }

    @Test
    void part1SampleInput() {
        File sampleInput = new File("src/test/resources/day2sample.txt");
        Reports reports = new Reports(sampleInput);

        assertEquals(2L, underTest.part1(reports));
    }

    @Test
    void reportCanBeSafeWithOneLevelRemoved() {
        List<Integer> canBeSafeLevels = List.of(1, 3, 2, 4, 5);

        assertTrue(Day2.reportIsSafeWithDampener(canBeSafeLevels));
    }

    @Test
    void stillMarkedSafeWithDampenerIfNothingRemoved() {
        List<Integer> validReport = List.of(1, 2, 3, 4, 5);

        assertTrue(Day2.reportIsSafeWithDampener(validReport));
    }

    @Test
    void part2SampleInput() {
        File sampleInput = new File("src/test/resources/day2sample.txt");
        Reports reports = new Reports(sampleInput);

        assertEquals(4L, underTest.part2(reports));
    }
}