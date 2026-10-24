
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */

class Solution {
    public boolean isPalindrome(ListNode head) {

        // Find the middle of the linked list.
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half of the list.
        ListNode secondHalf = reverse(slow);
        ListNode firstHalf = head;

        // Compare the first half with the reversed second half.
        while (secondHalf != null) {

            // Different values mean the list is not a palindrome.
            if (firstHalf.val != secondHalf.val) {
                return false;
            }

            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        // All corresponding values matched.
        return true;
    }

    // Reverse a linked list iteratively.
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {

            // Save the next node before changing the link.
            ListNode nextNode = curr.next;

            // Reverse the current node's pointer.
            curr.next = prev;

            // Move both pointers forward.
            prev = curr;
            curr = nextNode;
        }

        // The previous node is now the new head.
        return prev;
    }
}
