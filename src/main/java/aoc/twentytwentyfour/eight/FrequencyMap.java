package aoc.twentytwentyfour.eight;

import aoc.twentytwentyfour.common.CoordinatePair;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record FrequencyMap(List<List<Character>> map) {

    private static final char BLANK = '.';

    static Set<CoordinatePair> antinodeCoordsOf(Antenna a1, Antenna a2) {
        Set<CoordinatePair> antinodes = new HashSet<>();
        if (a1.frequency() != a2.frequency()) {
            return antinodes;
        }

        CoordinatePair l1 = a1.location();
        int x1 = l1.x();
        int y1 = l1.y();

        CoordinatePair l2 = a2.location();
        int x2 = l2.x();
        int y2 = l2.y();

        int dy = y2 - y1;
        int dx = x2 - x1;

        antinodes.add(new CoordinatePair(x2 + dx, y2 + dy));
        antinodes.add(new CoordinatePair(x1 - dx, y1 - dy));

        return antinodes;
    }

    Set<CoordinatePair> resonantHarmonicsAntinodeCoordsOf(Antenna a1, Antenna a2) {
        Set<CoordinatePair> antinodes = new HashSet<>();
        if (a1.frequency() != a2.frequency()) {
            return antinodes;
        }
        CoordinatePair l1 = a1.location();
        int x1 = l1.x();
        int y1 = l1.y();

        CoordinatePair l2 = a2.location();
        int x2 = l2.x();
        int y2 = l2.y();

        int dy = y2 - y1;
        int dx = x2 - x1;

        antinodes.add(l1);
        antinodes.add(l2);

        CoordinatePair nextAntinodePositive = new CoordinatePair(x2 + dx, y2 + dy);
        while (inBounds(nextAntinodePositive)) {
            antinodes.add(nextAntinodePositive);
            nextAntinodePositive = new CoordinatePair(nextAntinodePositive.x() + dx, nextAntinodePositive.y() + dy);
        }
        CoordinatePair nextAntinodeNegative = new CoordinatePair(x1 - dx, y1 - dy);
        while (inBounds(nextAntinodeNegative)) {
            antinodes.add(nextAntinodeNegative);
            nextAntinodeNegative = new CoordinatePair(nextAntinodeNegative.x() - dx, nextAntinodeNegative.y() - dy);
        }

        return antinodes;
    }

    public Set<Antenna> antennas() {
        return IntStream.range(0, width())
                .boxed()
                .flatMap(x -> IntStream.range(0, height()).mapToObj(y -> new CoordinatePair(x, y)))
                .map(coords -> new Antenna(coords, getByCoords(coords)))
                .filter(antenna -> antenna.frequency() != BLANK)
                .collect(Collectors.toSet());
    }

    public Set<CoordinatePair> antinodes() {
        return antennas().stream()
                .flatMap(a -> getAntennasWithSameFrequency(a).stream()
                        .map(b -> antinodeCoordsOf(a, b).stream()
                                .filter(this::inBounds)
                                .collect(Collectors.toSet())
                        )
                )
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public Set<CoordinatePair> antinodesWithResonantHarmonics() {
        return antennas().stream()
                .flatMap(a -> getAntennasWithSameFrequency(a).stream()
                        .map(b -> resonantHarmonicsAntinodeCoordsOf(a, b).stream()
                                .filter(this::inBounds)
                                .collect(Collectors.toSet())
                        )
                )
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    Set<Antenna> getAntennasWithSameFrequency(Antenna antenna) {
        return antennas().stream()
                .filter(a -> a.frequency() == antenna.frequency())
                .filter(a -> !a.equals(antenna))
                .collect(Collectors.toSet());
    }

    Character getByCoords(CoordinatePair coords) {
        if (!inBounds(coords)) {
            throw new IllegalArgumentException(String.format("Coordinates %s were out of bounds", coords));
        }
        return map.get((map.size() - 1) - coords.y()).get(coords.x());
    }

    boolean inBounds(CoordinatePair coords) {
        int x = coords.x();
        int y = coords.y();
        return (x >= 0 && y >= 0 && x < width() && y < height());
    }

    int height() {
        return map().size();
    }

    int width() {
        return map().getFirst().size();
    }
}
