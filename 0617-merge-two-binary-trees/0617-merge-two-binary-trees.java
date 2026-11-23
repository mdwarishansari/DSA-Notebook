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
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // Base case 1: If the first tree node is null, return the second tree's subtree directly
        if (root1 == null) {
            return root2;
        }
        
        // Base case 2: If the second tree node is null, return the first tree's subtree directly
        if (root2 == null) {
            return root1;
        }
        
        // Both nodes exist (overlap): create a new merged node with the sum of their values
        TreeNode mergedNode = new TreeNode(root1.val + root2.val);
        
        // Recursively merge the left children and right children of both trees
        mergedNode.left = mergeTrees(root1.left, root2.left);
        mergedNode.right = mergeTrees(root1.right, root2.right);
        
        return mergedNode;
    }
}