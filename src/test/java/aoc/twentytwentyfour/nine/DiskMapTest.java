package aoc.twentytwentyfour.nine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static aoc.twentytwentyfour.nine.Block.emptyBlock;
import static aoc.twentytwentyfour.nine.Block.fileBlock;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiskMapTest {

    private static Stream<Arguments> getDiskMapsWithBlockFileToMove() {
        return Stream.of(
                Arguments.of(new DiskMap(List.of(
                        fileBlock(0), fileBlock(0),
                        emptyBlock(), emptyBlock(),
                        fileBlock(1), fileBlock(1),
                        emptyBlock(), emptyBlock(), emptyBlock(),
                        fileBlock(2), fileBlock(2), fileBlock(2)
                )), Optional.of(new BlockFileToMove(2, 9, 3)), 2),
                Arguments.of(new DiskMap(List.of(
                        fileBlock(0), fileBlock(0),
                        emptyBlock(), emptyBlock(),
                        fileBlock(1), fileBlock(1),
                        emptyBlock(), emptyBlock(), emptyBlock()
                )), Optional.of(new BlockFileToMove(1, 4, 2)), 1),
                Arguments.of(new DiskMap(List.of(
                        fileBlock(0), fileBlock(0),
                        emptyBlock(), emptyBlock(),
                        fileBlock(1),
                        emptyBlock(), emptyBlock(), emptyBlock()
                )), Optional.of(new BlockFileToMove(1, 4, 1)), 1),
                Arguments.of(new DiskMap(List.of(
                        emptyBlock(), emptyBlock(), emptyBlock()
                )), Optional.empty(), 1),
                Arguments.of(new DiskMap(List.of(
                        fileBlock(0), fileBlock(0), fileBlock(0),
                        fileBlock(1), fileBlock(1)
                )), Optional.of(new BlockFileToMove(1, 3, 2)), 1)
        );
    }

    private static Stream<Arguments> getMapsWithNextEmptyBlock() {
        return Stream.of(
                Arguments.of(new DiskMap(List.of(fileBlock(0), fileBlock(0), emptyBlock())), 2),
                Arguments.of(new DiskMap(List.of(emptyBlock(), fileBlock(0), emptyBlock())), 0),
                Arguments.of(new DiskMap(List.of(fileBlock(0), fileBlock(1), fileBlock(2))), -1),
                Arguments.of(new DiskMap(List.of(fileBlock(0), emptyBlock(), fileBlock(2), emptyBlock(), fileBlock(4), fileBlock(5))), 1)
        );
    }

    private static Stream<Arguments> getMapsWithNextFileBlock() {
        return Stream.of(
                Arguments.of(new DiskMap(List.of(fileBlock(0), emptyBlock(), fileBlock(2), emptyBlock(), fileBlock(4), fileBlock(5))), 5),
                Arguments.of(new DiskMap(List.of(emptyBlock(), fileBlock(0), emptyBlock(), fileBlock(1))), 3),
                Arguments.of(new DiskMap(List.of(fileBlock(0), emptyBlock(), emptyBlock(), emptyBlock())), 0)
        );
    }

    private static Stream<Arguments> getDiskMapsForFragment() {
        return Stream.of(
                Arguments.of(new DiskMap(List.of(fileBlock(0), fileBlock(4), fileBlock(2), emptyBlock(), emptyBlock())), false),
                Arguments.of(new DiskMap(List.of(fileBlock(0), emptyBlock(), fileBlock(2), emptyBlock(), fileBlock(4))), true),
                Arguments.of(new DiskMap(List.of(emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock())), false),
                Arguments.of(new DiskMap(List.of(fileBlock(2), fileBlock(12), fileBlock(7), fileBlock(3))), false)
        );
    }

    @ParameterizedTest
    @MethodSource("getMapsWithNextEmptyBlock")
    void findsNextEmptyBlockIndex(DiskMap diskMap, int index) {
        assertEquals(index, diskMap.nextEmptyBlockIndex());
    }

    @ParameterizedTest
    @MethodSource("getMapsWithNextFileBlock")
    void findsNextFileBlockIndexToMove(DiskMap diskMap, int index) {
        assertEquals(index, diskMap.nextFileBlockIndex());
    }

    @ParameterizedTest
    @MethodSource("getDiskMapsForFragment")
    void checksThereArePotentialMoves(DiskMap diskMap, boolean canDefrag) {
        assertEquals(canDefrag, diskMap.canFragment());
    }

    @Test
    void movesBlocksOneAtATime() {
        DiskMap map = new DiskMap(List.of(fileBlock(0), emptyBlock(), fileBlock(2), emptyBlock(), fileBlock(4), fileBlock(5)));
        map.moveBlock();
        assertEquals(List.of(fileBlock(0), fileBlock(5), fileBlock(2), emptyBlock(), fileBlock(4), emptyBlock()), map.blocks());
        map.moveBlock();
        assertEquals(List.of(fileBlock(0), fileBlock(5), fileBlock(2), fileBlock(4), emptyBlock(), emptyBlock()), map.blocks());
    }

    @Test
    void canFragment() {
        DiskMap mapToDefrag = new DiskMap(List.of(
                fileBlock(0),
                emptyBlock(), emptyBlock(),
                fileBlock(1), fileBlock(1), fileBlock(1),
                emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(2), fileBlock(2), fileBlock(2), fileBlock(2), fileBlock(2)
        ));

        DiskMap defragged = new DiskMap(List.of(
                fileBlock(0),
                fileBlock(2), fileBlock(2),
                fileBlock(1), fileBlock(1), fileBlock(1),
                fileBlock(2), fileBlock(2), fileBlock(2),
                emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock()
        ));

        mapToDefrag.fragment();
        assertEquals(defragged, mapToDefrag);
    }

    @Test
    void calculatesChecksum() {
        DiskMap map = new DiskMap(List.of(
                fileBlock(0), fileBlock(0),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(1), fileBlock(1), fileBlock(1),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(2),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(3), fileBlock(3), fileBlock(3),
                emptyBlock(),
                fileBlock(4), fileBlock(4),
                emptyBlock(),
                fileBlock(5), fileBlock(5), fileBlock(5), fileBlock(5),
                emptyBlock(),
                fileBlock(6), fileBlock(6), fileBlock(6), fileBlock(6),
                emptyBlock(),
                fileBlock(7), fileBlock(7), fileBlock(7),
                emptyBlock(),
                fileBlock(8), fileBlock(8), fileBlock(8), fileBlock(8),
                fileBlock(9), fileBlock(9)
        ));

        map.fragment();
        assertEquals(1928, map.checksum());
    }

    @Test
    void getStartIndexOfFreeSpaceBlockForFile() {
        DiskMap map = new DiskMap(List.of(
                fileBlock(0), fileBlock(0),
                emptyBlock(), emptyBlock(),
                fileBlock(1), fileBlock(1),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(2), fileBlock(2), fileBlock(2)
        ));

        assertEquals(Optional.of(2), map.freeSpaceIndexForFile(new BlockFileToMove(1, 4, 2)));
        assertEquals(Optional.of(6), map.freeSpaceIndexForFile(new BlockFileToMove(2, 9, 3)));
        assertEquals(Optional.empty(), map.freeSpaceIndexForFile(new BlockFileToMove(3, 11, 4)));
    }

    @ParameterizedTest
    @MethodSource("getDiskMapsWithBlockFileToMove")
    void getWholeFileInfoNextToMove(DiskMap diskMap, Optional<BlockFileToMove> blockFileToMove, int id) {
        assertEquals(blockFileToMove, diskMap.fileIndexAndLengthToMove(id));
    }

    @Test
    void movesFilesOneAtATime() {
        DiskMap map = new DiskMap(List.of(
                fileBlock(0), fileBlock(0),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(1), fileBlock(1), fileBlock(1),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(2),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(3), fileBlock(3), fileBlock(3),
                emptyBlock(),
                fileBlock(4), fileBlock(4),
                emptyBlock(),
                fileBlock(5), fileBlock(5), fileBlock(5), fileBlock(5),
                emptyBlock(),
                fileBlock(6), fileBlock(6), fileBlock(6), fileBlock(6),
                emptyBlock(),
                fileBlock(7), fileBlock(7), fileBlock(7),
                emptyBlock(),
                fileBlock(8), fileBlock(8), fileBlock(8), fileBlock(8),
                fileBlock(9), fileBlock(9)
        ));

        map.moveFile(9);

        assertEquals(List.of(
                fileBlock(0), fileBlock(0),
                fileBlock(9), fileBlock(9),
                emptyBlock(),
                fileBlock(1), fileBlock(1), fileBlock(1),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(2),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(3), fileBlock(3), fileBlock(3),
                emptyBlock(),
                fileBlock(4), fileBlock(4),
                emptyBlock(),
                fileBlock(5), fileBlock(5), fileBlock(5), fileBlock(5),
                emptyBlock(),
                fileBlock(6), fileBlock(6), fileBlock(6), fileBlock(6),
                emptyBlock(),
                fileBlock(7), fileBlock(7), fileBlock(7),
                emptyBlock(),
                fileBlock(8), fileBlock(8), fileBlock(8), fileBlock(8),
                emptyBlock(), emptyBlock()
        ), map.blocks());

        map.moveFile(8);

        assertEquals(List.of(
                fileBlock(0), fileBlock(0),
                fileBlock(9), fileBlock(9),
                emptyBlock(),
                fileBlock(1), fileBlock(1), fileBlock(1),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(2),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(3), fileBlock(3), fileBlock(3),
                emptyBlock(),
                fileBlock(4), fileBlock(4),
                emptyBlock(),
                fileBlock(5), fileBlock(5), fileBlock(5), fileBlock(5),
                emptyBlock(),
                fileBlock(6), fileBlock(6), fileBlock(6), fileBlock(6),
                emptyBlock(),
                fileBlock(7), fileBlock(7), fileBlock(7),
                emptyBlock(),
                fileBlock(8), fileBlock(8), fileBlock(8), fileBlock(8),
                emptyBlock(), emptyBlock()
        ), map.blocks());

        map.moveFile(7);

        assertEquals(List.of(
                fileBlock(0), fileBlock(0),
                fileBlock(9), fileBlock(9),
                emptyBlock(),
                fileBlock(1), fileBlock(1), fileBlock(1),
                fileBlock(7), fileBlock(7), fileBlock(7),
                fileBlock(2),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(3), fileBlock(3), fileBlock(3),
                emptyBlock(),
                fileBlock(4), fileBlock(4),
                emptyBlock(),
                fileBlock(5), fileBlock(5), fileBlock(5), fileBlock(5),
                emptyBlock(),
                fileBlock(6), fileBlock(6), fileBlock(6), fileBlock(6),
                emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(8), fileBlock(8), fileBlock(8), fileBlock(8),
                emptyBlock(), emptyBlock()
        ), map.blocks());

        map.moveFile(6);

        assertEquals(List.of(
                fileBlock(0), fileBlock(0),
                fileBlock(9), fileBlock(9),
                emptyBlock(),
                fileBlock(1), fileBlock(1), fileBlock(1),
                fileBlock(7), fileBlock(7), fileBlock(7),
                fileBlock(2),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(3), fileBlock(3), fileBlock(3),
                emptyBlock(),
                fileBlock(4), fileBlock(4),
                emptyBlock(),
                fileBlock(5), fileBlock(5), fileBlock(5), fileBlock(5),
                emptyBlock(),
                fileBlock(6), fileBlock(6), fileBlock(6), fileBlock(6),
                emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(8), fileBlock(8), fileBlock(8), fileBlock(8),
                emptyBlock(), emptyBlock()
        ), map.blocks());

        map.moveFile(5);

        assertEquals(List.of(
                fileBlock(0), fileBlock(0),
                fileBlock(9), fileBlock(9),
                emptyBlock(),
                fileBlock(1), fileBlock(1), fileBlock(1),
                fileBlock(7), fileBlock(7), fileBlock(7),
                fileBlock(2),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(3), fileBlock(3), fileBlock(3),
                emptyBlock(),
                fileBlock(4), fileBlock(4),
                emptyBlock(),
                fileBlock(5), fileBlock(5), fileBlock(5), fileBlock(5),
                emptyBlock(),
                fileBlock(6), fileBlock(6), fileBlock(6), fileBlock(6),
                emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(8), fileBlock(8), fileBlock(8), fileBlock(8),
                emptyBlock(), emptyBlock()
        ), map.blocks());

        map.moveFile(4);

        assertEquals(List.of(
                fileBlock(0), fileBlock(0),
                fileBlock(9), fileBlock(9),
                emptyBlock(),
                fileBlock(1), fileBlock(1), fileBlock(1),
                fileBlock(7), fileBlock(7), fileBlock(7),
                fileBlock(2),
                fileBlock(4), fileBlock(4),
                emptyBlock(),
                fileBlock(3), fileBlock(3), fileBlock(3),
                emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(5), fileBlock(5), fileBlock(5), fileBlock(5),
                emptyBlock(),
                fileBlock(6), fileBlock(6), fileBlock(6), fileBlock(6),
                emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(8), fileBlock(8), fileBlock(8), fileBlock(8),
                emptyBlock(), emptyBlock()
        ), map.blocks());

        map.moveFile(3);

        assertEquals(List.of(
                fileBlock(0), fileBlock(0),
                fileBlock(9), fileBlock(9),
                emptyBlock(),
                fileBlock(1), fileBlock(1), fileBlock(1),
                fileBlock(7), fileBlock(7), fileBlock(7),
                fileBlock(2),
                fileBlock(4), fileBlock(4),
                emptyBlock(),
                fileBlock(3), fileBlock(3), fileBlock(3),
                emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(5), fileBlock(5), fileBlock(5), fileBlock(5),
                emptyBlock(),
                fileBlock(6), fileBlock(6), fileBlock(6), fileBlock(6),
                emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(8), fileBlock(8), fileBlock(8), fileBlock(8),
                emptyBlock(), emptyBlock()
        ), map.blocks());

        map.moveFile(2);

        List<Block> finalMap = List.of(
                fileBlock(0), fileBlock(0),
                fileBlock(9), fileBlock(9),
                fileBlock(2),
                fileBlock(1), fileBlock(1), fileBlock(1),
                fileBlock(7), fileBlock(7), fileBlock(7),
                emptyBlock(),
                fileBlock(4), fileBlock(4),
                emptyBlock(),
                fileBlock(3), fileBlock(3), fileBlock(3),
                emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(5), fileBlock(5), fileBlock(5), fileBlock(5),
                emptyBlock(),
                fileBlock(6), fileBlock(6), fileBlock(6), fileBlock(6),
                emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(8), fileBlock(8), fileBlock(8), fileBlock(8),
                emptyBlock(), emptyBlock()
        );

        assertEquals(finalMap, map.blocks());

        map.moveFile(2);
        assertEquals(finalMap, map.blocks());
        map.moveFile(1);
        assertEquals(finalMap, map.blocks());
        map.moveFile(0);
        assertEquals(finalMap, map.blocks());
    }

    @Test
    void canDefrag() {
        DiskMap map = new DiskMap(List.of(
                fileBlock(0), fileBlock(0),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(1), fileBlock(1), fileBlock(1),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(2),
                emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(3), fileBlock(3), fileBlock(3),
                emptyBlock(),
                fileBlock(4), fileBlock(4),
                emptyBlock(),
                fileBlock(5), fileBlock(5), fileBlock(5), fileBlock(5),
                emptyBlock(),
                fileBlock(6), fileBlock(6), fileBlock(6), fileBlock(6),
                emptyBlock(),
                fileBlock(7), fileBlock(7), fileBlock(7),
                emptyBlock(),
                fileBlock(8), fileBlock(8), fileBlock(8), fileBlock(8),
                fileBlock(9), fileBlock(9)
        ));
        List<Block> defraggedMap = List.of(
                fileBlock(0), fileBlock(0),
                fileBlock(9), fileBlock(9),
                fileBlock(2),
                fileBlock(1), fileBlock(1), fileBlock(1),
                fileBlock(7), fileBlock(7), fileBlock(7),
                emptyBlock(),
                fileBlock(4), fileBlock(4),
                emptyBlock(),
                fileBlock(3), fileBlock(3), fileBlock(3),
                emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(5), fileBlock(5), fileBlock(5), fileBlock(5),
                emptyBlock(),
                fileBlock(6), fileBlock(6), fileBlock(6), fileBlock(6),
                emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(), emptyBlock(),
                fileBlock(8), fileBlock(8), fileBlock(8), fileBlock(8),
                emptyBlock(), emptyBlock()
        );

        map.defrag();
        assertEquals(defraggedMap, map.blocks());
    }
}