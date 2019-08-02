package io.github.j4cku.java11.processor;


import java.io.IOException;

public class Example {

    public static void main(String[] args) throws IOException {
        ProcessHandle currentProcess = ProcessHandle.current();
        System.out.println("Current process PID = " + currentProcess.pid());

        ProcessBuilder builder = new ProcessBuilder("notepad.exe");
        Process process = builder.start();
    }

}
