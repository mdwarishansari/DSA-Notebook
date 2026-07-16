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

    public ListNode middleNode(ListNode head) {

        // 'slow' moves one step at a time.
        // 'fast' moves two steps at a time.
        // Initially, both start from the head.
        ListNode slow = head;
        ListNode fast = head;

        // Continue until fast reaches the end of the list.
        // We check both conditions because fast will move two steps.
        while (fast != null && fast.next != null) {

            // Move slow by one node
            slow = slow.next;

            // Move fast by two nodes
            fast = fast.next.next;
        }

        // When the loop ends:
        // - For an odd-length list, slow is at the exact middle.
        // - For an even-length list, slow is at the second middle,
        //   which is exactly what the problem asks for.
        return slow;
    }
}