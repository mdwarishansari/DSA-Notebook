
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // Dummy node simplifies handling the first merged node.
        ListNode dummy = new ListNode(0);

        // curr always points to the last node in the merged list.
        ListNode curr = dummy;

        // Compare nodes while both lists have elements.
        while (list1 != null && list2 != null) {

            // Choose the smaller node from the two lists.
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }

            // Move to the newly attached node.
            curr = curr.next;
        }

        // Attach the remaining nodes from either list.
        if (list1 != null) {
            curr.next = list1;
        } else {
            curr.next = list2;
        }

        // Skip the dummy node and return the merged list.
        return dummy.next;
    }
}
