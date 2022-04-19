/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

/**
 *
 * @author Xinnze
 */
import Graph.MyGraphDefine.*;
import java.util.*;

public class GraphBFS {
    public static Graph createGraph(Integer[][] matrix){
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++){
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];

            if (!graph.nodes.containsKey(from)){
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)){
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            toNode.edges.add(newEdge);
        }
        return graph;
    }
    public static void BFS(Node node){
        // 从node出发进行宽度优先遍历
        if (node == null) return ;
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        set.add(node);
        queue.add(node);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            for(Node next : cur.nexts){
                if(!set.contains(next)){
                    set.add(next);
                    queue.add(next);
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
        BFS(graph.nodes.get(1));
    }
}
