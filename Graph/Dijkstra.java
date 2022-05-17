package Graph;

import java.util.*;


public class Dijkstra {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param n int 顶点数
     * @param m int 边数
     * @param graph int二维数组 一维3个数据，表示顶点到另外一个顶点的边长度是多少
     * @return int
     */
    public static int findShortestPath (int n, int m, int[][] graph) {
        // write code here
        Graph myGraph = createGraph(graph);
        Node head = myGraph.nodes.get(1);
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        HashSet<Node> usedNodeSet = new HashSet<>();
        distanceMap.put(head, 0);
        Node minNode = findMinDistanceNotUsed(distanceMap, usedNodeSet);  // 寻找不在已使用点的集合里面的最小结点
        while(minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                }else {
                    distanceMap.put(toNode, Math.min(distance + edge.weight, distanceMap.get(toNode)));
                }
            }
            usedNodeSet.add(minNode);
            minNode = findMinDistanceNotUsed(distanceMap, usedNodeSet);
        }
        Node nodeN = myGraph.nodes.get(n);
        return distanceMap.containsKey(nodeN) ? distanceMap.get(nodeN) : -1;
    }

    public static Node findMinDistanceNotUsed(HashMap<Node, Integer> distance, HashSet<Node> usedNodeSet) {
        Node res = null;
        Integer minNum = Integer.MAX_VALUE;
        for (Node i : distance.keySet()) {
            if (!usedNodeSet.contains(i) && distance.get(i) < minNum) {
                    res = i;
                    minNum = distance.get(i);
            }
        }
        return res;
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
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashSet<Edge> edges = new HashSet<>();
    }

    public static Graph createGraph(int[][] matrix){
        Graph graph = new Graph();
        for (int[] value : matrix){
            Integer from = value[0];
            Integer to = value[1];
            Integer weight = value[2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            graph.edges.add(newEdge);
            fromNode.out++;
            fromNode.nexts.add(toNode);
            fromNode.edges.add(newEdge);
            toNode.in++;
        }
        return graph;
    }

    public static class edgeCompare implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[7][3];
        matrix[0] = new int[]{1, 1, 3};
        matrix[1] = new int[]{1, 1, 2};
        matrix[2] = new int[]{1, 1, 4};
        matrix[3] = new int[]{1, 3, 2};
        matrix[4] = new int[]{1, 2, 4};
        matrix[5] = new int[]{1, 3, 7};
        matrix[6] = new int[]{1, 2, 16};
        int res = findShortestPath(3, 7, matrix);
        System.out.println(res);
    }
}
