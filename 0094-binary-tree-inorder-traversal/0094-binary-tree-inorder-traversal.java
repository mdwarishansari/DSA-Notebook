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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        // Base case
        if (root == null) return res;

        // Traverse left subtree
        res.addAll(inorderTraversal(root.left));

        // Visit root node
        res.add(root.val);

        // Traverse right subtree
        res.addAll(inorderTraversal(root.right));

        return res;
    }
}