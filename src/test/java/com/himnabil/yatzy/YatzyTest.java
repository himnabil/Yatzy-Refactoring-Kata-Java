package com.himnabil.yatzy;

import org.junit.Test;

import static com.himnabil.yatzy.Yatzy.score;
import static com.himnabil.yatzy.Categories.*;
import static org.junit.Assert.*;

public class YatzyTest {

    @Test
    public void chance_scores_sum_of_all_dice() {
        assertEquals(15, score( CHANCE,2,3,4,5,1));
        assertEquals(16, score( CHANCE, 3,3,4,5,1));
    }

    @Test
    public void yatzy_scores_50() {
        assertEquals(50, score( YATZY,4,4,4,4,4));
        assertEquals(50, score( YATZY,6,6,6,6,6));
        assertEquals(0, score( YATZY,6,6,6,6,3));
    }

    @Test
    public void test_1s() {
        assertEquals(0, score( ONES,2,2,3,4,5));
        assertEquals(1, score( ONES,1,2,3,4,5));
        assertEquals(2, score( ONES,1,1,3,4,5));
        assertEquals(3, score( ONES,1,1,1,4,5));
        assertEquals(4, score( ONES,1,1,1,1,5));
        assertEquals(5, score( ONES,1,1,1,1,1));
    }
    
    @Test
    public void test_2s() {
        assertEquals(0, score( TWOS,1,1,3,4,5));
        assertEquals(2, score( TWOS,1,2,3,4,5));
        assertEquals(4, score( TWOS,2,2,3,4,5));
        assertEquals(6, score( TWOS,2,2,2,4,5));
        assertEquals(8, score( TWOS,2,2,2,2,5));
        assertEquals(10, score( TWOS,2,2,2,2,2));
    }
    
    @Test
    public void test_3s() {
        assertEquals(0, score( THREES,1,1,2,4,5));
        assertEquals(3, score( THREES,1,2,3,4,5));
        assertEquals(6, score( THREES,2,3,3,4,5));
        assertEquals(9, score( THREES,2,3,3,3,5));
        assertEquals(12, score( THREES,2,3,3,3,3));
        assertEquals(15, score( THREES,3,3,3,3,3));
    }
    
    @Test
    public void test_4s() {
        assertEquals(0, score( FOURS,1,1,2,3,5));
        assertEquals(4, score( FOURS,1,2,4,3,5));
        assertEquals(8, score( FOURS,1,4,4,3,5));
        assertEquals(12, score( FOURS,4,4,4,3,5));
        assertEquals(16, score( FOURS,4,4,4,4,5));
        assertEquals(20, score( FOURS,4,4,4,4,4));
    }

    @Test
    public void test_5s() {
        assertEquals(0, score( FIVES,1,1,2,3,4));
        assertEquals(5, score( FIVES,1,2,5,3,4));
        assertEquals(10, score( FIVES,1,5,5,3,4));
        assertEquals(15, score( FIVES,5,5,5,3,4));
        assertEquals(20, score( FIVES,5,5,5,5,4));
        assertEquals(25, score( FIVES,5,5,5,5,5));
    }

    @Test
    public void test_6s() {
        assertEquals(0, score( SIXES,1,1,2,3,4));
        assertEquals(6, score( SIXES,1,2,6,3,4));
        assertEquals(12, score( SIXES,1,6,6,3,4));
        assertEquals(18, score( SIXES,6,6,6,3,4));
        assertEquals(24, score( SIXES,6,6,6,6,4));
        assertEquals(30, score( SIXES,6,6,6,6,6));
    }

    @Test
    public void one_pair() {
        assertEquals(6, score(SCORE_PAIR,3,4,3,5,6));
        assertEquals(10, score(SCORE_PAIR,5,3,3,3,5));
        assertEquals(12, score(SCORE_PAIR,5,3,6,6,5));
        assertEquals(12, score(SCORE_PAIR,5,6,6,6,5));
    }

    @Test
    public void two_pairs() {
        assertEquals(16, score(TWO_PAIR,3,3,5,4,5));
        assertEquals(16, score(TWO_PAIR,3,3,5,5,5));
    }

    @Test
    public void three_of_a_kind() {
        assertEquals(9, score(THREE_OF_A_KIND,3,3,3,5,5));
        assertEquals(15, score(THREE_OF_A_KIND,5,5,5,5,5));
    }

    @Test
    public void four_of_a_kind() {
        assertEquals(12, score(FOUR_OF_A_KIND,3,3,3,3,5));
        assertEquals(20, score(FOUR_OF_A_KIND,5,5,5,5,5));
    }

    @Test
    public void smallStraight() {
        assertEquals(15, score(SMALL_STRAIGHT,1,2,3,4,5));
        assertEquals(15, score(SMALL_STRAIGHT,2,3,4,5,1));
        assertEquals(0, score(SMALL_STRAIGHT,1,2,2,4,5));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, score(LARGE_STRAIGHT,6,2,3,4,5));
        assertEquals(20, score(LARGE_STRAIGHT,2,3,4,5,6));
        assertEquals(0, score(LARGE_STRAIGHT,1,2,2,4,5));
    }

    @Test
    public void fullHouse() {
        assertEquals(18, score(FULL_HOUSE,6,2,2,2,6));
        assertEquals(0, score(FULL_HOUSE,2,3,4,5,6));
    }

}
