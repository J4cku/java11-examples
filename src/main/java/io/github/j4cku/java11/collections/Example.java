package io.github.j4cku.java11.collections;

import static java.util.Map.entry;
import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Example {

    public static void main(String[] args) {

        // Empty list of Objects
        List immutableList = List.of();

        // Create List<String>
        var foo = List.of("qiagen", "qiaspace");

        // Empty map, Key & Value of type Object
        Map emptyImmutableMap = Map.of();

        // Quickly create a map
        var mmp = Map.of(2017, "value1", 2018, "valu2");

        // Create map with entries
        Map<Integer, String> emptyEntryMap = Map.ofEntries(
            entry(20, "value1"),
            entry(30, "value2"),
            entry(40, "value3")
        );

        // Create Map.Entry
        Map.Entry<String, String> immutableMapEntry = Map.entry("key", "value");
        // same as above
        Map<String, String> stringStringMap = Map.ofEntries(immutableMapEntry);

        // Create empty String set
        Set<String> immutableSet = Set.of();

        // Create a Set<String>
        Set<String> bar = Set.of("v1", "v2", "v3", "v4", "v5");

        //teeing collector
        var list = List.of(1, 4, 2, 7, 4, 6, 5);
        String listString = list.stream().map(String::valueOf).collect(joining(","));
        double average = list.stream()
            .collect(
                Collectors.teeing(
                    Collectors.summingDouble(i -> i),
                    Collectors.counting(),
                    (sum, n) -> sum / n)
            );
        System.out.println("For List: [" + listString + "] Sum is " + list.stream().mapToInt(i -> i).sum() + " and average is " + average);

        //creating array from list
        Integer[] intArray = list.toArray(Integer[]::new);


    }

}
