/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

import java.util.*;

class Solution {
    public int[] nextLargerNodes(ListNode head) {

        // Step 1: Convert linked list into an ArrayList.
        List<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int n = list.size();

        // Step 2: Initialize answer array with zeros.
        // Zero means no greater node exists.
        int[] ans = new int[n];

        // Step 3: Stack stores indices of unresolved elements.
        Stack<Integer> st = new Stack<>();

        // Step 4: Traverse the array from left to right.
        for (int i = 0; i < n; i++) {

            // Resolve previous elements smaller than current.
            while (!st.isEmpty() &&
                   list.get(i) > list.get(st.peek())) {

                // Get the index of the previous smaller element.
                int idx = st.pop();

                // Current value is its next greater element.
                ans[idx] = list.get(i);
            }

            // Store current index for future comparison.
            st.push(i);
        }

        // Remaining indices automatically have answer 0.
        return ans;
    }
}
