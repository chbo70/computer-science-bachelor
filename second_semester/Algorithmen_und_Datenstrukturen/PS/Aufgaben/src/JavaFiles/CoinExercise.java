package JavaFiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinExercise {
    public static void maxSumOfArray(int[] array){
        //find max sum of array elements in O(n) time
        int maxSum = 0;
        int currentSum = 0;
        for (int i = 0; i < array.length; i++) {
            currentSum += array[i];
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        System.out.println(maxSum);
    }
    public static void coinChange(int[] S, int x, int y) {
        int[] availableCoins = S;
        int cost = x;
        int money = y;
        int exchangeMoney = money - cost;

        List<Integer> coins = new ArrayList<>();
        for (int i = S.length; i > 0 ; --i) {
            while (exchangeMoney >= availableCoins[i - 1]) {
                exchangeMoney -= availableCoins[i - 1];
                coins.add(availableCoins[i - 1]);
            }
        }
        System.out.println("your exchange: ");
        coins.forEach(System.out::println);
    }
    public static void coinChange2(int[] S, int x, int y){
        //given a set of coins in S, find the minimum number of coins needed to make change for y-x
        int[] availableCoins = S;
        int cost = x;
        int money = y;
        int exchangeMoney = money - cost;
        int[] coins = new int[exchangeMoney+1];
        coins[0] = 0;
        for(int i = 1; i <= exchangeMoney; i++){
            coins[i] = Integer.MAX_VALUE;
            for(int j = 0; j < availableCoins.length; j++){
                if(i - availableCoins[j] >= 0){
                    coins[i] = Math.min(coins[i], coins[i-availableCoins[j]] + 1);
                }
            }
        }
        //print out all the coins
        List<Integer> coinList = new ArrayList<>();
        int i = exchangeMoney;
        while(i > 0){
            for(int j = 0; j < availableCoins.length; j++){
                if(i - availableCoins[j] >= 0 && coins[i] == coins[i-availableCoins[j]] + 1){
                    coinList.add(availableCoins[j]);
                    i = i - availableCoins[j];
                    break;
                }
            }
        }
        System.out.println("Exchange: ");
        System.out.println(coinList);
    }


    public static void main(String[] args) {
        int[] S = {1,5,40,41};
        int x = 1;
        int y = 46;
        coinChange(S, x, y);
        coinChange2(S, x, y);
        int[] array = {-8,-2,13,-4,6,-5,1};
        maxSumOfArray(array);
    }

}
