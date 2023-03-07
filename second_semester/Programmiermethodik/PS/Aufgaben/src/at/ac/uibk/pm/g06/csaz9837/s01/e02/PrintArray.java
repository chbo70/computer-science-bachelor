package at.ac.uibk.pm.g06.csaz9837.s01.e02;

import java.util.Arrays;

public class PrintArray {

    public static void printArray (int[][] array, String sep){

        for (int[] i:array) {
            for (int j:i) {
                System.out.print(j + sep);
            }
            System.out.println();
        }

    }
    public static void main(String[] args) {
        int[][] array = {{1, 2, 3}, {4, 5}, {6, 7, 8, 9}};
        //System.out.println((array.length));
        printArray(array, " | ");
    }
}
