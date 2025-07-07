package aoc.twentytwentyfour.ten;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Day10 {

    private final TopographicMap map;

    public Day10(File input) {
        this.map = parseInput(input);
    }

    static TopographicMap parseInput(File input) {
        List<List<Integer>> grid = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line;
            while ((line = br.readLine()) != null) {
                List<Integer> heights = new ArrayList<>();
                for (char c : line.toCharArray()) {
                    heights.add(Character.getNumericValue(c));
                }
                grid.add(heights);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new TopographicMap(grid);
    }

    public long part1() {
        return map.trailheads().stream()
                .map(Node::getAllDistinctTrails)
                .mapToInt(List::size)
                .sum();
    }

    public long part2() {
        return map.trailheads().stream()
                .map(Node::getAllTrails)
                .mapToInt(List::size)
                .sum();
    }
}
