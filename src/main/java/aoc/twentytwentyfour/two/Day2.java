package aoc.twentytwentyfour.two;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class Day2 {

    public long part1(Reports reports) {
        return reports.asList()
                .stream()
                .filter(Day2::reportIsSafe)
                .count();
    }

    public long part2(Reports reports) {
        return reports.asList()
                .stream()
                .filter(Day2::reportIsSafeWithDampener)
                .count();
    }

    static boolean reportIsSafe(List<Integer> report) {
        return (levelsAllIncreasing(report) || levelsAllDecreasing(report))
                && levelsChangeIsSafe(report);
    }

    static boolean reportIsSafeWithDampener(List<Integer> report) {
        if (reportIsSafe(report)) {
            return true;
        }
        for (int i = 0; i < report.size(); i++) {
            List<Integer> withoutCurrent = new ArrayList<>(report);
            withoutCurrent.remove(i);
            if (reportIsSafe(withoutCurrent)) {
                return true;
            }
        }
        return false;
    }

    static boolean levelsAllIncreasing(List<Integer> report) {
        return IntStream.range(1, report.size())
                .allMatch(i -> report.get(i) > report.get(i - 1));
    }

    static boolean levelsAllDecreasing(List<Integer> report) {
        return IntStream.range(1, report.size())
                .allMatch(i -> report.get(i) < report.get(i - 1));
    }

    static boolean levelsChangeIsSafe(List<Integer> report) {
        return IntStream.range(1, report.size())
                .allMatch(i -> Math.abs(report.get(i) - report.get(i - 1)) <= 3);
    }
}
