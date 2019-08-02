package io.github.j4cku.java11.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Example {

    public static void main(String[] args) throws IOException {
        String text = "Hello qiagen.";

        // Write text
        Files.writeString(Paths.get("hello.txt"), text);

        // read text
        String readText = Files.readString(Paths.get("hello.txt"));
        System.out.println(text.equals(readText));

        // delete
        Files.delete(Paths.get("hello.txt"));
    }

}
