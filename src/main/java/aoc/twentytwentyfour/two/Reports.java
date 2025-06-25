package aoc.twentytwentyfour.two;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reports {

    private final List<List<Integer>> levels;

    public Reports(File input) {
        levels = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line;
            while ((line = br.readLine()) != null) {
                String[] reportLevels = line.split(" ");
                List<Integer> integerLevels = Arrays.stream(reportLevels).map(Integer::valueOf).toList();
                levels.add(integerLevels);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<List<Integer>> asList() {
        return levels;
    }
}
