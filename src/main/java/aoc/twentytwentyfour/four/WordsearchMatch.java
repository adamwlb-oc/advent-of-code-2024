package aoc.twentytwentyfour.four;

public record WordsearchMatch(String word, CoordinatePair startingCoordinates, SearchDirection direction) {

    @Override
    public String toString() {
        return String.format("%s - Starting at %s, moving %s", word, startingCoordinates, direction);
    }
}
