package Graph;

import java.util.*;

import Graph.MyGraphDefine.*;

public class GraphDFS {
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
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }

    public static void DFS(Node node) {
        if (node == null) return;
        Stack<Node> st = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        st.push(node);
        set.add(node);
        System.out.println(node.value);
        while (!st.isEmpty()) {
            Node cur = st.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    st.push(cur);
                    st.push(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
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
        DFS(graph.nodes.get(1));
    }
}
