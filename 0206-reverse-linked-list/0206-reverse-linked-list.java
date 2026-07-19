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

    public ListNode reverseList(ListNode head) {

        // 'prev' will always point to the already reversed part of the list.
        // Initially, nothing has been reversed, so it is null.
        ListNode prev = null;

        // 'curr' is used to traverse the original linked list.
        // We start traversal from the head node.
        ListNode curr = head;

        // Keep processing nodes until we reach the end of the list.
        while (curr != null) {

            // ---------------------------------------------------------
            // STEP 1:
            // Save the next node before changing any pointer.
            //
            // Example:
            // curr = 3
            // nextNode = 4
            //
            // If we don't save it, we'll lose access to the remaining list
            // after reversing the pointer.
            // ---------------------------------------------------------
            ListNode nextNode = curr.next;

            // ---------------------------------------------------------
            // STEP 2:
            // Reverse the direction of the current node.
            //
            // Before:
            // 3 -> 4
            //
            // After:
            // 3 -> 2
            //
            // 'prev' represents the previous node in the reversed list.
            // ---------------------------------------------------------
            curr.next = prev;

            // ---------------------------------------------------------
            // STEP 3:
            // Move 'prev' one step forward.
            //
            // The current node has now become the last reversed node,
            // so update prev to point to it.
            // ---------------------------------------------------------
            prev = curr;

            // ---------------------------------------------------------
            // STEP 4:
            // Move 'curr' to the next node that we saved earlier.
            //
            // Continue processing the remaining list.
            // ---------------------------------------------------------
            curr = nextNode;
        }

        // When the loop ends,
        // 'curr' is null (end of list),
        // and 'prev' is pointing to the new head
        // of the completely reversed linked list.
        return prev;
    }
}