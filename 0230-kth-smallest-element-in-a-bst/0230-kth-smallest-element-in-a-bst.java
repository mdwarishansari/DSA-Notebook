/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // Counter to keep track of how many nodes we have visited in sorted order
    private int count = 0;
    // Variable to store the k-th smallest value once found
    private int result = -1;

    public int kthSmallest(TreeNode root, int k) {
        // Perform an in-order traversal (Left -> Root -> Right),
        // which visits BST nodes in ascending sorted order.
        inOrderTraversal(root, k);
        return result;
    }

    private void inOrderTraversal(TreeNode node, int k) {
        // Base case: if node is null or we already found the k-th element, stop early
        if (node == null || count >= k) {
            return;
        }

        // 1. Traverse the left subtree first (smaller values)
        inOrderTraversal(node.left, k);

        // 2. Process the current node
        count++;
        if (count == k) {
            result = node.val;
            return; // Stop further traversal once the answer is found
        }

        // 3. Traverse the right subtree (larger values)
        inOrderTraversal(node.right, k);
    }
}