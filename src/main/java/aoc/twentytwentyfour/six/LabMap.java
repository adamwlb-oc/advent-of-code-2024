package aoc.twentytwentyfour.six;

import aoc.twentytwentyfour.common.CoordinatePair;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public class LabMap {

    private static final char OBSTACLE = '#';
    private static final char GUARD = '^';

    private final List<List<Character>> grid;
    private final Guard guard;

    public LabMap(List<List<Character>> grid) {
        this.grid = grid;
        this.guard = new Guard(findGuard(), Facing.UP);
    }

    public Guard guard() {
        return guard;
    }

    public void patrol() {
        if (isObstacle(guard.getNextSpace())) {
            guard.turnRight();
        } else {
            guard.move();
        }
    }

    public boolean guardIsPresent() {
        return inBounds(guard.location());
    }

    public LabMap addObstacle(CoordinatePair coords) {
        if (inBounds(coords) && !isGuard(coords)) {
            List<Character> row = grid.get((height() - 1) - coords.y());
            row.set(coords.x(), OBSTACLE);
        }
        return this;
    }

    public boolean isLoop() {
        Set<Guard> statesBeforeCollision = new HashSet<>();
        while (guardIsPresent()) {
            if (isObstacle(guard.getNextSpace())) {
                if (!statesBeforeCollision.add(new Guard(guard.location(), guard.facing()))) {
                    return true;
                }
            }
            patrol();
        }
        return false;
    }

    public CoordinatePair findGuard() {
        return IntStream.range(0, width())
                .boxed()
                .flatMap(i -> IntStream.range(0, height()).mapToObj(j -> new CoordinatePair(i, j)))
                .filter(this::isGuard)
                .findFirst()
                .orElse(null);
    }

    Character getByCoords(CoordinatePair coords) {
        if (!inBounds(coords)) {
            throw new IllegalArgumentException(String.format("Coordinates %s were out of bounds", coords));
        }
        return grid.get((height() - 1) - coords.y()).get(coords.x());
    }

    boolean isObstacle(CoordinatePair coords) {
        return inBounds(coords) && getByCoords(coords).equals(OBSTACLE);
    }

    boolean isGuard(CoordinatePair coords) {
        return inBounds(coords) && getByCoords(coords).equals(GUARD);
    }

    boolean inBounds(CoordinatePair coords) {
        int x = coords.x();
        int y = coords.y();
        return (x >= 0 && y >= 0 && x < width() && y < height());
    }

    public List<List<Character>> grid() {
        return grid;
    }

    private int height() {
        return grid.size();
    }

    private int width() {
        return grid.getFirst().size();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LabMap labMap = (LabMap) o;
        return Objects.equals(grid, labMap.grid) && Objects.equals(guard, labMap.guard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grid, guard);
    }
}
