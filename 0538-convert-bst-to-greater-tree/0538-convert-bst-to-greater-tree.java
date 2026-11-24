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
    // Accumulator to keep track of the sum of all nodes greater than the current node
    private int runningSum = 0;

    public TreeNode convertBST(TreeNode root) {
        // Perform a Reverse In-Order Traversal (Right -> Root -> Left).
        // Because it's a BST, this visits nodes in descending order (largest to smallest).
        reverseInOrder(root);
        return root;
    }

    private void reverseInOrder(TreeNode node) {
        // Base case: if the current node is null, return
        if (node == null) {
            return;
        }

        // 1. Traverse the right subtree first (all values greater than the current node)
        reverseInOrder(node.right);

        // 2. Process the current node:
        // Add the current node's original value to the running sum,
        // then update the node's value to this new total.
        runningSum += node.val;
        node.val = runningSum;

        // 3. Traverse the left subtree (all values smaller than the current node)
        reverseInOrder(node.left);
    }
}