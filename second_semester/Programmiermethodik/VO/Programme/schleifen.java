public class schleifen {
    public static void main (String[] args){
        int[] toSum = {1,2,3,6,10,12};
        int sum = 0;
        int i = 0;
        while (i < toSum.length) {
            sum += toSum[i];
            ++i;
        }
        System.out.println(sum);
        int[] toSum2 = {1, 2, 3, 6, 10, 13};
        int sum2 = 0;
        for (int value : toSum2) {
            sum2 += value;
            if (sum2 > 12) {
                break;
            }
        }
        System.out.println(sum2);
    }
}
