package io.github.j4cku.java11.interfaces;


public interface Example {

    private static void sayHello() {
        System.out.println("Hello!");
    }

    // Ordinary method
    void normalInterfaceMethod();

    default void interfaceMethodWithDefault() {
        init();
    }

    default void anotherDefaultMethod() {
        init();
    }

    // Method that is not a part of public API
    private void init() {
        System.out.println("test");
    }

}
