package Graph;

public class Test {
    public static void main(String[] args) {
        GraphMatrix graphMatrix = new GraphMatrix();
        graphMatrix.createGraph();
        graphMatrix.clearGraph();
        System.out.println("该图的邻接矩阵如下：");
        //graphMatrix.displayGraph();
        graphMatrix.TSP();

    }
}
