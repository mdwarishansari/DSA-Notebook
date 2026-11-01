/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {

        // Handle an empty list
        if (head == null) return null;

        // Flatten the entire list
        flattenList(head);

        return head;
    }

    // Returns the TAIL of the flattened list
    private Node flattenList(Node head) {

        Node curr = head;
        Node tail = head;

        while (curr != null) {

            // Save the original next node
            Node next = curr.next;

            // Check if current node has a child
            if (curr.child != null) {

                // Get the child list
                Node child = curr.child;

                // Recursively flatten child list
                Node childTail = flattenList(child);

                // Connect current node to child
                curr.next = child;
                child.prev = curr;

                // Remove child pointer
                curr.child = null;

                // Connect child tail to original next
                if (next != null) {
                    childTail.next = next;
                    next.prev = childTail;
                }

                // Update tail
                tail = childTail;

            } else {
                // Current node is the latest tail
                tail = curr;
            }

            // Continue with the original next node
            curr = next;
        }

        return tail;
    }
}
