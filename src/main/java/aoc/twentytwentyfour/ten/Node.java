package aoc.twentytwentyfour.ten;

import aoc.twentytwentyfour.common.CoordinatePair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class Node {
    private final int height;
    private final CoordinatePair coordinates;
    private final Collection<Node> neighbours;

    public Node(int height, CoordinatePair coordinates) {
        this(height, coordinates, new HashSet<>());
    }

    Node(int height, CoordinatePair coordinates, Collection<Node> neighbours) {
        this.height = height;
        this.coordinates = coordinates;
        this.neighbours = neighbours;
    }

    public static List<List<Node>> getAllDistinctTrails(Node start) {
        List<List<Node>> trails = new ArrayList<>();
        explore(start, new ArrayList<>(), trails);

        List<List<Node>> result = new ArrayList<>();
        Set<String> seenTrails = new HashSet<>();
        for (List<Node> trail : trails) {
            Node first = trail.getFirst();
            Node last = trail.getLast();
            String key = first.coordinates.toString() + last.coordinates.toString();

            if (!seenTrails.contains(key)) {
                result.add(trail);
                seenTrails.add(key);
            }
        }
        return result;
    }

    public static List<List<Node>> getAllTrails(Node start) {
        List<List<Node>> trails = new ArrayList<>();
        explore(start, new ArrayList<>(), trails);
        return trails;
    }

    private static void explore(Node current, List<Node> path, List<List<Node>> trails) {
        path.add(current);
        List<Node> steps = current.getSteps();
        if (steps.isEmpty() && current.height() == 9) {
            trails.add(new ArrayList<>(path));
        } else {
            for (Node next : steps) {
                if (!path.contains(next)) {
                    explore(next, new ArrayList<>(path), trails);
                }
            }
        }
    }

    List<Node> getSteps() {
        return neighbours().stream()
                .filter(neighbour -> neighbour.height() == height() + 1)
                .toList();
    }

    public boolean isTrailhead() {
        return height == 0;
    }

    public int height() {
        return height;
    }

    public CoordinatePair coordinates() {
        return coordinates;
    }

    public Collection<Node> neighbours() {
        return neighbours;
    }

    public void addNeighbours(Collection<Node> collection) {
        neighbours.addAll(collection);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Node) obj;
        return this.height == that.height &&
                Objects.equals(this.coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, coordinates);
    }

    @Override
    public String toString() {
        return "Node[" +
                "height=" + height + ", " +
                "coordinates=" + coordinates + ']';
    }

}
