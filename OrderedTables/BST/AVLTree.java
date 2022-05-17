package OrderedTables.BST;

import java.lang.Math;

public class AVLTree extends AbstractSelfBalancingBinarySearchTree {

    protected static class AVLNode extends Node {
        public int height;

        public AVLNode(int value, Node parents, Node left, Node right) {
            super(value, parents, left, right);
        }
    }

    // node1 与 node2 最大的高度
    private int maxHeight(AVLNode node1, AVLNode node2) {
        if (node1 != null && node2 != null) {
            return Math.max(node1.height, node2.height);
        } else if (node1 == null) {
            return node2 != null ? node2.height : -1;
        } else if (node2 == null) {
            return node1 != null ? node1.height : -1;
        }
        return -1;
    }

    private static final void updateHeight(AVLNode node) {
        int leftHeight = (node.left == null) ? -1 : ((AVLNode) node.left).height;
        int rightheight = (node.right == null) ? -1 : ((AVLNode) node.right).height;
        node.height = Math.max(leftHeight, rightheight) + 1;
    }

    @Override
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new AVLNode(value, parent, left, right);
    }

    // 从调整节点开始一直到头节点 即波及到的节点一直要调整
    private void rebalance(AVLNode node) {
        while (node != null) {
            Node parent = node.parent;

            int leftHeight = (node.left == null) ? -1 : ((AVLNode) node.left).height;
            int rightHeight = (node.right == null) ? -1 : ((AVLNode) node.right).height;
            int nodeBalance = rightHeight - leftHeight;

            // 为RR / RL 型
            if (nodeBalance == 2) {
                int rightLeftHeight = (node.right.left == null) ? -1 : ((AVLNode) node.right.left).height;
                int rightRightHeight = (node.right.right == null) ? -1 : ((AVLNode) node.right.right).height;
                if (rightRightHeight > rightLeftHeight) {  // RR
                    node = (AVLNode) avlRotateLeft(node);
                } else {  // RL
                    node = (AVLNode) doubleRotateRightLeft(node);
                }
                break;
            } else if (nodeBalance == -2) {  // 为LL / LR 型
                int leftLeftHeight = (node.left.left == null) ? -1 : ((AVLNode) node.left.left).height;
                int leftRightHeight = (node.left.right == null) ? -1 : ((AVLNode) node.left.right).height;
                if (leftLeftHeight > leftRightHeight) {  // LL
                    node = (AVLNode) avlRotateRight(node);
                } else {
                    node = (AVLNode) doubleRotateLeftRight(node);
                }
                break;
            } else {  // 若没有中 只需调整当前的节点的高度即可
                updateHeight(node);
            }

            node = (AVLNode) parent;
        }
    }

    private Node avlRotateLeft(Node node) {
        Node temp = super.rotateLeft(node);

        // 左旋后 现在这棵树的头节点和头节点的左孩子高度改变 从下往上更新高度
        updateHeight((AVLNode) temp.left);
        updateHeight((AVLNode) temp);
        return temp;
    }

    private Node avlRotateRight(Node node) {
        Node temp = super.rotateRight(node);

        updateHeight((AVLNode) temp.right);
        updateHeight((AVLNode) temp);
        return temp;
    }

    private Node doubleRotateRightLeft(Node node) {
        node.right = avlRotateRight(node.right);
        return avlRotateLeft(node);
    }

    private Node doubleRotateLeftRight(Node node) {
        node.left = avlRotateLeft(node.left);
        return avlRotateRight(node);
    }

    // 对于插入的节点来说只需要沿着当前的路依次检查并改正平衡即可
    @Override
    public Node insert(int element) {
        Node newNode = super.insert(element);
        rebalance((AVLNode) newNode);
        return newNode;
    }

    @Override
    public Node delete(int element) {
        Node deleteNode = super.search(element);
        if (deleteNode != null) {
            Node successorNode = super.delete(deleteNode);
            if (successorNode != null) {  // 删除节点左右子树均存在的情况
                // 从波及到的节点开始查违规  (从替换的环境节点开始查)
                AVLNode minimum = successorNode.right != null ? (AVLNode) getMinimum(successorNode) : (AVLNode) successorNode;
                recomputeHeight(minimum);
                rebalance(minimum);
            } else {  // 删除节点至少有一个子树不存在的情况
                recomputeHeight((AVLNode) deleteNode.parent);
                rebalance((AVLNode) deleteNode.parent);
            }
         return deleteNode;
        }
        return null;
    }

    private void recomputeHeight(AVLNode node) {
        while (node != null) {
            node.height = maxHeight((AVLNode) node.left, (AVLNode) node.right) + 1;
            node = (AVLNode) node.parent;
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
//        tree.insert(5);
//        tree.insert(3);
//        tree.insert(11);
//        tree.insert(1);
//        tree.insert(4);
//        tree.insert(7);
//        tree.insert(12);
//        tree.insert(6);
//        tree.insert(9);
//        tree.insert(10);
//        tree.insert(15);
        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * 1000);
            tree.insert(x);
        }
        tree.printTree();
    }
}
