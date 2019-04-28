package 顺序表;

import 顺序表.Mysequence;

public class Test {
        public static void main(String[] args) {
            Mysequence mySequence = new Mysequence();
            mySequence.add(0,18);
            mySequence.add(1,"bit");
            mySequence.add(2,"hello");
            mySequence.add(1,99);
            mySequence.add(2,18);
            mySequence.display();//18  99 bit hello
            System.out.println(mySequence.remove("bit"));
            mySequence.display();//18  99  hello
            System.out.println(mySequence.contains("gao"));//true
            mySequence.removeAll(18);
            mySequence.display();
        }
    }
