package aoc.twentytwentyfour.five;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderingRuleTest {

    @Test
    public void validToString() {
        assertEquals("47|53", new OrderingRule(47, 53).toString());
    }

}