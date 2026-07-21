/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *
 *     ListNode() {}
 *
 *     ListNode(int val) {
 *         this.val = val;
 *     }
 *
 *     ListNode(int val, ListNode next) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */

class Solution {

    /**
     * Merges two sorted linked lists into one sorted linked list.
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // ------------------------------------------------------------
        // Create a dummy node.
        //
        // Dummy node makes handling the head of the merged list easy.
        // We will finally return dummy.next.
        // ------------------------------------------------------------
        ListNode dummy = new ListNode(-1);

        // Tail always points to the last node
        // in our merged linked list.
        ListNode tail = dummy;

        // ------------------------------------------------------------
        // Continue until one list becomes empty.
        // ------------------------------------------------------------
        while (list1 != null && list2 != null) {

            // If current node of list1 is smaller
            if (list1.val <= list2.val) {

                // Connect list1 node after tail
                tail.next = list1;

                // Move list1 forward
                list1 = list1.next;

            } else {

                // Connect list2 node after tail
                tail.next = list2;

                // Move list2 forward
                list2 = list2.next;
            }

            // Move tail to the node we just attached
            tail = tail.next;
        }

        // ------------------------------------------------------------
        // If list1 still has remaining nodes,
        // connect all of them.
        // ------------------------------------------------------------
        if (list1 != null) {
            tail.next = list1;
        }

        // ------------------------------------------------------------
        // If list2 still has remaining nodes,
        // connect all of them.
        // ------------------------------------------------------------
        if (list2 != null) {
            tail.next = list2;
        }

        // ------------------------------------------------------------
        // Skip the dummy node and return the real head.
        // ------------------------------------------------------------
        return dummy.next;
    }
}