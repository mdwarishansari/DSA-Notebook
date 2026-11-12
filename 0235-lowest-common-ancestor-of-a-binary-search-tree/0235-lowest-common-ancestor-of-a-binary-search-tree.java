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
        // Traverse the BST iteratively
        while (root != null) {
            // If both p and q are greater than root, the LCA lies in the right subtree
            if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } 
            // If both p and q are less than root, the LCA lies in the left subtree
            else if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } 
            // We have found the split point where p and q diverge (or one is the parent of the other)
            else {
                return root;
            }
        }
        
        return null;
    }
}