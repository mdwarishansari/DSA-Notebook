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
    public boolean isSameTree(TreeNode p, TreeNode q) {

        // Case 1: Both nodes are null, so they are identical.
        if (p == null && q == null) {
            return true;
        }

        // Case 2: Only one node is null, so trees are different.
        if (p == null || q == null) {
            return false;
        }

        // Case 3: Check current node values.
        if (p.val != q.val) {
            return false;
        }

        // Case 4: Recursively compare left and right subtrees.
        return isSameTree(p.left, q.left) &&
               isSameTree(p.right, q.right);
    }
}
