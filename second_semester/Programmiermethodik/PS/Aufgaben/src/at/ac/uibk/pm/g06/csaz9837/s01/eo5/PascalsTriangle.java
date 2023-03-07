package at.ac.uibk.pm.g06.csaz9837.s01.eo5;

import java.sql.SQLOutput;
import java.util.Arrays;

public class PascalsTriangle {
    public static int maxA(int[] array){
        int max = array[0];

        for (int i: array){
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
    public static void pascalsTriangle (int height){
        int[][] pascalsArray = new int[height][];

        //sets the first 2 rows and generates a sub-array with every iteration
        for (int i = 0; i < height; ++i){
            pascalsArray[i] = new int[i + 1];
            pascalsArray[i][0] = 1;
            pascalsArray[i][i] = 1;
        }
        //
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < i ; ++j) {
                if (j != 0) { // pascalArray[4] [1] =  pascalArray[3][0] + pascalArray[3][1]
                    pascalsArray[i][j] = pascalsArray[i - 1][j - 1] + pascalsArray[i - 1][j];
                }

            }
        }
        //loops through the Array and prints it
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print(pascalsArray[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int height = Integer.parseInt(args[0]);
        pascalsTriangle(height);
    }
}
