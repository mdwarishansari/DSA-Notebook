import java.util.*;

class Solution {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        // Stores:
        // key   -> number in nums2
        // value -> next greater element
        Map<Integer, Integer> nextGreater = new HashMap<>();

        // Monotonic Decreasing Stack
        // Stores numbers whose next greater element
        // has not been found yet.
        Stack<Integer> stack = new Stack<>();

        // Traverse nums2 once
        for (int num : nums2) {

            // If current number is greater than the
            // top of the stack, then it is the next
            // greater element for stack.peek().
            while (!stack.isEmpty() && num > stack.peek()) {

                // Remove waiting element
                int smaller = stack.pop();

                // Store its answer
                nextGreater.put(smaller, num);
            }

            // Current number now waits for its own
            // next greater element.
            stack.push(num);
        }

        // Any element still inside the stack
        // has no greater element on its right.
        while (!stack.isEmpty()) {
            nextGreater.put(stack.pop(), -1);
        }

        // Build final answer for nums1
        int[] ans = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {

            // Directly fetch answer from map
            ans[i] = nextGreater.get(nums1[i]);
        }

        return ans;
    }
}