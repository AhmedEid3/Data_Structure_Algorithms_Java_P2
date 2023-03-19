package ds;

import java.util.ArrayList;

public class BinarySearchTree<T> extends BaseTree<T> implements ITree<T> {
    private class NodeAndParent extends Node {
        Node parent;
        boolean isLeft;

        public NodeAndParent(Node node, boolean isLeft, Node parent) {
            super(node.value);
            left = node.left;
            right = node.right;
            this.isLeft = isLeft;
            this.parent = parent;
        }

        @Override
        public String toString() {
            StringBuffer out = new StringBuffer("Node=" + value + ", left=" + left + ", right=" + right
                    + ", isLeft=" + isLeft);
            return parent != null ? out.append(", parent=" + parent.value).toString() : out.toString();
        }
    }

    public void insert(T item) {
        var newNode = new Node(item);
        if (root == null) {
            root = newNode;
            return;
        }

        // assign root to currentNode
        var currentNode = root;
        // iterative throw tree start from root
        while (currentNode != null) {
            // if value of newNode < currentNode && currentNode.left == null:
            if (((int) newNode.value) < ((int) currentNode.value)) {
                if (currentNode.left == null) {
                    currentNode.left = newNode;
                    break;
                }
                currentNode = currentNode.left;
            }

            if (((int) newNode.value) > ((int) currentNode.value)) {
                if (currentNode.right == null) {
                    currentNode.right = newNode;
                    break;
                }
                currentNode = currentNode.right;
            }
        }

    }

    public void balance() {
        ArrayList<T> nodes = toSortedArray();
        root = recursiveBalance(0, nodes.size() - 1, nodes);
    }

    private Node recursiveBalance(int start, int end, ArrayList<T> nodes) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        var newNode = new Node<T>((T) nodes.get(mid));
        newNode.left = recursiveBalance(start, mid - 1, nodes);
        newNode.right = recursiveBalance(mid + 1, end, nodes);

        return newNode;
    }


    public boolean contains(T item) {
        return findNode((int) item) != null ? true : false;
    }

    public void remove(T item) {
        var node = findNode((int) item);
        var parent = node.parent;

        if (isLeaf(node)) {
            if (parent == null) {
                root = null;
            } else if (node.isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }

            node.right = node.left = null;
            return;
        }

        if (hasNodeOneChild(node)) {
            if (node.isLeft) {
                parent.left = node.left != null ? node.left : node.right;
            } else {
                parent.right = node.right != null ? node.right : node.left;
            }
            return;
        }

        // if node has left and right children
        NodeAndParent smallestNode = smallest(node.right, node, false);
        //  remove pointer of parent smallestNode
        if (smallestNode.isLeft) {
            smallestNode.parent.left = null;
        } else {
            smallestNode.parent.right = null;
        }

        smallestNode.left = node.left;

        if (node.value != root.value) {
            node.parent.right = smallestNode;
        } else {
            smallestNode.right = node.right;
            root = smallestNode;
        }
        // delete node
        node.left = node.right = null;
    }

    public int min() {
        return min(root);
    }

    private int min(Node root) {
        if (root == null) return 0;
        if (root.left == null) return (int) root.value;

        return min(root.left);
    }

    public int max() {
        return max(root);
    }

    private int max(Node root) {
        if (root == null) return 0;

        if (root.right == null) return (int) root.value;

        return max(root.right);
    }


    private boolean isLeaf(Node node) {
        if (node == null) return false;

        return node.left == null && node.right == null;
    }

    private boolean hasNodeOneChild(Node node) {
        if (node == null) return false;

        return node.left != null ^ node.right != null;
    }

    private NodeAndParent findNode(int item) {
        var currentNode = root;
        Node parent = null;
        boolean isLeft = false;

        while (currentNode != null) {
            if ((int) currentNode.value == item) return new NodeAndParent(currentNode, isLeft, parent);

            parent = currentNode;

            if ((int) currentNode.value > item) {
                currentNode = currentNode.left;
                isLeft = true;
            } else if ((int) currentNode.value < item) {
                currentNode = currentNode.right;
                isLeft = false;
            }
        }

        return null;
    }

    private NodeAndParent smallest(Node node, Node parent, boolean isLeft) {
        if (node == null) return null;

        if (node.left == null) return new NodeAndParent(node, isLeft, parent);

        return smallest(node.left, node, true);
    }

    private ArrayList<T> toSortedArray() {
        ArrayList<T> nodes = new ArrayList<>();
        toSortedArray(root, nodes);
        return nodes;
    }

    private void toSortedArray(Node node, ArrayList<T> nodes) {
        if (node == null) return;

        toSortedArray(node.left, nodes);
        nodes.add((T) node.value);
        toSortedArray(node.right, nodes);
    }

}
