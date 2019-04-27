package 小游戏;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: GAOBO
 * Date: 2019-04-06
 * Time: 14:45
 */
//组合    a part of
public class Game {
    private Person person;
    private Computer computer;
    public Game() {
        person = new Person("bit");
        computer = new Computer("alphago");
    }

    public void playThreeTime() {
        int count = 0;
        while(count < 3) {
            String pFist = person.fist();
            String cFist = computer.fist();
            if(pFist.equals("石头") && cFist.equals("剪刀")
                    ||pFist.equals("剪刀") && cFist.equals("布") ||
                    pFist.equals("布") && cFist.equals("石头")) {
                System.out.println(person.getName()+" 此局获胜");
                person.addScore();
            }else if(pFist.equals(cFist)) {
                System.out.println("此局平局");
            }else{
                System.out.println("alpha获胜");
                computer.addScore();
            }
            count++;
        }
    }

    public void getResult() {
        int pScore = person.getScore();
        int cScore = computer.getScore();
        if(pScore > cScore) {
            System.out.println(person.getName() + "：最终结果赢了");
        }else if(pScore == cScore){
            System.out.println("平局");
        }else{
            System.out.println(computer.getName() + "：最终结果赢了");
        }
        System.out.println(person.getName()+":"+computer.getName()+
                "===>"+person.getScore()+":"+computer.getScore());
    }

    public void start(){
        while(true) {
            playThreeTime();
            getResult();
            System.out.println("是否继续？");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.next();
            if (str.equals("是")) {
                continue;
            }else{
                break;
            }
        }

    }
}
