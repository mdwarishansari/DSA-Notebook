/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *
 *     TreeNode() {}
 *
 *     TreeNode(int val) {
 *         this.val = val;
 *     }
 *
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {

    // Returns the maximum depth (height) of the binary tree
    public int maxDepth(TreeNode root) {

        // ---------------- Base Case ----------------
        // If the current node is null,
        // the tree is empty, so its height is 0.
        if (root == null) {
            return 0;
        }

        // ---------------- Step 1 ----------------
        // Recursively find the height of the left subtree.
        int leftHeight = maxDepth(root.left);

        // ---------------- Step 2 ----------------
        // Recursively find the height of the right subtree.
        int rightHeight = maxDepth(root.right);

        // ---------------- Step 3 ----------------
        // Current node contributes 1 to the height.
        // Add 1 to the larger of the two subtree heights.
        return 1 + Math.max(leftHeight, rightHeight);
    }
}