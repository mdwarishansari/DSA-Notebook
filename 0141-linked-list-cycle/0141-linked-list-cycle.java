/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class Solution {

    public boolean hasCycle(ListNode head) {

        // Two pointers starting from the head
        ListNode slow = head;
        ListNode fast = head;

        // Continue while fast can safely move two steps
        while (fast != null && fast.next != null) {

            // Slow moves one node
            slow = slow.next;

            // Fast moves two nodes
            fast = fast.next.next;

            // If both pointers meet,
            // the list contains a cycle
            if (slow == fast) {
                return true;
            }
        }

        // Fast reached the end of the list,
        // so no cycle exists
        return false;
    }
}