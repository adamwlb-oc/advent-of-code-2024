package aoc.twentytwentyfour.four;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static aoc.twentytwentyfour.four.SearchDirection.DOWN_LEFT;
import static aoc.twentytwentyfour.four.SearchDirection.DOWN_RIGHT;
import static aoc.twentytwentyfour.four.SearchDirection.UP_LEFT;
import static aoc.twentytwentyfour.four.SearchDirection.UP_RIGHT;
import static aoc.twentytwentyfour.four.SearchDirection.allDirections;
import static aoc.twentytwentyfour.four.SearchDirection.diagonals;

public class Day4 {

    public int part1(Wordsearch wordsearch) {
        return getOccurancesOfWord(wordsearch, "XMAS", allDirections());
    }

    public int part2(Wordsearch wordsearch) {
        Set<WordsearchMatch> mases = getDetailedMatches(wordsearch, "MAS", diagonals());

        Set<WordsearchMatch> hasPairs = mases.stream()
                .filter(mas -> hasDiagonalPair(mas, mases))
                .collect(Collectors.toSet());
        return hasPairs.size() / 2;
    }

    private static boolean hasDiagonalPair(WordsearchMatch candidate, Set<WordsearchMatch> allMatches) {
        Set<SearchDirection> directions = switch (candidate.direction()) {
            case UP_RIGHT -> Set.of(UP_LEFT, DOWN_RIGHT);
            case UP_LEFT -> Set.of(UP_RIGHT, DOWN_LEFT);
            case DOWN_LEFT -> Set.of(DOWN_RIGHT, UP_LEFT);
            case DOWN_RIGHT -> Set.of(DOWN_LEFT, UP_RIGHT);
            default -> throw new IllegalStateException("Unexpected value: " + candidate.direction());
        };

        Set<WordsearchMatch> diagonalPairs = directions.stream()
                .map(direction -> new WordsearchMatch(candidate.word(), getDirectionalDiagonalPairStartCoordinates(candidate, direction), direction))
                .collect(Collectors.toSet());

        return allMatches.stream().anyMatch(diagonalPairs::contains);
    }

    static CoordinatePair getDirectionalDiagonalPairStartCoordinates(WordsearchMatch match, SearchDirection searchDirection) {
        int x = match.startingCoordinates().x();
        int y = match.startingCoordinates().y();
        int shift = match.word().length() - 1;

        if (match.direction().equals(UP_RIGHT)) {
            if (searchDirection.equals(UP_LEFT)) {
                return new CoordinatePair(x + shift, y);
            }
            if (searchDirection.equals(DOWN_RIGHT)) {
                return new CoordinatePair(x, y + shift);
            }
        }
        if (match.direction().equals(UP_LEFT)) {
            if (searchDirection.equals(UP_RIGHT)) {
                return new CoordinatePair(x - shift, y);
            }
            if (searchDirection.equals(DOWN_LEFT)) {
                return new CoordinatePair(x, y + shift);
            }
        }
        if (match.direction().equals(DOWN_LEFT)) {
            if (searchDirection.equals(DOWN_RIGHT)) {
                return new CoordinatePair(x - shift, y);
            }
            if (searchDirection.equals(UP_LEFT)) {
                return new CoordinatePair(x, y - shift);
            }
        }
        if (match.direction().equals(DOWN_RIGHT)) {
            if (searchDirection.equals(DOWN_LEFT)) {
                return new CoordinatePair(x + shift, y);
            }
            if (searchDirection.equals(UP_RIGHT)) {
                return new CoordinatePair(x, y - shift);
            }
        }
        throw new IllegalStateException("Invalid match and search direction combination");
    }

    static int getOccurancesOfWord(Wordsearch space, String term, Set<SearchDirection> directions) {
        List<CoordinatePair> candidateStarts = getCandidateStarts(space, term);
        return candidateStarts.stream()
                .mapToInt(coords -> getOccurancesFromCandidateStart(space, term, coords, directions))
                .sum();
    }

    static Set<WordsearchMatch> getDetailedMatches(Wordsearch space, String term, Set<SearchDirection> directions) {
        List<CoordinatePair> candidateStarts = getCandidateStarts(space, term);
        return candidateStarts.stream()
                .map(coords -> getDetailedMatchesFromCandidateStart(space, term, coords, directions))
                .reduce(new HashSet<>(), (acc, set) -> {
                    acc.addAll(set);
                    return acc;
                });
    }

    static boolean searchInDirection(Wordsearch space, String term, CoordinatePair start, SearchDirection direction) {
        return IntStream.range(1, term.length())
                .allMatch(i -> {
                    CoordinatePair coords = new CoordinatePair(
                            start.x() + direction.xStep() * i,
                            start.y() + direction.yStep() * i
                    );
                    return space.inBounds(coords) && space.getByCoords(coords).equals(term.charAt(i));
                });
    }

    static List<CoordinatePair> getCandidateStarts(Wordsearch wordsearch, String term) {
        return IntStream.range(0, wordsearch.width())
                .boxed()
                .flatMap(i -> IntStream.range(0, wordsearch.height()).mapToObj(j -> new CoordinatePair(i, j)))
                .filter(coords -> wordsearch.getByCoords(coords).equals(term.charAt(0)))
                .collect(Collectors.toList());
    }

    private static int getOccurancesFromCandidateStart(Wordsearch space, String term, CoordinatePair start, Set<SearchDirection> directions) {
        return (int) directions.stream()
                .filter(direction -> searchInDirection(space, term, start, direction))
                .count();
    }

    private static Set<WordsearchMatch> getDetailedMatchesFromCandidateStart(Wordsearch space, String term, CoordinatePair start, Set<SearchDirection> directions) {
        return directions.stream()
                .filter(direction -> searchInDirection(space, term, start, direction))
                .map(direction -> new WordsearchMatch(term, start, direction))
                .collect(Collectors.toSet());
    }
}
