package aoc.twentytwentyfour.six;

import aoc.twentytwentyfour.common.CoordinatePair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class Day6 {

    private final LabMap labMap;

    public Day6(File input) {
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
        this.labMap = new LabMap(grid);
    }

    public int part1() {
        return getGuardPositions().size();
    }

    public long part2() {
        return getObstaclePositions().size();
    }

    private Set<CoordinatePair> getGuardPositions() {
        Set<CoordinatePair> positions = new HashSet<>();
        positions.add(labMap.guard().location());
        while (true) {
            labMap.patrol();
            if (!labMap.guardIsPresent()) {
                break;
            }
            positions.add(labMap.guard().location());
        }
        return positions;
    }
    
    private Set<CoordinatePair> getObstaclePositions() {
        return getGuardPositions().stream()
                .filter(obstacle -> getStartingMap().addObstacle(obstacle).isLoop())
                .collect(Collectors.toSet());
    }

    private LabMap getStartingMap() {
        List<List<Character>> grid = new ArrayList<>();
        for (List<Character> row : labMap.grid()) {
            grid.add(new ArrayList<>(row));
        }
        return new LabMap(grid);
    }

    LabMap labMap() {
        return labMap;
    }
}
