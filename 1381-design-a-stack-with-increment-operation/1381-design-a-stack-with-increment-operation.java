
import java.util.ArrayList;

class CustomStack {

    // Stores elements from bottom to top.
    private ArrayList<Integer> st;

    // Maximum allowed number of elements.
    private int maxSize;

    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        st = new ArrayList<>();
    }

    public void push(int x) {
        // Push only if the stack has available space.
        if (st.size() < maxSize) {
            st.add(x);
        }
    }

    public int pop() {
        // An empty stack returns -1.
        if (st.isEmpty()) {
            return -1;
        }

        // The last element is the top of the stack.
        return st.remove(st.size() - 1);
    }

    public void increment(int k, int val) {
        // Increment only the bottom k elements,
        // or all elements if fewer than k exist.
        int limit = Math.min(k, st.size());

        for (int i = 0; i < limit; i++) {
            st.set(i, st.get(i) + val);
        }
    }
}


/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */