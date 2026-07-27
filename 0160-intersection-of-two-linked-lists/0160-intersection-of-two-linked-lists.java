/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        // Pointer to traverse List A
        ListNode tempA = headA;

        // Pointer to traverse List B
        ListNode tempB = headB;

        // Store lengths of both linked lists
        int lengthA = 0;
        int lengthB = 0;

        // ----------------------------
        // Step 1 : Calculate length of List A
        // ----------------------------
        while (tempA != null) {
            lengthA++;
            tempA = tempA.next;
        }

        // ----------------------------
        // Step 2 : Calculate length of List B
        // ----------------------------
        while (tempB != null) {
            lengthB++;
            tempB = tempB.next;
        }

        // Reset pointers back to the heads
        tempA = headA;
        tempB = headB;

        // ----------------------------
        // Step 3 : Move the pointer of
        // the longer list ahead
        // ----------------------------

        if (lengthA > lengthB) {

            // Difference between lengths
            int steps = lengthA - lengthB;

            // Move pointer in List A
            while (steps > 0) {
                tempA = tempA.next;
                steps--;
            }

        } else {

            // Difference between lengths
            int steps = lengthB - lengthA;

            // Move pointer in List B
            while (steps > 0) {
                tempB = tempB.next;
                steps--;
            }
        }

        // ----------------------------
        // Step 4 : Move both pointers together
        // ----------------------------
        while (tempA != null && tempB != null) {

            // If both pointers point to the same node,
            // this is the intersection.
            if (tempA == tempB) {
                return tempA;
            }

            // Otherwise move both forward
            tempA = tempA.next;
            tempB = tempB.next;
        }

        // ----------------------------
        // No intersection found
        // ----------------------------
        return null;
    }
}