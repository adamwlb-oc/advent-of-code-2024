package aoc.twentytwentyfour.runner;

import aoc.twentytwentyfour.eight.Day8;
import aoc.twentytwentyfour.eleven.Day11;
import aoc.twentytwentyfour.five.Day5;
import aoc.twentytwentyfour.four.Day4;
import aoc.twentytwentyfour.four.Wordsearch;
import aoc.twentytwentyfour.nine.Day9;
import aoc.twentytwentyfour.one.Day1;
import aoc.twentytwentyfour.one.LocationIdLists;
import aoc.twentytwentyfour.seven.Day7;
import aoc.twentytwentyfour.six.Day6;
import aoc.twentytwentyfour.ten.Day10;
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
        System.out.println(day5Part1());
        System.out.println(day5Part2());
        System.out.println(day6Part1());
        System.out.println(day6Part2());
        System.out.println(day7Part1());
        System.out.println(day7Part2());
        System.out.println(day8Part1());
        System.out.println(day8Part2());
        System.out.println(day9Part1());
        System.out.println(day9Part2());
        System.out.println(day10Part1());
        System.out.println(day10Part2());
        System.out.println(day11Part1());
        System.out.println(day11Part2());
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

    private static String day5Part1() {
        int answer = new Day5(new File("src/main/resources/day5.txt")).part1();
        return getResultString(answer, 5, 1);
    }

    private static String day5Part2() {
        int answer = new Day5(new File("src/main/resources/day5.txt")).part2();
        return getResultString(answer, 5, 2);
    }

    private static String day6Part1() {
        int answer = new Day6(new File("src/main/resources/day6.txt")).part1();
        return getResultString(answer, 6, 1);
    }

    private static String day6Part2() {
        long answer = new Day6(new File("src/main/resources/day6.txt")).part2();
        return getResultString(answer, 6, 2);
    }

    private static String day7Part1() {
        long answer = new Day7(new File("src/main/resources/day7.txt")).part1();
        return getResultString(answer, 7, 1);
    }

    private static String day7Part2() {
        long answer = new Day7(new File("src/main/resources/day7.txt")).part2();
        return getResultString(answer, 7, 2);
    }

    private static String day8Part1() {
        int answer = new Day8(new File("src/main/resources/day8.txt")).part1();
        return getResultString(answer, 8, 1);
    }

    private static String day8Part2() {
        int answer = new Day8(new File("src/main/resources/day8.txt")).part2();
        return getResultString(answer, 8, 2);
    }

    private static String day9Part1() {
        long answer = new Day9(new File("src/main/resources/day9.txt")).part1();
        return getResultString(answer, 9, 1);
    }

    private static String day9Part2() {
        long answer = new Day9(new File("src/main/resources/day9.txt")).part2();
        return getResultString(answer, 9, 2);
    }

    private static String day10Part1() {
        var answer = new Day10(new File("src/main/resources/day10.txt")).part1();
        return getResultString(answer, 10, 1);
    }

    private static String day10Part2() {
        var answer = new Day10(new File("src/main/resources/day10.txt")).part2();
        return getResultString(answer, 10, 2);
    }

    private static String day11Part1() {
        var answer = new Day11(new File("src/main/resources/day11.txt")).part1();
        return getResultString(answer, 11, 1);
    }

    private static String day11Part2() {
        var answer = new Day11(new File("src/main/resources/day11.txt")).part2();
        return getResultString(answer, 11, 2);
    }

    static String getResultString(Object result, int day, int part) {
        return String.format("Day %s part %s result: %s", day, part, result);
    }
}
