package aoc.twentytwentyfour.three;

import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mul {

    private static final String MUL_OPERAND_REGEX = "mul\\((\\d+),\\s*(\\d+)\\)";

    private final int opOne;
    private final int opTwo;

    public Mul(String instruction) {
        Pattern pattern = Pattern.compile(MUL_OPERAND_REGEX);
        Matcher matcher = pattern.matcher(instruction);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid mul instruction " + instruction);
        }

        MatchResult result = matcher.toMatchResult();

        opOne = Integer.parseInt(result.group(1));
        opTwo = Integer.parseInt(result.group(2));
    }

    public int evaluate() {
        return opOne * opTwo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Mul mul = (Mul) o;
        return opOne == mul.opOne && opTwo == mul.opTwo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(opOne, opTwo);
    }

    @Override
    public String toString() {
        return "mul(" +
                opOne +
                "," +
                opTwo +
                ")";
    }
}
