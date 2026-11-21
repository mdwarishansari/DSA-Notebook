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
    public boolean isValidBST(TreeNode root) {
        // Start validation at the root with unbounded 64-bit limits to avoid 32-bit int overflow
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Recursively verifies that every node in the subtree satisfies min < node.val < max.
     */
    private boolean validate(TreeNode node, long min, long max) {
        // An empty subtree is a valid BST by definition
        if (node == null) {
            return true;
        }

        // Current node's value must be strictly greater than min and strictly less than max
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // Left subtree values must be strictly less than current node's value
        // Right subtree values must be strictly greater than current node's value
        return validate(node.left, min, node.val) && 
               validate(node.right, node.val, max);
    }
}