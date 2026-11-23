import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public TreeNode canMerge(List<TreeNode> trees) {
        // Maps each root's value to its corresponding TreeNode for O(1) lookup
        Map<Integer, TreeNode> rootMap = new HashMap<>();
        // Stores values of all leaf nodes across all given BSTs
        Set<Integer> leafValues = new HashSet<>();

        for (TreeNode root : trees) {
            rootMap.put(root.val, root);
            if (root.left != null) {
                leafValues.add(root.left.val);
            }
            if (root.right != null) {
                leafValues.add(root.right.val);
            }
        }

        // Find the single root that is NOT a leaf in any tree.
        // If a valid merged BST exists, this node must be the final global root.
        TreeNode mainRoot = null;
        for (TreeNode root : trees) {
            if (!leafValues.contains(root.val)) {
                // If more than one candidate root exists, we cannot merge all into a single tree
                if (mainRoot != null) {
                    return null;
                }
                mainRoot = root;
            }
        }

        // If every root appears as a leaf (cycle), no valid starting root exists
        if (mainRoot == null) {
            return null;
        }

        // Remove the mainRoot from the map since it is already placed as the root
        rootMap.remove(mainRoot.val);

        // Perform DFS to attach matching subtrees and validate BST range constraints
        if (!buildAndValidate(mainRoot, Integer.MIN_VALUE, Integer.MAX_VALUE, rootMap)) {
            return null;
        }

        // Ensure all n - 1 subtrees were successfully merged (no disconnected components left)
        return rootMap.isEmpty() ? mainRoot : null;
    }

    private boolean buildAndValidate(TreeNode node, int minVal, int maxVal, Map<Integer, TreeNode> rootMap) {
        if (node == null) {
            return true;
        }

        // Check strict BST ordering invariant
        if (node.val <= minVal || node.val >= maxVal) {
            return false;
        }

        // If the current node is a leaf and matches another BST's root value, merge them
        if (node.left == null && node.right == null && rootMap.containsKey(node.val)) {
            TreeNode subtree = rootMap.remove(node.val);
            node.left = subtree.left;
            node.right = subtree.right;
        }

        // Recursively validate and merge left and right children with tightened bounds
        return buildAndValidate(node.left, minVal, node.val, rootMap) &&
               buildAndValidate(node.right, node.val, maxVal, rootMap);
    }
}