import java.util.*;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        // Stack stores possible next greater elements
        Stack<Integer> st = new Stack<>();

        // Map each number to its next greater element
        HashMap<Integer, Integer> map = new HashMap<>();

        // Traverse nums2 from right to left
        for (int i = nums2.length - 1; i >= 0; i--) {

            int current = nums2[i];

            // Remove elements that cannot be
            // the next greater element
            while (!st.isEmpty() && st.peek() <= current) {
                st.pop();
            }

            // If stack is empty, no greater element exists
            if (st.isEmpty()) {
                map.put(current, -1);
            } else {
                // Top of stack is the nearest greater element
                map.put(current, st.peek());
            }

            // Current element can be useful
            // for elements to its left
            st.push(current);
        }

        // Build answer for nums1
        int[] ans = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }
}