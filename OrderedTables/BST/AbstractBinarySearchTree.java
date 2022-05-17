package OrderedTables.BST;

/**
 * 复刻一般算法
 * 实现搜索二叉树的增删查
 */
public class AbstractBinarySearchTree {
    // 树节点的定义
    public static class Node {
        public Node(Integer value, Node parent, Node left, Node right) {
            super();
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public Integer value;
        public Node parent;
        public Node left;
        public Node right;
    }

    public Node root;  // 根节点

    protected int size;    // 节点个数

    // 创建节点
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new Node(value, parent, left, right);
    }

    // 通过元素查找搜索二叉树中是否存在节点 返回该节点
    public Node search(int element) {
        Node curNode = root;
        while (curNode != null && curNode.value != null && curNode.value != element) {
            if (element < curNode.value) {
                curNode = curNode.left;
            } else {
                curNode = curNode.right;
            }
        }
        return curNode;
    }

    // 搜索二叉树插入一个节点 返回插入的节点
    public Node insert(int element) {
        if (root == null) {
            root = createNode(element, null, null, null);
            size++;
            return root;
        }

        Node insertParentNode = null;
        Node searchTmpNode = root;
        // 寻找节点应该存放在哪个位置
        while (searchTmpNode != null && searchTmpNode.value != null) {
            insertParentNode = searchTmpNode;
            if (element < searchTmpNode.value) {
                searchTmpNode = searchTmpNode.left;
            } else if (element > searchTmpNode.value) {
                searchTmpNode = searchTmpNode.right;
            } else {
                return null;
            }
        }
        Node newNode = createNode(element, insertParentNode, null, null);
        if (insertParentNode.value > newNode.value) {
            insertParentNode.left = newNode;
        } else {
            insertParentNode.right = newNode;
        }

        size++;
        return newNode;
    }

    // 删除节点 若删除节点存在 返回删除节点 否则返回空
    public Node delete(int element) {
        Node deleteNode = search(element);
        if (deleteNode != null) {
            return delete(deleteNode);
        } else {
            return null;
        }
    }

    // 当节点已经找到时删除节点 返回代替该节点环境的新节点
    protected Node delete(Node deleteNode) {
        if (deleteNode != null) {
            Node nodeToReturn = null;
            // 第一种情况 左右孩子都为空
            if (deleteNode.left == null && deleteNode.right == null) {
                nodeToReturn = transplant(deleteNode, null);
            } else if (deleteNode.left == null) {
                nodeToReturn = transplant(deleteNode, deleteNode.right);
            } else if (deleteNode.right == null) {
                nodeToReturn = transplant(deleteNode, deleteNode.left);
            } else {  // nodeToReturn 既有左子树又有右子树 找到其右子树的最左位置 替换nodeToReturn的环境
                Node successorNode = deleteNode.right;
                while (successorNode.left != null) {
                    successorNode = successorNode.left;
                }
                // 如果寻找到的节点不是删除节点的右孩子， 则需要将寻找节点的环境替换成寻找节点的右孩子
                if (successorNode.parent != deleteNode) {
                    transplant(successorNode, successorNode.right);
                    successorNode.right = deleteNode.right;
                    successorNode.right.parent = successorNode;
                }
                transplant(deleteNode, successorNode);
                successorNode.left = deleteNode.left;
                successorNode.left.parent = successorNode;
                nodeToReturn = successorNode;
            }
            size--;
            return nodeToReturn;
        }
        return null;
    }

    // 将nodeToReplace的环境由newNode替代 ,并且nodeToReplace从树中删去
    private Node transplant(Node nodeToReplace, Node newNode) {
        if (nodeToReplace.parent == null) {
            this.root = newNode;
        } else if (nodeToReplace == nodeToReplace.parent.left) {
            nodeToReplace.parent.left = newNode;
        } else {
            nodeToReplace.parent.right = newNode;
        }
        if (newNode != null) {
            newNode.parent = nodeToReplace.parent;
        }
        return newNode;
    }

    protected Node getMinimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void printNodeValue(Node node) {
        if (node.value == null) {
            System.out.print("<null>");
        } else {
            System.out.print(node.value.toString());
        }
        System.out.println();
    }

    // 打印一棵树 先画右子树 再画自己 最后画左子树
    public void printTree(Node node, boolean isRight, String indent) {
        if (node.right != null) {
            printTree(node.right, true, indent + (isRight ? "        " : " |      "));
        }
        System.out.print(indent);
        if (isRight) {
            System.out.print(" /");
        } else {
            System.out.print(" \\");
        }
        System.out.print("----- ");
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, indent + (isRight ? " |      " : "        "));
        }
    }
    public void printSubTree(Node node) {
        if (node.right != null) {
            printTree(node.right, true, "");
        }
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, "");
        }
    }
    public void printTree() {
        printSubTree(root);
    }
}
