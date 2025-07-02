package aoc.twentytwentyfour.eight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Day8 {

    private final FrequencyMap frequencyMap;

    public Day8(File input) {
        frequencyMap = getFrequencyMapFor(input);
    }

    static FrequencyMap getFrequencyMapFor(File input) {
        List<List<Character>> map = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> chars = new ArrayList<>();
                for (char c : line.toCharArray()) {
                    chars.add(c);
                }
                map.add(chars);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new FrequencyMap(map);
    }

    public int part1() {
        return frequencyMap.antinodes().size();
    }

    public int part2() {
        return frequencyMap.antinodesWithResonantHarmonics().size();
    }
}
