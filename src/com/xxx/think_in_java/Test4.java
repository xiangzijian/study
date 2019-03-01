package com.xxx.think_in_java;

public class Test4 {

    public static void main(String[] args) {
        Holder<Test3.Apple> holder=new Holder(new Test3.Apple());

    }

    static class Holder<T> {
        private T value;

        public Holder() {
        }

        public Holder(T val) {
            value = val;
        }

        public void set(T val) {
            value = val;
        }

        public T get() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            return value.equals(obj);
        }

    }
}
