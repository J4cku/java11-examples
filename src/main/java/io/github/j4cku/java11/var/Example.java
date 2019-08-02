package io.github.j4cku.java11.var;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Example {

    public static void main(String[] args) throws Exception {
        var list   = new ArrayList<String>();   // infer ArrayList<String>
        var stream = list.stream();             // infer Stream<String>

        var newList = List.of("hello", "javek");
        newList.forEach(System.out::println);

        String fileName = "./pom.xml";

        var path  = Paths.get(fileName);
        var bytes = Files.readAllBytes(path);

        System.out.println("Byte array: " + bytes);

        for (var b : bytes) {
            // TODO
//            System.out.print(b);
            System.out.print((char) b);
        }

        try (var foo = new FileInputStream(new File(""))) {
            System.out.println(foo);
        } catch (Exception e) {
            // ignore
        }

    }
}
