package com.github.damienbarrion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is made to give scores of a roll of dices.
 * In Yatzy, a roll contains 5 dices, but we can extend the class to work with 6 dices (Maxi Yatzy).
 *
 * @author Damien Barrion.
 */
public class DiceRollChecker {

    private final List<Integer> dices;
    private final int dicesSize;
    private final int dicesSum;
    private final Map<Integer, Integer> dicesCountByNumber;

    // Protected to be used by variations
    protected static final List<Integer> SMALL_STRAIGHT = Arrays.asList(1, 2, 3, 4, 5);
    protected static final List<Integer> LARGE_STRAIGHT = Arrays.asList(2, 3, 4, 5, 6);
    protected int scoreYatzy = 50;

    public DiceRollChecker(int... dices) {
        this.dicesSize = dices.length;
        if (dicesSize < 5) {
            throw new IllegalArgumentException("At least 5 dices needed");
        }

        this.dices = Arrays.stream(dices).sorted().boxed().toList();
        this.dicesSum = sum();
        this.dicesCountByNumber = countDicesByNumber();
    }

    // TOOLS TO MANIPULATE DICES

    protected int sum() {
        return dices.stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * Very useful mapping of dices
     *
     * @return a map with all the numbers (key) and the N time (value) they are appearing in the roll
     */
    protected Map<Integer, Integer> countDicesByNumber() {
        return dices.stream()
                .collect(Collectors.groupingBy(d -> d, Collectors.summingInt(i -> 1)));
    }

    protected int sumCountDicesForNumber(int number) {
        return dicesCountByNumber.getOrDefault(number, 0) * number;
    }

    /**
     * Filter for dicesCountByNumber
     *
     * @param minCount minimal amount of count
     * @return all numbers matching the count
     */
    protected Stream<Integer> findNumbersWithMinimalCount(int minCount) {
        return dicesCountByNumber.entrySet().stream()
                .filter(e -> e.getValue() >= minCount)
                .map(Map.Entry::getKey);
    }

    /**
     * Count Of A Kind
     *
     * @param count to search
     * @return sum of count if they exist or 0
     */
    protected int countOfAKind(int count) {
        return findNumbersWithMinimalCount(count).findFirst().orElse(0) * count;
    }

    /**
     * Compares the roll to a straight
     *
     * @param straight of a desired type
     * @return sum of dices
     */
    protected int straight(List<Integer> straight) {
        return straight.equals(dices) ? dicesSum : 0;
    }

    // UPPER SECTION OF YATZY

    /**
     * Ones
     *
     * @return sum of ones
     */
    public int ones() {
        return sumCountDicesForNumber(1);
    }

    /**
     * Twos
     *
     * @return sum of twos
     */
    public int twos() {
        return sumCountDicesForNumber(2);
    }

    /**
     * Threes
     *
     * @return sum of threes
     */
    public int threes() {
        return sumCountDicesForNumber(3);
    }

    /**
     * Fours
     *
     * @return sum of fours
     */
    public int fours() {
        return sumCountDicesForNumber(4);
    }

    /**
     * Fives
     *
     * @return sum of fives
     */
    public int fives() {
        return sumCountDicesForNumber(5);
    }

    /**
     * Sixes
     *
     * @return sum of sixes
     */
    public int sixes() {
        return sumCountDicesForNumber(6);
    }

    // LOWER SECTION OF YATZY

    /**
     * One Pair
     *
     * @return sum of the two dices if they exist or 0
     */
    public int onePair() {
        return findNumbersWithMinimalCount(2).max(Comparator.naturalOrder()).orElse(0) * 2;
    }

    /**
     * Two Pairs
     *
     * @return sum of pairs if they exist or 0
     */
    public int twoPairs() {
        List<Integer> pairs = findNumbersWithMinimalCount(2).sorted(Comparator.reverseOrder()).toList();
        return pairs.size() > 1 ? pairs.get(0) * 2 + pairs.get(1) * 2 : 0;
    }

    /**
     * Three Of A Kind
     *
     * @return sum of three if they exist or 0
     */
    public int threeOfAKind() {
        return countOfAKind(3);
    }

    /**
     * Four Of A Kind
     *
     * @return sum of four if they exist or 0
     */
    public int fourOfAKind() {
        return countOfAKind(4);
    }

    /**
     * Straight with lower numbers
     *
     * @return sum of dices if small straight or 0
     */
    public int smallStraight() {
        return straight(SMALL_STRAIGHT);
    }

    /**
     * Straight with upper numbers
     *
     * @return sum of dices if large straight or 0
     */
    public int largeStraight() {
        return straight(LARGE_STRAIGHT);
    }

    /**
     * Full House : Three Of A Kind + Pair
     *
     * @return sum of dices if full house or 0
     */
    public int fullHouse() {
        int threeOfAKind = findNumbersWithMinimalCount(3).findFirst().orElse(0);
        if (threeOfAKind == 0) {
            return 0;
        }
        for (int pair : findNumbersWithMinimalCount(2).toList()) {
            if (pair == threeOfAKind) {
                continue;
            }
            return sum();
        }

        return 0;
    }

    /**
     * Chance
     *
     * @return sum of dices
     */
    public int chance() {
        return sum();
    }

    /**
     * YATZY ! All dices of the same number
     *
     * @return 50 if yatzy or 0
     */
    public int yatzy() {
        return countDicesByNumber().containsValue(dicesSize) ? scoreYatzy : 0;
    }
}


