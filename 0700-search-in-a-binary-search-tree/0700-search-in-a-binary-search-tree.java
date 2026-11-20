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
    public TreeNode searchBST(TreeNode root, int val) {
        // Traverse the tree until we hit a null leaf or find the target node[cite: 1]
        while (root != null) {
            // Case 1: Current node's value matches the target value[cite: 1]
            if (root.val == val) {
                return root; // Return the subtree rooted at this node[cite: 1]
            } 
            // Case 2: Target value is smaller than the current node's value
            else if (val < root.val) {
                // By BST property, the target can only exist in the left subtree
                root = root.left;
            } 
            // Case 3: Target value is greater than the current node's value
            else {
                // By BST property, the target can only exist in the right subtree
                root = root.right;
            }
        }
        
        // If we exit the loop, the value was not found in the BST[cite: 1]
        return null;
    }
}