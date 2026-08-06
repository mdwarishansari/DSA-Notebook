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

    public TreeNode insertIntoBST(TreeNode root, int val) {

        // Base Case:
        // If we've reached a null position,
        // this is exactly where the new node should be inserted.
        if (root == null) {
            return new TreeNode(val);
        }

        // If the new value is smaller,
        // insert it into the left subtree.
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        }
        // Otherwise, insert it into the right subtree.
        // (The problem guarantees val does not already exist.)
        else {
            root.right = insertIntoBST(root.right, val);
        }

        // Return the current subtree root.
        // This reconnects the updated subtree to its parent.
        return root;
    }
}