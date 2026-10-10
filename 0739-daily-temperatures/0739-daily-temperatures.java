
import java.util.Stack;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;

        // Stores the number of days to wait for a warmer temperature.
        // Defaults to 0 when no warmer day exists.
        int[] answer = new int[n];

        // Store day indices instead of temperatures so we can
        // calculate the waiting time using currentIndex - previousIndex.
        Stack<Integer> stack = new Stack<>();

        // Process each day from left to right.
        for (int i = 0; i < n; i++) {

            // If today's temperature is warmer than the temperature
            // at the top index, today's day resolves that previous day.
            while (!stack.isEmpty()
                    && temperatures[i] > temperatures[stack.peek()]) {

                // Remove the index of the previous colder day.
                int previousDay = stack.pop();

                // Calculate how many days the previous day had to wait.
                answer[previousDay] = i - previousDay;
            }

            // Push today's index because its warmer day
            // has not been found yet.
            stack.push(i);
        }

        // Any indices remaining in the stack have no warmer future day.
        // Their answer values remain 0.
        return answer;
    }
}
