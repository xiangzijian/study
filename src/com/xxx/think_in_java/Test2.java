package com.xxx.think_in_java;

public class Test2 {
    interface Keep {
        public void keep();
    }

    interface Pip {
        public void pip();
    }

    static class Human implements Keep, Pip {

        @Override
        public void keep() {

        }

        @Override
        public void pip() {

        }
    }

    public static  <T extends Keep> void readBook(T k) {
    }

    public static <T extends Pip> void run(T p) {
    }

    public static void main(String[] args) {
        Human human = new Human();
        readBook(human);
        run(human);
    }
}
