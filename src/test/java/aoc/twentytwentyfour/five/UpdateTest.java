package aoc.twentytwentyfour.five;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateTest {

    private Update underTest;

    @BeforeEach
    void setup() {
        underTest = new Update(List.of(75, 47, 61, 53, 29));
    }

    @Test
    void validToString() {
        assertEquals("75,47,61,53,29", underTest.toString());
    }

    @Test
    public void findsMiddlePageNumber() {
        assertEquals(61, underTest.getMiddlePage());
    }
}