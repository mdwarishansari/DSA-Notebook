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
    public ListNode oddEvenList(ListNode head) {

        // If there are fewer than two nodes, no rearrangement is needed.
        if (head == null || head.next == null) {
            return head;
        }

        // odd points to the first node of the odd-index group.
        ListNode odd = head;

        // even points to the first node of the even-index group.
        ListNode even = head.next;

        // Save the start of the even group for the final connection.
        ListNode evenHead = even;

        // Process pairs while another odd-position node exists.
        while (even != null && even.next != null) {

            // Link the current odd node to the next odd node.
            odd.next = even.next;
            odd = odd.next;

            // Link the current even node to the next even node.
            even.next = odd.next;
            even = even.next;
        }

        // Append the even group after the odd group.
        odd.next = evenHead;

        // Return the original head of the rearranged list.
        return head;
    }
}
