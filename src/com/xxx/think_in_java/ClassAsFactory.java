package com.xxx.think_in_java;

public class ClassAsFactory<T> {
    T x;

    public ClassAsFactory(Class<T> kind) {
        try {
            x = kind.newInstance();
        } catch (Exception e) {

        }
    }

    static class Employee {
    }

    public static void main(String[] args) {
        ClassAsFactory<Employee> fe = new ClassAsFactory<>(Employee.class);

    }

    interface Factory<T> {
        T create();
    }

    class Food<T> {
        private T x;

        public <F extends Factory<T>> Food(F f) {
            this.x = f.create();
        }
    }

    class IntFactory implements Factory<Integer> {

        @Override
        public Integer create() {
            return new Integer(1);
        }
    }

    class Widget {
        public class Factory implements ClassAsFactory.Factory<Widget> {
            @Override
            public Widget create() {
                return new Widget();
            }
        }
    }


}
