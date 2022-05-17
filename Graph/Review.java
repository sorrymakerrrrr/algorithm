package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Review {

    // 点集合
    public static class Node {
        int val;
        int in;
        int out;
        List<Edge> edges;
        List<Node> nexts;

        public Node(int val) {
            this.val = val;
            in = 0;
            out = 0;
            edges = new ArrayList<>();
            nexts = new ArrayList<>();
        }
    }

    // 边集合
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

    // 图
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
        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][2];
            int from = matrix[i][0];
            int to = matrix[i][1];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);

            fromNode.out++;
            fromNode.nexts.add(toNode);
            fromNode.edges.add(newEdge);

            toNode.in++;

            graph.edges.add(newEdge);
        }
        return graph;
    }

    public static int findShortestPath(int n, int m, int[][] matrix) {
        Graph graph = createGraph(matrix);
        Node head = graph.nodes.get(1);
        HashMap<Node, Integer> map = new HashMap<>();
        HashSet<Node> usedSet = new HashSet<>();
        map.put(head, 0);
        Node minNode = findMinNodeNotUsed(map, usedSet);
        while (minNode != null) {
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (map.containsKey(toNode)) {
                    map.put(toNode, Math.min(map.get(toNode), map.get(minNode) + edge.weight));
                } else {
                    map.put(toNode, map.get(minNode) + edge.weight);
                }
            }
            usedSet.add(minNode);
            minNode = findMinNodeNotUsed(map, usedSet);
        }
        return map.containsKey(graph.nodes.get(n)) ? map.get(graph.nodes.get(n)) : -1;
    }

    public static Node findMinNodeNotUsed(HashMap<Node, Integer> map, HashSet<Node> set) {
        int mininum = Integer.MAX_VALUE;
        Node res = null;
        for (Node node : map.keySet()) {
            if (!set.contains(node) && map.get(node) < mininum) {
                res = node;
                mininum = map.get(node);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[5][3];
        matrix[0] = new int[]{1, 2, 2};
        matrix[1] = new int[]{1, 4, 5};
        matrix[2] = new int[]{2, 3, 3};
        matrix[3] = new int[]{3, 5, 4};
        matrix[4] = new int[]{4, 5, 5};
        System.out.println(findShortestPath(5, 5, matrix));
    }
}
