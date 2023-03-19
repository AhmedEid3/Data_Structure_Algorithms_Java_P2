package ds;

public interface ITree<T> extends IBaseTree<T> {
    void insert(T item);

    boolean contains(T item);

    void remove(T item);

    int min();

    int max();
}
