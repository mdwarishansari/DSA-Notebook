
import java.util.Stack;

class MyQueue {

    // Stores newly inserted elements.
    private Stack<Integer> inStack;

    // Supplies elements in FIFO order.
    private Stack<Integer> outStack;

    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x) {
        // Add the new element to the back of the queue.
        inStack.push(x);
    }

    // Transfers elements only when outStack is empty.
    private void transfer() {
        if (outStack.isEmpty()) {
            // Reversing the order makes the oldest
            // inserted element available at the top.
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }

    public int pop() {
        // Ensure the front element is on top.
        transfer();

        // Remove and return the front element.
        return outStack.pop();
    }

    public int peek() {
        // Ensure the front element is on top.
        transfer();

        // Return the front element without removing it.
        return outStack.peek();
    }

    public boolean empty() {
        // The queue is empty only if both stacks are empty.
        return inStack.isEmpty() && outStack.isEmpty();
    }
}


/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */