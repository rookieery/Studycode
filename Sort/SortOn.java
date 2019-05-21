package Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SortOn {
    public static void main(String[] args) {
        SortOn p = new SortOn();
        Random random = new Random();
        int[] array = new int[100000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100000) + 1;
        }

//        long start1 = System.currentTimeMillis();
//        int[] arr = p.countSort(array);
//        long end1 = System.currentTimeMillis();
//        System.out.println(end1 - start1);
//        System.out.println(Arrays.toString(arr));

        long start1 = System.currentTimeMillis();
        p.radixSort(array);
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);
        System.out.println(Arrays.toString(array));
    }

    //计数排序
    public int[] countSort(int[] array) {
        //1.得到数组的最大值和最小值，并算出差值d
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int d = max - min;
        //2.创建统计数组并统计对应元素个数
        int[] countArray = new int[d + 1];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i] - min]++;
        }
        //3.统计数组做变形，后面元素等于前面的元素之和
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }
        //4.倒序遍历原始数组，从统计数组找到正确位置，输出结果数组
        int[] sortedArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            sortedArray[countArray[array[i] - min] - 1] = array[i];//-1是因为sortedArray数组也是从0号下标开始计数
            countArray[array[i] - min]--;
        }
        return sortedArray;
    }

    //基数排序
    public void radixSort(int[] array) {
        //找到数组中的最大值
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        //确定最大值的位数
        int keysNum = 0;
        while (max > 0) {
            max /= 10;
            keysNum++;
        }
        //设置10个桶
        List<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < keysNum; i++) {
            //将元素放到对应的桶中
            for (int j = 0; j < array.length; j++) {
                int key = array[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                buckets.get(key).add(array[j]);
            }
            int count = 0;
            //将桶中的元素依次复制回数组
            for (int j = 0; j < 10; j++) {
                ArrayList<Integer> bucket = buckets.get(j);
                while (bucket.size() > 0) {
                    array[count++] = bucket.remove(0);
                }
            }
        }
    }
}
