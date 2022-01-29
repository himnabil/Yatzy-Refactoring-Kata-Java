package com.himnabil.yatzy;

public class Yatzy {

    private static final int NUM_DICE = 5;

    private static Roll roll( int [] dice ) {
        if ( dice.length != NUM_DICE ) {
            throw new IllegalArgumentException( "Expected " + NUM_DICE + " dice, got " + dice.length );
        }
        return Roll.of( dice );
    }

    public static long score( Category category, int ... dice ) {
        return category.score( roll(dice) );
    }
}
