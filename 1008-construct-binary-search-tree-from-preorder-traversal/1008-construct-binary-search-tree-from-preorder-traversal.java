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
    // Global index to track our current position in the preorder array
    private int index = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        // Start building the tree with an upper bound of Integer.MAX_VALUE
        return buildBST(preorder, Integer.MAX_VALUE);
    }

    private TreeNode buildBST(int[] preorder, int upperBound) {
        // Base case: If we have processed all elements OR the current element
        // exceeds the valid upper bound for this subtree, return null.
        if (index == preorder.length || preorder[index] > upperBound) {
            return null;
        }

        // Create the root node for the current subtree and advance the index
        TreeNode root = new TreeNode(preorder[index++]);

        // All nodes in the left subtree must be strictly less than root.val
        root.left = buildBST(preorder, root.val);

        // All nodes in the right subtree must be strictly less than the parent's upperBound[cite: 4]
        root.right = buildBST(preorder, upperBound);

        return root;
    }
}