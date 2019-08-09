package io.github.j4cku.java11.optional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Example {

    public static void main(String[] args) {
//        Optional<String> stringOptional = Optional.of("Optional value of string");
        Optional<String> stringOptional = Optional.empty();

        stringOptional.ifPresentOrElse(System.out::println, () -> System.err.println("Error when not present"));

        stringOptional.or(() -> Optional.of("new String")).ifPresent(System.out::println);

        List<String> collect = stringOptional.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(String.join(",", collect));

//        stringOptional.orElseThrow(IllegalStateException::new);
        stringOptional.orElseThrow();
    }
}
