import ds.AVLTree;

public class Main {
    public static void main(String[] args) {
        var tree = new AVLTree();

        tree.insert(10);
        tree.insert(30);
        tree.insert(20);
//        tree.insert(5);
//        tree.insert(3);
//        tree.insert(12);
//        tree.insert(10);
//        tree.insert(15);
//        tree.insert(14);


        System.out.println(tree);
    }
}