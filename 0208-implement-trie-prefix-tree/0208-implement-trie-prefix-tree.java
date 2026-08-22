class Trie {

    // Each node represents one character in the Trie.
    static class TrieNode {

        // There can be up to 26 lowercase English characters
        // coming from this node.
        TrieNode[] children = new TrieNode[26];

        // true means a complete word ends at this node.
        boolean isEnd;
    }

    // Root does not represent any character.
    private TrieNode root;

    // Constructor
    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the Trie.
    public void insert(String word) {

        // Start from the root.
        TrieNode current = root;

        // Process every character in the word.
        for (char c : word.toCharArray()) {

            // Convert character into an index:
            // 'a' -> 0
            // 'b' -> 1
            // ...
            // 'z' -> 25
            int index = c - 'a';

            // If this character's node doesn't exist,
            // create a new node.
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            // Move to the next node.
            current = current.children[index];
        }

        // The complete word ends at this node.
        current.isEnd = true;
    }

    // Returns true if the complete word exists in the Trie.
    public boolean search(String word) {

        // Try to find the path for this word.
        TrieNode current = root;

        for (char c : word.toCharArray()) {

            // Convert character to array index.
            int index = c - 'a';

            // If the path doesn't exist,
            // the word cannot exist.
            if (current.children[index] == null) {
                return false;
            }

            // Move to the next node.
            current = current.children[index];
        }

        // The path exists, but we also need to make sure
        // that a complete word actually ends here.
        return current.isEnd;
    }

    // Returns true if any inserted word starts with the prefix.
    public boolean startsWith(String prefix) {

        // Start from the root.
        TrieNode current = root;

        for (char c : prefix.toCharArray()) {

            // Convert character to array index.
            int index = c - 'a';

            // If this character doesn't exist,
            // no word can have this prefix.
            if (current.children[index] == null) {
                return false;
            }

            // Move to the next node.
            current = current.children[index];
        }

        // The complete prefix path exists.
        return true;
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */