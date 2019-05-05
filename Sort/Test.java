package Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
public class Test {
    public static void main(String[] args) {
        TestMySort testMySort = new TestMySort();
        Random random = new Random();
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10000)+1;
        }
        /*long start = System.currentTimeMillis();
        testMySort.shellSort(array);
        long end = System.currentTimeMillis();
        System.out.println(end-start);*///     10
        /*long start = System.currentTimeMillis();
        testMySort.BubbleSort(array);
        long end = System.currentTimeMillis();
        System.out.println(end-start);*///       130
        /*long start = System.currentTimeMillis();
        testMySort.selectSort(array);
        long end = System.currentTimeMillis();
        System.out.println(end-start);*///         170
        /*long start = System.currentTimeMillis();
        testMySort.insertSort(array);
        long end = System.currentTimeMillis();
        System.out.println(end-start);*///           16
        /*long start = System.currentTimeMillis();
        testMySort.heapSort(array);
        long end = System.currentTimeMillis();
        System.out.println(end-start);*///           3
        /*long start = System.currentTimeMillis();
        testMySort.quick(array,0,9999);
        long end = System.currentTimeMillis();
        System.out.println(end-start);*///            2
        /*long start = System.currentTimeMillis();
        testMySort.quickSort(array);
        long end = System.currentTimeMillis();
        System.out.println(end-start);*///              2
        /*long start = System.currentTimeMillis();
        testMySort.mergeSort(array,0,9999);
        long end = System.currentTimeMillis();
        System.out.println(end-start);*///            110
        /*long start = System.currentTimeMillis();
        testMySort.mergeSort(array);
        long end = System.currentTimeMillis();
        System.out.println(end-start);*///             5
        /*long start = System.currentTimeMillis();
        int[] arr = testMySort.countSort(array);
        long end = System.currentTimeMillis();
        System.out.println(end-start);*///               1
        long start = System.currentTimeMillis();
        testMySort.radixSort(array);
        long end = System.currentTimeMillis();
        System.out.println(end-start);//                 19
        System.out.println(Arrays.toString(array));
    }
}
