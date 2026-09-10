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

    // Stores the final number of nodes whose value
    // is equal to the average of their subtree.
    private int count = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return count;
    }

    /*
     * Returns information about the subtree rooted at 'node'.
     *
     * result[0] = sum of values in the subtree
     * result[1] = number of nodes in the subtree
     */
    private int[] dfs(TreeNode node) {

        // Base case:
        // An empty subtree has sum = 0 and count = 0.
        if (node == null) {
            return new int[]{0, 0};
        }

        // Get sum and count from the left subtree.
        int[] left = dfs(node.left);

        // Get sum and count from the right subtree.
        int[] right = dfs(node.right);

        // Calculate the sum of the current subtree.
        int sum = node.val + left[0] + right[0];

        // Calculate the number of nodes in the current subtree.
        int nodes = 1 + left[1] + right[1];

        // Java integer division automatically rounds down
        // for positive values, which matches the problem requirement.
        int average = sum / nodes;

        // Check whether the current node's value
        // is equal to the average of its subtree.
        if (node.val == average) {
            count++;
        }

        // Return sum and count to the parent node.
        return new int[]{sum, nodes};
    }
}