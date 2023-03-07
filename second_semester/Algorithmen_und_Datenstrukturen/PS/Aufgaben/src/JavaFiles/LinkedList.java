package JavaFiles;

public class LinkedList {

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,0,0,0,0,0};
        Pool p = new Pool(200);
        int a = p.malloc(8);
        int b = p.malloc(5);
        p.free(b);
        p.free(a);
        int c = p.malloc(10);
        p.free(a);
        System.out.println(p);
    }
}
