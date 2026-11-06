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

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) {
 *         this.val = val;
 *     }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {

    // Main method: Checks whether subRoot exists inside root.
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        // If subRoot is null, it is considered a subtree.
        if (subRoot == null) {
            return true;
        }

        // If root becomes null but subRoot is not null,
        // subRoot cannot exist inside root.
        if (root == null) {
            return false;
        }

        // Check whether the current root node matches subRoot.
        if (isSameTree(root, subRoot)) {
            return true;
        }

        // Otherwise, search in the left and right subtrees.
        return isSubtree(root.left, subRoot) ||
               isSubtree(root.right, subRoot);
    }

    // Helper method: Checks whether two trees are identical.
    private boolean isSameTree(TreeNode p, TreeNode q) {

        // Both nodes are null: structures match.
        if (p == null && q == null) {
            return true;
        }

        // Only one node is null: structures do not match.
        if (p == null || q == null) {
            return false;
        }

        // If values are different, trees are not identical.
        if (p.val != q.val) {
            return false;
        }

        // Recursively compare left and right subtrees.
        return isSameTree(p.left, q.left) &&
               isSameTree(p.right, q.right);
    }
}
