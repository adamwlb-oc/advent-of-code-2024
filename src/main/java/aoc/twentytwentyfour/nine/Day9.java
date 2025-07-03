package aoc.twentytwentyfour.nine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static aoc.twentytwentyfour.nine.Block.emptyBlock;
import static aoc.twentytwentyfour.nine.Block.fileBlock;

public final class Day9 {

    private final DiskMap diskMap;

    public Day9(File input) {
        this.diskMap = parseInput(input);
    }

    static DiskMap parseInput(File input) {
        List<Block> blocks = new ArrayList<>();
        int nextId = 0;
        boolean isFile = true;

        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String map = br.readLine();
            for (char c : map.toCharArray()) {
                int digit = Integer.parseInt(String.valueOf(c));
                if (isFile) {
                    for (int i = 0; i < digit; i++) {
                        blocks.add(fileBlock(nextId));
                    }
                    nextId++;
                } else {
                    for (int i = 0; i < digit; i++) {
                        blocks.add(emptyBlock());
                    }
                }
                isFile = !isFile;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new DiskMap(blocks);
    }

    public long part1() {
        diskMap.fragment();
        return diskMap.checksum();
    }

    public long part2() {
        diskMap.defrag();
        return diskMap.checksum();
    }
}
