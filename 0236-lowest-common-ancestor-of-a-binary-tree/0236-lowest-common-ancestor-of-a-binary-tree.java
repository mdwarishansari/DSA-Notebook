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

        // Base Case 1:
        // If we reached outside the tree,
        // there is nothing to search.
        if (root == null) {
            return null;
        }

        // Base Case 2:
        // If current node is either p or q,
        // return it immediately.
        // A node can be its own ancestor.
        if (root == p || root == q) {
            return root;
        }

        // Search in the left subtree.
        TreeNode left = lowestCommonAncestor(root.left, p, q);

        // Search in the right subtree.
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both sides returned a non-null node,
        // then p and q are in different subtrees.
        // So current node is their Lowest Common Ancestor.
        if (left != null && right != null) {
            return root;
        }

        // If only the left subtree returned something,
        // propagate it upward.
        if (left != null) {
            return left;
        }

        // Otherwise return whatever the right subtree found
        // (either a node or null).
        return right;
    }
}