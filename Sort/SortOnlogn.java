package Sort;

import java.util.Arrays;
import java.util.Random;

public class SortOnlogn {
    public static void main(String[] args) {
        SortOnlogn p = new SortOnlogn();
        Random random = new Random();
        int[] array = new int[100000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100000) + 1;
        }
//        long start1 = System.currentTimeMillis();
//        p.heapSort(array);
//        long end1 = System.currentTimeMillis();
//        System.out.println(end1 - start1);

//        long start1 = System.currentTimeMillis();
//        p.mergeSort(array,0,99999);//递归和非递归的时间差距这么大？
//        long end1 = System.currentTimeMillis();
//        System.out.println(end1 - start1);

        long start1 = System.currentTimeMillis();
        p.quick3(array, 0, 99999);
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);
        System.out.println(Arrays.toString(array));
    }

    //堆排序（不稳定）
    //一次调整
    public void adjust(int[] array, int start, int end) {
        int tmp = array[start];
        for (int i = 2 * start + 1; i <= end; i = i * 2 + 1) {//巧妙的连接了父节点与其左孩子节点，从0节点走流程更有助于理解
            if ((i < end) && array[i] < array[i + 1]) {//i < end必须放在左边，否则array[i+1]会出现数组越界
                i++;//巧妙的取得了左右孩子的最大值下标
            }
            if (array[i] > tmp) {
                array[start] = array[i];
                start = i;
            } else if (array[i] < tmp) {
                break;
            }
        }
        array[start] = tmp;
    }

    public void heapSort(int[] array) {
        //靖国调整i个数据，变成了一个“有序”的大根堆
        for (int i = (array.length - 1 - 1) / 2; i >= 0; i--) {//i表示最后一个有孩子的父节点，0节点最后调整
            adjust(array, i, array.length - 1);
        }
        //由于已经“有序”，所以在进行交换后只需调整0号节点即可
        for (int i = 1; i < array.length; i++) {
            int tmp = array[array.length - i];
            array[array.length - i] = array[0];
            array[0] = tmp;
            adjust(array, 0, array.length - 1 - i);
        }
    }

    //归并排序（稳定）
    //归并排序的递归形式
    public void merge(int[] array, int start, int mid,
                      int end) {
        int[] tmpArray = new int[array.length];
        int tmpIndex = start;
        int start2 = mid + 1;
        int i = start;//把start的值保存下来
        while (start <= mid && start2 <= end) {
            if (array[start] <= array[start2]) {
                tmpArray[tmpIndex++] = array[start++];
            } else {
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

    public void mergeSort(int[] array, int start, int end) {
        if (start == end) {//只有一个元素
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(array, start, mid);
        mergeSort(array, mid + 1, end);//递归分解
        merge(array, start, mid, end);//本身就在递归方法里，所以它也是无形中进行了迭代
    }

    //归并排序的非递归形式
    //gap 表示每组数据的个数，通过gap来确定s和e
    public void merge(int[] array, int gap) {
        int[] tmpArray = new int[array.length];
        int i = 0;
        int start1 = 0;
        int end1 = start1 + gap - 1;
        int start2 = end1 + 1;
        int end2 = start2 + gap - 1 > array.length - 1 ? array.length - 1 : start2 + gap - 1;
        while (start2 < array.length) {
            while (start1 <= end1 && start2 <= end2) {
                if (array[start1] <= array[start2]) {
                    tmpArray[i++] = array[start1++];
                } else {
                    tmpArray[i++] = array[start2++];
                }
            }
            while (start1 <= end1) {
                tmpArray[i++] = array[start1++];
            }
            while (start2 <= end2) {
                tmpArray[i++] = array[start2++];
            }
            start1 = end2 + 1;
            end1 = start1 + gap - 1;
            start2 = end1 + 1;
            end2 = start2 + gap - 1 > array.length - 1 ? array.length - 1 : start2 + gap - 1;
        }
        while (start1 <= array.length - 1) {
            tmpArray[i++] = array[start1++];
        }
        for (int j = 0; j < tmpArray.length; j++) {
            array[j] = tmpArray[j];
        }
    }

    public void mergeSort(int[] array) {
        for (int i = 1; i < array.length; i = i * 2) {
            merge(array, i);
        }
    }

    //快速排序（不稳定）
    //确定一个数在这组数中的位置
    //双边循环法
    public int partion(int[] array, int low, int high) {
        int tmp = array[low];
        while (low < high) {//这个循环和一般的循环有些不一样，改变循环的条件在循环的循环里面
            while ((low < high) && array[high] >= tmp) {//注意high和low之间的距离一直在缩小
                high--;
            }
            if (low == high) {
                break;
            } else {
                array[low] = array[high];
            }
            while ((low < high) && array[low] <= tmp) {
                low++;
            }
            if (low == high) {
                break;
            } else {
                array[high] = array[low];
            }
        }
        array[low] = tmp;//和adjust方法的开头结尾有异曲同工之处
        return low;
    }

    public int partion1(int[] array, int low, int high) {
        int tmp = array[low];
        while (low < high) {
            while ((low < high) && array[high] >= tmp) {
                high--;
            }
            while ((low < high) && array[low] <= tmp) {
                low++;
            }
            if (low < high) {
                swap(array, low, high);
            }
        }
        array[low] = tmp;
        return low;
    }

    //单边循环法
    public int partion2(int[] array, int low, int high) {
        int tmp = array[low];
        int mark = low;
        for (int i = low + 1; i <= high; i++) {
            if (array[i] < tmp) {
                mark++;
                swap(array, mark, i);
            }
        }
        array[low] = array[mark];
        array[mark] = tmp;
        return mark;
    }

    public void swap(int[] array, int start, int end) {
        int tmp = array[start];
        array[start] = array[end];
        array[end] = tmp;
    }

    //三数取中法(快排的一种优化)
    public void medianOfThree(int[] array, int low, int high) {
        int mid = (low + high) >> 1;
        if (array[mid] > array[low]) {
            swap(array, mid, low);
        }
        if (array[mid] > array[high]) {
            swap(array, mid, high);
        }
        if (array[low] > array[high]) {
            swap(array, low, high);
        }
    }

    public void insertSort(int[] array, int start, int end) {
        SortOn2.getinsertSort(array, start, end);
    }

    //快排的递归形式
    public void quick1(int[] array, int start, int end) {
        if (end <= start) {
            return;
        }
        //递归到小的子区间时，考虑使用插入排序(快排的一种优化)
        if (end - start + 1 <= 5) {
            insertSort(array, start, end);
            return;
        }
        medianOfThree(array, start, end);
        int par = partion(array, start, end);
        //找左边是否有两个数据以上
        if (par > start + 1) {
            quick1(array, start, par - 1);
        }
        //右边是否有两个数据以上
        if (par < end - 1) {
            quick1(array, par + 1, end);
        }
    }

    public void quick2(int[] array, int start, int end) {
        if (end <= start) {
            return;
        }
        if (end - start + 1 <= 5) {
            insertSort(array, start, end);
            return;
        }
        medianOfThree(array, start, end);
        int par = partion2(array, start, end);
        if (par > start + 1) {
            quick2(array, start, par - 1);
        }
        if (par < end - 1) {
            quick2(array, par + 1, end);
        }
    }

    //三路快排（快排的一种优化）
    public void quick3(int[] array, int low, int high) {
        if (high < low) {
            return;
        }
        int tmp = array[low];
        int lt = low;
        int gt = high;
        int i = low;
        while (i <= gt) {
            if (array[i] < tmp) {
                int a = array[i];
                array[i] = array[lt];
                array[lt] = a;
                lt++;
                i++;
            } else if (array[i] > tmp) {
                int a = array[i];
                array[i] = array[gt];
                array[gt] = a;
                gt--;
            } else {
                i++;
            }
        }
        quick3(array, low, lt - 1);
        quick3(array, gt + 1, high);
    }

    //快排的非递归形式
    public void quickSort(int[] array) {
        int[] stack = new int[2 * array.length];
        int top = 0;
        int low = 0;
        int high = array.length - 1;
        int par = partion(array, low, high);
        if (par > low + 1) {
            stack[top++] = low;
            stack[top++] = par - 1;
        }
        if (par < high - 1) {
            stack[top++] = par + 1;
            stack[top++] = high;
        }
        while (top > 0) {
            high = stack[top - 1];
            top--;
            low = stack[top - 1];
            top--;
            par = partion(array, low, high);
            if (par > low + 1) {
                stack[top++] = low;
                stack[top++] = par - 1;
            }
            if (par < high - 1) {
                stack[top++] = par + 1;
                stack[top++] = high;
            }
        }
    }
}
