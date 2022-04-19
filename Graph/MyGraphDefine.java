/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;
import java.util.*;

/**
 *
 * @author Xinnze
 */
public class MyGraphDefine {
    // 定义图中的点
    public static class Node{
        public int value;  // 点的值
        public int in;  // 点的入度
        public int out;  // 点的出度
        public ArrayList<Node> nexts;  // Node的直接邻居
        public ArrayList<Edge> edges;  // 从Node 发散 出去的边的集合
        
        public Node(int value){
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }
    
    // 定义图中的边
    public static class Edge{
        int weight;  // 边的权重
        Node from; // 从哪个点
        Node to;  // 到哪个点
        
        public Edge(int weight, Node from, Node to){
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }
    
    // 定义图 图为点的集合加边的集合
    public static class Graph{
        public HashMap<Integer, Node> nodes;  // 编号对应结点
        public HashSet<Edge> edges;
        
        public Graph(){
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }
}
