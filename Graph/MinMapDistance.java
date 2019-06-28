package Graph;


import java.util.*;

public class MinMapDistance {
    //迪杰斯特拉算法
    private static Map<Integer, Integer> dijkstra(Graph graph) {
        //创建距离表，储存从起点到每一个顶点的临时距离
        Map<Integer, Integer> distanceMap = new HashMap<>();
        //记录遍历过的顶点
        Set<Integer> accessedSet = new HashSet<>();
        //图的顶点数量
        int size = graph.vertexes.length;
        //初始化最短路劲表，到达每个顶点的距离默认为无穷大
        for (int i = 1; i < size; i++) {
            distanceMap.put(i, Integer.MAX_VALUE);
        }
        //遍历起点，刷新距离表
        accessedSet.add(0);
        List<Edge> edgesFromStart = graph.adj[0];
        for (Edge edge : edgesFromStart) {
            distanceMap.put(edge.index, edge.weight);
        }
        //主循环，重复遍历最短路径顶点和刷新距离表
        for (int i = 1; i < size; i++) {
            //寻找最短路径顶点
            int minDistanceFromStart = Integer.MAX_VALUE;//最短路径
            int minDistanceIndex = -1;//最短路径的顶点
            for (int j = 1; j < size; j++) {
                if (!accessedSet.contains(j) && distanceMap.get(j) < minDistanceFromStart) {
                    minDistanceFromStart = distanceMap.get(j);
                    minDistanceIndex = j;
                }
            }
            //每个顶点都以及遍历过
            if (minDistanceIndex == -1) {
                break;
            }
            //刷新距离表
            accessedSet.add(minDistanceIndex);
            for (Edge edge : graph.adj[minDistanceIndex]) {
                if (accessedSet.contains(edge.index)) {
                    continue;
                }
                int weigh = edge.weight;
                int preDistance = distanceMap.get(edge.index);
                if (weigh != Integer.MAX_VALUE && (minDistanceFromStart + weigh < preDistance)) {
                    distanceMap.put(edge.index, minDistanceFromStart + weigh);//最短距离累加
                }
            }
        }
        return distanceMap;
    }

    private static void initGraph(Graph graph) {
        graph.vertexes[0] = new Vertex("1");
        for (int i = 0; i < graph.vertexes.length; i++) {
            graph.vertexes[i] = new Vertex(String.valueOf(i + 1));
        }
        System.out.println("请输入邻接矩阵：");
        int[][] arr = new int[graph.vertexes.length][graph.vertexes.length];
        for (int i = 0; i < arr.length; i++) {
            Scanner scanner = new Scanner(System.in);
            String strLine = scanner.nextLine();
            Scanner s = new Scanner(strLine);
            int j = 0;
            while (s.hasNextInt()) {
                arr[i][j++] = s.nextInt();
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    graph.adj[i].add(new Edge(j,arr[i][j]));
                }
            }
        }
    }

    //图的顶点
    private static class Vertex {
        String data;//顶点名字

        Vertex(String data) {
            this.data = data;
        }
    }

    //图的边
    private static class Edge {
        int index;//顶点数组下标
        int weight;//边的权重

        Edge(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }

    //图
    private static class Graph {
        private Vertex[] vertexes;
        private List<Edge>[] adj;

        Graph(int size) {//初始化顶点和邻接矩阵
            vertexes = new Vertex[size];
            adj = new LinkedList[size];
            for (int i = 0; i < adj.length; i++) {
                adj[i] = new LinkedList<>();
            }
        }
    }
    public static void code() {
        System.out.print("请输入图的顶点数量：");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        Graph graph = new Graph(input);
        initGraph(graph);
        Map<Integer, Integer> distanceMap = dijkstra(graph);//泛型Map，第一个表示endIndex，第二个表示两者之间的最短距离
        System.out.println("结果如下：");
        for (int i = 1; i < 6; i++) {
            int dintance = distanceMap.get(i);
            System.out.println("点1到点" + (i + 1) + "的最短路径长度为；" + dintance);
        }
    }

    public static void main(String[] args) {
        code();
    }
}
