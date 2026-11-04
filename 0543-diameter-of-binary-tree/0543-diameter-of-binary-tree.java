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

    // Stores the maximum diameter found in the tree.
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {

        // Calculate height and update the maximum diameter.
        height(root);

        return max;
    }

    // Returns the height of the current subtree.
    private int height(TreeNode root) {

        // Base case: An empty node has height 0.
        if (root == null) {
            return 0;
        }

        // Calculate the height of left and right subtrees.
        int left = height(root.left);
        int right = height(root.right);

        // Diameter through the current node = left height + right height.
        max = Math.max(max, left + right);

        // Return the height of the current subtree.
        return 1 + Math.max(left, right);
    }
}
