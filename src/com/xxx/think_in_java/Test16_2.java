package com.xxx.think_in_java;

public class Test16_2 {
    public static class BerylliumSphere{
        private int i=0;
        public BerylliumSphere() {

        }

        public void setI(int i) {
            this.i = i;
        }

        public int getI() {
            return i;
        }
    }

    public static void test1(BerylliumSphere[] berylliumSpheres){

    }

    public static void main(String[] args){
        /**没办法通过聚集初始化来动态传入参数*/
       /* test1({new BerylliumSphere()});*/
        BerylliumSphere[] berylliumSpheres={new BerylliumSphere()};
    }
    public static BerylliumSphere[] test(int count){
        BerylliumSphere[] berylliumSpheres=new BerylliumSphere[count];
        for (int i=0;i<count;i++) {
            berylliumSpheres[i]=new BerylliumSphere();
        }
        return berylliumSpheres;
    }
}
