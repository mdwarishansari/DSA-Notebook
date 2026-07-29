/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {

    public ListNode detectCycle(ListNode head) {

        // Empty list or only one node cannot have a cycle
        if (head == null || head.next == null) {
            return null;
        }

        // Initialize slow and fast pointers
        ListNode slow = head;
        ListNode fast = head;

        // -----------------------------
        // Step 1: Detect if a cycle exists
        // -----------------------------
        while (fast != null && fast.next != null) {

            // Slow moves one step
            slow = slow.next;

            // Fast moves two steps
            fast = fast.next.next;

            // If both pointers meet,
            // a cycle is present
            if (slow == fast) {

                // -----------------------------
                // Step 2: Find the starting node
                // -----------------------------

                // Move slow back to the head
                slow = head;

                // Move both pointers one step at a time
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                // The meeting point is the
                // starting node of the cycle
                return slow;
            }
        }

        // No cycle found
        return null;
    }
}