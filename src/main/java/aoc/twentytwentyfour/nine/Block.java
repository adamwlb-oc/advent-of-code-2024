package aoc.twentytwentyfour.nine;

import java.util.Objects;
import java.util.Optional;

public final class Block {

    private static final int EMPTY_BLOCK_MARKER = -1;
    private final int id;

    private Block(int id) {
        this.id = id;
    }

    public static Block fileBlock(int id) {
        return new Block(id);
    }

    public static Block emptyBlock() {
        return new Block(EMPTY_BLOCK_MARKER);
    }

    public Optional<Integer> id() {
        if (id == EMPTY_BLOCK_MARKER) {
            return Optional.empty();
        }
        return Optional.of(id);
    }

    public boolean isEmpty() {
        return id == EMPTY_BLOCK_MARKER;
    }

    @Override
    public String toString() {
        if (id == EMPTY_BLOCK_MARKER) {
            return ".";
        }
        return String.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return id == block.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
