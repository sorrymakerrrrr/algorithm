package Graph;

import java.util.*;

import Graph.MyGraphDefine.*;

public class TopolopySort {
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (Integer[] value : matrix) {
            Integer weight = value[0];
            Integer from = value[1];
            Integer to = value[2];
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
            fromNode.edges.add(newEdge);
            fromNode.nexts.add(toNode);
            toNode.in++;
            graph.edges.add(newEdge);
        }
        return graph;
    }

    public static List<Integer> sortTopology(Graph graph) {
        // key 某一结点的node
        // value 该节点的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zerosInNode = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zerosInNode.add(node);
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!zerosInNode.isEmpty()){
            Node cur = zerosInNode.poll();
            res.add(cur.value);
            for (Node next : cur.nexts){
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0){
                    zerosInNode.add(next);
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Integer[][] matrix = new Integer[7][3];
        matrix[0] = new Integer[]{1, 1, 3};
        matrix[1] = new Integer[]{1, 1, 2};
        matrix[2] = new Integer[]{1, 1, 4};
        matrix[3] = new Integer[]{1, 3, 2};
        matrix[4] = new Integer[]{1, 2, 4};
        matrix[5] = new Integer[]{1, 3, 7};
        matrix[6] = new Integer[]{1, 2, 16};
//        System.out.println(matrix[0][1]);
        Graph graph = createGraph(matrix);
        List<Integer> res = sortTopology(graph);
        System.out.println(res);
    }
}
