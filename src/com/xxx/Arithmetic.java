package com.xxx;

public class Arithmetic {
   public static int[] quickSort(int[] ints){
    return new int[]{};
   }

   public static int[] a(int[] arrays){
       int i=0;
       int j=arrays.length;
       while (true){
           if(arrays[i]>arrays[j]){
               arrays[i]=arrays[i]^arrays[j];
               arrays[j]=arrays[i]^arrays[j];
               arrays[i]=arrays[i]^arrays[j];

           }
           if(arrays[i]<arrays[j]){

           }
       }
   }
}
