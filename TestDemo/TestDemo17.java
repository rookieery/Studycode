package TestDemo;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TestDemo17 {
    private static void code1() {
        Stack<String> stack = new Stack<>();
        stack.push("Java");
        stack.push("C++");
        stack.push("Python");
        stack.push("Golang");
        System.out.println("栈的大小："+stack.size());
        System.out.println("栈是否为空："+stack.isEmpty());
        System.out.println("观察栈顶："+stack.peek());//Golang
        System.out.println("出栈："+stack.pop());
        System.out.println("出栈："+stack.pop());
        System.out.println("出栈："+stack.pop());
        System.out.println("出栈："+stack.pop());
        //EmptyStackException
        System.out.println("出栈："+stack.pop());
    }
    private static void code2() {
        Queue<String> queue = new ArrayDeque<>();
        queue.add("Java");
        queue.add("C++");
        queue.add("C#");
        queue.add("PHP");
        System.out.println("队列是否为空："+queue.isEmpty());
        System.out.println("队列元素个数："+queue.size());
        System.out.println("出队列："+queue.poll());//Java
        System.out.println("观察队头："+queue.peek());//C++
        System.out.println("出队列："+queue.poll());
        System.out.println("出队列："+queue.poll());
        System.out.println("出队列："+queue.poll());
        System.out.println("出队列："+queue.poll());
    }
    public static void main(String[] args) {
        code2();
    }
}
