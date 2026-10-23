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
    public ListNode detectCycle(ListNode head) {

        // Initialize slow and fast pointers at the head.
        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect whether a cycle exists.
        while (fast != null && fast.next != null) {

            // Move slow by one node.
            slow = slow.next;

            // Move fast by two nodes.
            fast = fast.next.next;

            // If both pointers meet, a cycle exists.
            if (slow == fast) {
                break;
            }
        }

        // If fast reached the end, there is no cycle.
        if (fast == null || fast.next == null) {
            return null;
        }

        // Phase 2: Reset slow to the head.
        slow = head;

        // Move both pointers one step at a time.
        // They will meet at the cycle's starting node.
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // Return the actual node where the cycle begins.
        return slow;
    }
}
