package aoc.twentytwentyfour.four;

import aoc.twentytwentyfour.common.CoordinatePair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordsearchMatchTest {

    @Test
    void validToString() {
        assertEquals("XMAS - Starting at (1, 2), moving RIGHT", new WordsearchMatch("XMAS", new CoordinatePair(1, 2), SearchDirection.RIGHT).toString());
    }

}