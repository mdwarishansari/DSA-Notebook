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

    // Main function to reorder the linked list
    public void reorderList(ListNode head) {

        // If list has 0, 1 or 2 nodes,
        // it is already in required order.
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        // -------------------------------------------------------
        // STEP 1 : Find the middle of the linked list
        // -------------------------------------------------------
        ListNode middle = findMiddle(head);

        // Store the beginning of the second half
        ListNode secondHalf = middle.next;

        // Break the list into two separate lists
        middle.next = null;

        // -------------------------------------------------------
        // STEP 2 : Reverse the second half
        // -------------------------------------------------------
        secondHalf = reverse(secondHalf);

        // -------------------------------------------------------
        // STEP 3 : Merge the two halves alternately
        // -------------------------------------------------------
        merge(head, secondHalf);
    }

    // ==========================================================
    // Finds the middle node using Slow and Fast Pointer
    // ==========================================================
    private ListNode findMiddle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        // Slow moves 1 step
        // Fast moves 2 steps
        while (fast.next != null && fast.next.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }

        // Slow is now pointing to the middle node
        return slow;
    }

    // ==========================================================
    // Reverses a linked list
    // ==========================================================
    private ListNode reverse(ListNode head) {

        ListNode previous = null;
        ListNode current = head;

        while (current != null) {

            // Store next node before breaking the link
            ListNode nextNode = current.next;

            // Reverse current node's pointer
            current.next = previous;

            // Move previous one step forward
            previous = current;

            // Move current one step forward
            current = nextNode;
        }

        // Previous becomes the new head
        return previous;
    }

    // ==========================================================
    // Merge two linked lists alternately
    // ==========================================================
    private void merge(ListNode first, ListNode second) {

        // Continue until every node from second list
        // has been inserted.
        while (second != null) {

            // Save next pointers before changing links
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;

            // Insert node from second list
            first.next = second;

            // Connect second node to next node
            // of first list
            second.next = firstNext;

            // Move pointers forward
            first = firstNext;
            second = secondNext;
        }
    }
}