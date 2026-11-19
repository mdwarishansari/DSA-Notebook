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
    // Global pointer to track the current root node in the postorder array.
    // We traverse postorder backwards (Root -> Right -> Left).
    private int postIndex;
    
    // Map to store value -> index mappings of the inorder array for O(1) lookups.
    private Map<Integer, Integer> inorderIndexMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Initialize postIndex to the last element, which is the root of the entire tree
        postIndex = postorder.length - 1;
        inorderIndexMap = new HashMap<>();

        // Populate the HashMap with values and their corresponding indices from inorder
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        // Start recursive construction covering the entire inorder array range
        return buildSubtree(postorder, 0, inorder.length - 1);
    }

    /**
     * Helper method to recursively construct the binary tree.
     *
     * @param postorder The postorder traversal array.
     * @param inLeft    The starting index of the current subtree in the inorder array.
     * @param inRight   The ending index of the current subtree in the inorder array.
     * @return          The root node of the constructed subtree.
     */
    private TreeNode buildSubtree(int[] postorder, int inLeft, int inRight) {
        // Base case: If the left boundary exceeds the right boundary, there are no elements
        // to construct a subtree, so return null.
        if (inLeft > inRight) {
            return null;
        }

        // 1. Pick the current element at postIndex as the root of the current subtree
        int rootVal = postorder[postIndex];
        TreeNode root = new TreeNode(rootVal);

        // Decrement postIndex to move to the next root node (moving backwards in postorder)
        postIndex--;

        // 2. Find the index of this root in the inorder array to split left and right subtrees
        int inorderRootIndex = inorderIndexMap.get(rootVal);

        // 3. IMPORTANT: Build the RIGHT subtree BEFORE the LEFT subtree.
        // Because postorder traversal is [Left, Right, Root], reading it backwards
        // gives [Root, Right, Left]. Thus, the elements immediately preceding the root
        // in postorder belong to the right subtree.
        root.right = buildSubtree(postorder, inorderRootIndex + 1, inRight);

        // 4. Build the LEFT subtree using the elements to the left of the root in inorder
        root.left = buildSubtree(postorder, inLeft, inorderRootIndex - 1);

        return root;
    }
}