package Graph;

import java.util.Scanner;

public class Tourist {
    public static void tourist() {
        System.out.print("请输入顶点数量：");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] dis = new int[n][n];
        int[][] f = new int[1 << n][n];
        int[][] pos = new int[1 << n][n];
        System.out.println("请输入邻接矩阵：");
        for (int i = 0; i < n; i++) {
            Scanner scanner1 = new Scanner(System.in);
            String strLine = scanner1.nextLine();
            Scanner s = new Scanner(strLine);
            int j = 0;
            while (s.hasNextInt()) {
                dis[i][j++] = s.nextInt();
            }
        }
        int b = 1 << (n - 1);
        for (int i = 1; i < f.length; i++) {
            for (int j = 0; j < f[i].length; j++) {
                f[i][j] = -1;
            }
        }
        for (int i = 0; i < n; i++) {//边界处理
            f[0][i] = dis[i][0];//st==0，表示都已经访问完了，剩下路程就是从i点回到0点
        }
        for (int st = 1; st < b - 1; st++) {
            for (int i = 1; i < n; i++) {
                //如果i点在集合中没有被访问，那么f[st][i]这个状态就不成立
                if ((st & (1 << (i - 1))) != 0) {
                    continue;
                }
                int minn = Integer.MAX_VALUE;
                for (int j = 1; j < n; j++) {//枚举从i点要去j点
                    if ((st & (1 << (j - 1))) != 0) {//保证j点在集合st中
                        int tmp = dis[i][j] + f[st ^ (1 << (j - 1))][j];//把j点从st集合中去掉
                        if (tmp < minn) {//更新最小值
                            minn = tmp;
                            f[st][i] = tmp;
                            pos[st][i] = j;//记录路径
                        }
                    }
                }
            }
        }
        int minn = Integer.MAX_VALUE;
        for (int k = 1; k < n; k++) {
            int tmp = f[(b - 1) ^ (1 << (k - 1))][k] + dis[0][k];//最小路径汇总
            if (tmp < minn) {
                minn = tmp;
                f[b - 1][0] = tmp;
                pos[b - 1][0] = k;
            }
        }
        System.out.println("最短路径如下：");
        System.out.print("1->");
        for (int st = b - 1, next = 0; st != 0; ) {
            next = pos[st][next];
            System.out.print((next + 1) + "->");
            st = st ^ (1 << (next - 1));
        }
        System.out.print("1");
        System.out.println();
        System.out.println("总最短路径长度为：");
        System.out.println(f[b - 1][0]);
    }
}
