class Solution {

    public int[] dailyTemperatures(int[] temperatures) {

        // Total number of days
        int n = temperatures.length;

        // Answer array
        int[] result = new int[n];

        // Monotonic Decreasing Stack
        // Stores indices of temperatures
        Stack<Integer> st = new Stack<>();

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {

            // Remove all temperatures that are
            // smaller than or equal to today's temperature.
            // They cannot be the next warmer day.
            while (!st.isEmpty() &&
                   temperatures[i] >= temperatures[st.peek()]) {
                st.pop();
            }

            // If stack is empty,
            // no warmer day exists.
            if (st.isEmpty()) {
                result[i] = 0;
            }
            // Otherwise, top of stack is the next warmer day.
            else {
                result[i] = st.peek() - i;
            }

            // Add today's index for future comparisons.
            st.push(i);
        }

        return result;
    }
}