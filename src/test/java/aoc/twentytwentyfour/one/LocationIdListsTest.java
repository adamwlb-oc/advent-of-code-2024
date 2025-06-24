package aoc.twentytwentyfour.one;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationIdListsTest {

    @Test
    public void canParseInputFile() {
        File sampleInput = new File("src/test/resources/sampleInput.txt");

        LocationIdLists result = new LocationIdLists(sampleInput);

        assertEquals(List.of(3, 4, 2, 1, 3, 3), result.getLeftList());
        assertEquals(List.of(4, 3, 5, 3, 9, 3), result.getRightList());
    }
}