package ds;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinaryTree<T> extends BaseTree<T> implements ITree<T> {

    public void insert(T item) {
        var newNode = new Node(item);

        if (root == null) {
            root = newNode;
            return;
        }

        Queue<Node> queue = new ArrayDeque<Node>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node dequeueNode = queue.remove();

            if (dequeueNode.left == null) {
                dequeueNode.left = newNode;
                break;
            }
            queue.add(dequeueNode.left);


            if (dequeueNode.right == null) {
                dequeueNode.right = newNode;
                break;
            }
            queue.add(dequeueNode.right);

        }

    }


    public boolean contains(T item) {
        if (root == null) {
            return false;
        }

        Queue<Node> queue = new ArrayDeque<Node>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node dequeueNode = queue.remove();

            if (dequeueNode.value == item) return true;

            if (dequeueNode.left != null) {
                queue.add(dequeueNode.left);
            }

            if (dequeueNode.right != null) {
                queue.add(dequeueNode.right);
            }
        }

        return false;
    }

    public void remove(Object item) {

    }

    public int min() {
        if (root == null) {
            throw new NoSuchElementException("Binary tree is empty.");
        }

        return min(root);
    }

    private int min(Node root) {
        int minValue = (int) root.value;
        int leftMin = Integer.MAX_VALUE;
        int rightMin = Integer.MAX_VALUE;

        if (root.left != null) {
            leftMin = min(root.left);
        }
        if (root.right != null) {
            rightMin = min(root.right);
        }

        if (leftMin < minValue) {
            minValue = leftMin;
        }
        if (rightMin < minValue) {
            minValue = rightMin;
        }

        return minValue;
    }

    public int max() {
        return max(root);
    }

    private int max(Node root) {
        if (root == null) return 0;

        if (isLeaf(root)) return (int) root.value;

        var left = max(root.left);
        var right = max(root.right);
        var maxOfLR = Math.max(left, right);

        return Math.max(maxOfLR, (Integer) root.value);
    }

    private boolean isLeaf(Node node) {
        if (node == null) return false;

        return node.left == null && node.right == null;
    }

}
