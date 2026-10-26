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

    public ListNode sortList(ListNode head) {

        // Base case: an empty list or a single node is already sorted.
        if (head == null || head.next == null) {
            return head;
        }

        // Find the node just before the middle of the list.
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Split the list into two separate halves.
        ListNode secondHalf = slow.next;
        slow.next = null;

        // Recursively sort each half.
        ListNode left = sortList(head);
        ListNode right = sortList(secondHalf);

        // Merge the two sorted halves.
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {

        // Dummy node makes it easier to build the result.
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // Select the smaller node from the two lists.
        while (left != null && right != null) {

            if (left.val <= right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }

            // Advance the tail of the merged list.
            tail = tail.next;
        }

        // Attach any remaining nodes.
        if (left != null) {
            tail.next = left;
        } else {
            tail.next = right;
        }

        // Return the merged list, excluding the dummy node.
        return dummy.next;
    }
}
