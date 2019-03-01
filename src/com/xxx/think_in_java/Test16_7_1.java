package com.xxx.think_in_java;

public class Test16_7_1 {
    public static void main(String[] args){
        Test16_2.BerylliumSphere[] berylliumSpheres=new Test16_2.BerylliumSphere[10];
        for (int i=0;i<berylliumSpheres.length;i++) {
            berylliumSpheres[i]=new Test16_2.BerylliumSphere();
        }
        berylliumSpheres[5].setI(5);
        berylliumSpheres[6].setI(6);
        for (Test16_2.BerylliumSphere berylliumSphere:berylliumSpheres) {
            System.out.println(berylliumSphere.hashCode() +"  ------"+berylliumSphere.getI());
        }
        System.out.println("----------------------------------");
        Test16_2.BerylliumSphere[] berylliumSpheres2=new Test16_2.BerylliumSphere[10];
        System.arraycopy(berylliumSpheres,0,berylliumSpheres2,0,berylliumSpheres.length);
        berylliumSpheres2[5].setI(3);

    }

}
