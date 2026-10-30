/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/


import java.util.HashMap;

class Solution {
    public Node copyRandomList(Node head) {

        // Handle an empty linked list.
        if (head == null) {
            return null;
        }

        // Map original nodes to their corresponding copied nodes.
        HashMap<Node, Node> map = new HashMap<>();

        // First pass: Create a copy of every node.
        Node curr = head;

        while (curr != null) {

            // Create a new node with the same value.
            Node copy = new Node(curr.val);

            // Store original node -> copied node.
            map.put(curr, copy);

            curr = curr.next;
        }

        // Second pass: Connect next and random pointers.
        curr = head;

        while (curr != null) {

            // Get the copied version of the current node.
            Node copy = map.get(curr);

            // Connect the copied next pointer.
            copy.next = map.get(curr.next);

            // Connect the copied random pointer.
            copy.random = map.get(curr.random);

            curr = curr.next;
        }

        // Return the copied head.
        return map.get(head);
    }
}
