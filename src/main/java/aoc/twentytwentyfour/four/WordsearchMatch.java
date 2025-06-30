package aoc.twentytwentyfour.four;

import aoc.twentytwentyfour.common.CoordinatePair;

public record WordsearchMatch(String word, CoordinatePair startingCoordinates, SearchDirection direction) {

    @Override
    public String toString() {
        return String.format("%s - Starting at %s, moving %s", word, startingCoordinates, direction);
    }
}
