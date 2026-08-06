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

    public boolean isValidBST(TreeNode root) {

        // Initially, the root can have any integer value.
        // So we start with the widest possible range.
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // Checks whether every node in this subtree
    // lies strictly between min and max.
    private boolean validate(TreeNode node, long min, long max) {

        // An empty tree is always a valid BST.
        if (node == null) {
            return true;
        }

        // Current node must be strictly inside the allowed range.
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // Left subtree:
        // Values must be smaller than the current node.
        boolean leftValid = validate(node.left, min, node.val);

        // Right subtree:
        // Values must be greater than the current node.
        boolean rightValid = validate(node.right, node.val, max);

        // The current subtree is valid only if
        // both left and right subtrees are valid.
        return leftValid && rightValid;
    }
}