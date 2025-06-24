package aoc.twentytwentyfour.one;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class LocationIdLists {
    private final List<Integer> leftList;
    private final List<Integer> rightList;

    public LocationIdLists(File input) {
        this.leftList = new ArrayList<>();
        this.rightList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line;
            while ((line = br.readLine()) != null) {
                String[] entries = line.split("\s+");
                leftList.add(Integer.valueOf(entries[0]));
                rightList.add(Integer.valueOf(entries[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> getLeftList() {
        return this.leftList;
    }

    public List<Integer> getRightList() {
        return this.rightList;
    }
}
