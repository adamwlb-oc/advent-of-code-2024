package aoc.twentytwentyfour.five;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public record PrinterInstructions(List<OrderingRule> orderingRules, List<Update> updates) {

    static boolean canIgnoreRule(Update update, OrderingRule rule) {
        List<Integer> pageNumbers = update.pageNumbers();
        return !(pageNumbers.contains(rule.before()) && pageNumbers.contains(rule.after()));
    }

    static boolean applyRule(Update update, OrderingRule rule) {
        List<Integer> pageNumbers = update.pageNumbers();

        if (canIgnoreRule(update, rule)) {
            return true;
        }
        return pageNumbers.indexOf(rule.before()) < pageNumbers.indexOf(rule.after());
    }

    private static Comparator<Integer> orderRuleComparator(OrderingRule rule) {
        return (l, r) -> {
            if (l == rule.after() && r == rule.before()) {
                return 1;
            }
            if (l == rule.before() && r == rule.after()) {
                return -1;
            }
            return 0;
        };
    }

    public List<Update> getInOrderUpdates() {
        return updates().stream()
                .filter(update -> orderingRules.stream().allMatch(rule -> PrinterInstructions.applyRule(update, rule)))
                .toList();
    }

    public List<Update> getIncorrectlyOrderedUpdates() {
        return updates.stream()
                .filter(update -> orderingRules.stream().anyMatch(rule -> !PrinterInstructions.applyRule(update, rule)))
                .toList();
    }

    public Update fixUpdate(Update update) {
        List<Integer> pageNumbers = new ArrayList<>(update.pageNumbers());
        List<OrderingRule> rulesToApply = orderingRules.stream().filter(rule -> !canIgnoreRule(update, rule)).toList();
        rulesToApply.forEach(rule -> pageNumbers.sort(orderRuleComparator(rule)));
        Update sorted = new Update(pageNumbers);
        if (sorted.equals(update)) {
            return sorted;
        }
        return fixUpdate(sorted);
    }

}
