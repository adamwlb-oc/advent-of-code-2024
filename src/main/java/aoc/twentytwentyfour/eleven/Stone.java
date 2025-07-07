package aoc.twentytwentyfour.eleven;

import java.util.List;

public record Stone(long number) {

    public List<Stone> blink() {
        if (number == 0) {
            return List.of(new Stone(1));
        }
        if (isEvenNumberDigits()) {
            return List.of(
                    new Stone(getLeftDigits()),
                    new Stone(getRightDigits())
            );
        }
        return List.of(new Stone(number * 2024));
    }

    boolean isEvenNumberDigits() {
        if (number == 0) {
            return false;
        }
        return ((int) (Math.log10(number) + 1)) % 2 == 0;
    }

    long getLeftDigits() {
        String value = String.valueOf(number);
        return Long.parseLong(value.substring(0, value.length() / 2));
    }

    long getRightDigits() {
        String value = String.valueOf(number);
        return Long.parseLong(value.substring(value.length() / 2));
    }

    @Override
    public String toString() {
        return "" + number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Stone) obj;
        return this.number == that.number;
    }

}
