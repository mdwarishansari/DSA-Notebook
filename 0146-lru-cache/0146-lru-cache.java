
import java.util.*;

class LRUCache {

    // Node of the doubly linked list
    class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;

    // Stores key and its corresponding node
    private HashMap<Integer, Node> map;

    // Dummy head and tail nodes
    private Node head, tail;

    public LRUCache(int capacity) {

        this.capacity = capacity;
        map = new HashMap<>();

        head = new Node(0, 0);
        tail = new Node(0, 0);

        // Connect dummy nodes
        head.next = tail;
        tail.prev = head;
    }

    // Remove a node from the linked list
    private void removeNode(Node node) {

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Insert a node immediately after head
    private void addNode(Node node) {

        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    // Move an existing node to the most recently used position
    private void moveToHead(Node node) {

        removeNode(node);
        addNode(node);
    }

    // Return the least recently used node
    private Node removeTail() {

        Node lru = tail.prev;
        removeNode(lru);

        return lru;
    }

    // Get the value associated with a key
    public int get(int key) {

        // Key does not exist
        if (!map.containsKey(key)) {
            return -1;
        }

        // Get the node
        Node node = map.get(key);

        // Mark it as recently used
        moveToHead(node);

        return node.value;
    }

    // Insert or update a key-value pair
    public void put(int key, int value) {

        // If key already exists, update its value
        if (map.containsKey(key)) {

            Node node = map.get(key);
            node.value = value;

            // Mark it as recently used
            moveToHead(node);

        } else {

            // Create a new node
            Node node = new Node(key, value);

            // Add node to HashMap and linked list
            map.put(key, node);
            addNode(node);

            // Check whether capacity is exceeded
            if (map.size() > capacity) {

                // Remove least recently used node
                Node lru = removeTail();

                // Remove its key from HashMap
                map.remove(lru.key);
            }
        }
    }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */