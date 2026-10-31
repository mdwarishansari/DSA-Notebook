
import java.util.*;

class LFUCache {

    // Node of the doubly linked list
    class Node {
        int key, value, freq;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    // Doubly Linked List for each frequency
    class DLL {
        Node head, tail;
        int size;

        DLL() {
            head = new Node(0, 0);
            tail = new Node(0, 0);

            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        // Add node at the beginning (Most Recently Used)
        void add(Node node) {
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;

            size++;
        }

        // Remove a node from the list
        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            size--;
        }

        // Remove Least Recently Used node
        Node removeLast() {
            if (size == 0) return null;

            Node node = tail.prev;
            remove(node);

            return node;
        }
    }

    private int capacity;
    private int minFreq;

    // Key -> Node
    private HashMap<Integer, Node> keyMap;

    // Frequency -> Doubly Linked List
    private HashMap<Integer, DLL> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;

        keyMap = new HashMap<>();
        freqMap = new HashMap<>();
    }

    // Increase frequency of an existing node
    private void updateFreq(Node node) {

        int oldFreq = node.freq;

        // Get the old frequency list
        DLL oldList = freqMap.get(oldFreq);

        // Remove node from old frequency list
        oldList.remove(node);

        // If old list becomes empty, remove it
        if (oldList.size == 0) {
            freqMap.remove(oldFreq);

            // Update minimum frequency
            if (minFreq == oldFreq) {
                minFreq++;
            }
        }

        // Increase node frequency
        node.freq++;

        // Get or create new frequency list
        DLL newList = freqMap.computeIfAbsent(
            node.freq, k -> new DLL()
        );

        // Add node as most recently used
        newList.add(node);
    }

    // Get value associated with a key
    public int get(int key) {

        // Key does not exist
        if (!keyMap.containsKey(key)) {
            return -1;
        }

        Node node = keyMap.get(key);

        // Accessing a key increases its frequency
        updateFreq(node);

        return node.value;
    }

    // Insert or update a key-value pair
    public void put(int key, int value) {

        // Capacity is zero
        if (capacity == 0) return;

        // If key already exists
        if (keyMap.containsKey(key)) {

            Node node = keyMap.get(key);

            // Update its value
            node.value = value;

            // Increase its frequency
            updateFreq(node);

            return;
        }

        // If cache is full, remove LFU key
        if (keyMap.size() == capacity) {

            DLL list = freqMap.get(minFreq);

            // Remove LRU node from minimum frequency list
            Node lru = list.removeLast();

            // Remove evicted key from HashMap
            keyMap.remove(lru.key);

            // Remove empty frequency list
            if (list.size == 0) {
                freqMap.remove(minFreq);
            }
        }

        // Create new node (frequency starts at 1)
        Node node = new Node(key, value);

        // Add node to key map
        keyMap.put(key, node);

        // Add node to frequency 1 list
        DLL list = freqMap.computeIfAbsent(
            1, k -> new DLL()
        );

        list.add(node);

        // Every new key starts with frequency 1
        minFreq = 1;
    }
}


/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */