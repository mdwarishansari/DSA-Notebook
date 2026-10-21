
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Dummy node handles removal of the head itself.
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // Keep a gap of n nodes between the pointers.
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast reaches the end.
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the nth node from the end.
        slow.next = slow.next.next;

        // Return the updated head.
        return dummy.next;
    }
}
