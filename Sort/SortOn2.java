package Sort;

import java.util.Arrays;
import java.util.Random;

public class SortOn2 {
    public static void main(String[] args) {
        SortOn2 p = new SortOn2();
        Random random = new Random();
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10000) + 1;
        }
        long start1 = System.currentTimeMillis();
        p.BubbleSort3(array);
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);

//        long start2 = System.currentTimeMillis();
//        p.selectSort(array);
//        long end2 = System.currentTimeMillis();
//        System.out.println(end2 - start2);

//        long start3 = System.currentTimeMillis();
//        p.insertSort(array);
//        long end3 = System.currentTimeMillis();
//        System.out.println(end3 - start3);
        System.out.println(Arrays.toString(array));
    }

    //冒泡排序（稳定）
    public void BubbleSort1(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {//i表示趟数
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

    //优化1
    public void BubbleSort2(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {//i表示趟数
            boolean isSorted = true;//有序标记
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    //优化2???
    public void BubbleSort3(int[] array) {
        int sortBorder = array.length - 1;//无序边界
        for (int i = 0; i < array.length - 1; i++) {//i表示趟数
            boolean isSorted = true;//有序标记
            int a = sortBorder;
            for (int j = 0; j < a; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    isSorted = false;
                    sortBorder = j;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    //直接选择排序（不稳定）
    public void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {//i表示数组的下标
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    //直接插入排序:越有序越快（稳定）
    public void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {//这里的array.length相当于end+1
            int tmp = array[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (array[j] > tmp) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = tmp;
        }
    }

    public void insertSort(int[] array, int start, int end) {
        getinsertSort(array, start, end);
    }

    static void getinsertSort(int[] array, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int tmp = array[i];
            int j;
            for (j = i - 1; j >= start; j--) {
                if (array[j] > tmp) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = tmp;
        }
    }

    //希尔排序：分成多组，组内进行直接插入排序，最后一组为全体进行直接插入排序（不稳定）
    //其性能优于O(n2)，但是比不上O(nlogn)
    public void shell(int[] array, int gap) {
        for (int i = gap; i < array.length; i++) {
            int tmp = array[i];
            int j;
            for (j = i - gap; j >= 0; j -= gap) {
                if (array[j] > tmp) {
                    array[j + gap] = array[j];
                } else {
                    break;
                }
            }
            array[j + gap] = tmp;
        }
    }

    public void shellSort(int[] array) {
        int[] drr = {5, 3, 1};
        for (int i = 0; i < drr.length; i++) {
            shell(array, drr[i]);
        }
    }
}
