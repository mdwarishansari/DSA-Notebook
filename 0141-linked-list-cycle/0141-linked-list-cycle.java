/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {
    public boolean hasCycle(ListNode head) {

        // Both pointers start at the head of the linked list.
        ListNode slow = head;
        ListNode fast = head;

        // Fast must have a next node to move two steps safely.
        while (fast != null && fast.next != null) {

            // Slow moves one step.
            slow = slow.next;

            // Fast moves two steps.
            fast = fast.next.next;

            // If both pointers meet, a cycle exists.
            if (slow == fast) {
                return true;
            }
        }

        // Fast reached the end, so no cycle exists.
        return false;
    }
}
