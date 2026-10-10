
import java.util.Stack;

class StockSpanner {

    // Each entry stores:
    // [0] = stock price
    // [1] = span of that price
    private Stack<int[]> stack;

    // Initialize an empty stack when StockSpanner is created.
    public StockSpanner() {
        stack = new Stack<>();
    }

    // Process today's stock price and return its span.
    public int next(int price) {

        // Today's price always contributes at least one day.
        int span = 1;

        // All previous prices less than or equal to today's price
        // can be included in today's span.
        while (!stack.isEmpty() && stack.peek()[0] <= price) {

            // Remove the previous price because today's price
            // is greater than or equal to it.
            int[] previous = stack.pop();

            // Include all the days represented by that entry.
            span += previous[1];
        }

        // Store today's price along with its calculated span.
        // Remaining stack prices are strictly greater than today's.
        stack.push(new int[]{price, span});

        // Return the number of consecutive qualifying days.
        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */