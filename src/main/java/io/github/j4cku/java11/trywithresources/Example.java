package io.github.j4cku.java11.trywithresources;

import java.io.BufferedReader;
import java.io.FileReader;

public class Example {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("./README.md"));
        try (reader) {
            while(reader.ready()){
                System.out.println(reader.readLine());
            }
        }

        try(BufferedReader reader2 = new BufferedReader(new FileReader("./README.md"))){
            while(reader2.ready()){
                System.out.println(reader2.readLine());
            }
        }
    }

}
