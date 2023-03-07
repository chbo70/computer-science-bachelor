package at.ac.uibk.pm.g06.csaz9837.s01.e03;

import java.util.Arrays;

public class TimesTwo {
    public static void timesTwo (int x){
        System.out.println(x*2);
    }
    public static void timesTwo (int [] array){
        int x;
        for (int i:array) {
            System.out.println((i*2));
        }


    }
    public static void main(String[] args) {
        int number = 20;
        int[] array = {1,2,3,4,5};
        timesTwo(number);
        timesTwo(array);
    }
}
