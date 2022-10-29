public class QueueArr<E> {
    private E[] queue;
    private int size;
    private int front;
    private int rear;

    public QueueArr() {
        queue = (E[]) new Object[10];
        size = 0;
        front = 0;
        rear = 0;
    }

    public void enqueue(E item) {
        if (size == queue.length) {
            ensureCapacity();
        }
        queue[rear] = item;
        rear = (rear + 1) % queue.length;
        size++;
    }

    public E dequeue() {
        E temp = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        size--;
        return temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        E[] temp = (E[]) new Object[queue.length * 2];
        for (int i = 0; i < size; i++) {
            temp[i] = queue[(front + i) % queue.length];
        }
        queue = temp;
        front = 0;
        rear = size;
    }

    public boolean contains(E item) {
        for (int i = 0; i < size; i++) {
            if (queue[(front + i) % queue.length].equals(item)) {
                return true;
            }
        }
        return false;
    }

}
