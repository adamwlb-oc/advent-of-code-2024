package aoc.twentytwentyfour.ten;

import aoc.twentytwentyfour.common.CoordinatePair;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TopographicMapTest {

    @Test
    void getsByCoordinates() {
        List<List<Integer>> grid = List.of(
                List.of(0, 1, 2),
                List.of(3, 4, 5),
                List.of(6, 7, 8)
        );
        TopographicMap topography = new TopographicMap(grid);

        assertEquals(6, topography.getByCoords(new CoordinatePair(0, 0)).height());
        assertEquals(4, topography.getByCoords(new CoordinatePair(1, 1)).height());
        assertEquals(0, topography.getByCoords(new CoordinatePair(0, 2)).height());
        assertEquals(2, topography.getByCoords(new CoordinatePair(2, 2)).height());
    }

    @Test
    void getNeighboursForNode() {
        List<List<Integer>> grid = List.of(
                List.of(0, 1, 2),
                List.of(3, 4, 5),
                List.of(6, 7, 8)
        );
        TopographicMap topography = new TopographicMap(grid);

        Collection<Node> middleNeighbours = Set.of(
                new Node(3, new CoordinatePair(0, 1)),
                new Node(1, new CoordinatePair(1, 2)),
                new Node(7, new CoordinatePair(1, 0)),
                new Node(5, new CoordinatePair(2, 1))

        );
        Collection<Node> bottomNeighbours = Set.of(
                new Node(4, new CoordinatePair(1, 1)),
                new Node(6, new CoordinatePair(0, 0)),
                new Node(8, new CoordinatePair(2, 0))
        );
        Collection<Node> topLeftNeighbours = Set.of(
                new Node(1, new CoordinatePair(1, 2)),
                new Node(3, new CoordinatePair(0, 1))
        );

        assertEquals(middleNeighbours, topography.initialiseNeighbours(new Node(4, new CoordinatePair(1, 1))));
        assertEquals(bottomNeighbours, topography.initialiseNeighbours(new Node(1, new CoordinatePair(1, 0))));
        assertEquals(topLeftNeighbours, topography.initialiseNeighbours(new Node(6, new CoordinatePair(0, 2))));
    }

    @Test
    void outOfBoundsCoordsThrowIllegalArgumentException() {
        TopographicMap topography = new TopographicMap(List.of(
                List.of(0, 1, 2),
                List.of(3, 4, 5),
                List.of(6, 7, 8)
        ));

        CoordinatePair invalidCoords1 = new CoordinatePair(-1, 2);
        assertEquals(String.format("Coordinates %s were out of bounds", invalidCoords1),
                assertThrows(IllegalArgumentException.class, () -> topography.getByCoords(invalidCoords1)).getMessage());

        CoordinatePair invalidCoords2 = new CoordinatePair(2, 4);
        assertEquals(String.format("Coordinates %s were out of bounds", invalidCoords2),
                assertThrows(IllegalArgumentException.class, () -> topography.getByCoords(invalidCoords2)).getMessage());
    }

    @Test
    void getsAllTrailheadsInMap() {
        TopographicMap topography = new TopographicMap(List.of(
                List.of(0, 1, 2),
                List.of(3, 0, 5),
                List.of(6, 7, 0)
        ));
        Set<Node> actual = topography.trailheads();
        assertEquals(3, actual.size());
        assertTrue(actual.stream().allMatch(Node::isTrailhead));
    }
}