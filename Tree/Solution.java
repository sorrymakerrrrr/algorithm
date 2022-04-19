/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

/**
 *  判断是否为满二叉树
 * @author Xinnze
 */
public class Solution {
    
  
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }
 
    public static class Info{
    boolean isFull;
    int level;
    public Info(boolean isFull, int level){
        this.isFull = isFull;
        this.level = level;
        }
    }
    
    public static Info isFullTree(TreeNode node){
    if (node == null){
        return new Info(true, 0);
        }
    Info leftData = isFullTree(node.left);
    Info rightData = isFullTree(node.right);
    boolean isFull = false;
    int level = Math.max(isFullTree(node.left).level, isFullTree(node.right).level) + 1;
    if((isFullTree(node.left).isFull) && (isFullTree(node.right).isFull) 
       && (isFullTree(node.left).level == isFullTree(node.right).level)){
        isFull = true;
        }
    return new Info(isFull, level);
    }
    
    public boolean judgeIt (TreeNode root) {
        // write code here
        Info res = isFullTree(root);
        return res.isFull;
    }
    public static void main(String[] args) {
        System.out.println("1");
    }
}
