package Graph;

import java.util.*;


public class Prim {
    public static int miniSpanningTree (int n, int m, int[][] cost) {
        // write code here
        Graph graph = createGraph(cost);
        HashSet<Node> nodeSet = new HashSet<>();
        // 选取一个结点
        Node curNode = graph.nodes.get(Integer.valueOf(cost[0][1]));
        nodeSet.add(curNode);
        PriorityQueue<Edge> heap = new PriorityQueue<>(new edgeCompare());
        int res = 0;
        for(Edge edge : curNode.edges){
            heap.add(edge);
        }
        while (!heap.isEmpty()){
            Edge curEdge = heap.poll();
            curNode = curEdge.to;
            if (!nodeSet.contains(curNode)){
                for(Edge edge : curNode.edges){
                    heap.add(edge);
                }
                nodeSet.add(curEdge.to);
                res += curEdge.weight;
            }
        }
        return res;
    }

    public static class Node {
        int value;
        int in;
        int out;
        List<Node> nexts = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        public Node(int value) {
            this.value = value;
        }
    }

    public static class Edge{
        int weight;
        Node from;
        Node to;
        public Edge(int weight, Node from, Node to){
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    public static class Graph{
        HashMap<Integer, Node> nodes;
        HashSet<Edge> edges;
        public Graph(){
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }

    public static Graph createGraph(int[][] matrix){
        Graph graph = new Graph();
        for (int[] value : matrix) {
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

    public static class edgeCompare implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2){
            return o1.weight - o2.weight;
        }
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
