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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // Base Case: Found the null spot where the new node belongs
        if (root == null) {
            return new TreeNode(val);
        }
        
        // If val is smaller than current node, insert into the left subtree
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } 
        // If val is greater than current node, insert into the right subtree
        else {
            root.right = insertIntoBST(root.right, val);
        }
        
        // Return the unchanged root node pointer
        return root;
    }
}