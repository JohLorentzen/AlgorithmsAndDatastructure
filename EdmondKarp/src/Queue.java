public class Queue {
    private Object[] array;
    private int first = 0;
    private int last = 0;
    private int size = 0;

    public Queue(int size) {
        this.array = new Object[size];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == array.length;
    }

    public void addToQueue(Object element) {
        if (isFull()) return;
        this.array[last] = element;
        last = (last+1) % array.length;
        size++;
    }

    public Object nextInQueue() {
        if (!isEmpty()) {
            Object e = array[first];
            first = (first+1) % array.length;
            size--;
            return e;
        }
        return null;
    }

    public Object checkQueue() {
        if (!isEmpty()) return array[first];
        return null;
    }
}
