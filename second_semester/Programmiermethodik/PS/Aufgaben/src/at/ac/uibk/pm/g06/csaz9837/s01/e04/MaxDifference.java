package at.ac.uibk.pm.g06.csaz9837.s01.e04;

import java.util.Arrays;

public class MaxDifference {
    public static int maxDifference(int[] array){
        int max = array[0];
        int min = array[0];
        for (int i: array){
            if (i > max) {
                max = i;
            } else if(i < min) {
                min = i;
            }

        }
        return max - min;
    }
    public static void main(String[] args) {
        int[] array = {1,1,255,39,50};
        System.out.println(maxDifference(array));
    }
}
