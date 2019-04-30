package 链表;

public interface IDoubleLinked {
    void addFirst(int data);
    void addLast(int data);
    boolean addindex(int index,int data);
    boolean contains(int key);
    int remove(int key);
    void removeAllkey(int key);
    int getLength();
    void display();
    void clear();
}
