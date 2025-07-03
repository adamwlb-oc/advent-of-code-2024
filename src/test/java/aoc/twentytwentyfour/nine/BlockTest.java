package aoc.twentytwentyfour.nine;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static aoc.twentytwentyfour.nine.Block.emptyBlock;
import static aoc.twentytwentyfour.nine.Block.fileBlock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlockTest {

    @Test
    void createBlockWithFileData() {
        int id = 1;
        Block fileBlock = fileBlock(id);
        assertTrue(fileBlock.id().isPresent());
        assertEquals(id, fileBlock.id().get());
    }

    @Test
    void createEmptySpaceBlock() {
        Block emptyBlock = emptyBlock();
        assertEquals(Optional.empty(), emptyBlock.id());
    }

    @Test
    void testToString() {
        assertEquals("1", fileBlock(1).toString());
        assertEquals(".", emptyBlock().toString());
    }

    @Test
    void identifiesIfABlockIsEmptyOrContainsFileData() {
        assertFalse(fileBlock(1).isEmpty());
        assertTrue(emptyBlock().isEmpty());
    }

}