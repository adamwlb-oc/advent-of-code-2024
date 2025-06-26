package aoc.twentytwentyfour.five;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrinterInstructionsTest {

    private PrinterInstructions underTest;

    private static Stream<Arguments> getRulesToApplyWithResults() {
        return Stream.of(
                Arguments.of(new OrderingRule(47, 53), true),
                Arguments.of(new OrderingRule(75, 47), true),
                Arguments.of(new OrderingRule(75, 61), true),
                Arguments.of(new OrderingRule(29, 53), false),
                Arguments.of(new OrderingRule(1, 2), true)
        );
    }

    private static Stream<Arguments> getIncorrectUpdatesWithFixes() {
        return Stream.of(
                Arguments.of(new Update(List.of(75, 97, 47, 61, 53)), new Update(List.of(97, 75, 47, 61, 53))),
                Arguments.of(new Update(List.of(61, 13, 29)), new Update(List.of(61, 29, 13))),
                Arguments.of(new Update(List.of(97, 13, 75, 29, 47)), new Update(List.of(97, 75, 47, 29, 13)))
        );
    }

    @BeforeEach
    void setup() {
        List<OrderingRule> orderingRules = List.of(
                new OrderingRule(47, 53),
                new OrderingRule(97, 13),
                new OrderingRule(97, 61),
                new OrderingRule(97, 47),
                new OrderingRule(75, 29),
                new OrderingRule(61, 13),
                new OrderingRule(75, 53),
                new OrderingRule(29, 13),
                new OrderingRule(97, 29),
                new OrderingRule(53, 29),
                new OrderingRule(61, 53),
                new OrderingRule(97, 53),
                new OrderingRule(61, 29),
                new OrderingRule(47, 13),
                new OrderingRule(75, 47),
                new OrderingRule(97, 75),
                new OrderingRule(47, 61),
                new OrderingRule(75, 61),
                new OrderingRule(47, 29),
                new OrderingRule(75, 13),
                new OrderingRule(53, 13)
        );
        List<Update> updates = List.of(
                new Update(List.of(75, 47, 61, 53, 29)),
                new Update(List.of(97, 61, 53, 29, 13)),
                new Update(List.of(75, 29, 13)),
                new Update(List.of(75, 97, 47, 61, 53)),
                new Update(List.of(61, 13, 29)),
                new Update(List.of(97, 13, 75, 29, 47))
        );
        underTest = new PrinterInstructions(orderingRules, updates);
    }

    @ParameterizedTest
    @MethodSource("getRulesToApplyWithResults")
    public void appliesOrderingRuleToUpdate(OrderingRule rule, boolean expectedResult) {
        Update update = new Update(List.of(75, 47, 61, 53, 29));

        assertEquals(expectedResult, PrinterInstructions.applyRule(update, rule));
    }

    @Test
    public void canIgnoreRulesWithNonApplicablePageNumbers() {
        Update update = new Update(List.of(75, 47, 61, 53, 29));

        assertTrue(PrinterInstructions.canIgnoreRule(update, new OrderingRule(1, 75)));
        assertFalse(PrinterInstructions.canIgnoreRule(update, new OrderingRule(75, 47)));
    }

    @Test
    public void canReturnInOrderUpdates() {
        List<Update> validUpdates = List.of(
                new Update(List.of(75, 47, 61, 53, 29)),
                new Update(List.of(97, 61, 53, 29, 13)),
                new Update(List.of(75, 29, 13)));

        assertEquals(validUpdates, underTest.getInOrderUpdates());
    }

    @Test
    public void canReturnIncorrectlyOrderedUpdates() {
        List<Update> invalidUpdates = List.of(
                new Update(List.of(75, 97, 47, 61, 53)),
                new Update(List.of(61, 13, 29)),
                new Update(List.of(97, 13, 75, 29, 47))
        );

        assertEquals(invalidUpdates, underTest.getIncorrectlyOrderedUpdates());
    }

    @ParameterizedTest
    @MethodSource("getIncorrectUpdatesWithFixes")
    public void fixesIncorrectlyOrderedUpdates(Update invalidUpdate, Update fixedUpdate) {
        assertEquals(fixedUpdate, underTest.fixUpdate(invalidUpdate));
    }

}