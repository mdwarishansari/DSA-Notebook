import java.util.Stack;

class StockSpanner {

    // -----------------------------------------------------------------
    // This inner class represents one entry in our stack.
    //
    // price -> stock price of that day
    // span  -> how many consecutive days this price already covers
    // -----------------------------------------------------------------
    class Pair {
        int price;
        int span;

        Pair(int price, int span) {
            this.price = price;
            this.span = span;
        }
    }

    // Monotonic Decreasing Stack
    // The prices inside the stack are always in decreasing order.
    Stack<Pair> stack;

    // Constructor
    public StockSpanner() {
        stack = new Stack<>();
    }

    public int next(int price) {

        // Every new price always counts itself.
        int span = 1;

        // ----------------------------------------------------------
        // Remove all previous prices that are smaller than or equal
        // to today's price.
        //
        // Their spans can directly be added because today's price
        // covers all of them.
        // ----------------------------------------------------------
        while (!stack.isEmpty() && stack.peek().price <= price) {

            // Add the span of removed element.
            span += stack.peek().span;

            // Remove it from stack.
            stack.pop();
        }

        // Store today's price along with its calculated span.
        stack.push(new Pair(price, span));

        // Return answer for today.
        return span;
    }
}