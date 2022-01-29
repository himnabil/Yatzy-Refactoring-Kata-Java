package com.himnabil.yatzy;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@AllArgsConstructor(staticName = "of")
public class Roll {

    private final int[] dices;

    public IntStream stream(){
        return Arrays.stream(dices);
    }

    @Value(staticConstructor = "of")
    static public class DiceFrequency{

        public static DiceFrequency form ( Map.Entry<Integer, Long> entry){
            return of(entry.getKey(), entry.getValue());
        }

        int value;
        long frequency;
    }

    public Stream<DiceFrequency> frequencies(){
        return stream()
            .boxed()
            .collect(groupingBy(identity(), counting()))
            .entrySet()
            .stream()
            .map(DiceFrequency::form);
    }

    public int getDice(int index){
        return dices[index];
    }
    public long frequencyOf(int value){
        return stream()
            .filter(dice -> dice == value)
            .count();
    }

    public long sum(){
        return stream().sum();
    }


}
