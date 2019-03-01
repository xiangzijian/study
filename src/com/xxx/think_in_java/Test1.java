package com.xxx.think_in_java;

import java.lang.reflect.Constructor;

/**
 * 想要通过class创建一个新的对象  最开始的想法是通过泛型直接创造对象 转为了
 */
public class Test1 {
    public Test1(int cc) {
        System.out.println("xxx" + cc);
    }

    public class GainApple {
        private int i = 0;
        public GainApple(int i) {
            this.i = i;
            System.out.println("xxx" + i);
        }
       public GainApple(String i) {

           System.out.println("xxx" + i);
       }
    }

    static class ClassAsFactory<T> {
        T x;

        public ClassAsFactory(Class<T> kind) {
            try {
                Constructor[] xs=kind.getConstructors();
                Constructor x = kind.getConstructor(int.class);
                x.newInstance(11);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
      ClassAsFactory<Test1> c=new ClassAsFactory<Test1>(Test1.class);
    }


}
