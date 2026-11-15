import java.util.HashMap;
import java.util.Map;

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
    // Tracks the current root index in the preorder array
    private int preorderIndex = 0;
    // Stores value -> index mappings for the inorder array for O(1) lookups
    private Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Populate the map with inorder values and their corresponding indices
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        // Start recursive construction covering the entire inorder array range
        return construct(preorder, 0, inorder.length - 1);
    }

    private TreeNode construct(int[] preorder, int left, int right) {
        // Base case: if there are no elements to construct the subtree
        if (left > right) {
            return null;
        }

        // The current element in preorder is always the root of the current subtree
        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);

        // Find the root's position in the inorder array to split left and right subtrees
        int inorderIndex = inorderIndexMap.get(rootVal);

        // Recursively build the left subtree using elements to the left of root in inorder
        // Note: Left subtree MUST be built first because preorder visits: Root -> Left -> Right
        root.left = construct(preorder, left, inorderIndex - 1);

        // Recursively build the right subtree using elements to the right of root in inorder
        root.right = construct(preorder, inorderIndex + 1, right);

        return root;
    }
}