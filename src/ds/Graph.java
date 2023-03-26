package ds;

import java.util.*;

public class Graph {
    private class Node {
        public String label;

        public Node(String label) {
            this.label = label;
        }

    }
    private HashMap<String, Integer> indexingLabel;
    private List<LinkedList<Node>> graph;
    public Graph() {
        graph = new ArrayList<>();
        indexingLabel = new HashMap<>();
    }

    public void addNode(String label) {
        // find index of label in hash map
        Integer labelIndex = getLabelIndex(label);
        if (labelIndex != null) throw new IllegalArgumentException(label + " exist!");

        // if not found:
        //      add label as a key to hash map and value is size;
        indexingLabel.put(label, graph.size());

        //create a new node
        var newNode = new Node(label);

        // add it to array
        graph.add(new LinkedList());
        graph.get(graph.size() - 1).add(newNode);
    }

    public void removeNode(String label) {
        // check if not throw error not found
        var labelIndex = getLabelIndex(label);
        if (labelIndex == null) throw new IllegalArgumentException(label + " not found");

        // else:
        // get index, object of label

        // remove edge first from whole graph
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).get(0).label == label) continue;
            removeEdge(graph.get(i).get(0).label, label);
        }

        // then remove it index from list and hashmap
        graph.get(labelIndex).removeAll(graph.get(labelIndex));
        indexingLabel.remove(label);
    }

    public void addEdge(String from, String to) {
        // find indexOF from , and To
        Integer indexFrom = getLabelIndex(from);
        Integer indexTo = getLabelIndex(to);

        // if None of them found throw error
        if (indexFrom == null) throw new IllegalArgumentException(from + " not found!");
        if (indexTo == null) throw new IllegalArgumentException(to + " not found!");

        // before adding, search is it exist ?
        for (var node : graph.get(indexFrom)) {
            if (node.label == to) throw new IllegalArgumentException("Edge is exist");
        }

        // else : add to node to linked list of from
        var toNode = graph.get(indexTo).get(0);
        graph.get(indexFrom).add(toNode);
    }

    public void removeEdge(String from, String to) {
        // find indexOF from , and To
        Integer indexFrom = getLabelIndex(from);
        Integer indexTo = getLabelIndex(to);

        // if None of them found return
        if (indexFrom == null || indexTo == null) return;
        var toNode = graph.get(indexTo).get(0);
        graph.get(indexFrom).remove(toNode);
    }

    public void traverseDepth(String from) {
        Set<String> set = new HashSet();
        traverseDepthFirst(from, set);

        System.out.println(set);
    }

    private void traverseDepthFirst(String from, Set set) {
        var indexFrom = getLabelIndex(from);
        if (indexFrom == null) return;

        System.out.println(from);
        set.add(from);

        for (var node: graph.get(indexFrom)
             ) {
            if(!set.contains(node.label)){
                traverseDepthFirst(node.label, set);
            }
        }

    }

    private void traverseDepthFirstIterative(String from, Set set) {
        var indexFrom = getLabelIndex(from);
        if (indexFrom == null) return;

        System.out.println(from);


        for (var node: graph.get(indexFrom)
        ) {
            if(!set.contains(node.label)){
                var indexNeighbor = getLabelIndex(node.label);
                for (var neighborNode:graph.get(indexNeighbor)
                     ) {
                    traverseDepthFirst(node.label, set);
                }

            }
        }

    }

    public void print() {
        for (var list : graph) {
            if (list == null || list.size() == 0) continue;
            var node = list.get(0);
            System.out.print(node.label);
            for (var i = 1; i < list.size(); i++) {
                System.out.print(" -> " + list.get(i).label);
            }
            System.out.println();
        }
    }

    private Integer getLabelIndex(String label) {
        return indexingLabel.get(label);
    }
}
