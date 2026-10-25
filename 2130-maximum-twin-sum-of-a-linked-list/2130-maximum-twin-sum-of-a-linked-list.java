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
    public int pairSum(ListNode head) {

        // Step 1: Find the start of the second half
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half of the list
        ListNode second = reverse(slow);

        // Step 3: Compare corresponding twin nodes
        ListNode first = head;
        int maxSum = 0;

        while (second != null) {

            // Calculate the sum of a twin pair
            int sum = first.val + second.val;

            // Update the maximum twin sum
            maxSum = Math.max(maxSum, sum);

            // Move both pointers forward
            first = first.next;
            second = second.next;
        }

        return maxSum;
    }

    // Reverses a linked list and returns its new head
    private ListNode reverse(ListNode node) {
        ListNode previous = null;

        while (node != null) {
            // Save the next node before changing the link
            ListNode nextNode = node.next;

            // Reverse the current link
            node.next = previous;

            // Advance both pointers
            previous = node;
            node = nextNode;
        }

        return previous;
    }
}
