import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    // Delimiter to separate node values in the serialized string
    private static final String DELIMITER = ",";
    // Marker to represent null child pointers
    private static final String NULL_MARKER = "null";

    // Encodes a tree to a single string using Preorder Traversal (Root -> Left -> Right).
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    /**
     * Recursive helper method to build the serialized string via Preorder DFS.
     *
     * @param node The current TreeNode being processed.
     * @param sb   StringBuilder accumulating the serialized output.
     */
    private void serializeHelper(TreeNode node, StringBuilder sb) {
        // Base case: If the current node is null, append the null marker and delimiter
        if (node == null) {
            sb.append(NULL_MARKER).append(DELIMITER);
            return;
        }

        // 1. Append the current node's value followed by the delimiter
        sb.append(node.val).append(DELIMITER);

        // 2. Recursively serialize the left subtree
        serializeHelper(node.left, sb);

        // 3. Recursively serialize the right subtree
        serializeHelper(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // Split the serialized string by the delimiter and load tokens into a Queue
        // for sequential O(1) consumption in Preorder fashion.
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(DELIMITER)));
        return deserializeHelper(nodes);
    }

    /**
     * Recursive helper method to reconstruct the binary tree from the token queue.
     *
     * @param nodes Queue containing node values and null markers in Preorder sequence.
     * @return      The reconstructed TreeNode.
     */
    private TreeNode deserializeHelper(Queue<String> nodes) {
        // Poll the next token from the front of the queue
        String val = nodes.poll();

        // Base case: If the token represents a null node, return null
        if (val == null || val.equals(NULL_MARKER)) {
            return null;
        }

        // 1. Create the current root node with the parsed integer value
        TreeNode root = new TreeNode(Integer.parseInt(val));

        // 2. Recursively reconstruct the left subtree
        root.left = deserializeHelper(nodes);

        // 3. Recursively reconstruct the right subtree
        root.right = deserializeHelper(nodes);

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));