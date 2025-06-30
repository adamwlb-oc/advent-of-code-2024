package aoc.twentytwentyfour.six;

import aoc.twentytwentyfour.common.CoordinatePair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GuardTest {

    private static Stream<Arguments> getInitialGuardsAndNextSpace() {
        return Stream.of(
                Arguments.of(new Guard(new CoordinatePair(2, 2), Facing.UP), new CoordinatePair(2, 3)),
                Arguments.of(new Guard(new CoordinatePair(2, 2), Facing.RIGHT), new CoordinatePair(3, 2)),
                Arguments.of(new Guard(new CoordinatePair(2, 2), Facing.DOWN), new CoordinatePair(2, 1)),
                Arguments.of(new Guard(new CoordinatePair(2, 2), Facing.LEFT), new CoordinatePair(1, 2))
        );
    }

    private static Stream<Arguments> getInitialGuardsAndFacingsAfterRightTurn() {
        return Stream.of(
                Arguments.of(new Guard(new CoordinatePair(2, 2), Facing.UP), Facing.RIGHT),
                Arguments.of(new Guard(new CoordinatePair(2, 2), Facing.RIGHT), Facing.DOWN),
                Arguments.of(new Guard(new CoordinatePair(2, 2), Facing.DOWN), Facing.LEFT),
                Arguments.of(new Guard(new CoordinatePair(2, 2), Facing.LEFT), Facing.UP)
        );
    }

    @ParameterizedTest
    @MethodSource("getInitialGuardsAndNextSpace")
    void getsGuardNextSpace(Guard guard, CoordinatePair inFront) {
        assertEquals(inFront, guard.getNextSpace());
    }

    @ParameterizedTest
    @MethodSource("getInitialGuardsAndNextSpace")
    void movingUpdatesLocation(Guard guard, CoordinatePair inFront) {
        guard.move();
        assertEquals(inFront, guard.location());
    }

    @ParameterizedTest
    @MethodSource("getInitialGuardsAndFacingsAfterRightTurn")
    void turnsRightAndUpdatesFacing(Guard guard, Facing turnedRight) {
        guard.turnRight();
        assertEquals(turnedRight, guard.facing());
    }
}