package aoc.twentytwentyfour.four;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WordsearchTest {

    private static final File SAMPLE_INPUT = new File("src/test/resources/day4sample.txt");

    private Wordsearch underTest;

    @BeforeEach
    void setup() {
        underTest = new Wordsearch(SAMPLE_INPUT);
    }

    @Test
    void canParseInput() {
        List<List<Character>> expected = List.of(List.of('M', 'M', 'M', 'S', 'X', 'X', 'M', 'A', 'S', 'M'),
                List.of('M', 'S', 'A', 'M', 'X', 'M', 'S', 'M', 'S', 'A'),
                List.of('A', 'M', 'X', 'S', 'X', 'M', 'A', 'A', 'M', 'M'),
                List.of('M', 'S', 'A', 'M', 'A', 'S', 'M', 'S', 'M', 'X'),
                List.of('X', 'M', 'A', 'S', 'A', 'M', 'X', 'A', 'M', 'M'),
                List.of('X', 'X', 'A', 'M', 'M', 'X', 'X', 'A', 'M', 'A'),
                List.of('S', 'M', 'S', 'M', 'S', 'A', 'S', 'X', 'S', 'S'),
                List.of('S', 'A', 'X', 'A', 'M', 'A', 'S', 'A', 'A', 'A'),
                List.of('M', 'A', 'M', 'M', 'M', 'X', 'M', 'M', 'M', 'M'),
                List.of('M', 'X', 'M', 'X', 'A', 'X', 'M', 'A', 'S', 'X'));
        assertEquals(expected, underTest.asCharacterGrid());
    }

    @Test
    void getsSpecificCharByCoordinates() {
        assertEquals('X', underTest.getByCoords(new CoordinatePair(1, 0)));
        assertEquals('S', underTest.getByCoords(new CoordinatePair(2, 3)));
        assertEquals('A', underTest.getByCoords(new CoordinatePair(7, 4)));
    }

    @ParameterizedTest
    @MethodSource("getInvalidCoords")
    void illegalCoordsThrowIllegalArgumentException(CoordinatePair coords) {
        Exception error = assertThrows(IllegalArgumentException.class, () -> underTest.getByCoords(coords));
        assertEquals(String.format("Coordinates %s were out of bounds", coords), error.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getInvalidCoords")
    void checksOutOfBoundCoords(CoordinatePair coords) {
        assertFalse(underTest.inBounds(coords));
    }

    private static Stream<Arguments> getInvalidCoords() {
        return Stream.of(
                Arguments.of(new CoordinatePair(10, 3)),
                Arguments.of(new CoordinatePair(4, 12))
        );
    }
}