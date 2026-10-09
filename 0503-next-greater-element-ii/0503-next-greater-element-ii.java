
import java.util.*;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;

        // Store the next greater element for each index.
        // Initialize with -1 because some elements have no answer.
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        // Store indices of elements waiting for a greater element.
        Stack<Integer> st = new Stack<>();

        // Traverse twice to simulate the circular array.
        for (int i = 0; i < 2 * n; i++) {

            // Wrap around to the beginning after reaching the end.
            int curr = i % n;

            // If the current element is greater than the element
            // at the stack's top index, we found its next greater.
            while (!st.isEmpty() && nums[curr] > nums[st.peek()]) {
                // Remove the index whose answer has been found.
                int idx = st.pop();

                // Save the current element as its next greater.
                ans[idx] = nums[curr];
            }

            // Push each original index only once.
            if (i < n) {
                st.push(i);
            }
        }

        // Any unanswered elements remain -1.
        return ans;
    }
}
