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

    // Stores the maximum diameter found so far.
    // Diameter is measured in number of EDGES.
    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {

        // Start DFS to calculate heights and update diameter.
        height(root);

        // Return the largest diameter found.
        return diameter;
    }

    // Returns the height of the subtree rooted at 'root'.
    private int height(TreeNode root) {

        // ---------------- Base Case ----------------
        // An empty tree has height 0.
        if (root == null) {
            return 0;
        }

        // ---------------- Step 1 ----------------
        // Find the height of the left subtree.
        int leftHeight = height(root.left);

        // ---------------- Step 2 ----------------
        // Find the height of the right subtree.
        int rightHeight = height(root.right);

        // ---------------- Step 3 ----------------
        // The longest path passing through the current node
        // is leftHeight + rightHeight (measured in edges).
        diameter = Math.max(diameter, leftHeight + rightHeight);

        // ---------------- Step 4 ----------------
        // Return the height of the current subtree.
        // Current node contributes 1 to the height.
        return 1 + Math.max(leftHeight, rightHeight);
    }
}