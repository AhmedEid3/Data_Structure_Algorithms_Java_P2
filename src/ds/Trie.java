package ds;

import java.util.ArrayList;
import java.util.HashMap;

public class Trie {
    private class Node {
        private char value;
        private HashMap<Character, Node> children;
        private boolean isEndOfWord;

        public Node(char value) {
            this.value = value;
            this.children = new HashMap<>();
            this.isEndOfWord = false;
        }

        public boolean hasChild(char ch) {
            return this.children.containsKey(ch);
        }

        public void addChild(char ch) {
            this.children.put(ch, new Node(ch));
        }

        public Node getChild(char ch) {
            return this.children.get(ch);
        }

        public Node[] getChildren() {
            return this.children.values().toArray(new Node[0]);
        }

        public boolean hasChildren() {
            return !this.children.isEmpty();
        }

        public void removeChild(char ch) {
            this.children.remove(ch);
        }

        @Override
        public String toString() {
            return "value=" + value;
        }
    }

    Node root = new Node(' ');

    public void insert(String word) {
        var chars = word.toLowerCase().toCharArray();
        var currentNode = root;

        for (char ch : chars
        ) {
            if (!currentNode.hasChild(ch)) {
                currentNode.addChild(ch);
            }

            currentNode = currentNode.getChild(ch);
        }
        currentNode.isEndOfWord = true;
    }

    public boolean contains(String word) {
        if (word == null) return false;

        var chars = word.toLowerCase().toCharArray();
        var currentNode = root;

        for (char ch : chars
        ) {
            if (!currentNode.hasChild(ch)) {
                return false;
            }

            currentNode = currentNode.getChild(ch);
        }
        return currentNode.isEndOfWord;
    }

    public String remove(String word) {
        if (word == null) return null;

        remove(root, word, 0);

        return word;
    }

    private void remove(Node node, String word, int index) {
        if (index == word.length()) {
            node.isEndOfWord = false;
            return;
        }

        char ch = word.charAt(index);
        var child = node.getChild(ch);
        if (child == null) return;

        remove(child, word, index + 1);

        if (!child.hasChildren() && !child.isEndOfWord) {
            child.removeChild(ch);
        }
    }

    public ArrayList<String> findWords(String prefix) {
        var words = new ArrayList<String>();
        var lastNode = finedLastNodeOf(prefix);

        findWords(lastNode, prefix, words);
        return words;
    }

    private void findWords(Node node, String prefix, ArrayList<String> words) {
        if (node == null) return;

        if (node.isEndOfWord) {
            words.add(prefix);
        }

        for (var child : node.getChildren()
        ) {
            findWords(child, prefix + child.value, words);
        }
    }

    private Node finedLastNodeOf(String prefix) {
        var currentNode = root;
        if (currentNode == null || prefix == null) return null;

        for (char ch : prefix.toLowerCase().toCharArray()
        ) {
            if (!currentNode.hasChild(ch)) return null;

            currentNode = currentNode.getChild(ch);
        }

        return currentNode;
    }


    public void traverse() {
        traverse(root);
    }

    private void traverse(Node child) {
        // pre-order travers
        System.out.println(child.value);
        for (var ch : child.getChildren()
        ) {
            traverse(ch);
        }


    }
}
