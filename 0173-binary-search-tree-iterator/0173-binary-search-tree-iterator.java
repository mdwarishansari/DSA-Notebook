import java.util.ArrayDeque;
import java.util.Deque;

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
class BSTIterator {
    // Explicit stack to store the current left-spine path of unvisited nodes
    private final Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.stack = new ArrayDeque<>();
        // Initialize the stack with the leftmost path from the root
        pushLeftBranch(root);
    }
    
    /**
     * Moves the pointer to the next smallest element and returns its value.
     */
    public int next() {
        // The top of the stack is always the next smallest element in in-order traversal
        TreeNode smallestNode = stack.pop();
        
        // If this node has a right subtree, push its leftmost path onto the stack
        if (smallestNode.right != null) {
            pushLeftBranch(smallestNode.right);
        }
        
        return smallestNode.val;
    }
    
    /**
     * Returns true if there are still elements left in the traversal.
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * Helper method that pushes a node and all of its left descendants onto the stack.
     */
    private void pushLeftBranch(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */