package aoc.twentytwentyfour.four;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum SearchDirection {
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1),
    UP_RIGHT(1, 1),
    UP_LEFT(-1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(1, -1);

    private static final SearchDirection[] VALUES = SearchDirection.values();
    private final int xStep;
    private final int yStep;

    SearchDirection(int xStep, int yStep) {
        this.xStep = xStep;
        this.yStep = yStep;
    }

    public int xStep() {
        return xStep;
    }

    public int yStep() {
        return yStep;
    }

    public static Set<SearchDirection> allDirections() {
        return Arrays.stream(VALUES).collect(Collectors.toSet());
    }

    public static Set<SearchDirection> diagonals() {
        return Set.of(
                UP_LEFT,
                UP_RIGHT,
                DOWN_LEFT,
                DOWN_RIGHT
        );
    }

}
