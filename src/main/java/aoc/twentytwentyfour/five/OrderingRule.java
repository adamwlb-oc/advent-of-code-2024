package aoc.twentytwentyfour.five;

public record OrderingRule(int before, int after) {

    @Override
    public String toString() {
        return before + "|" + after;
    }
}
