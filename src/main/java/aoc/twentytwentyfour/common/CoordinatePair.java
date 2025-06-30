package aoc.twentytwentyfour.common;

public record CoordinatePair(int x, int y) {

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
