package aoc.twentytwentyfour.eleven;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StoneLineTest {

    @Test
    void updatesStonesAfterBlink() {
        StoneLine stones = new StoneLine(List.of(
                new Stone(0),
                new Stone(1),
                new Stone(10),
                new Stone(99),
                new Stone(999)
        ));

        Map<Stone, Long> expected = Map.of(
                new Stone(1), 2L,
                new Stone(2024), 1L,
                new Stone(0), 1L,
                new Stone(9), 2L,
                new Stone(2021976), 1L
        );

        stones.blink();
        assertEquals(expected, stones.stones());
    }

    @Test
    void storeStoneCount() {
        StoneLine stones = new StoneLine(List.of(
                new Stone(0),
                new Stone(1),
                new Stone(10),
                new Stone(99),
                new Stone(999)
        ));

        Map<Stone, Long> hashMap = Map.of(
                new Stone(0), 1L,
                new Stone(1), 1L,
                new Stone(10), 1L,
                new Stone(99), 1L,
                new Stone(999), 1L
        );

        assertEquals(hashMap, stones.stones());
    }
}