package aoc.twentytwentyfour.ten;

import aoc.twentytwentyfour.common.CoordinatePair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class TopographicMap {

    private final List<List<Node>> grid;

    public TopographicMap(List<List<Integer>> grid) {
        List<List<Node>> map = new ArrayList<>();
        for (int y = 0; y < grid.size(); y++) {
            map.addFirst(new ArrayList<>());
            for (int x = 0; x < grid.get(y).size(); x++) {
                CoordinatePair coords = new CoordinatePair(x, y);
                int height = grid.get((grid.size() - 1) - y).get(x);
                map.getFirst().add(new Node(height, coords));
            }
        }
        this.grid = map;

        initialiseNodesWithNeighbours();
    }

    void initialiseNodesWithNeighbours() {
        grid.forEach(list ->
                list.forEach(n ->
                        n.addNeighbours(initialiseNeighbours(n))));
    }

    Collection<Node> initialiseNeighbours(Node node) {
        if (!node.neighbours().isEmpty()) {
            return node.neighbours();
        }
        int x = node.coordinates().x();
        int y = node.coordinates().y();
        Set<Node> neighbours = new HashSet<>();
        CoordinatePair right = new CoordinatePair(x + 1, y);
        if (inBounds(right))
            neighbours.add(getByCoords(right));
        CoordinatePair up = new CoordinatePair(x, y + 1);
        if (inBounds(up))
            neighbours.add(getByCoords(up));
        CoordinatePair left = new CoordinatePair(x - 1, y);
        if (inBounds(left))
            neighbours.add(getByCoords(left));
        CoordinatePair down = new CoordinatePair(x, y - 1);
        if (inBounds(down))
            neighbours.add(getByCoords(down));
        return neighbours;
    }

    Node getByCoords(CoordinatePair coords) {
        if (!inBounds(coords)) {
            throw new IllegalArgumentException(String.format("Coordinates %s were out of bounds", coords));
        }
        return grid().get((height() - 1) - coords.y()).get(coords.x());
    }

    boolean inBounds(CoordinatePair coords) {
        int x = coords.x();
        int y = coords.y();
        return (x >= 0 && y >= 0 && x < width() && y < height());
    }

    Set<Node> trailheads() {
        return grid().stream()
                .flatMap(row -> row.stream()
                        .filter(Node::isTrailhead))
                .collect(Collectors.toSet());
    }

    public List<List<Node>> grid() {
        return grid;
    }

    private int height() {
        return grid().size();
    }

    private int width() {
        return grid().getFirst().size();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TopographicMap that = (TopographicMap) o;
        return Objects.equals(grid, that.grid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(grid);
    }
}
