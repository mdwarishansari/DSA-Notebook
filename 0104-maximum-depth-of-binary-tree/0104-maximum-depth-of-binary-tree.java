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
    public int maxDepth(TreeNode root) {

        // Base case: Empty tree has depth 0
        if (root == null) return 0;

        // Calculate left subtree depth
        int l = maxDepth(root.left);

        // Calculate right subtree depth
        int r = maxDepth(root.right);

        // Return maximum depth including current node
        return 1 + Math.max(l, r);
    }
}
