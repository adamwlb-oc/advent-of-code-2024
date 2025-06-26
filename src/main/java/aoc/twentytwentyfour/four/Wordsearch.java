package aoc.twentytwentyfour.four;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Wordsearch {

    private final List<List<Character>> grid;

    public Wordsearch(File input) {
        List<List<Character>> grid = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> chars = new ArrayList<>();
                for (char c : line.toCharArray()) {
                    chars.add(c);
                }
                grid.add(chars);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.grid = grid;
    }

    public Wordsearch(List<List<Character>> grid) {
        this.grid = grid;
    }

    public List<List<Character>> asCharacterGrid() {
        return grid;
    }

    public int height() {
        return asCharacterGrid().size();
    }
    
    public int width() {
        return asCharacterGrid().getFirst().size();
    }

    public Character getByCoords(CoordinatePair coords) {
        if (!inBounds(coords)) {
            throw new IllegalArgumentException(String.format("Coordinates %s were out of bounds", coords));
        }
        return grid.get((grid.size() - 1) - coords.y()).get(coords.x());
    }

    public boolean inBounds(CoordinatePair coords) {
        int x = coords.x();
        int y = coords.y();
        return (x >= 0 && y >= 0 && x < grid.getFirst().size() && y < grid.size());
    }
}
