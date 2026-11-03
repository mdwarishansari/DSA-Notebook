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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        // Base case
        if (root == null) return res;

        // Visit root node first
        res.add(root.val);

        // Traverse left subtree
        res.addAll(preorderTraversal(root.left));

        // Traverse right subtree
        res.addAll(preorderTraversal(root.right));

        return res;
    }
}