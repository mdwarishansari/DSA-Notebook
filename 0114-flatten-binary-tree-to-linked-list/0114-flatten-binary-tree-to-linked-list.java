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
    public void flatten(TreeNode root) {
        // Base case: if the tree is empty, nothing to flatten
        if (root == null) {
            return;
        }
        
        TreeNode current = root;
        
        while (current != null) {
            // If the current node has a left child, we need to insert its subtree
            // into the correct position in the pre-order traversal order.
            if (current.left != null) {
                // Find the rightmost node (predecessor) of the left subtree
                TreeNode predecessor = current.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                
                // Connect the current node's original right subtree to the 
                // rightmost node of the left subtree
                predecessor.right = current.right;
                
                // Shift the entire left subtree to the right child pointer
                current.right = current.left;
                
                // Set the left child pointer to null as required
                current.left = null;
            }
            
            // Move to the next node on the right
            current = current.right;
        }
    }
}