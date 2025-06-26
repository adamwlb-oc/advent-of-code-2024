package aoc.twentytwentyfour.three;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Day3 {

    private static final String MUL_INSTRUCTION_REGEX = "mul\\(\\d+,\\s*\\d+\\)";
    private static final String DO_INSTRUCTION_REGEX = "do\\(\\)";
    private static final String DONT_INSTRUCTION_REGEX = "don't\\(\\)";

    private static final String DO = "do()";
    private static final String DONT = "don't()";

    public int part1(Memory input) {
        return getMulInstructions(input.asString())
                .stream().mapToInt(Mul::evaluate)
                .sum();
    }

    public int part2(Memory input) {
        return extractEnabledMuls(aggregateInstructions(input.asString()))
                .stream().mapToInt(Mul::evaluate)
                .sum();
    }

    static List<Mul> getMulInstructions(String memoryString) {
        Pattern pattern = Pattern.compile(MUL_INSTRUCTION_REGEX);
        Matcher matcher = pattern.matcher(memoryString);

        List<Mul> muls = new ArrayList<>();
        while (matcher.find()) {
            muls.add(new Mul(matcher.group()));
        }
        return muls;
    }

    static List<Mul> extractEnabledMuls(SortedMap<Integer, String> instructions) {
        boolean enabled = true;
        List<Mul> enabledMuls = new ArrayList<>();

        for (Map.Entry<Integer, String> entry : instructions.entrySet()) {
            String value = entry.getValue();
            if (value.equals(DO)) {
                enabled = true;
            } else if (value.equals(DONT)) {
                enabled = false;
            } else if (enabled) {
                enabledMuls.add(new Mul(value));
            }
        }
        return enabledMuls;
    }

    static SortedMap<Integer, String> aggregateInstructions(String memoryString) {
        SortedMap<Integer, String> instructions = new TreeMap<>(getMulsWithIndicies(memoryString));
        getDoIndicies(memoryString).forEach(i -> instructions.put(i, DO));
        getDontIndicies(memoryString).forEach(i -> instructions.put(i, DONT));

        return instructions;
    }

    static List<Integer> getDoIndicies(String memoryString) {
        Pattern pattern = Pattern.compile(DO_INSTRUCTION_REGEX);
        Matcher matcher = pattern.matcher(memoryString);

        List<Integer> dos = new ArrayList<>();
        while (matcher.find()) {
            dos.add(matcher.start());
        }
        return dos;
    }

    static List<Integer> getDontIndicies(String memoryString) {
        Pattern pattern = Pattern.compile(DONT_INSTRUCTION_REGEX);
        Matcher matcher = pattern.matcher(memoryString);

        List<Integer> dos = new ArrayList<>();
        while (matcher.find()) {
            dos.add(matcher.start());
        }
        return dos;
    }

    static Map<Integer, String> getMulsWithIndicies(String memoryString) {
        Pattern pattern = Pattern.compile(MUL_INSTRUCTION_REGEX);
        Matcher matcher = pattern.matcher(memoryString);

        Map<Integer, String> muls = new HashMap<>();
        while (matcher.find()) {
            muls.put(matcher.start(), matcher.group());
        }
        return muls;
    }
}
