package io.github.j4cku.java11.switches;

import java.util.stream.IntStream;

public class Example {

    public static void main(String[] args) {
        IntStream.range(0, 6).forEach((var s) -> {
            Day day = Day.values()[s];
            int numLetters = switch (day) {
                case MONDAY, FRIDAY, SUNDAY -> 6;
                case TUESDAY -> 7;
                case THURSDAY, SATURDAY -> 8;
                case WEDNESDAY -> 9;
                default -> throw new IllegalStateException("Huh? " + day);
            };
            System.out.println(day.name() + " has " + numLetters + " letters!");
        });


    }

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY, OTHER
    }
}
