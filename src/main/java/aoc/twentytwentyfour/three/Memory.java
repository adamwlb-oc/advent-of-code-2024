package aoc.twentytwentyfour.three;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Memory {

    private final String memory;

    public Memory(File input) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.memory = sb.toString();
    }

    public String asString() {
        return memory;
    }
}
