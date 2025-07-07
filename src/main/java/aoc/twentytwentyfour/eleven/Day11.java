package aoc.twentytwentyfour.eleven;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public final class Day11 {

    private final StoneLine stoneLine;

    public Day11(File input) {
        this.stoneLine = parseInput(input);
    }

    static StoneLine parseInput(File input) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            List<Stone> stones = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .map(Stone::new)
                    .toList();
            return new StoneLine(stones);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long part1() {
        for (int i = 0; i < 25; i++) {
            stoneLine.blink();
        }
        return stoneLine.countStones();
    }

    public long part2() {
        for (int i = 0; i < 75; i++) {
            stoneLine.blink();
        }
        return stoneLine.countStones();
    }
}
