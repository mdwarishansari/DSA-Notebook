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

    // Counts how many nodes have been visited
    // during inorder traversal.
    private int count = 0;

    // Stores the kth smallest value.
    private int answer = 0;

    public int kthSmallest(TreeNode root, int k) {

        // Perform inorder traversal.
        inorder(root, k);

        return answer;
    }

    // Inorder Traversal:
    // Left -> Root -> Right
    private void inorder(TreeNode node, int k) {

        // Base Case:
        // Empty subtree.
        if (node == null) {
            return;
        }

        // Visit the left subtree first,
        // because it contains smaller values.
        inorder(node.left, k);

        // Current node is visited now.
        count++;

        // If this is the kth visited node,
        // store its value.
        if (count == k) {
            answer = node.val;

            // No need to continue searching.
            return;
        }

        // Visit the right subtree.
        inorder(node.right, k);
    }
}