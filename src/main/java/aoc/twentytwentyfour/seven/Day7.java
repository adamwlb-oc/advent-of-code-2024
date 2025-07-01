package aoc.twentytwentyfour.seven;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Day7 {

    private final List<Equation> calibrationEquations;

    public Day7(File input) {
        this.calibrationEquations = parseInput(input);
    }

    static List<Equation> parseInput(File input) {
        ArrayList<Equation> equations = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line;
            while ((line = br.readLine()) != null) {
                String[] sections = line.split(":");
                long testValue = Long.parseLong(sections[0]);
                List<Integer> operands = Arrays.stream(sections[1].trim().split(" "))
                        .map(Integer::parseInt)
                        .toList();
                equations.add(new Equation(testValue, operands));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return equations;
    }

    public long part1() {
        return getTotalCalibrationResult(Operator.part1Operations());
    }

    public long part2() {
        return getTotalCalibrationResult(Operator.part2Operations());
    }

    private long getTotalCalibrationResult(List<Operator> operators) {
        return calibrationEquations.stream()
                .filter(equation -> equation.canBeTrue(operators))
                .mapToLong(Equation::testValue)
                .sum();
    }
}
