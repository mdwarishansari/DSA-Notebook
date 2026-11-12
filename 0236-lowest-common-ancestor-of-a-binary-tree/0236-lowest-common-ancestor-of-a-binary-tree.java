/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if root is null, or if root matches p or q, return root
        if (root == null || root == p || root == q) {
            return root;
        }

        // Recursively search for p and q in the left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both left and right are non-null, p and q are in separate subtrees.
        // Therefore, the current root is their Lowest Common Ancestor.
        if (left != null && right != null) {
            return root;
        }

        // Otherwise, return the non-null child result (or null if neither subtree contained p or q)
        return left != null ? left : right;
    }
}