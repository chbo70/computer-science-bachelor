public class hello_world {
    public static void main(String[] args){
        System.out.println("Hello World!");

        String stringtest = "Test Text!";
        String stringtest2 = "Test Text";

        byte z = 3;

        int x = (int) 2.5;//cast findet hier statt
        System.out.println(x);
        System.out.println(z);
        
        if (stringtest == stringtest2)
            System.out.println(true);
        else 
            System.out.println(false);

        System.out.println(stringtest);
        System.out.println(stringtest2);
    }

}