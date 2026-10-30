
import java.util.Stack;

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // Create two stacks to store digits of both numbers.
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        // Push all digits of the first linked list.
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }

        // Push all digits of the second linked list.
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        // Initialize carry and result list.
        int carry = 0;
        ListNode head = null;

        // Process digits from right to left.
        while (!s1.isEmpty() || !s2.isEmpty() || carry != 0) {

            // Start with the previous carry.
            int sum = carry;

            // Add the top digit from the first stack.
            if (!s1.isEmpty()) {
                sum += s1.pop();
            }

            // Add the top digit from the second stack.
            if (!s2.isEmpty()) {
                sum += s2.pop();
            }

            // Extract the current digit and calculate carry.
            int digit = sum % 10;
            carry = sum / 10;

            // Create a new node.
            ListNode node = new ListNode(digit);

            // Insert the node at the beginning of the result.
            node.next = head;
            head = node;
        }

        // Return the resulting linked list.
        return head;
    }
}
