package BinaryTree;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Heap heap = new TestHeap();
        heap.pushHeap(4);
        heap.pushHeap(3);
        heap.pushHeap(9);
        heap.pushHeap(6);
        heap.pushHeap(7);
        heap.pushHeap(13);
        heap.pushHeap(15);
        heap.pushHeap(2);
        heap.pushHeap(1);
        heap.HeapSort();
        heap.show();
    }
}
