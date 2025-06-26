package aoc.twentytwentyfour.runner;

import aoc.twentytwentyfour.four.Day4;
import aoc.twentytwentyfour.four.Wordsearch;
import aoc.twentytwentyfour.one.Day1;
import aoc.twentytwentyfour.one.LocationIdLists;
import aoc.twentytwentyfour.three.Day3;
import aoc.twentytwentyfour.three.Memory;
import aoc.twentytwentyfour.two.Day2;
import aoc.twentytwentyfour.two.Reports;

import java.io.File;

public class Runner {

    public static void main(String[] args) {
        System.out.println(day1Part1());
        System.out.println(day1Part2());
        System.out.println(day2Part1());
        System.out.println(day2Part2());
        System.out.println(day3Part1());
        System.out.println(day3Part2());
        System.out.println(day4Part1());
        System.out.println(day4Part2());
    }

    private static String day1Part1() {
        LocationIdLists day1Input = new LocationIdLists(new File("src/main/resources/day1.txt"));
        int answer = new Day1().part1(day1Input);
        return getResultString(answer, 1, 1);
    }

    private static String day1Part2() {
        LocationIdLists day1Input = new LocationIdLists(new File("src/main/resources/day1.txt"));
        int answer = new Day1().part2(day1Input);
        return getResultString(answer, 1, 2);
    }

    private static String day2Part1() {
        Reports day2Input = new Reports(new File("src/main/resources/day2.txt"));
        long answer = new Day2().part1(day2Input);
        return getResultString(answer, 2, 1);
    }

    private static String day2Part2() {
        Reports day2Input = new Reports(new File("src/main/resources/day2.txt"));
        long answer = new Day2().part2(day2Input);
        return getResultString(answer, 2, 2);
    }

    private static String day3Part1() {
        Memory day3Input = new Memory(new File("src/main/resources/day3.txt"));
        int answer = new Day3().part1(day3Input);
        return getResultString(answer, 3, 1);
    }

    private static String day3Part2() {
        Memory day3Input = new Memory(new File("src/main/resources/day3.txt"));
        int answer = new Day3().part2(day3Input);
        return getResultString(answer, 3, 2);
    }

    private static String day4Part1() {
        Wordsearch day4Input = new Wordsearch(new File("src/main/resources/day4.txt"));
        int answer = new Day4().part1(day4Input);
        return getResultString(answer, 4, 1);
    }

    private static String day4Part2() {
        Wordsearch day4Input = new Wordsearch(new File("src/main/resources/day4.txt"));
        int answer = new Day4().part2(day4Input);
        return getResultString(answer, 4, 2);
    }

    static String getResultString(Object result, int day, int part) {
        return String.format("Day %s part %s result: %s", day, part, result);
    }
}
