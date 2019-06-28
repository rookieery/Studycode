package Graph;

import java.util.Scanner;

public class GraphMatrix implements IGraph {
    private static final int MAX_NUM = 20;
    private static final int MAX_VALUE = 65535;
    char[] Vertex = new char[MAX_NUM];
    int GType = 0;
    int VertexNum = 5 ;
    int EdgeNum = 10;
    int bestc= MAX_VALUE;
    int cc = 0;
    int[][] EdgeWeigh = new  int[MAX_NUM][MAX_NUM];
    int[] isTrav = {0,0,0,0,0};
    int[] x = new int[VertexNum];
    int[] bestx = new int[VertexNum];
    public GraphMatrix() {
        for (int i = 0; i < VertexNum; i++) {
            x[i] = i;
        }
    }
    @Override
    public void createGraph() {
        Scanner input = new Scanner(System.in);
        int weigh;
        char EstartV,EendV;
        System.out.println("输入图中各顶点信息");
        for (int i = 0; i < VertexNum; i++) {
            System.out.println("第"+(i+1)+"个顶点");
            Vertex[i] = (input.next().toCharArray())[0];
        }
        System.out.println("输入构成各条边的顶点以及权值");
        for (int i = 0; i <EdgeNum ; i++) {
            int j,k;
            System.out.println("第"+(i+1)+"条边");
            EstartV = input.next().charAt(0);
            EendV = input.next().charAt(0);
            weigh = input.nextInt();
            for ( j = 0; EstartV != Vertex[j]; j++) ;
            for ( k = 0; EendV != Vertex[k]; k++) ;
            EdgeWeigh[j][k] = weigh;
            if (GType == 0) {
                EdgeWeigh[k][j] = weigh;
            }
        }
    }

    @Override
    public void clearGraph() {
        for (int i = 0; i < VertexNum; i++) {
                EdgeWeigh[i][i] = MAX_VALUE;
        }
    }

    @Override
    public void displayGraph() {
        System.out.print(" "+"\t");
        for (int i = 0; i < VertexNum; i++) {
            System.out.print(Vertex[i]+"\t");
        }
        System.out.println("");
        for (int i = 0; i < VertexNum; i++) {
            System.out.print(Vertex[i]+"\t");
            for (int j = 0; j < VertexNum; j++) {
                if (EdgeWeigh[i][j] == MAX_VALUE) {
                    System.out.print(0+"\t");
                }
                else {
                    System.out.print(EdgeWeigh[i][j]+"\t");
                }
            }
            System.out.println("");
        }
    }

    @Override
    public void deepTraOne(int n) {
        isTrav[n] = 1;
        System.out.println("->"+Vertex[n]);
        for (int i = 0; i < VertexNum; i++) {
            if (EdgeWeigh[n][i] != MAX_VALUE && isTrav[n] == 0) {
                deepTraOne(i);
            }
        }
    }
    public void backtrack(int i){
        if (i == VertexNum - 1) {
            if (  EdgeWeigh[x[i-1]][x[i]] != MAX_VALUE && EdgeWeigh[x[i]][x[1]] != MAX_VALUE && (bestc > cc + EdgeWeigh[x[i-1]][x[i]] +EdgeWeigh[x[i]][x[0]] || bestc == MAX_VALUE)) {
                bestc = cc + EdgeWeigh[x[i-1]][x[i]] +EdgeWeigh[x[i]][x[1]]; //保存最优值
                for (int j = 0; j < VertexNum; j++)
                {
                    bestx[j] = x[j]; //保存最优解
                }
            }
        }
        else {
            for (int j = i; j < VertexNum; j++) {
                if ((EdgeWeigh[x[i-1]][x[j]] != MAX_VALUE)&&(cc + EdgeWeigh[x[i-1]][x[j]] < bestc || bestc == MAX_VALUE)) {
                    int a = x[i];
                    x[i] = x[j];
                    x[j] = a;
                    cc += EdgeWeigh[x[i-1]][x[i]];
                    backtrack(i+1);
                    cc -= EdgeWeigh[x[i-1]][x[i]];
                    int b = x[i];
                    x[i] = x[j];
                    x[j] = b;
                }
            }
        }
    }
    public void TSP() {
        GraphMatrix graphMatrix = new GraphMatrix();
        backtrack(1);
        System.out.println("最短路程为："+bestc);
        System.out.print("最短路线为：");
        for (int i = 0; i < VertexNum ; i++) {
            System.out.print(bestx[i]+1+" ");
        }
        System.out.println(bestx[0]+1);
    }
}
