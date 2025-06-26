package aoc.twentytwentyfour.four;

public record CoordinatePair(int x, int y) {

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
