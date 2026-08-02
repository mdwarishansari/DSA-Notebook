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

    // List to store the inorder traversal result
    List<Integer> answer = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {

        // Start DFS traversal from the root
        inorder(root);

        // Return the final traversal
        return answer;
    }

    // Recursive DFS function
    private void inorder(TreeNode root) {

        // Base Case:
        // If current node is null, there is nothing to visit.
        if (root == null) {
            return;
        }

        // Step 1:
        // Visit the entire left subtree first.
        inorder(root.left);

        // Step 2:
        // Process the current node.
        answer.add(root.val);

        // Step 3:
        // Visit the entire right subtree.
        inorder(root.right);
    }
}