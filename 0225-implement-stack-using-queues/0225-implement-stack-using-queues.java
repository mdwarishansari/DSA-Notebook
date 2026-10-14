
import java.util.Queue;
import java.util.LinkedList;

class MyStack {

    // q1 always keeps the stack's top at its front.
    private Queue<Integer> q1;

    // q2 helps rearrange elements during push().
    private Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        // Insert the new element first into q2.
        q2.offer(x);

        // Move existing elements behind the new element.
        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }

        // Swap the queues so q1 holds the correct order.
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public int pop() {
        // The front of q1 is the stack's top.
        return q1.poll();
    }

    public int top() {
        // Read the top without removing it.
        return q1.peek();
    }

    public boolean empty() {
        // q1 contains all stack elements.
        return q1.isEmpty();
    }
}


/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */