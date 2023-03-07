public class methoden {
    public static int pow2(int base){
        base = base * base;
        return base;
    }
    public static void pow2_2(int[] bases){
        for (int i = 0; i < bases.length;++i) {
            bases[i] = bases[i] * bases[i];
        }
    }
    public static void main(String[] args){
        int value = 4;
        int[] values = {2, 4, 10};
        System.out.println(values[1]);
        pow2_2(values);
        System.out.println(values[1]);

        System.out.println("");
        
        System.out.println(value);
        System.out.println(pow2(value));
        System.out.println(value);
        
    }
}
