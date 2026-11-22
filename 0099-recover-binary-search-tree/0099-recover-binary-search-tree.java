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
    // Pointers to track the two swapped nodes and the previously visited node in inorder traversal
    private TreeNode firstElement = null;
    private TreeNode secondElement = null;
    private TreeNode prevElement = null;

    /**
     * Recovers a BST where two nodes were swapped by mistake without changing its structure.
     *
     * @param root The root node of the Binary Search Tree.
     */
    public void recoverTree(TreeNode root) {
        // Perform inorder traversal to locate the two out-of-order nodes
        inorderTraversal(root);

        // Swap the values of the two misplaced nodes to restore the valid BST
        if (firstElement != null && secondElement != null) {
            int temp = firstElement.val;
            firstElement.val = secondElement.val;
            secondElement.val = temp;
        }
    }

    /**
     * Helper method performing standard Left -> Root -> Right inorder traversal.
     * In a valid BST, node values must strictly increase during this traversal.
     */
    private void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        // 1. Traverse the left subtree
        inorderTraversal(root.left);

        // 2. Process current node: check if the previous node's value is greater than current
        if (prevElement != null && prevElement.val > root.val) {
            // If this is the first violation, mark prevElement as the first swapped node
            if (firstElement == null) {
                firstElement = prevElement;
            }
            // Always update secondElement to the current node.
            // This handles both adjacent swaps (1 violation) and non-adjacent swaps (2 violations).
            secondElement = root;
        }

        // Update prevElement to the current node before moving right
        prevElement = root;

        // 3. Traverse the right subtree
        inorderTraversal(root.right);
    }
}