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
    // Stores the overall maximum path sum found across the entire tree
    private int maxSum;

    public int maxPathSum(TreeNode root) {
        // Initialize to minimum integer to handle cases where all node values are negative
        maxSum = Integer.MIN_VALUE;

        // Traverse the tree and compute branch gains
        calculateMaxGain(root);

        return maxSum;
    }

    /**
     * Computes the maximum single-branch path sum extending down from `node`.
     * Also updates `maxSum` with the complete path turning at `node`.
     */
    private int calculateMaxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Recursively compute the maximum gain from left and right subtrees.
        // If the contribution of a branch is negative, ignore it by clamping to 0.
        int leftGain = Math.max(0, calculateMaxGain(node.left));
        int rightGain = Math.max(0, calculateMaxGain(node.right));

        // The maximum path sum passing through `node` as the highest ancestor
        int currentPathSum = node.val + leftGain + rightGain;

        // Update the global maximum path sum
        maxSum = Math.max(maxSum, currentPathSum);

        // Return the maximum single-direction path gain that can be extended to the parent
        return node.val + Math.max(leftGain, rightGain);
    }
}