package Graph;

import java.util.*;

// 最小生成树 K算法
public class Kruskal {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 返回最小的花费代价使得这n户人家连接起来
     *
     * @param n    int n户人家的村庄
     * @param m    int m条路
     * @param cost int二维数组 一维3个参数，表示连接1个村庄到另外1个村庄的花费的代价
     * @return int
     */
    public static int miniSpanningTree(int n, int m, int[][] cost) {
        // write code here
        Graph graph = createGraph(cost);
        PriorityQueue<Edge> pqueue = new PriorityQueue<>(new compareEdges());
        for (Edge edge : graph.edges) {
            pqueue.add(edge);
        }
        Querry querry = new Querry(graph.nodes);
        int res = 0;
        while (!pqueue.isEmpty()) {
            Edge cur = pqueue.poll();
            if (!querry.inSameSet(cur.from, cur.to)) {
                querry.union(cur.from, cur.to);
                res += cur.weight;
            }
        }
        return res;
    }

    public static class Querry {
        HashMap<Node, List<Node>> mapSet = new HashMap<>();

        // 初始化querry类
        public Querry(HashMap<Integer, Node> Nodes) {
            for (Node cur : Nodes.values()) {
                List<Node> set = new ArrayList<>();
                set.add(cur);
                mapSet.put(cur, set);
            }
        }

        // 判断结点是否在一个集合内
        public boolean inSameSet(Node fromNode, Node toNode) {
            List<Node> fromSet = mapSet.get(fromNode);
            List<Node> toSet = mapSet.get(toNode);
            return fromSet == toSet;
        }

        // 合并结点所在集合
        public void union(Node fromNode, Node toNode) {
            List<Node> fromSet = mapSet.get(fromNode);
            List<Node> toSet = mapSet.get(toNode);
            for (Node cur : toSet) {
                fromSet.add(cur);
                mapSet.put(cur, fromSet);
            }
        }
    }

    public static class compareEdges implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static class Node {
        int value;
        int in;
        int out;
        List<Node> nexts;
        List<Edge> edges;

        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    public static class Edge {
        int weight;
        Node from;
        Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    public static class Graph {
        HashMap<Integer, Node> nodes;
        HashSet<Edge> edges;

        public Graph() {
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }

    public static Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int[] value : matrix) {
            Integer from = value[0];
            Integer to = value[1];
            Integer weight = value[2];
            if (!graph.nodes.containsKey(from)){
                graph.nodes.put(from, new Node(from));
            }
            if(!graph.nodes.containsKey(to)){
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            graph.nodes.put(from, fromNode);
            graph.nodes.put(to, toNode);

            Edge newEdge1 = new Edge(weight, fromNode, toNode);
            graph.edges.add(newEdge1);

            fromNode.out++;
            fromNode.nexts.add(toNode);
            fromNode.edges.add(newEdge1);
            toNode.in++;

            Edge newEdge2 = new Edge(weight, toNode, fromNode);
            graph.edges.add(newEdge2);

            toNode.out++;
            toNode.nexts.add(fromNode);
            toNode.edges.add(newEdge2);
            fromNode.in++;
        }
        return graph;
    }
    public static void main(String[] args) {
        int[][] cost = new int[3][3];
        cost[0] = new int[]{1, 3, 3};  //  [[1,3,3],[1,2,1],[2,3,1]]
        cost[1] = new int[]{1, 2, 1};
        cost[2] = new int[]{2, 3, 1};
        int res = miniSpanningTree(3, 3, cost);
        System.out.println(res);
    }
}
