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
    /**
     * Deletes the node with the given key in the BST and returns the root reference.
     *
     * @param root The root node of the Binary Search Tree.
     * @param key  The value of the node to be deleted.
     * @return The root node reference (possibly updated) of the BST.
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        // Base case: tree is empty or key is not found
        if (root == null) {
            return null;
        }

        // Stage 1: Search for the node to remove using BST properties[cite: 2]
        if (key < root.val) {
            // Target key is smaller, recurse on the left subtree
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            // Target key is larger, recurse on the right subtree
            root.right = deleteNode(root.right, key);
        } else {
            // Stage 2: Node to be deleted is found (root.val == key)[cite: 2]

            // Case 1 & 2: Node has 0 or 1 child
            // Return the non-null child (or null if leaf) to bypass the current node
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Case 3: Node has 2 children
            // Find the inorder successor (the smallest value node in the right subtree)
            TreeNode successor = findMin(root.right);

            // Copy the inorder successor's value into the current node
            root.val = successor.val;

            // Recursively delete the inorder successor from the right subtree
            root.right = deleteNode(root.right, successor.val);
        }

        return root;
    }

    /**
     * Helper method to find the minimum value node in a given subtree.
     * In a BST, the minimum node is always the leftmost leaf.
     */
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}