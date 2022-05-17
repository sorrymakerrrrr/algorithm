package OrderedTables.BST;

public class AbstractSelfBalancingBinarySearchTree extends AbstractBinarySearchTree {

    // 平衡二叉树左旋
    protected Node rotateLeft(Node node) {
        Node temp = node.right;  // 左旋后的父节点
        temp.parent = node.parent;  // 该节点 -> 原先节点的父节点
        // 原先节点的父节点 ->该节点
        if (temp.parent != null) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }

        node.right = temp.left;
        if (node.right != null) {
            node.right.parent = node;
        }

        temp.left = node;
        node.parent = temp;

        return temp;
    }

    protected Node rotateRight(Node node) {
        Node temp = node.left;
        temp.parent = node.parent;

        if (temp.parent != null) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }

        node.left = temp.right;
        if (node.left != null) {
            node.left.parent = node;
        }
        temp.right = node;
        node.parent = temp;

        return temp;
    }
}
