/**
 * Definition for singly-linked list.
 *
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
     * Main Function
     * -------------
     * Checks whether the given linked list is a palindrome.
     *
     * Approach:
     * 1. Find the middle node.
     * 2. Reverse the second half.
     * 3. Compare both halves.
     * 4. (Optional) Restore the list.
     */
    public boolean isPalindrome(ListNode head) {

        // Empty list or single node is always a palindrome.
        if (head == null || head.next == null) {
            return true;
        }

        //-------------------------------------------------------
        // STEP 1 : Find the middle of the linked list
        //-------------------------------------------------------
        ListNode middle = findMiddle(head);

        //-------------------------------------------------------
        // STEP 2 : Reverse the second half
        //-------------------------------------------------------
        ListNode secondHalfHead = reverseList(middle);

        //-------------------------------------------------------
        // STEP 3 : Compare first half and reversed second half
        //-------------------------------------------------------
        boolean isPalindrome = compareLists(head, secondHalfHead);

        //-------------------------------------------------------
        // STEP 4 (Optional)
        // Restore the original linked list.
        // Many interviewers appreciate this.
        //-------------------------------------------------------
        reverseList(secondHalfHead);

        return isPalindrome;
    }

    /**
     * Finds the middle node using Slow & Fast Pointer technique.
     *
     * Example:
     *
     * 1 -> 2 -> 3 -> 4 -> 5
     *                ^
     *              Returns 3
     *
     * 1 -> 2 -> 3 -> 4
     *           ^
     *        Returns 3
     *
     * Time  : O(n)
     * Space : O(1)
     */
    private ListNode findMiddle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        // Slow moves one step.
        // Fast moves two steps.
        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * Reverses a linked list.
     *
     * Example:
     *
     * Before:
     * 1 -> 2 -> 3
     *
     * After:
     * 3 -> 2 -> 1
     *
     * Time  : O(n)
     * Space : O(1)
     */
    private ListNode reverseList(ListNode head) {

        ListNode previous = null;
        ListNode current = head;

        while (current != null) {

            // Store next node before breaking the link.
            ListNode nextNode = current.next;

            // Reverse current node's pointer.
            current.next = previous;

            // Move both pointers one step ahead.
            previous = current;
            current = nextNode;
        }

        // 'previous' becomes the new head
        // of the reversed linked list.
        return previous;
    }

    /**
     * Compares two linked lists node by node.
     *
     * Returns false immediately if any value differs.
     *
     * Example:
     *
     * List 1 : 1 -> 2 -> 3
     * List 2 : 1 -> 2 -> 3
     *
     * Returns true
     *
     * Time : O(n/2)
     */
    private boolean compareLists(ListNode first, ListNode second) {

        while (second != null) {

            // If values differ, it is not a palindrome.
            if (first.val != second.val) {
                return false;
            }

            // Move to next nodes.
            first = first.next;
            second = second.next;
        }

        // Every compared node matched.
        return true;
    }
}