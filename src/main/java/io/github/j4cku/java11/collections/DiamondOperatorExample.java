package io.github.j4cku.java11.collections;

public class DiamondOperatorExample {

    static abstract class MyHandler<T> {

        private T content;

        MyHandler(T content) {
            this.content = content;
            System.out.println("Constructor received content: " + content.toString());
        }

        T getContent() {
            return content;
        }

        public void setContent(T content) {
            this.content = content;
        }

        abstract void handle();
    }

    public static void main(String[] args) {
        MyHandler<Integer> intHandler = new MyHandler<>(1) {
            @Override
            public void handle() {
                System.out.println("Received > " + getContent());
            }
        };
        intHandler.handle();

        MyHandler<? extends Integer> intHandler1 = new MyHandler<>(10) {
            @Override
            void handle() {
                System.out.println("Received > " + getContent());
            }
        };
        intHandler1.handle();

        MyHandler<?> handler = new MyHandler<>("Magic!") {
            @Override
            void handle() {
                System.out.println("Received > " + getContent());
            }
        };
        handler.handle();

    }

}
