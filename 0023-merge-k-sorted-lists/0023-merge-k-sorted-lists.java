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
import java.util.PriorityQueue;

class Solution {

    public ListNode mergeKLists(ListNode[] lists) {

        // Edge case:
        // If there are no lists, return null.
        if (lists == null || lists.length == 0) {
            return null;
        }

        // ---------------- Step 1 ----------------
        // Create a Min Heap.
        // Compare nodes using their values.
        PriorityQueue<ListNode> minHeap =
                new PriorityQueue<>(
                        (a, b) -> a.val - b.val
                );

        // ---------------- Step 2 ----------------
        // Insert the first node of every list.
        for (ListNode node : lists) {

            // Ignore empty lists.
            if (node != null) {
                minHeap.offer(node);
            }
        }

        /*
            Example

            List1 : 1 -> 4 -> 5
            List2 : 1 -> 3 -> 4
            List3 : 2 -> 6

            Heap

            1
            1
            2
        */


        // ---------------- Step 3 ----------------
        // Dummy node helps build the answer list easily.
        ListNode dummy = new ListNode(-1);

        // Tail always points to the last node
        // of the merged list.
        ListNode tail = dummy;


        // ---------------- Step 4 ----------------
        // Process until heap becomes empty.
        while (!minHeap.isEmpty()) {

            // Smallest node among all lists.
            ListNode smallest = minHeap.poll();

            // Attach it to answer.
            tail.next = smallest;

            // Move tail forward.
            tail = tail.next;

            // If this node has a next node,
            // push it into the heap.
            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        // Return merged list.
        return dummy.next;
    }
}