
class MyCircularDeque {

    private final int[] deque;
    private final int capacity;

    // Index of the front element.
    private int front;

    // Number of elements currently stored.
    private int size;

    // Initialize the circular deque.
    public MyCircularDeque(int k) {
        capacity = k;
        deque = new int[k];
        front = 0;
        size = 0;
    }

    // Insert an element at the front.
    public boolean insertFront(int value) {
        // Cannot insert if the deque is full.
        if (isFull()) {
            return false;
        }

        // Move front one position backward circularly.
        front = (front - 1 + capacity) % capacity;

        deque[front] = value;
        size++;

        return true;
    }

    // Insert an element at the rear.
    public boolean insertLast(int value) {
        // Cannot insert if the deque is full.
        if (isFull()) {
            return false;
        }

        // Calculate the next available rear position.
        int rear = (front + size) % capacity;

        deque[rear] = value;
        size++;

        return true;
    }

    // Delete an element from the front.
    public boolean deleteFront() {
        // Cannot delete if the deque is empty.
        if (isEmpty()) {
            return false;
        }

        // Move front one position forward circularly.
        front = (front + 1) % capacity;
        size--;

        return true;
    }

    // Delete an element from the rear.
    public boolean deleteLast() {
        // Cannot delete if the deque is empty.
        if (isEmpty()) {
            return false;
        }

        // The rear element is removed by reducing size.
        size--;

        return true;
    }

    // Return the front element.
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }

        return deque[front];
    }

    // Return the rear element.
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }

        // Calculate the index of the last element.
        int rear = (front + size - 1) % capacity;

        return deque[rear];
    }

    // Check whether the deque is empty.
    public boolean isEmpty() {
        return size == 0;
    }

    // Check whether the deque is full.
    public boolean isFull() {
        return size == capacity;
    }
}
