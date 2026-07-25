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

    // Main function that sorts the linked list
    public ListNode sortList(ListNode head) {

        // Base Case:
        // If the list is empty or has only one node,
        // it is already sorted.
        if (head == null || head.next == null) {
            return head;
        }

        // Find the middle node of the linked list.
        ListNode middle = findMiddle(head);

        // Right half starts after the middle.
        ListNode rightHead = middle.next;

        // Break the list into two separate halves.
        middle.next = null;

        // Recursively sort left half.
        ListNode left = sortList(head);

        // Recursively sort right half.
        ListNode right = sortList(rightHead);

        // Merge the two sorted halves.
        return merge(left, right);
    }

    // -----------------------------------------------------
    // Function to find the middle node using Slow & Fast Pointer
    // -----------------------------------------------------
    private ListNode findMiddle(ListNode head) {

        ListNode slow = head;

        // Start fast one step ahead.
        // This ensures slow stops at the node BEFORE
        // the beginning of the right half.
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // -----------------------------------------------------
    // Merge two sorted linked lists
    // -----------------------------------------------------
    private ListNode merge(ListNode list1, ListNode list2) {

        // Dummy node simplifies merging.
        ListNode dummy = new ListNode(-1);

        // Tail always points to the last node
        // in the merged list.
        ListNode tail = dummy;

        // Compare nodes from both lists
        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {

                tail.next = list1;
                list1 = list1.next;

            } else {

                tail.next = list2;
                list2 = list2.next;
            }

            // Move tail forward.
            tail = tail.next;
        }

        // Attach remaining nodes from list1.
        if (list1 != null) {
            tail.next = list1;
        }

        // Attach remaining nodes from list2.
        if (list2 != null) {
            tail.next = list2;
        }

        // First node is dummy,
        // actual sorted list starts from dummy.next
        return dummy.next;
    }
}