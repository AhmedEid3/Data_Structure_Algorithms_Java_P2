import ds.BinarySearchTree;
import ds.BinaryTree;
import ds.*;

public class Main {
    public static void main(String[] args) {
            var tree  = new BinarySearchTree<Integer>();

            tree.insert(1);

            tree.insert(2);
            tree.insert(3);

            tree.insert(4);
            tree.insert(6);
            tree.insert(8);
            tree.insert(-18);

            tree.insert(17);
            tree.insert(19);

        System.out.println(tree);
//
//
        System.out.println(tree.min());
        System.out.println(tree.max());


        System.out.println(tree.contains(6));
        System.out.println(tree.contains(16));
        System.out.println(tree.contains(19));
//
//
        System.out.println(tree.height());
        System.out.println(tree.nodesAtKDistance(2));
        tree.remove(6);
        tree.traverseBreadthFirst();



    }
}