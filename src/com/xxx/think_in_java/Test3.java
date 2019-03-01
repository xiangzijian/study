package com.xxx.think_in_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test3 {
    public static class Fruit {
    }

    public static class Apple extends Fruit {
    }

    public static class Jonathan extends Apple {
    }

    public static class Orange extends Fruit {
    }

/**此处证明在运行时数组运行机制会判断出该数组的真实类型*/
    public static void main(String[] args) {
        /*Fruit[] fruits = new Apple[10];
        fruits[0] = new Apple();
        fruits[1] = new Jonathan();*/

        List<? extends Fruit> list=new ArrayList<Apple>(Arrays.asList(new Apple()));
        Apple a=(Apple) list.get(0);
        /**这块编译器将调用的是objec方法*/
        list.contains(new Apple());
        list.indexOf(new Apple());
       /* list.add(new Apple());*/
    }
}
