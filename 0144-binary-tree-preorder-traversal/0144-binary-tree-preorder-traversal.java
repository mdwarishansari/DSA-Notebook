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

    // List to store the preorder traversal
    List<Integer> answer = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {

        // Start DFS traversal from the root node
        preorder(root);

        // Return the preorder traversal list
        return answer;
    }

    // Recursive DFS function for Preorder Traversal
    private void preorder(TreeNode root) {

        // ---------------- Base Case ----------------
        // If the current node is null,
        // there is nothing to visit.
        if (root == null) {
            return;
        }

        // ---------------- Step 1 ----------------
        // Visit (Process) the current node first.
        // Since this is PREORDER,
        // Root is processed before its children.
        answer.add(root.val);

        // ---------------- Step 2 ----------------
        // Traverse the entire left subtree.
        preorder(root.left);

        // ---------------- Step 3 ----------------
        // Traverse the entire right subtree.
        preorder(root.right);
    }
}