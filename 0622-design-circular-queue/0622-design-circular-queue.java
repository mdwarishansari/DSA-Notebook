
class MyCircularQueue {

    private final int[] queue;
    private final int capacity;

    // Index of the front element.
    private int front;

    // Number of elements currently in the queue.
    private int size;

    // Initialize the circular queue.
    public MyCircularQueue(int k) {
        capacity = k;
        queue = new int[k];
        front = 0;
        size = 0;
    }

    // Insert an element at the rear.
    public boolean enQueue(int value) {
        // Cannot insert when the queue is full.
        if (isFull()) {
            return false;
        }

        // Calculate the circular insertion position.
        int rear = (front + size) % capacity;

        queue[rear] = value;
        size++;

        return true;
    }

    // Remove the front element.
    public boolean deQueue() {
        // Cannot remove from an empty queue.
        if (isEmpty()) {
            return false;
        }

        // Move front forward circularly.
        front = (front + 1) % capacity;
        size--;

        return true;
    }

    // Return the first element.
    public int Front() {
        if (isEmpty()) {
            return -1;
        }

        return queue[front];
    }

    // Return the last element.
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }

        // Calculate the index of the last element.
        int rear = (front + size - 1) % capacity;

        return queue[rear];
    }

    // Check whether the queue has no elements.
    public boolean isEmpty() {
        return size == 0;
    }

    // Check whether the queue has reached its capacity.
    public boolean isFull() {
        return size == capacity;
    }
}
