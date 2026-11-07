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
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Calculate the depth of the leftmost and rightmost paths
        int leftHeight = getLeftHeight(root);
        int rightHeight = getRightHeight(root);

        // If the heights are identical, the tree is a perfect binary tree.
        // Node count formula: 2^height - 1
        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1; // Bitwise shift (1 << h) equals 2^h
        }

        // Otherwise, the tree is complete but not perfect.
        // Recursively count nodes in left and right subtrees, plus 1 for the current node.
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * Helper method to compute the height along the leftmost edge.
     */
    private int getLeftHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }

    /**
     * Helper method to compute the height along the rightmost edge.
     */
    private int getRightHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.right;
        }
        return height;
    }
}