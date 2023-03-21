package ds;

public class PriorityQueueByHeap {

    private Heap items;

    public PriorityQueueByHeap(){
        this.items = new Heap(7);
    }

    public void enqueue(int item){
        this.items.insert(item);
    }

    public int dequeue(){
        return this.items.remove();
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }
}
