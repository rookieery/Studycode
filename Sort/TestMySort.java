package Sort;

import java.util.ArrayList;
import java.util.List;

public class TestMySort {
    //直接插入排序:越有序越快（稳定）
    //2 4 5 10  插7
    public void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {//这里的array.length相当于end+1
            int tmp = array[i];
            int j = 0;
            for (j = i - 1; j >= 0 ; j--) {
                if (array[j] > tmp) {
                    array[j + 1] = array[j];
                }
                else {
                    break;
                }
            }
            array[j + 1] = tmp;
        }
    }
    public void insertSort(int[] array,int start,int end) {
        for (int i = start+1; i <= end; i++) {
            int tmp = array[i];
            int j = 0;
            for (j = i - 1; j >= start ; j--) {
                if (array[j] > tmp) {
                    array[j + 1] = array[j];
                }
                else {
                    break;
                }
            }
            array[j + 1] = tmp;
        }
    }
    //希尔排序：分成多组，组内进行直接插入排序，最后一组为全体进行直接插入排序（不稳定）
    public void shell(int[] array,int gap) {
        for (int i = gap; i < array.length; i++) {
            int tmp = array[i];
            int j = 0;
            for (j = i - gap; j >= 0 ; j -= gap) {
                if (array[j] > tmp) {
                    array[j + gap] = array[j];
                }
                else {
                    break;
                }
            }
            array[j + gap] = tmp;
        }
    }
    public void shellSort(int[] array) {
        int[] drr = {5,3,1};
        for (int i = 0; i < drr.length; i++) {
            shell(array,drr[i]);
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
    //冒泡排序（稳定）
    public void BubbleSort(int[] array) {
        for (int i = 1; i < array.length-1; i++) {//i表示趟数
            for (int j = 0; j < array.length-1-i; j++) {
                if (array[j] > array[j+1]) {
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
    }
    //堆排序（不稳定）
    //一次调整
    public void adjust (int[] array,int start,int end) {
        int tmp = array[start];
        for (int i = 2*start+1; i <= end; i = i*2+1) {//巧妙的连接了父节点与其左孩子节点，从0节点走流程更有助于理解
            if ((i < end) && array[i] < array[i+1]) {//i < end必须放在左边，否则array[i+1]会出现数组越界
                i++;//巧妙的取得了左右孩子的最大值下标
                }
            if (array[i] > tmp) {
                array[start] = array[i];
                start = i;
            }
            else if (array[i] < tmp) {
                break;
            }
        }
        array[start] = tmp;
    }
    public void heapSort(int[] array) {
        //靖国调整i个数据，变成了一个“有序”的大根堆
        for (int i = (array.length-1-1)/2; i >= 0; i--) {//i表示最后一个有孩子的父节点，0节点最后调整
            adjust(array,i,array.length-1);
        }
        //由于已经“有序”，所以在进行交换后只需调整0号节点即可
        for (int i = 1; i < array.length; i++) {
            int tmp = array[array.length-i];
            array[array.length-i] = array[0];
            array[0] = tmp;
            adjust(array,0,array.length-1-i);
        }
    }
    //快速排序（不稳定）
    //确定一个数在这组数中的位置
    public int partion(int[] array,int low,int high) {
        int tmp = array[low];
        while (low < high) {//这个循环和一般的循环有些不一样，改变循环的条件在循环的循环里面
            while ((low < high) && array[high] >= tmp) {//注意high和low之间的距离一直在缩小
                high--;
            }
            if (low == high) {
                break;
            }
            else {
                array[low] = array[high];
            }
            while ((low < high) && array[low] <= tmp) {
                low++;
            }
            if (low == high) {
                break;
            }
            else {
                array[high] = array[low];
            }
        }
        array[low] = tmp;//和adjust方法的开头结尾有异曲同工之处
        return low;
    }
    public void swap(int[] array,int start,int end){
        int tmp = array[start];
        array[start] = array[end];
        array[end] = tmp;
    }
    //三数取中法(快排的一种优化)
    public void medianOfThree(int[] array,int low,int high) {
        int mid = (low+high)>>1;
        if (array[mid] > array[low]) {
            swap(array,mid,low);
        }
        if (array[mid] > array[high]) {
            swap(array,mid,high);
        }
        if (array[low] > array[high]) {
            swap(array,low,high);
        }
    }
    //快排的递归形式
    public void quick(int[] array,int start,int end) {
        //递归到小的子区间时，考虑使用插入排序(快排的一种优化)
        if(end-start+1 <= 5){
            insertSort(array,start,end);
            return;
        }
        medianOfThree(array,start,end);
        int par = partion(array,start,end);
        //找左边是否有两个数据以上
        if(par > start+1){
            quick(array,start,par-1);
        }
        //右边是否有两个数据以上
        if(par < end-1){
            quick(array,par+1,end);
        }
    }
    //快排的非递归形式
    public void quickSort(int[] array) {
        int[] stack = new  int[2*array.length];
        int top = 0;
        int low = 0;
        int high = array.length-1;
        int par = partion(array,low,high);
        if (par > low + 1) {
            stack[top++] = low;
            stack[top++] = par-1;
        }
        if (par < high - 1) {
            stack[top++] = par+1;
            stack[top++] = high;
        }
        while (top > 0) {
            high = stack[top-1];
            top--;
            low = stack[top-1];
            top--;
            par = partion(array,low,high);
            if (par > low + 1) {
                stack[top++] = low;
                stack[top++] = par-1;
            }
            if (par < high - 1) {
                stack[top++] = par+1;
                stack[top++] = high;
            }
        }
    }
    //归并排序（稳定）
    //归并排序的递归形式
    public void merge(int[] array,int start,int mid,
                             int end) {
        int[] tmpArray = new int[array.length];
        int tmpIndex = start;
        int start2 = mid+1;
        int i = start;//把start的值保存下来
        while(start <= mid && start2 <= end) {
            if(array[start] <= array[start2]) {
                tmpArray[tmpIndex++] = array[start++];
            }else {
                tmpArray[tmpIndex++] = array[start2++];
            }
        }
        while (start <= mid) {
            tmpArray[tmpIndex++] = array[start++];
        }
        while (start2 <= end) {
            tmpArray[tmpIndex++] = array[start2++];
        }
        //把排好序的数组复制给原数组
        while (i <= end) {
            array[i] = tmpArray[i];
            i++;
        }
    }
    public void mergeSort(int[] array,int start,int end) {
        if (start == end) {//只有一个元素
            return;
        }
        int mid = (start+end)/2;
        mergeSort(array,start,mid);
        mergeSort(array,mid+1,end);//递归分解
        merge(array,start,mid,end);//本身就在递归方法里，所以它也是无形中进行了迭代
    }
    //归并排序的非递归形式
    //gap 表示每组数据的个数，通过gap来确定s和e
    public void merge(int[] array,int gap) {
        int[] tmpArray = new int[array.length];
        int i = 0;
        int start1 = 0;
        int end1 = start1+gap-1;
        int start2 = end1+1;
        int end2 = start2+gap-1 > array.length-1 ? array.length-1 : start2+gap-1;
        while (start2 < array.length) {
            while (start1 <= end1 && start2 <= end2) {
                if (array[start1] <= array[start2]) {
                    tmpArray[i++] = array[start1++];
                }
                else {
                    tmpArray[i++] = array[start2++];
                }
            }
            while (start1 <= end1) {
                tmpArray[i++] = array[start1++];
            }
            while (start2 <= end2) {
                tmpArray[i++] = array[start2++];
            }
            start1 = end2+1;
            end1 = start1+gap-1;
            start2 = end1+1;
            end2 = start2+gap-1 > array.length-1 ? array.length-1 : start2+gap-1;
        }
        while (start1 <= array.length-1) {
            tmpArray[i++] = array[start1++];
        }
        for (int j = 0; j < tmpArray.length; j++) {
            array[j] = tmpArray[j];
        }
    }
    public void mergeSort(int[] array) {
        for (int i = 1; i < array.length; i = i*2) {
            merge(array,i);
        }
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
        int[] countArray = new int[d+1];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i]-min]++;
        }
        //3.统计数组做变形，后面元素等于前面的元素之和
        int sum = 0;
        for (int i = 0; i < countArray.length; i++) {
            sum += countArray[i];
            countArray[i] = sum;
        }
        //4.倒序遍历原始数组，从统计数组找到正确位置，输出结果数组
        int[] sortedArray = new int[array.length];
        for (int i = array.length-1; i >= 0; i--) {
            sortedArray[countArray[array[i]-min]-1] = array[i];//-1是因为sortedArray数组也是从0号下标开始计数
            countArray[array[i]-min]--;
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
                int key = array[j]%(int)Math.pow(10,i+1)/(int)Math.pow(10,i);
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
