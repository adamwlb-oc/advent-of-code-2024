package aoc.twentytwentyfour.six;

public enum Facing {
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1);

    private final int xStep;
    private final int yStep;

    Facing(int xStep, int yStep) {
        this.xStep = xStep;
        this.yStep = yStep;
    }

    public int xStep() {
        return xStep;
    }

    public int yStep() {
        return yStep;
    }
}
