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
    public boolean isSymmetric(TreeNode root) {

        // An empty tree is always symmetric.
        if (root == null) {
            return true;
        }

        // Compare left and right subtrees as mirror images.
        return isMirror(root.left, root.right);
    }

    // Recursively check whether two subtrees are mirrors.
    private boolean isMirror(TreeNode p, TreeNode q) {

        // Both nodes are null, so they are symmetric.
        if (p == null && q == null) {
            return true;
        }

        // Only one node is null, so they are not symmetric.
        if (p == null || q == null) {
            return false;
        }

        // Node values must be equal.
        if (p.val != q.val) {
            return false;
        }

        // Compare opposite children to maintain mirror symmetry.
        return isMirror(p.left, q.right) &&
               isMirror(p.right, q.left);
    }
}
