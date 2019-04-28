package 顺序表;

public interface Isequence {
    boolean add(int pos,Object data);
        int search(Object key);
        boolean contains(Object key);
        Object getPos(int pos);
        Object remove(Object key);
        int size();
        void display();
        void clear();
}
