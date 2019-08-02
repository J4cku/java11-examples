package io.github.j4cku.java11.time;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Example {

    public static void main(String[] args) {
        long day = TimeUnit.DAYS.convert(Duration.ofHours(24));
        System.out.println(day == 1);

        // 1 day
        System.out.println(TimeUnit.DAYS.convert(Duration.ofHours(26)));

        // 1 minute
        System.out.println(TimeUnit.MINUTES.convert(Duration.ofSeconds(60)));

        // 3 minutes
        System.out.println(TimeUnit.MINUTES.convert(Duration.ofSeconds(190)));
    }

}
