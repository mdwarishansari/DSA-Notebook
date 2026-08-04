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

    // Stores the maximum path sum found anywhere in the tree.
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

        // Start DFS traversal.
        dfs(root);

        // Return the maximum path sum found.
        return maxSum;
    }

    // Returns:
    // Maximum gain that can be extended upward to the parent.
    private int dfs(TreeNode node) {

        // Empty node contributes nothing.
        if (node == null) {
            return 0;
        }

        // Recursively get maximum gain from left subtree.
        int leftGain = dfs(node.left);

        // Recursively get maximum gain from right subtree.
        int rightGain = dfs(node.right);

        // Ignore negative gains because they only reduce
        // the total path sum.
        leftGain = Math.max(0, leftGain);
        rightGain = Math.max(0, rightGain);

        // Best path passing THROUGH the current node.
        // Here we are allowed to take both children because
        // this path ends at the current node.
        int currentPath = node.val + leftGain + rightGain;

        // Update global maximum path sum.
        maxSum = Math.max(maxSum, currentPath);

        // Return only ONE side to the parent.
        // Parent cannot continue both branches.
        return node.val + Math.max(leftGain, rightGain);
    }
}