package aoc.twentytwentyfour.nine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static aoc.twentytwentyfour.nine.Block.emptyBlock;
import static aoc.twentytwentyfour.nine.Block.fileBlock;
import static java.util.function.Predicate.not;

public record DiskMap(List<Block> blocks) {

    public DiskMap(List<Block> blocks) {
        this.blocks = new ArrayList<>(blocks);
    }

    public void fragment() {
        while (canFragment()) {
            moveBlock();
        }
    }


    public void defrag() {
        blocks.reversed().stream()
                .filter(not(Block::isEmpty))
                .map(b -> b.id().get())
                .forEach(this::moveFile);
    }

    public long checksum() {
        long checksum = 0;
        for (int i = 0; i < blocks.size(); i++) {
            Block current = blocks.get(i);
            if (!current.isEmpty()) {
                checksum += (long) i * current.id().get();
            }
        }
        return checksum;
    }

    void moveBlock() {
        if (!canFragment()) {
            return;
        }
        int nextFileBlockIndex = nextFileBlockIndex();
        Block blockToMove = blocks.get(nextFileBlockIndex);

        blocks.set(nextEmptyBlockIndex(), blockToMove);
        blocks.set(nextFileBlockIndex, emptyBlock());
    }

    int nextEmptyBlockIndex() {
        return IntStream.range(0, blocks.size())
                .filter(i -> blocks.get(i).isEmpty())
                .findFirst()
                .orElse(-1);
    }

    int nextFileBlockIndex() {
        return IntStream.range(0, blocks.size())
                .filter(i -> !blocks.get((blocks.size() - 1) - i).isEmpty())
                .map(i -> (blocks.size() - 1) - i)
                .findFirst()
                .orElse(-1);
    }

    boolean canFragment() {
        int lastEmpty = Integer.MAX_VALUE;
        int lastData = Integer.MIN_VALUE;

        for (int i = 0; i < blocks.size(); i++) {
            Block current = blocks.get(i);
            if (current.isEmpty()) {
                lastEmpty = i;
            } else {
                lastData = i;
            }
            if (lastEmpty < lastData) {
                return true;
            }
        }
        return false;
    }

    void moveFile(int id) {
        Optional<BlockFileToMove> fileToMoveOptional = fileIndexAndLengthToMove(id);
        if (fileToMoveOptional.isEmpty()) {
            return;
        }
        BlockFileToMove fileToMove = fileToMoveOptional.get();
        Optional<Integer> freeSpaceOptional = freeSpaceIndexForFile(fileToMove);
        if (freeSpaceOptional.isEmpty()) {
            return;
        }
        int freeSpace = freeSpaceOptional.get();
        for (int i = freeSpace; i < freeSpace + fileToMove.length(); i++) {
            blocks.set(i, fileBlock(fileToMove.id()));
        }
        for (int i = fileToMove.startIndex(); i < fileToMove.startIndex() + fileToMove.length(); i++) {
            blocks.set(i, emptyBlock());
        }
    }

    Optional<Integer> freeSpaceIndexForFile(BlockFileToMove fileToMove) {
        int candidate = 1;
        int contiguousBlocks = fileToMove.length();
        for (int i = 0; i < fileToMove.startIndex(); i++) {
            if (blocks.get(i).isEmpty()) {
                contiguousBlocks--;
                if (contiguousBlocks == 0) {
                    return Optional.of(candidate);
                }
            } else {
                candidate = i + 1;
                contiguousBlocks = fileToMove.length();
            }
        }
        return Optional.empty();
    }

    Optional<BlockFileToMove> fileIndexAndLengthToMove(int id) {
        int lastIndex = blocks.size() - 1;
        int length = 1;
        for (int i = lastIndex; i > 0; i--) {
            Block current = blocks.get(i);
            if (current.isEmpty() || !current.id().get().equals(id)) {
                continue;
            }
            Block next = blocks.get(i - 1);
            if (next.isEmpty() || !next.id().get().equals(id)) {
                return Optional.of(new BlockFileToMove(id, i, length));
            }
            length++;
        }
        return Optional.empty();
    }
}
