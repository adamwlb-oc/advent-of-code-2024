package aoc.twentytwentyfour.eight;

import aoc.twentytwentyfour.common.CoordinatePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrequencyMapTest {

    private FrequencyMap underTest;

    private static Stream<Arguments> getInvalidCoords() {
        return Stream.of(
                Arguments.of(new CoordinatePair(21, 3)),
                Arguments.of(new CoordinatePair(4, 13))
        );
    }

    private static Stream<Arguments> getAntennaPairs() {
        return Stream.of(
                Arguments.of(
                        new Antenna(5, 4, 'a'),
                        new Antenna(4, 6, 'a'),
                        new CoordinatePair(3, 8),
                        new CoordinatePair(6, 2)),
                Arguments.of(
                        new Antenna(5, 4, 'a'),
                        new Antenna(8, 5, 'a'),
                        new CoordinatePair(2, 3),
                        new CoordinatePair(11, 6)),
                Arguments.of(
                        new Antenna(4, 6, 'a'),
                        new Antenna(8, 5, 'a'),
                        new CoordinatePair(0, 7),
                        new CoordinatePair(12, 4))
        );
    }

    @BeforeEach
    void setup() {
        underTest = new FrequencyMap(List.of(
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '0', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '0', '.', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '0', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '0', '.', '.', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', 'A', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', 'A', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', 'A', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'),
                List.of('.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.')
        ));
    }

    @Test
    void getsSpecificCharByCoordinates() {
        assertEquals('A', underTest.getByCoords(new CoordinatePair(6, 6)));
        assertEquals('0', underTest.getByCoords(new CoordinatePair(4, 7)));
        assertEquals('.', underTest.getByCoords(new CoordinatePair(11, 11)));
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

    @Test
    void getsAllAntennas() {
        Set<Antenna> expected = Set.of(
                new Antenna(9, 2, 'A'),
                new Antenna(8, 3, 'A'),
                new Antenna(4, 7, '0'),
                new Antenna(6, 6, 'A'),
                new Antenna(7, 8, '0'),
                new Antenna(5, 9, '0'),
                new Antenna(8, 10, '0')
        );
        Set<Antenna> actual = underTest.antennas();
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test
    void getsAntennasWithSameFrequencyAsAGivenAntenna() {
        char frequency = 'A';
        Antenna searchAntenna = new Antenna(9, 2, frequency);
        Set<Antenna> expected = Set.of(
                new Antenna(8, 3, frequency),
                new Antenna(6, 6, frequency)
        );
        Set<Antenna> actual = underTest.getAntennasWithSameFrequency(searchAntenna);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("getAntennaPairs")
    void calculateAntinodeCoordsForAntennaPair(Antenna antenna1, Antenna antenna2, CoordinatePair antinode1, CoordinatePair antinode2) {
        assertEquals(Set.of(antinode1, antinode2), FrequencyMap.antinodeCoordsOf(antenna1, antenna2));
    }

    @Test
    void antennasWithDifferentFrequencyGenerateNoAntinodes() {
        assertEquals(Set.of(), FrequencyMap.antinodeCoordsOf(new Antenna(5, 4, 'a'), new Antenna(4, 6, 'b')));
    }

    @Test
    void getsAllAntinodesInMap() {
        Set<CoordinatePair> expected = Set.of(
                new CoordinatePair(0, 4),
                new CoordinatePair(1, 6),
                new CoordinatePair(2, 8),
                new CoordinatePair(3, 5),
                new CoordinatePair(3, 10),
                new CoordinatePair(4, 9),
                new CoordinatePair(6, 6),
                new CoordinatePair(6, 11),
                new CoordinatePair(7, 4),
                new CoordinatePair(9, 7),
                new CoordinatePair(10, 0),
                new CoordinatePair(10, 1),
                new CoordinatePair(10, 9),
                new CoordinatePair(11, 11)
        );

        assertEquals(expected, underTest.antinodes());
    }

    @Test
    void resonantHarmonicAntinodesIncludeAntennaLocations() {
        Antenna antenna1 = new Antenna(9, 2, 'A');
        Antenna antenna2 = new Antenna(8, 3, 'A');
        Set<CoordinatePair> actual = underTest.resonantHarmonicsAntinodeCoordsOf(antenna1, antenna2);
        assertTrue(actual.contains(antenna1.location()));
        assertTrue(actual.contains(antenna2.location()));
    }

    @Test
    void antennaWithNoMatchingFrequencyHasNoAntinodes() {
        Antenna antenna1 = new Antenna(9, 2, 'X');
        Antenna antenna2 = new Antenna(8, 3, 'A');
        assertEquals(Set.of(), underTest.resonantHarmonicsAntinodeCoordsOf(antenna1, antenna2));
    }

    @Test
    void getsAllAntinodeLocationsWithResonantHarmonics() {
        Antenna antenna1 = new Antenna(9, 2, 'A');
        Antenna antenna2 = new Antenna(8, 3, 'A');
        Antenna antenna3 = new Antenna(6, 6, 'A');

        Set<CoordinatePair> expected = Set.of(
                antenna1.location(),
                antenna2.location(),
                antenna3.location(),
                new CoordinatePair(11, 0),
                new CoordinatePair(10, 1),
                new CoordinatePair(10, 0),
                new CoordinatePair(7, 4),
                new CoordinatePair(6, 5),
                new CoordinatePair(5, 6),
                new CoordinatePair(4, 7),
                new CoordinatePair(4, 9),
                new CoordinatePair(3, 10),
                new CoordinatePair(3, 8),
                new CoordinatePair(2, 9),
                new CoordinatePair(1, 10),
                new CoordinatePair(0, 11)
        );

        Set<CoordinatePair> actual = underTest.resonantHarmonicsAntinodeCoordsOf(antenna1, antenna2);
        actual.addAll(underTest.resonantHarmonicsAntinodeCoordsOf(antenna2, antenna3));
        actual.addAll(underTest.resonantHarmonicsAntinodeCoordsOf(antenna1, antenna3));

        assertEquals(expected, actual);
    }

    @Test
    void getsAllAntinodesInMapWithResonantHarmonics() {
        assertEquals(34, underTest.antinodesWithResonantHarmonics().size());
    }
}