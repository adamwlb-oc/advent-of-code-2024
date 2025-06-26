package aoc.twentytwentyfour.five;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {

    private final PrinterInstructions printerInstructions;

    public Day5(File input) {
        List<OrderingRule> orderingRules = new ArrayList<>();
        List<Update> updates = new ArrayList<>();
        boolean parseUpdates = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) {
                    parseUpdates = true;
                } else if (parseUpdates) {
                    updates.add(new Update(Arrays.stream(line.split(",")).map(Integer::parseInt).toList()));
                } else {
                    String[] rule = line.split("\\|");
                    orderingRules.add(new OrderingRule(Integer.parseInt(rule[0]), Integer.parseInt(rule[1])));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.printerInstructions = new PrinterInstructions(orderingRules, updates);
    }

    PrinterInstructions printerInstructions() {
        return printerInstructions;
    }

    public int part1() {
        return printerInstructions.getInOrderUpdates().stream()
                .mapToInt(Update::getMiddlePage)
                .sum();
    }

    public int part2() {
        return printerInstructions.getIncorrectlyOrderedUpdates().stream()
                .map(printerInstructions::fixUpdate)
                .mapToInt(Update::getMiddlePage)
                .sum();
    }

}
