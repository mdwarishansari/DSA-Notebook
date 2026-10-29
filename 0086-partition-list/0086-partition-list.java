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
    public ListNode partition(ListNode head, int x) {

        // Dummy nodes simplify building both lists.
        ListNode smallDummy = new ListNode(0);
        ListNode largeDummy = new ListNode(0);

        ListNode small = smallDummy;
        ListNode large = largeDummy;

        // Traverse the original linked list.
        ListNode curr = head;

        while (curr != null) {
            // Save the next node before changing any links.
            ListNode nextNode = curr.next;

            // Detach the current node to avoid unwanted links.
            curr.next = null;

            if (curr.val < x) {
                // Append to the list containing values < x.
                small.next = curr;
                small = small.next;
            } else {
                // Append to the list containing values >= x.
                large.next = curr;
                large = large.next;
            }

            curr = nextNode;
        }

        // Join the two partitions.
        small.next = largeDummy.next;

        // Ensure the resulting list ends properly.
        large.next = null;

        return smallDummy.next;
    }
}
