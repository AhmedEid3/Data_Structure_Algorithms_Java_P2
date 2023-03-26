import ds.Graph;

public class Main {
    public static void main(String[] args) {

        var graph1 = new Graph();

        graph1.addNode("A");
        graph1.addNode("B");
        graph1.addNode("C");
        graph1.addNode("D");

        graph1.addEdge("A", "B");
        graph1.addEdge("B", "D");
        graph1.addEdge("D", "C");
        graph1.addEdge("A", "C");

        graph1.print();

        graph1.traverseDepth("GG");
    }
}