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

class Solution {

    public Node copyRandomList(Node head) {

        // If the original list is empty,
        // there is nothing to copy.
        if (head == null) {
            return null;
        }

        // HashMap to store:
        // Original Node  --> Copied Node
        HashMap<Node, Node> map = new HashMap<>();

        // ===========================
        // First Pass
        // Create all copied nodes.
        // ===========================
        Node current = head;

        while (current != null) {

            // Create a new node having the same value
            Node copyNode = new Node(current.val);

            // Store mapping
            map.put(current, copyNode);

            current = current.next;
        }

        // ===========================
        // Second Pass
        // Connect next and random pointers.
        // ===========================
        current = head;

        while (current != null) {

            // Get copied node corresponding to current node
            Node copyNode = map.get(current);

            // Connect next pointer
            // map.get(null) automatically returns null
            copyNode.next = map.get(current.next);

            // Connect random pointer
            copyNode.random = map.get(current.random);

            current = current.next;
        }

        // Return copied head
        return map.get(head);
    }
}