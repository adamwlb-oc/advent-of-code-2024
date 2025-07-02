package aoc.twentytwentyfour.eight;

import aoc.twentytwentyfour.common.CoordinatePair;

public record Antenna(CoordinatePair location, char frequency) {

    public Antenna(int x, int y, char frequency) {
        this(new CoordinatePair(x, y), frequency);
    }
}
