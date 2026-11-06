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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // Base case: If the current node is null, there is no path
        if (root == null) {
            return false;
        }

        // Subtract the current node's value from targetSum
        int remainingSum = targetSum - root.val;

        // Check if the current node is a leaf (no left or right child)
        if (root.left == null && root.right == null) {
            // If it's a leaf, check if the remaining sum has reached zero
            return remainingSum == 0;
        }

        // Recursively check left and right subtrees with the updated remaining sum.
        // If either subtree has a valid root-to-leaf path, return true.
        return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum);
    }
}