package com.himnabil.yatzy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Categories {

    public static Category CHANCE = Roll::sum;

    private static Category similarDicesCategory(int dice) {
        return roll -> dice * roll.frequencyOf(dice);
    }

    public static final Category ONES = similarDicesCategory(1);
    public static final Category TWOS = similarDicesCategory(2);
    public static final Category THREES = similarDicesCategory(3);
    public static final Category FOURS = similarDicesCategory(4);
    public static final Category FIVES = similarDicesCategory(5);
    public static final Category SIXES = similarDicesCategory(6);


    private static IntStream sortedNOfaKind(Roll roll , long n) {
        return roll
            .frequencies()
            .filter(dice -> dice.getFrequency() >= n)
            .sorted(Comparator.comparingInt(dice -> - dice.getValue())) // Decreasing order
            .mapToInt(Roll.DiceFrequency::getValue);
    }

    private static Category nOfaKind(long n) {
        return roll -> sortedNOfaKind(roll , n)
            .findFirst()
            .orElse(0) * n;
    }

    public static final Category SCORE_PAIR = nOfaKind(2L);

    public static final Category TWO_PAIR = roll -> {
        int[] pairsValues = sortedNOfaKind(roll, 2L)
            .limit(2)
            .toArray();
        if (pairsValues.length != 2) {
            return 0L;
        }
        return Arrays.stream(pairsValues).sum() * 2L;
    };

    public static final Category THREE_OF_A_KIND = nOfaKind(3L);
    public static final Category FOUR_OF_A_KIND = nOfaKind(4L);

    private static Category scoreIfRule( Predicate<Roll> rule, long score){
        return roll -> rule.test(roll) ? score : 0L;
    }

    private static Category scoreIfContain(Set<Integer> diceSet, long score){
        return scoreIfRule( roll -> roll.stream()
                .boxed()
                .collect(Collectors.toSet())
                .containsAll(diceSet) ,
            score);
    }

    public static final Category SMALL_STRAIGHT = scoreIfContain(Set.of(1,2,3,4,5), 15L);
    public static final Category LARGE_STRAIGHT = scoreIfContain(Set.of(2,3,4,5,6), 20L);


    private static Predicate<Roll> hasExactlyNOfAKind(long n) {
        return roll -> roll.frequencies().anyMatch(dice -> dice.getFrequency() == n);
    }

    private static final Predicate<Roll> hasPair = hasExactlyNOfAKind(2L);
    private static final Predicate<Roll> hasFull = hasExactlyNOfAKind(3L);

    private static final Predicate<Roll> isFullHouse = hasFull.and(hasPair);

    public static final Category FULL_HOUSE = scoreIfRule(isFullHouse,18L);

    public static final Predicate<Roll> yatzyRule = roll -> {
        int firstDice = roll.getDice(0);
        return roll.stream().skip(1).allMatch(dice -> dice == firstDice);
    };

    public static final Category YATZY = scoreIfRule(yatzyRule, 50L);
}
