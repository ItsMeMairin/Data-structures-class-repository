/*
Allison Butt CMSC 315 6380 Project 3 
22 June 26
This class is our main binary tree constructor and handler. This class constructs trees based off of user input, and provides
several checks to check if it is a Binary Search Tree, or a Max Heap. 
It also provides us several methods to display that information in a readable format back to the user. 
*/
import java.util.ArrayList;

public class CompleteBinaryTree {

    protected TreeNode root;

    /**
     * A static nested class representing a node in the binary tree.
     * Contains an integer value and references to left and right children.
     */
    public static class TreeNode {
        protected Integer value;
        protected TreeNode left;
        protected TreeNode right;

        /**
         * Constructs a TreeNode with a given integer value.
         * 
         * @param value the value to store in the node
         */
        public TreeNode(Integer value) {
            this.value = value;
        }
    }

    /**
     * Constructs a CompleteBinaryTree from an array of Integer values that
     * represent a complete binary tree in level-order.
     * 
     * If the input array is not null and contains elements, it initializes the
     * root of the tree by calling the recursive method `makeNode`, starting from
     * index 0.
     * 
     * @param values an array of Integer values representing the binary tree
     *               in level-order
     * @throws InvalidTreeException if the array contains a null element
     *                              where a node is expected
     */
    public CompleteBinaryTree(Integer[] values) throws InvalidTreeException {
        if (values != null && values.length > 0) {
            root = makeNode(values, 0);
        }
    }

    /**
     * Constructs a CompleteBinaryTree from a whitespace-separated string of
     * integers representing the tree in level-order.
     * 
     * The string is parsed into the integer toens and used to recursively build
     * the tree starting from index 0 via {@code makeNode}.
     * 
     * If the input is null or contains only whitespace, the tree is consiedered
     * empty ({@code root} is null). If any token is not a valid integer, an
     * {@code InvalidTreeException} is thrown.
     * 
     * @param levelOrderValues the level-order representation of the tree as a 
     * string
     * 
     * @throws InvalidTreeException if any token is not a valid integer
     */

    public CompleteBinaryTree(String levelOrderValues) throws InvalidTreeException {
        if (levelOrderValues == null || levelOrderValues.isBlank()) {
            return;
        }        
        String[] cleanedStringArray = levelOrderValues.strip().split("\\s+");
        Integer[] toIntArray = new Integer[cleanedStringArray.length];
        for (int i = 0 ; i < cleanedStringArray.length ; i++) {

            try {
             toIntArray[i] = Integer.valueOf(cleanedStringArray[i]);
            }
            catch (NumberFormatException e) {
                throw new InvalidTreeException("Node value must be an integer.");
            }
        }

        root = makeNode(toIntArray, 0);
        
    }

    /**
     * Recursively constructs a complete binary tree from an array.
     * The array is assumed to represent a complete binary tree in level-order
     * traversal.
     * 
     * For each index `i` in the array:
     * - The element at index `i` represents the node.
     * - The left child of the node is at index `2*i + 1`.
     * - The right child of the node is at index `2*i + 2`.
     * 
     * This method constructs the tree in a level-by-level manner.
     * 
     * @param values array of integer values representing the tree in
     *               level-order
     * @param index  current index in the array that corresponds to the
     *               current node
     * @return TreeNode at the current index, with left and right children
     *         recursively constructed
     * @throws InvalidTreeException if a node value is null or invalid
     */
    protected TreeNode makeNode(Integer[] values, int index) throws InvalidTreeException {
        if (index >= values.length) {
            return null;
        }
        if (values[index] == null) {
            throw new InvalidTreeException("Node element must not be null");
        }

        TreeNode node = new TreeNode(values[index]);
        node.left = makeNode(values, 2 * index + 1);
        node.right = makeNode(values, 2 * index + 2);

        return node;
    }

    /**
     * Performs a preorder traversal of the tree.
     */
    public void preorder() {
        System.out.println("Preorder:");
        preorder(root, 0);
    }

    /**
     * Recursive helper method for preorder traversal.
     *
     * @param root the current subtree root
     */
    private void preorder(TreeNode root, int level) {
        if (root == null)
            return;
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(root.value);

        preorder(root.left, level + 1);
        preorder(root.right, level + 1);
    }

    public boolean isMaxHeap() {
        return isMaxHeap(root);
    }

    private boolean isMaxHeap(TreeNode node) {
        if (node == null) return true;

        if (node.left != null && node.value < node.left.value) return false;
        if (node.right != null && node.value < node.right.value) return false;

        return isMaxHeap(node.left) && isMaxHeap(node.right);
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, null, null);
    }

    private boolean isBinarySearchTree(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;

        if (min != null && node.value <= min) return false;
        if (max != null && node.value >= max) return false;

        return isBinarySearchTree(node.left, min, node.value)
            && isBinarySearchTree(node.right, node.value, max);
    }

    public ArrayList<Integer> inorderList() {
        ArrayList<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(TreeNode node, ArrayList<Integer> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.value);
        inorder(node.right, list);
    }

}
