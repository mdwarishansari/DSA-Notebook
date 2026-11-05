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
    public boolean isBalanced(TreeNode root) {
        // If height returns -1, the tree is unbalanced
        return height(root) != -1;
    }

    // Calculate height and check balance simultaneously
    private int height(TreeNode root) {

        // Base case: Empty node has height 0
        if (root == null) return 0;

        // Find height of left subtree
        int l = height(root.left);

        // If left subtree is unbalanced, return -1
        if (l == -1) return -1;

        // Find height of right subtree
        int r = height(root.right);

        // If right subtree is unbalanced, return -1
        if (r == -1) return -1;

        // Check difference between left and right heights
        if (Math.abs(l - r) > 1) return -1;

        // Return height of current node
        return 1 + Math.max(l, r);
    }
}
