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

    public TreeNode searchBST(TreeNode root, int val) {

        // Base Case 1:
        // If we reached a null node, the value does not exist in the BST.
        if (root == null) {
            return null;
        }

        // Base Case 2:
        // If the current node contains the target value,
        // return this node (which is the root of the required subtree).
        if (root.val == val) {
            return root;
        }

        // If the target value is smaller than the current node,
        // it can only exist in the left subtree because of BST property.
        if (val < root.val) {
            return searchBST(root.left, val);
        }

        // Otherwise, the target value is greater than the current node,
        // so search only in the right subtree.
        return searchBST(root.right, val);
    }
}