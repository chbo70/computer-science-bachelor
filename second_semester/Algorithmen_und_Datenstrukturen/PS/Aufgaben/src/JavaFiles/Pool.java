package JavaFiles;

import java.util.Arrays;


public class Pool {
    private final int max;
    private int[] array;
    int startingIndex = 11;
    int savedStart;
    //int newIndex;
    boolean flag;
    //private final int[] array;

    public Pool(int x) {
        this.array = new int[x];
        this.max = x;
    }

    private int endingIndex(int blockIndex){
        blockIndex += startingIndex;
        //System.out.println(blockIndex);
        return blockIndex;
    }
    public int malloc(int size){
        int newIndex;
        int startingIndexTemp = 11;
        //stores the sizes
        for (int i = 0; i < 5; ++i) {
            if (array[i]== 0){
                array[i]=size;
                break;
            }
        }
        //stores the start indexes
        for (int i = 5; i < 11; ++i) {
            if (array[i]== 0){
                array[i]=startingIndexTemp;
                break;
            }
        }
        //System.out.println("now: " + startingIndexTemp);
        //starting block
        for (int i = startingIndexTemp; i < endingIndex(size); ++i) {
            array[i] = 1;
            flag = true;

        }
        newIndex = startingIndex + size;
        startingIndex = startingIndex + size;
        //System.out.println(newIndex);
        startingIndexTemp = newIndex;
        return startingIndexTemp - size;
    }

    public void free(int index){
        for (int i = index; i < max; ++i) {
            array[i]=0;
        }
    }

    /*
    public int malloc(int size){
        //int[] block = new int[200];
        for (int i = 0; i < size; ++i) {
            if (array[i]!= 0){
                continue;
            } else{
                return i;
            }
        }
        return 0;
    }
    public void free(int index){
        for (int i = index; i < array.length; ++i) {
            Arrays.fill(array,0);
        }
    }*/

    @Override
    public String toString() {
        return "Pool: " + "\n" + Arrays.toString(array);
    }
}
