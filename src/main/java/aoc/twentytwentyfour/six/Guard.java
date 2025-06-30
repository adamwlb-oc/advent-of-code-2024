package aoc.twentytwentyfour.six;

import aoc.twentytwentyfour.common.CoordinatePair;

import java.util.Objects;

import static aoc.twentytwentyfour.six.Facing.DOWN;
import static aoc.twentytwentyfour.six.Facing.LEFT;
import static aoc.twentytwentyfour.six.Facing.RIGHT;
import static aoc.twentytwentyfour.six.Facing.UP;

public class Guard {

    private Facing facing;
    private CoordinatePair location;

    public Guard(CoordinatePair location, Facing facing) {
        this.location = location;
        this.facing = facing;
    }

    public CoordinatePair location() {
        return location;
    }

    Facing facing() {
        return facing;
    }

    public void move() {
        location = new CoordinatePair(location.x() + facing.xStep(), location.y() + facing.yStep());
    }

    public void turnRight() {
        facing = switch (facing) {
            case LEFT -> UP;
            case RIGHT -> DOWN;
            case UP -> RIGHT;
            case DOWN -> LEFT;
        };
    }

    public CoordinatePair getNextSpace() {
        return new CoordinatePair(location.x() + facing.xStep(), location.y() + facing.yStep());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Guard guard = (Guard) o;
        return facing == guard.facing && Objects.equals(location, guard.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facing, location);
    }
}
