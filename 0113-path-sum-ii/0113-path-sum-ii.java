import java.util.ArrayList;
import java.util.List;

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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPaths(root, targetSum, currentPath, allPaths);
        return allPaths;
    }

    private void findPaths(TreeNode node, int remainingSum, List<Integer> currentPath, List<List<Integer>> allPaths) {
        // Base case: null node cannot form a path
        if (node == null) {
            return;
        }

        // Add current node to the tracking path
        currentPath.add(node.val);

        // Check if the current node is a leaf and completes the target sum
        if (node.left == null && node.right == null && remainingSum == node.val) {
            // Add a snapshot copy of the valid path to the result list
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            // Recursively explore left and right children with updated remaining sum
            findPaths(node.left, remainingSum - node.val, currentPath, allPaths);
            findPaths(node.right, remainingSum - node.val, currentPath, allPaths);
        }

        // Backtrack: remove current node before returning to the parent caller
        currentPath.remove(currentPath.size() - 1);
    }
}