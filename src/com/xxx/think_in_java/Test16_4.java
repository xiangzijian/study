package com.xxx.think_in_java;

import java.util.Arrays;

public class Test16_4 {
    public static double[][] test1(int count1,int count2,int min,int max){
        double[][] s=new double[count1][count2];
        int c=max-min;
        for (int i=0;i<count1;i++) {
            for (int j=0;j<count2;j++) {
                s[i][j]=Math.random()*c+min;
            }
        }
        return s;
    }

    public static void test1_1(double[][] d){
        System.out.println(Arrays.deepToString(d));
    }

    public static double[][][] test2(int count1,int count2,int count3,int min,int max){
        double[][][] s=new double[count1][count2][count3];
        int c=max-min;
        for (int i=0;i<count1;i++) {
            for (int j=0;j<count2;j++) {
                for (int k=0;k<count3;k++) {
                    s[i][j][k]=Math.random()*c+min;
                }
            }
        }
        return s;
    }

    public static double[][] test3(int count1,int count2){
        double[][] s=new double[count1][count2];

        return s;
    }

    public static Test16_2.BerylliumSphere[][] test4(int count1,int count2){
        Test16_2.BerylliumSphere[][] s=new Test16_2.BerylliumSphere[count1][count2];
        for (int i=0;i<count1;i++) {
            for (int j=0;j<count2;j++) {
                s[count1][count2]=new Test16_2.BerylliumSphere();
            }
        }
        return s;
    }

    public static void main(String[] args){
        test1_1(test1(5,5,1,5));
        test1_1(test3(1,5));
    }

}
