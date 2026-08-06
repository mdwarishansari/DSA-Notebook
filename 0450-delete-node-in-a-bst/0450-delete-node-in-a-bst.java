/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *
 *     TreeNode() {}
 *
 *     TreeNode(int val) {
 *         this.val = val;
 *     }
 *
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {

    public TreeNode deleteNode(TreeNode root, int key) {

        // Base Case:
        // If the current subtree is empty,
        // the key does not exist.
        if (root == null) {
            return null;
        }

        // If the key is smaller,
        // search in the left subtree.
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }

        // If the key is larger,
        // search in the right subtree.
        else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }

        // Found the node to delete.
        else {

            // Case 1 & Case 2:
            // No left child OR only right child.
            if (root.left == null) {
                return root.right;
            }

            // No right child OR only left child.
            if (root.right == null) {
                return root.left;
            }

            // Case 3:
            // Node has two children.

            // Find the inorder successor
            // (smallest node in the right subtree).
            TreeNode successor = findMin(root.right);

            // Copy the successor's value
            // into the current node.
            root.val = successor.val;

            // Delete the duplicate successor node
            // from the right subtree.
            root.right = deleteNode(root.right, successor.val);
        }

        // Return the updated subtree root.
        return root;
    }

    // Finds the smallest node in a subtree.
    private TreeNode findMin(TreeNode node) {

        // Keep moving left until there is no left child.
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }
}