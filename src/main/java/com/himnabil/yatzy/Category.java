package com.himnabil.yatzy;

@FunctionalInterface
public interface Category {
    long score( Roll roll );
}
