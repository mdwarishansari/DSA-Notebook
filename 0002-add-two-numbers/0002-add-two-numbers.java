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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // Dummy node simplifies creating the result list.
        ListNode dummy = new ListNode(0);

        // curr points to the last node in the result list.
        ListNode curr = dummy;

        // Stores carry from the previous digit addition.
        int carry = 0;

        // Continue while either list has digits or carry remains.
        while (l1 != null || l2 != null || carry != 0) {

            // Get the current digit from each list.
            // Use 0 if that list has already ended.
            int a = (l1 != null) ? l1.val : 0;
            int b = (l2 != null) ? l2.val : 0;

            // Add both digits and the previous carry.
            int sum = a + b + carry;

            // The current result digit is the remainder.
            int digit = sum % 10;

            // The next carry is the quotient.
            carry = sum / 10;

            // Create a node for the result digit.
            curr.next = new ListNode(digit);

            // Move curr to the newly created node.
            curr = curr.next;

            // Move to the next input digit if available.
            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        // Skip the dummy node and return the actual result.
        return dummy.next;
    }
}
