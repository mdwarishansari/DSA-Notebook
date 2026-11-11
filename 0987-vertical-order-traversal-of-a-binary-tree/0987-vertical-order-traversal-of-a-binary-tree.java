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
import java.util.*;

class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // List to store each node's data as: {column, row, value}
        List<int[]> nodes = new ArrayList<>();
        
        // Step 1: Traverse the tree and record coordinates
        dfs(root, 0, 0, nodes);
        
        // Step 2: Sort the nodes based on:
        // 1. Column (ascending)
        // 2. Row (ascending)
        // 3. Node value (ascending)
        nodes.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]); // Compare columns
            } else if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]); // Compare rows
            } else {
                return Integer.compare(a[2], b[2]); // Compare values
            }
        });
        
        // Step 3: Group node values by their columns
        List<List<Integer>> result = new ArrayList<>();
        int prevCol = -2000; // Initialize with an out-of-bounds column value
        
        for (int[] node : nodes) {
            int col = node[0];
            int val = node[2];
            
            // If it's a new column, create a new list
            if (col != prevCol) {
                result.add(new ArrayList<>());
                prevCol = col;
            }
            
            // Add the value to the current column list
            result.get(result.size() - 1).add(val);
        }
        
        return result;
    }
    
    // Helper DFS method to traverse the tree and track row and column coordinates
    private void dfs(TreeNode node, int row, int col, List<int[]> nodes) {
        if (node == null) {
            return;
        }
        
        // Record current node's column, row, and value
        nodes.add(new int[] { col, row, node.val });
        
        // Recurse left (col - 1, row + 1) and right (col + 1, row + 1)
        dfs(node.left, row + 1, col - 1, nodes);
        dfs(node.right, row + 1, col + 1, nodes);
    }
}