import java.util.Stack;

class MinStack {

    // Stores all elements
    private Stack<Integer> stack;

    // Stores minimum element till each position
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    // Push element
    public void push(int val) {

        stack.push(val);

        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            // Store minimum till now
            minStack.push(Math.min(val, minStack.peek()));
        }
    }

    // Remove top element
    public void pop() {
        stack.pop();
        minStack.pop();
    }

    // Return top element
    public int top() {
        return stack.peek();
    }

    // Return minimum element
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */