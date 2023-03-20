package ds;

public class AVLTree {
    private class AVLNode {
        private int value;
        private AVLNode left;
        private AVLNode right;
        private int height = 0;
        private int freq = 0;

        public AVLNode(int value) {
            this.value = value;
            this.freq = 1;
            this.height = 1;
        }

        @Override
        public String toString() {
            return "AVLNode =" + value;
        }
    }

    AVLNode root;

    public void insert(int item) {
        root = insert(item, root);
    }

    private AVLNode insert(int item, AVLNode node) {
        if (node == null) return new AVLNode(item);

        if (node.value > item) {
            node.left = insert(item, node.left);
        } else if (node.value < item) {
            node.right = insert(item, node.right);
        } else {
            node.freq++;
        }

        updateHeight(node);

        return balance(node);
    }

    private void updateHeight(AVLNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }


    private int height(AVLNode node) {
        return node == null ? -1 : node.height;
    }

    private int balanceFactor(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private boolean isLeftHeavy(AVLNode node) {
        return balanceFactor(node) > 1;
    }

    private boolean isRightHeavy(AVLNode node) {
        return balanceFactor(node) < -1;
    }

    private AVLNode balance(AVLNode node) {
        if (isLeftHeavy(node)) {// left heavy case: do right rotation
            if (balanceFactor(node.left) < 0) { // left-right case: do left rotation first && right Rotation
                node.left = leftRotation(node.left);
            }
            return rightRotation(node);
        } else if (isRightHeavy(node)) {// right heavy case: do left rotation
            if (balanceFactor(node.right) > 0) {// right-left case: do right rotation first && left Rotation
                node.right = rightRotation(node.right);
            }
            return leftRotation(node);
        }

        return node;
    }

    private AVLNode leftRotation(AVLNode node) {
        var newRoot = node.right;

        node.right = newRoot.left;
        newRoot.left = node;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    private AVLNode rightRotation(AVLNode node) {
        var newRoot = node.left;

        node.left = newRoot.right;
        newRoot.right = node;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }
}
