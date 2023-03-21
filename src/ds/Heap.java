package ds;

public class Heap {
    private int[] items;
    private int size = 0;

    public Heap(int length) {
        items = new int[length];
    }

    public void insert(int item) {
        if (isFull()) resize();
        items[size++] = item;
        bubbleUp();
    }

    public int remove() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty");

        int removedValue = items[0];
        items[0] = items[--size];
        bubbleDown();

        return removedValue;
    }

    private void bubbleDown() {
        int index = 0;
        while (!isParentValid(index)) {
            swap(index, largeChildIndex(index));
            index = leftIndex(index);
        }
    }


    private void bubbleUp() {
        int index = size - 1;

        while (index > 0 && items[index] > items[parentIndex(index)]) {
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }

    private void swap(int firstIndex, int secondIndex) {
        int temp = items[firstIndex];
        items[firstIndex] = items[secondIndex];
        items[secondIndex] = temp;
    }


    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private int leftIndex(int index) {
        return index * 2 + 1;
    }

    private int rightIndex(int index) {
        return index * 2 + 2;
    }

    private int largeChildIndex(int index) {
        if (!hasLeftChild(index)) return index;

        if (!hasRightChild(index)) return leftIndex(index);

        return leftChild(index) > rightChild(index) ? leftIndex(index) : rightIndex(index);
    }

    private boolean isParentValid(int index) {
        if (!hasLeftChild(index)) return true;

        var isValid = items[index] >= leftChild(index);
        if (hasRightChild(index))
            isValid &=
                    items[index] >= rightChild(index);

        return isValid;
    }

    private boolean hasLeftChild(int index) {
        return leftIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return rightIndex(index) < size;
    }

    private int rightChild(int index) {
        return items[rightIndex(index)];
    }

    private int leftChild(int index) {
        return items[leftIndex(index)];
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public boolean isFull() {
        return size == items.length;
    }

    private void resize() {
        var newArray = new int[size * 2];
        for (int i = 0; i < size; i++) newArray[i] = items[i];
        items = newArray;
    }

    @Override
    public String toString() {
        StringBuffer out = new StringBuffer("[");
        for (int i = 0; i < size; i++) {
            out.append(items[i]);
            if (i < size - 1) out.append(", ");
        }
        return out.append("]").toString();
    }
}
