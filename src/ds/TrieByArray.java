package ds;

public class TrieByArray {
    public static int ALPHABET_SIZE = 26;

    private class Node {
        private char value;
        private Node[] children;
        private boolean isEndOfWord;

        public Node(char value) {
            this.value = value;
            this.children = new Node[ALPHABET_SIZE];
            this.isEndOfWord = false;
        }

        @Override
        public String toString() {
            return "value=" + value;
        }
    }

    Node root;

    public void insert(String word) {
        if (root == null) {
            root = new Node(Character.MIN_VALUE);
        }

        var chars = word.toLowerCase().toCharArray();

        int index;
        var currentNode = root;

        for (char ch : chars
        ) {
            index = ch - 'a';

            if (currentNode.children[index] == null) {
                currentNode.children[index] = new Node(ch);
            }

            currentNode = currentNode.children[index];
        }
        currentNode.isEndOfWord = true;
    }
}
