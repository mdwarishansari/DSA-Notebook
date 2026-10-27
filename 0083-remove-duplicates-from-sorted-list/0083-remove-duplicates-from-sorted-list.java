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
    public ListNode deleteDuplicates(ListNode head) {
        // Handle an empty list.
        if (head == null) {
            return head;
        }

        ListNode current = head;

        // Traverse the sorted linked list.
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                // Skip the duplicate node.
                current.next = current.next.next;
            } else {
                // Move forward only when values differ.
                current = current.next;
            }
        }

        // Return the list with duplicates removed.
        return head;
    }
}
