package ds;

import java.util.ArrayList;

public interface IBaseTree<T> {
    void traversePreOrder();

    void traverseInOrder();

    void traversePostOrder();

    void traverseBreadthFirst();

    boolean equals(Object other);

    int height();

    ArrayList nodesAtKDistance(int distance);

}
