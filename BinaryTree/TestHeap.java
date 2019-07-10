package BinaryTree;

import java.util.Arrays;

public class TestHeap implements Heap {
    private int[] elem;
    private int usedSize;
    private static final int DEfAULT_SIZE = 10;

    public TestHeap() {
        this.elem = new int[DEfAULT_SIZE];
        this.usedSize = 0;
    }

    //向下调整
    @Override
    public void AdjustDown(int root, int len) {
        int parent = root;
        int child = 2 * parent + 1;
        while (child < len) {
            if (child + 1 < len && this.elem[child] < this.elem[child + 1]) {
                child++;
            }
            if (this.elem[child] > this.elem[parent]) {
                int tmp = this.elem[child];
                this.elem[child] = this.elem[parent];
                this.elem[parent] = tmp;
                parent = child;
                child = 2 * parent + 1;
            } else {
                break;
            }
        }
    }

    //初始化建立大根堆
    @Override
    public void initHeap(int[] array) {
        for (int i = 0; i < array.length; i++) {
            this.elem[i] = array[i];
            this.usedSize++;
        }
        for (int i = 0; i < (this.elem.length - 2) / 2; i++) {
            AdjustDown(i, this.usedSize);
        }
    }

    //向上调整，从孩子节点开始调整
    @Override
    public void AdjustUp(int child) {
        int parent = (child - 1) / 2;
        while (child > 0) {
            if (this.elem[child] > this.elem[parent]) {
                int tmp = this.elem[child];
                this.elem[child] = this.elem[parent];
                this.elem[parent] = tmp;
                child = parent;
                parent = (child - 1) / 2;
            } else {
                break;
            }
        }
    }

    private boolean isFull() {
        return this.usedSize == this.elem.length;
    }

    // 插入 item 到堆中
    @Override
    public void pushHeap(int item) {
        if (isFull()) {
            this.elem = Arrays.copyOf(this.elem, 2 * usedSize);
        }
        this.elem[this.usedSize++] = item;
        AdjustUp(this.usedSize - 1);
    }

    // 返回堆顶元素，删除数据元素
    @Override
    public int popHeap() {
        if (this.usedSize == 0) {
            throw new UnsupportedOperationException("堆为空");
        }
        int tmp = this.elem[0];
        this.elem[0] = this.elem[this.usedSize - 1];
        this.elem[this.usedSize - 1] = tmp;
        this.usedSize--;
        AdjustDown(0, this.usedSize);
        return tmp;
    }

    // 返回堆顶元素，不删除数据元素
    @Override
    public int getHeapTop() {
        if (this.usedSize == 0) {
            throw new UnsupportedOperationException("堆为空");
        }
        return this.elem[0];
    }

    public void HeapSort2(int[] array) {
        initHeap(array);//AdjustDown
        int end = this.usedSize;
        while (end > 0) {
            int tmp = this.elem[0];
            this.elem[0] = this.elem[end];
            this.elem[end] = tmp;
            AdjustDown(0, end);
            end--;
        }
    }

    //堆排序
    @Override
    public void HeapSort() {
        //AdjustUp
        int end = this.usedSize - 1;
        while (end > 0) {
            int tmp = this.elem[0];
            this.elem[0] = this.elem[end];
            this.elem[end] = tmp;
            AdjustDown(0, end);
            end--;
        }
    }

    //打印堆
    @Override
    public void show() {
        for (int i = 0; i < this.usedSize; i++) {
            System.out.print(this.elem[i] + " ");
        }
        System.out.println();
    }
}
