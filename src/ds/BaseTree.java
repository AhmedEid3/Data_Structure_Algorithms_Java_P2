package ds;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BaseTree<T> implements IBaseTree<T> {
    protected class Node<T> {
        T value;
         Node left;
         Node right;

        public Node(T value) {
            this.value = value;
            left = right = null;
        }

        @Override
        public String toString() {
            return "Node=" + value;
        }
    }

    protected  Node root = null;

    public void traversePreOrder() {
        traversePreOrder(root);
    }

    private void traversePreOrder(Node node) {
        // Root - Left - Right
        if (node == null) return;

        System.out.println(node.value);
        traversePreOrder(node.left);
        traversePreOrder(node.right);
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node node) {
        // Left - Root - Right
        if (node == null) return;

        traverseInOrder(node.left);
        System.out.println(node.value);
        traverseInOrder(node.right);
    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }

    public void traverseBreadthFirst() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new ArrayDeque<Node>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node dequeueNode = queue.remove();

            System.out.println(dequeueNode);
            if (dequeueNode.left != null) {
                queue.add(dequeueNode.left);
            }

            if (dequeueNode.right != null) {
                queue.add(dequeueNode.right);
            }
        }
    }

    private void traversePostOrder(Node node) {
        //  Left - Right - Root
        if (node == null) return;

        System.out.println(node.value);
        traversePostOrder(node.left);
        traversePostOrder(node.right);
    }

    public boolean equals(BaseTree other) {
        if (other == null) return false;

        return equals(root, other.root);
    }
    private boolean equals( Node root, Node other) {

        if (other == null && root == null) return true;

        if (other != null && root != null) {

            var isRootEquals = root.value == other.value;
            var isLeftEquals = equals(root.left, other.left);
            var rightRoot = equals(root.right, other.right);

            return isRootEquals && isLeftEquals && rightRoot;
        }

        return false;
    }


    public int height() {
        return height(root);
    }

    private int height(Node node) {

        if (node == null) return 0;

        return 1 + Math.max(
                height(node.left),
                height(node.right)
        );
    }

    public ArrayList nodesAtKDistance(int distance) {
        var list = new ArrayList<>();
        nodesAtKDistance(root, distance, list);
        return list;
    }

    private void nodesAtKDistance( Node root, int distance, ArrayList list) {
        if (root == null) return;

        if (distance == 0) {
            list.add(root.value);
            return;
        }
        nodesAtKDistance(root.right, distance - 1, list);
        nodesAtKDistance(root.left, distance - 1, list);
    }

}
