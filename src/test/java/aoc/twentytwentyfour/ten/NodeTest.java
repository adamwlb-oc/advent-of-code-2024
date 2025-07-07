package aoc.twentytwentyfour.ten;

import aoc.twentytwentyfour.common.CoordinatePair;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NodeTest {

    @Test
    void nodesOfHeightZeroAreTrailheads() {
        assertTrue(new Node(0, null).isTrailhead());
        assertFalse(new Node(1, null).isTrailhead());
    }

    @Test
    void getsNextSteps() {
        Node start = new Node(0, new CoordinatePair(0, 0), Set.of(
                new Node(1, null, null),
                new Node(3, null, null),
                new Node(0, null, null)
        ));
        assertEquals(List.of(new Node(1, null, null)), start.getSteps());
    }

    @Test
    void getsTrail() {
        Node node9 = new Node(9, new CoordinatePair(9, 9), Set.of());
        Node node8 = new Node(8, null, Set.of(node9));
        Node node7 = new Node(7, null, Set.of(node8));
        Node node6 = new Node(6, null, Set.of(node7));
        Node node5 = new Node(5, null, Set.of(node6));
        Node node4 = new Node(4, null, Set.of(node5));
        Node node3 = new Node(3, null, Set.of(node4));
        Node node2 = new Node(2, null, Set.of(node3));
        Node node1 = new Node(1, null, Set.of(node2));
        Node start = new Node(0, new CoordinatePair(0, 0), Set.of(node1));

        assertEquals(List.of(
                List.of(start, node1, node2, node3, node4, node5, node6, node7, node8, node9)
        ), Node.getAllDistinctTrails(start));
    }
}