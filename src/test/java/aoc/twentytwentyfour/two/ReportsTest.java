package aoc.twentytwentyfour.two;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportsTest {

    private static final File SAMPLE_INPUT = new File("src/test/resources/day2sample.txt");

    @Test
    void canParseSampleInput() {
        Reports reports = new Reports(SAMPLE_INPUT);

        List<List<Integer>> expected = getExpectedReports();

        assertEquals(expected, reports.asList());
    }

    private static List<List<Integer>> getExpectedReports() {
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(List.of(7, 6, 4, 2, 1));
        expected.add(List.of(1, 2, 7, 8, 9));
        expected.add(List.of(9, 7, 6, 2, 1));
        expected.add(List.of(1, 3, 2, 4, 5));
        expected.add(List.of(8, 6, 4, 4, 1));
        expected.add(List.of(1, 3, 6, 7, 9));
        return expected;
    }
}