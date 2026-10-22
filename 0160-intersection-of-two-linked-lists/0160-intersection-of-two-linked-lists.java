/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        // Start one pointer at the head of each linked list.
        ListNode pA = headA;
        ListNode pB = headB;

        // Continue until both pointers refer to the same node.
        while (pA != pB) {

            // If pA reaches the end, switch it to list B.
            // Otherwise, move to the next node.
            if (pA == null) {
                pA = headB;
            } else {
                pA = pA.next;
            }

            // If pB reaches the end, switch it to list A.
            // Otherwise, move to the next node.
            if (pB == null) {
                pB = headA;
            } else {
                pB = pB.next;
            }
        }

        // Return the intersection node, or null if none exists.
        return pA;
    }
}
