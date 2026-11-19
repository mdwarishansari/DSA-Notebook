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
    // Map to store value -> index mappings of the postorder array for O(1) lookups
    private Map<Integer, Integer> postorderIndexMap;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        postorderIndexMap = new HashMap<>();

        // Populate the HashMap with values and their indices from postorder
        for (int i = 0; i < postorder.length; i++) {
            postorderIndexMap.put(postorder[i], i);
        }

        // Start recursive construction covering the entire range of both arrays
        return buildTree(preorder, 0, preorder.length - 1, 0, postorder.length - 1);
    }

    /**
     * Helper method to recursively construct the binary tree.
     *
     * @param preorder  The preorder traversal array (Root -> Left -> Right).
     * @param preStart  Starting index of the current subtree in preorder.
     * @param preEnd    Ending index of the current subtree in preorder.
     * @param postStart Starting index of the current subtree in postorder.
     * @param postEnd   Ending index of the current subtree in postorder.
     * @return          The root node of the constructed subtree.
     */
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int postStart, int postEnd) {
        // Base case 1: Invalid range means no nodes exist for this subtree
        if (preStart > preEnd) {
            return null;
        }

        // 1. The first element in the current preorder range is always the root
        TreeNode root = new TreeNode(preorder[preStart]);

        // Base case 2: If there is only one element in this range, it's a leaf node
        if (preStart == preEnd) {
            return root;
        }

        // 2. The element immediately after the root in preorder (preorder[preStart + 1])
        // is the root of the LEFT subtree (if we prioritize building left first).
        int leftSubtreeRootVal = preorder[preStart + 1];

        // 3. Find where this left subtree root appears in the postorder array.
        // Since postorder is [Left, Right, Root], everything from postStart up to
        // leftSubtreeRootPostIndex belongs to the left subtree.
        int leftSubtreeRootPostIndex = postorderIndexMap.get(leftSubtreeRootVal);

        // Calculate the total number of nodes in the left subtree
        int leftSubtreeSize = leftSubtreeRootPostIndex - postStart + 1;

        // 4. Recursively construct the LEFT subtree
        // Preorder range: starts right after root (preStart + 1), spans 'leftSubtreeSize' elements
        // Postorder range: starts at postStart, ends at leftSubtreeRootPostIndex
        root.left = buildTree(
            preorder,
            preStart + 1,
            preStart + leftSubtreeSize,
            postStart,
            leftSubtreeRootPostIndex
        );

        // 5. Recursively construct the RIGHT subtree
        // Preorder range: starts right after the left subtree ends, goes up to preEnd
        // Postorder range: starts right after leftSubtreeRootPostIndex, ends just before the current root (postEnd - 1)
        root.right = buildTree(
            preorder,
            preStart + leftSubtreeSize + 1,
            preEnd,
            leftSubtreeRootPostIndex + 1,
            postEnd - 1
        );

        return root;
    }
}