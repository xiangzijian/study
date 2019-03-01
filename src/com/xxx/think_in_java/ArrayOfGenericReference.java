package com.xxx.think_in_java;

public class ArrayOfGenericReference {
    static class Generic<T> {
    }

    public Generic<Integer>[] gia=(Generic<Integer>[]) new Generic[10];


    public static void main(String[] args) {

    }
    interface HasColor{
         void color();
    }
    class BigItem<T>{
        T item;
        BigItem(T item){this.item=item;}

    }
    /**这里指的是T的界限类型是会传递的*/
    class RedBigItem<T extends HasColor> extends BigItem<T>{

        RedBigItem(T item) {
            super(item);
        }
        void getRed(){
            item.color();
        }
    }


}
