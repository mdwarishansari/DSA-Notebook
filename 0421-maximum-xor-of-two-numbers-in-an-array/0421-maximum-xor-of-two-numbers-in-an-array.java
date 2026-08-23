class Solution {

    // Each Trie node has two possible children:
    // child[0] -> bit 0
    // child[1] -> bit 1
    static class TrieNode {
        TrieNode[] child = new TrieNode[2];
    }

    // Insert one number into the Binary Trie
    private void insert(TrieNode root, int num) {

        TrieNode current = root;

        // int has 31 useful bits for this problem
        // because nums[i] >= 0.
        for (int bit = 30; bit >= 0; bit--) {

            // Extract the current bit.
            int currentBit = (num >> bit) & 1;

            // If this path doesn't exist, create it.
            if (current.child[currentBit] == null) {
                current.child[currentBit] = new TrieNode();
            }

            // Move to the next level.
            current = current.child[currentBit];
        }
    }

    // Find the maximum XOR possible with num
    private int findMaxXOR(TrieNode root, int num) {

        TrieNode current = root;
        int maxXor = 0;

        // Start from the most significant bit.
        for (int bit = 30; bit >= 0; bit--) {

            int currentBit = (num >> bit) & 1;

            // To maximize XOR, we WANT the opposite bit.
            //
            // If currentBit = 0 -> want 1
            // If currentBit = 1 -> want 0
            int oppositeBit = 1 - currentBit;

            // If opposite bit exists,
            // choosing it makes this XOR bit = 1.
            if (current.child[oppositeBit] != null) {

                // Set this bit in the answer.
                maxXor |= (1 << bit);

                // Move to the opposite-bit path.
                current = current.child[oppositeBit];

            } else {

                // Opposite bit doesn't exist,
                // so we have to take the same bit.
                current = current.child[currentBit];
            }
        }

        return maxXor;
    }

    public int findMaximumXOR(int[] nums) {

        TrieNode root = new TrieNode();

        // Step 1:
        // Put every number into the Binary Trie.
        for (int num : nums) {
            insert(root, num);
        }

        int answer = 0;

        // Step 2:
        // For every number, find the number in the Trie
        // that gives the maximum XOR with it.
        for (int num : nums) {
            answer = Math.max(answer, findMaxXOR(root, num));
        }

        return answer;
    }
}