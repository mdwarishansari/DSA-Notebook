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
class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        // Two pointers starting from the head
        ListNode slow = head;
        ListNode fast = head;

        // Step 1:
        // Move the fast pointer 'n' nodes ahead.
        // This creates a fixed gap of 'n' nodes
        // between slow and fast.
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Step 2:
        // If fast becomes null, it means
        // we need to remove the first node.
        if (fast == null) {
            return head.next;
        }

        // Step 3:
        // Move both pointers together while
        // maintaining the same gap.
        // When fast reaches the last node,
        // slow will be just before the node
        // that needs to be deleted.
        while (fast.next != null) {

            slow = slow.next;
            fast = fast.next;
        }

        // Step 4:
        // Skip the target node.
        // Example:
        // slow -> target -> next
        //
        // becomes
        //
        // slow -------> next
        slow.next = slow.next.next;

        // Return the updated head
        return head;
    }
}