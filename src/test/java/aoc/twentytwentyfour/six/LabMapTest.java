package aoc.twentytwentyfour.six;

import aoc.twentytwentyfour.common.CoordinatePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LabMapTest {

    private LabMap underTest;

    private static Stream<Arguments> getInvalidCoords() {
        return Stream.of(
                Arguments.of(new CoordinatePair(10, 3)),
                Arguments.of(new CoordinatePair(4, 12)),
                Arguments.of(new CoordinatePair(-3, 2)),
                Arguments.of(new CoordinatePair(6, -2))
        );
    }

    public static Stream<Arguments> getObstaclesToAdd() {
        return Stream.of(
                Arguments.of(new CoordinatePair(3, 3), true),
                Arguments.of(new CoordinatePair(6, 2), true),
                Arguments.of(new CoordinatePair(7, 2), true),
                Arguments.of(new CoordinatePair(1, 1), true),
                Arguments.of(new CoordinatePair(3, 1), true),
                Arguments.of(new CoordinatePair(7, 0), true),
                Arguments.of(new CoordinatePair(5, 5), false),
                Arguments.of(new CoordinatePair(9, 4), false),
                Arguments.of(new CoordinatePair(4, 4), false),
                Arguments.of(new CoordinatePair(8, 7), false)
        );
    }

    @BeforeEach
    void setup() {
        underTest = new LabMap(List.of(
                new ArrayList<>(List.of('.', '.', '.', '.', '#', '.', '.', '.', '.', '.')),
                new ArrayList<>(List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', '#')),
                new ArrayList<>(List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', '.')),
                new ArrayList<>(List.of('.', '.', '#', '.', '.', '.', '.', '.', '.', '.')),
                new ArrayList<>(List.of('.', '.', '.', '.', '.', '.', '.', '#', '.', '.')),
                new ArrayList<>(List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', '.')),
                new ArrayList<>(List.of('.', '#', '.', '.', '^', '.', '.', '.', '.', '.')),
                new ArrayList<>(List.of('.', '.', '.', '.', '.', '.', '.', '.', '#', '.')),
                new ArrayList<>(List.of('#', '.', '.', '.', '.', '.', '.', '.', '.', '.')),
                new ArrayList<>(List.of('.', '.', '.', '.', '.', '.', '#', '.', '.', '.'))
        ));
    }

    @Test
    void updatesGuardPositionForPatrol() {
        Guard guard = underTest.guard();
        assertEquals(new CoordinatePair(4, 3), guard.location());
        assertEquals(Facing.UP, guard.facing());

        for (int i = 0; i < 6; i++) {
            underTest.patrol();
        }

        assertEquals(new CoordinatePair(4, 8), guard.location());
        assertEquals(Facing.RIGHT, guard.facing());
    }

    @ParameterizedTest
    @MethodSource("getInvalidCoords")
    void illegalCoordsThrowIllegalArgumentException(CoordinatePair coords) {
        Exception error = assertThrows(IllegalArgumentException.class, () -> underTest.getByCoords(coords));
        assertEquals(String.format("Coordinates %s were out of bounds", coords), error.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getInvalidCoords")
    void checksOutOfBoundCoords(CoordinatePair coords) {
        assertFalse(underTest.inBounds(coords));
    }

    @Test
    void identifiesObstacleCoord() {
        assertTrue(underTest.isObstacle(new CoordinatePair(0, 1)));
    }

    @Test
    void identifiesGuardCoord() {
        assertTrue(underTest.isGuard(new CoordinatePair(4, 3)));
    }

    @Test
    void findsCoordinatesOfGuard() {
        assertEquals(new CoordinatePair(4, 3), underTest.findGuard());
        LabMap mapWithNoGuard = new LabMap(List.of(
                List.of('.', '.', '.'),
                List.of('.', '#', '.'),
                List.of('.', '.', '.')
        ));
        assertNull(mapWithNoGuard.findGuard());
    }

    @Test
    void initialisesGuardStartPosition() {
        assertEquals(new Guard(new CoordinatePair(4, 3), Facing.UP), underTest.guard());
    }

    @Test
    void addsObstaclesToMap() {
        CoordinatePair coordinates = new CoordinatePair(3, 2);
        assertFalse(underTest.isObstacle(coordinates));
        underTest.addObstacle(coordinates);
        assertTrue(underTest.isObstacle(coordinates));
    }

    @ParameterizedTest
    @MethodSource("getObstaclesToAdd")
    void identifiesGuardLoop(CoordinatePair newObstacle, boolean createsLoop) {
        assertEquals(createsLoop, underTest.addObstacle(newObstacle).isLoop());
    }

    @Test
    void cannotPlaceObstacleWhereGuardIsStanding() {
        CoordinatePair guardLocation = underTest.guard().location();
        underTest.addObstacle(guardLocation);
        assertTrue(underTest.isGuard(guardLocation));
    }

}