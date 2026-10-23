
/**
 * Definition for singly-linked list:
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
    public ListNode middleNode(ListNode head) {

        // Both pointers start at the first node.
        ListNode slow = head;
        ListNode fast = head;

        // Continue while fast can move two steps safely.
        while (fast != null && fast.next != null) {

            // Move slow by one node.
            slow = slow.next;

            // Move fast by two nodes.
            fast = fast.next.next;
        }

        // Slow reaches the middle node.
        // For an even-length list, it reaches the second middle.
        return slow;
    }
}
