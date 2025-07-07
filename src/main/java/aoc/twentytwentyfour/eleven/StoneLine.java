package aoc.twentytwentyfour.eleven;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class StoneLine {

    private Map<Stone, Long> stones;

    public StoneLine(List<Stone> stones) {
        Map<Stone, Long> stoneMap = new HashMap<>();
        for (Stone stone : stones) {
            if (stoneMap.containsKey(stone)) {
                stoneMap.put(stone, stoneMap.get(stone) + 1);
            } else {
                stoneMap.put(stone, 1L);
            }
        }
        this.stones = stoneMap;
    }

    public void blink() {
        Map<Stone, Long> newStoneMap = new HashMap<>();
        for (Map.Entry<Stone, Long> entry : stones.entrySet()) {
            Stone key = entry.getKey();
            List<Stone> result = key.blink();
            for (Stone stone : result) {
                if (newStoneMap.containsKey(stone)) {
                    newStoneMap.put(stone, newStoneMap.get(stone) + entry.getValue());
                } else {
                    newStoneMap.put(stone, entry.getValue());
                }
            }
        }
        stones = newStoneMap;
    }

    public long countStones() {
        return stones.values()
                .stream()
                .mapToLong(l -> l)
                .sum();
    }

    public Map<Stone, Long> stones() {
        return stones;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (StoneLine) obj;
        return Objects.equals(this.stones, that.stones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stones);
    }

    @Override
    public String toString() {
        return "StoneLine[" +
                "stones=" + stones + ']';
    }

}
