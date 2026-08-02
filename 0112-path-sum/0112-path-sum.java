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

    // Returns true if there exists a root-to-leaf path
    // whose sum equals targetSum.
    public boolean hasPathSum(TreeNode root, int targetSum) {

        // Base Case 1:
        // Empty tree cannot have any path.
        if (root == null) {
            return false;
        }

        // Subtract current node's value from the remaining target.
        targetSum -= root.val;

        // Base Case 2:
        // If current node is a leaf,
        // check whether the remaining sum becomes 0.
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }

        // Recursively check the left subtree.
        boolean leftPath = hasPathSum(root.left, targetSum);

        // Recursively check the right subtree.
        boolean rightPath = hasPathSum(root.right, targetSum);

        // If either subtree contains a valid path,
        // return true.
        return leftPath || rightPath;
    }
}