package aoc.twentytwentyfour.five;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record Update(List<Integer> pageNumbers) {

    @Override
    public String toString() {
        return pageNumbers.stream().
                map(Objects::toString)
                .collect(Collectors.joining(","));
    }

    public int getMiddlePage() {
        return pageNumbers.get(pageNumbers.size() / 2);
    }
}
