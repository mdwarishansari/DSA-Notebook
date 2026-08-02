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

    // List to store the postorder traversal
    List<Integer> answer = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {

        // Start DFS traversal from the root node
        postorder(root);

        // Return the final postorder traversal
        return answer;
    }

    // Recursive DFS function for Postorder Traversal
    private void postorder(TreeNode root) {

        // ---------------- Base Case ----------------
        // If the current node is null,
        // there is nothing to visit.
        if (root == null) {
            return;
        }

        // ---------------- Step 1 ----------------
        // Traverse the entire left subtree first.
        postorder(root.left);

        // ---------------- Step 2 ----------------
        // Traverse the entire right subtree.
        postorder(root.right);

        // ---------------- Step 3 ----------------
        // Visit (Process) the current node.
        // Since this is POSTORDER,
        // the current node is processed
        // after both left and right subtrees.
        answer.add(root.val);
    }
}