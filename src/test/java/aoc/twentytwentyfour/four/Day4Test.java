package aoc.twentytwentyfour.four;

import aoc.twentytwentyfour.common.CoordinatePair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static aoc.twentytwentyfour.four.SearchDirection.DOWN;
import static aoc.twentytwentyfour.four.SearchDirection.DOWN_LEFT;
import static aoc.twentytwentyfour.four.SearchDirection.DOWN_RIGHT;
import static aoc.twentytwentyfour.four.SearchDirection.LEFT;
import static aoc.twentytwentyfour.four.SearchDirection.RIGHT;
import static aoc.twentytwentyfour.four.SearchDirection.UP;
import static aoc.twentytwentyfour.four.SearchDirection.UP_LEFT;
import static aoc.twentytwentyfour.four.SearchDirection.UP_RIGHT;
import static aoc.twentytwentyfour.four.SearchDirection.allDirections;
import static aoc.twentytwentyfour.four.SearchDirection.diagonals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day4Test {

    private static final File SAMPLE_INPUT = new File("src/test/resources/day4sample.txt");

    private static Stream<Arguments> getSearchParams() {
        return Stream.of(
                Arguments.of(new CoordinatePair(3, 3), LEFT),
                Arguments.of(new CoordinatePair(3, 3), RIGHT),
                Arguments.of(new CoordinatePair(3, 3), UP),
                Arguments.of(new CoordinatePair(3, 3), DOWN),
                Arguments.of(new CoordinatePair(3, 3), UP_LEFT),
                Arguments.of(new CoordinatePair(3, 3), UP_RIGHT),
                Arguments.of(new CoordinatePair(3, 3), DOWN_LEFT),
                Arguments.of(new CoordinatePair(3, 3), DOWN_RIGHT),
                Arguments.of(new CoordinatePair(6, 0), UP),
                Arguments.of(new CoordinatePair(0, 6), RIGHT),
                Arguments.of(new CoordinatePair(3, 6), DOWN_LEFT)
        );
    }

    private static Stream<Arguments> getDiagonalPairs() {
        String word = "MAS";
        return Stream.of(
                Arguments.of(new CoordinatePair(2, 3), new WordsearchMatch(word, new CoordinatePair(0, 3), DOWN_RIGHT), DOWN_LEFT),
                Arguments.of(new CoordinatePair(0, 1), new WordsearchMatch(word, new CoordinatePair(0, 3), DOWN_RIGHT), UP_RIGHT),
                Arguments.of(new CoordinatePair(0, 3), new WordsearchMatch(word, new CoordinatePair(2, 3), DOWN_LEFT), DOWN_RIGHT),
                Arguments.of(new CoordinatePair(2, 1), new WordsearchMatch(word, new CoordinatePair(2, 3), DOWN_LEFT), UP_LEFT),
                Arguments.of(new CoordinatePair(4, 3), new WordsearchMatch(word, new CoordinatePair(2, 3), DOWN_RIGHT), DOWN_LEFT),
                Arguments.of(new CoordinatePair(2, 1), new WordsearchMatch(word, new CoordinatePair(2, 3), DOWN_RIGHT), UP_RIGHT),
                Arguments.of(new CoordinatePair(2, 3), new WordsearchMatch(word, new CoordinatePair(4, 3), DOWN_LEFT), DOWN_RIGHT),
                Arguments.of(new CoordinatePair(4, 1), new WordsearchMatch(word, new CoordinatePair(4, 3), DOWN_LEFT), UP_LEFT)
        );
    }

    @Test
    void countsAppearancesOfWord() {
        Wordsearch wordsearch = new Wordsearch(List.of(
                List.of('A', 'D', 'A', 'M'),
                List.of('X', 'D', 'X', 'X'),
                List.of('X', 'X', 'A', 'X'),
                List.of('X', 'X', 'X', 'M')
        ));

        assertEquals(2, Day4.getOccurancesOfWord(wordsearch, "ADAM", allDirections()));
    }

    @Test
    void identifiesCandidateStarts() {
        Wordsearch wordsearch = new Wordsearch(List.of(
                List.of('A', 'X', 'X', 'X'),
                List.of('X', 'D', 'X', 'X'),
                List.of('X', 'X', 'A', 'X'),
                List.of('X', 'X', 'X', 'M')
        ));

        assertEquals(List.of(new CoordinatePair(0, 3), new CoordinatePair(2, 1)), Day4.getCandidateStarts(wordsearch, "ADAM"));
    }

    @ParameterizedTest
    @MethodSource("getSearchParams")
    void findsWordsInEightCardinalDirectionsWithEdgeCases(CoordinatePair start, SearchDirection direction) {
        Wordsearch wordsearch = new Wordsearch(List.of(
                List.of('T', 'E', 'S', 'T', 'X', 'X', 'T'),
                List.of('X', 'S', 'E', 'S', 'X', 'S', 'X'),
                List.of('X', 'S', 'E', 'E', 'E', 'X', 'X'),
                List.of('T', 'S', 'E', 'T', 'E', 'S', 'T'),
                List.of('X', 'X', 'E', 'E', 'E', 'X', 'S'),
                List.of('X', 'S', 'X', 'S', 'X', 'S', 'E'),
                List.of('T', 'X', 'X', 'T', 'X', 'X', 'T')
        ));
        String term = "TEST";
        assertTrue(Day4.searchInDirection(wordsearch, term, start, direction));
    }

    @Test
    void sampleInputPart1() {
        Wordsearch sampleInput = new Wordsearch(SAMPLE_INPUT);
        assertEquals(18, new Day4().part1(sampleInput));
    }

    @Test
    void returnDetailedMatchInformationForFoundWords() {
        Wordsearch wordsearch = new Wordsearch(List.of(
                List.of('A', 'D', 'A', 'M'),
                List.of('X', 'D', 'X', 'X'),
                List.of('X', 'X', 'A', 'X'),
                List.of('X', 'X', 'X', 'M')
        ));

        String searchTerm = "ADAM";
        CoordinatePair start = new CoordinatePair(0, 3);
        Set<WordsearchMatch> expected = Set.of(
                new WordsearchMatch(searchTerm, start, RIGHT),
                new WordsearchMatch(searchTerm, start, DOWN_RIGHT));
        assertEquals(expected, Day4.getDetailedMatches(wordsearch, searchTerm, allDirections()));
    }

    @Test
    void countsXMasInstances() {
        Wordsearch wordsearch = new Wordsearch(List.of(
                List.of('M', '.', 'M', '.', 'M'),
                List.of('.', 'A', 'M', 'A', 'M'),
                List.of('S', '.', 'S', 'A', 'S'),
                List.of('.', '.', 'S', '.', 'S')
        ));

        assertEquals(3, new Day4().part2(wordsearch));
    }

    @Test
    void findsMasInstances() {
        Wordsearch wordsearch = new Wordsearch(List.of(
                List.of('M', '.', 'M', '.', 'M'),
                List.of('.', 'A', 'M', 'A', 'M'),
                List.of('S', '.', 'S', 'A', 'S'),
                List.of('.', '.', 'S', '.', 'S')
        ));

        String word = "MAS";
        Set<WordsearchMatch> expected = Set.of(
                new WordsearchMatch(word, new CoordinatePair(0, 3), DOWN_RIGHT),
                new WordsearchMatch(word, new CoordinatePair(2, 3), DOWN_LEFT),
                new WordsearchMatch(word, new CoordinatePair(2, 3), DOWN_RIGHT),
                new WordsearchMatch(word, new CoordinatePair(2, 2), DOWN_RIGHT),
                new WordsearchMatch(word, new CoordinatePair(4, 2), DOWN_LEFT),
                new WordsearchMatch(word, new CoordinatePair(4, 3), DOWN_LEFT)
        );

        assertEquals(expected, Day4.getDetailedMatches(wordsearch, word, diagonals()));
    }

    @Test
    void countsReversedXMasInstances() {
        Wordsearch wordsearch = new Wordsearch(List.of(
                List.of('S', '.', 'M', '.', 'M'),
                List.of('.', 'A', 'M', 'A', 'M'),
                List.of('S', '.', 'M', 'A', 'S'),
                List.of('.', '.', 'S', '.', 'S')
        ));

        assertEquals(2, new Day4().part2(wordsearch));
    }

    @ParameterizedTest
    @MethodSource("getDiagonalPairs")
    void identifiesDiagonalPairStartPoint(CoordinatePair diagonalPairCoords, WordsearchMatch match, SearchDirection direction) {
        assertEquals(diagonalPairCoords, Day4.getDirectionalDiagonalPairStartCoordinates(match, direction));
    }

    @Test
    void sampleInputPart2() {
        Wordsearch sampleInput = new Wordsearch(SAMPLE_INPUT);
        assertEquals(9, new Day4().part2(sampleInput));
    }
}