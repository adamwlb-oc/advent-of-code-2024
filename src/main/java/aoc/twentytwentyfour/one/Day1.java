package aoc.twentytwentyfour.one;

import java.util.ArrayList;
import java.util.List;

public final class Day1 {

    public int part1(LocationIdLists lists) {
        List<Integer> leftList = lists.getLeftList();
        List<Integer> rightList = lists.getRightList();

        leftList.sort(Integer::compareTo);
        rightList.sort(Integer::compareTo);

        List<Integer> distances = new ArrayList<>();
        for (int i = 0; i < leftList.size(); i++) {
            distances.add(Math.abs(leftList.get(i) - rightList.get(i)));
        }
        return distances.stream().mapToInt(Integer::intValue).sum();
    }

    public int part2(LocationIdLists lists) {
        List<Integer> leftList = lists.getLeftList();
        List<Integer> rightList = lists.getRightList();

        return leftList.stream().map(i -> getSimilarityScore(i, rightList)).mapToInt(Integer::intValue).sum();
    }

    int getSimilarityScore(int base, List<Integer> searchList) {
        long appearances = searchList.stream().filter(i -> i == base).count();

        return (int) (base * appearances);
    }

}
