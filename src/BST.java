
/**
 * BST.java
 * @author
 * @author
 * CIS 22C Lab 4
 */

import java.util.NoSuchElementException;

public class BST<T extends Comparable<T>> {
    private class Node {
        private T data;
        private Node left;
        private Node right;

        public Node(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node root;

    /*** CONSTRUCTORS ***/

    /**
     * Default constructor for BST
     * sets root to null
     */
    public BST() {
        root = null;
    }

    /**
     * Copy constructor for BST
     * 
     * @param bst the BST to make
     *            a copy of
     */
    public BST(BST<T> bst) {

        if (bst == null) {
            return;
        }
        if (bst.getSize() == 0) {
            root = null;
        } else {
            root = bst.root;
            copyHelper(bst.root);
        }
    }

    /**
     * Helper method for copy constructor
     * 
     * @param node the node containing
     *             data to copy
     */
    private void copyHelper(Node node) {
        if (node == null) {
            return;
        } else {
            copyHelper(node.left);
            copyHelper(node.right);
            insert(node.data);
        }
    }

    /**
     * Creates a BST of minimal height given an array of values
     * 
     * @param array the list of values to insert
     * @precondition array must be sorted in ascending order
     * @throws IllegalArgumentException when the array is
     *                                  unsorted
     */
    public BST(T[] array) throws IllegalArgumentException {
        boolean isSorted = true;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                isSorted = false;
                break;
            }
        }
        if (!isSorted) {
            throw new IllegalArgumentException();
        }

        root = new Node(array[array.length / 2]);

        for (int i = 0; i < array.length; i++) {
            if (i == array.length / 2) {
                continue;
            }
            this.insert(array[i]);
        }

    }

    /*** ACCESSORS ***/

    /**
     * Returns the data stored in the root
     * 
     * @precondition !isEmpty()
     * @return the data stored in the root
     * @throws NoSuchElementException when
     *                                precondition is violated
     */
    public T getRoot() throws NoSuchElementException {
        if (root == null) {
            throw new NoSuchElementException("no list, no root. sorry.");
        }
        return root.data;
    }

    /**
     * Determines whether the tree is empty
     * 
     * @return whether the tree is empty
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Returns the current size of the
     * tree (number of nodes)
     * 
     * @return the size of the tree
     */
    public int getSize() {
        return -1;
    }

    /**
     * Helper method for the getSize method
     * 
     * @param node the current node to count
     * @return the size of the tree
     */
    private int getSize(Node node) {
        return -1;
    }

    /**
     * Returns the height of tree by
     * counting edges.
     * 
     * @return the height of the tree
     */
    public int getHeight() {
        return -2;
    }

    /**
     * Helper method for getHeight method
     * 
     * @param node the current
     *             node whose height to count
     * @return the height of the tree
     */
    private int getHeight(Node node) {
        return -2;
    }

    /**
     * Returns the smallest value in the tree
     * 
     * @precondition !isEmpty()
     * @return the smallest value in the tree
     * @throws NoSuchElementException when the
     *                                precondition is violated
     */
    public T findMin() throws NoSuchElementException {
        return null;
    }

    /**
     * Helper method to findMin method
     * 
     * @param node the current node to check
     *             if it is the smallest
     * @return the smallest value in the tree
     */
    private T findMin(Node node) {
        return null;
    }

    /**
     * Returns the largest value in the tree
     * 
     * @precondition !isEmpty()
     * @return the largest value in the tree
     * @throws NoSuchElementException when the
     *                                precondition is violated
     */
    public T findMax() throws NoSuchElementException {
        return null;
    }

    /**
     * Helper method to findMax method
     * 
     * @param node the current node to check
     *             if it is the largest
     * @return the largest value in the tree
     */
    private T findMax(Node node) {
        return null;
    }

    /*** MUTATORS ***/

    /**
     * Inserts a new node in the tree
     * 
     * @param data the data to insert
     */
    public void insert(T data) {
        if (root == null) {
            root = new Node(data);
        } else {
            insert(data, root);
        }
    }

    /**
     * Helper method to insert
     * Inserts a new value in the tree
     * 
     * @param data the data to insert
     * @param node the current node in the
     *             search for the correct location
     *             in which to insert
     */
    private void insert(T data, Node node) {
        if (node == null) {
            node = new Node(data);
        }

        if (data.compareTo(node.data) < 0) {
            insert(data, node.left);
        } else if (data.compareTo(node.data) > 0) {
            insert(data, node.right);
        } else { // if equal to one another, checks if left is null (creates new node), otherwise
                 // insertion
            if (node.left == null) {
                node.left = new Node(data);
            } else {
                Node temp = node.left;
                node.left = new Node(data);
                node.left.left = temp;
            }
        }
    }

    /**
     * Removes a value from the BST
     * 
     * @param data the value to remove
     * @precondition !isEmpty()
     * @throws IllegalStateException when BST is empty
     */
    public void remove(T data) throws IllegalStateException {
        if (this.isEmpty()) {
            throw new IllegalStateException("remove(): Tree is empty");
        }

        if (root.data.compareTo(data) == 0 && root.left == null && root.right == null) {
            root = null;
        } else {
            Node toRemove = remove(data, root);

        }
    }

    /**
     * Helper method to the remove method
     * 
     * @param data the data to remove
     * @param node the current node
     * @return an updated reference variable
     */
    private Node remove(T data, Node node) {
        // if (node.data.compareTo(data) == 0 && node.left == null && node.right ==
        // null) { // leaf
        // node = null;
        // }

        // If node is null
        // 1. return node
        if (node == null) {
            return node;
        }
        // 2. Otherwise, if value < the node's data
        // 1.set node's leftchild equal to the recursive call of remove helper on node's
        // leftchild
        if (node.data.compareTo(data) < 0) {
            node.left = remove(data, node.left);
            // 3. Otherwise, if value > the node's data
            // 1. set node's rightchild equal to the recursive call of remove helper on
            // node's rightchild
        } else if (node.data.compareTo(data) > 0) {
            node.right = remove(data, node.right);
        }

        else {
            // 4. Otherwise,
            // 1. If node is a leaf node
            // 1. Set node to null
            if (node.left == null && node.right == null) {
                node = null;
            }
            // 2. Otherwise if node has a leftchild but no rightchild
            // 1. set the leftchild to be the node
            else if (node.right == null && node.left != null) {
                node = node.left;
                // 3. Otherwise if node has a rightchild but no leftchild
                // 1. set the rightchild to be the node
            } else if (node.left == null && node.right != null) {
                node = node.right;
            } else {
                // first move right
                // then we check a buncha left, for a left with no left of its own. smallest guy
                // if the smallest guy is a leaf node, then node to remove = small guy.data, and
                // smallguy = null
                // if smallguy not leaf node, then nodetoremove.right = smallguy.right; and
                // smallguy = null
                Node temp = node.right;
                if (temp.left == null) {
                    node.data = temp.data;
                    node.right = temp.right;
                } else {
                    while (temp.left.left != null) {
                        temp = temp.left;
                    }
                    node.data = temp.left.data;
                    temp.left = null;
                }
            }
        }
        return node; // step 5!
        // 4. Otherwise
        // 1. Search for the minimum value in the right subtree of the node
        // 2. Set the node's data to be the minimum value in the node's right subtree
        // 3. Set node's rightchild equal to the recursive call of remove helper,
        // passing it node's お茶 あれあれなに (what?) and the minimum data of node's right
        // subtree
        // (i.e. delete the duplicate value in the right subtree)
        // 5. return the node
    }

    /*** ADDITIONAL OPERATIONS ***/

    /**
     * Searches for a specified value
     * in the tree
     * 
     * @param data the value to search for
     * @return whether the value is stored
     *         in the tree
     */
    public boolean search(T data) {
        return search(data, root);
    }

    /**
     * Helper method for the search method
     * 
     * @param data the data to search for
     * @param node the current node to check
     * @return whether the data is stored
     *         in the tree
     */
    private boolean search(T data, Node node) {
        if (node == null) {
            return false;
        }

        if (node.data.compareTo(data) == 0) {
            return true;
        } else if (data.compareTo(node.data) < 0) {
            search(data, node.left);
        } else {
            search(data, node.right);
        }
        return false;
    }

    /**
     * Determines whether a BST is balanced
     * using the definition given in the course
     * lesson notes
     * Note that we will consider an empty tree
     * to be trivially balanced
     * 
     * @return whether the BST is balanced
     */
    public boolean isBalanced() {
        if (root == null) {
            return true;
        }
        return isBalanced(root);
    }

    /**
     * Helper method for isBalanced
     * to determine if a BST is balanced
     * 
     * @param n a Node in the tree
     * @return whether the BST is balanced
     *         at the level of the given Node
     */
    private boolean isBalanced(Node n) {
        if (n != null) {
            if (Math.abs(getHeight(n.left) - getHeight(n.right)) > 1) {
                return false;
            }
            return isBalanced(n.left) && isBalanced(n.right);
        }
        return true;
    }

    /**
     * Returns a String containing the data
     * in post order
     * 
     * @return a String of data in post order
     */
    public String preOrderString() {
        return "\n";
    }

    /**
     * Helper method to preOrderString
     * Inserts the data in pre order into a String
     * 
     * @param node     the current Node
     * @param preOrder a String containing the data
     */
    private void preOrderString(Node node, StringBuilder preOrder) {

    }

    /**
     * Returns a String containing the data
     * in order
     * 
     * @return a String of data in order
     */
    public String inOrderString() {
        return "\n";
    }

    /**
     * Helper method to inOrderString
     * Inserts the data in order into a String
     * 
     * @param node    the current Node
     * @param inOrder a String containing the data
     */
    private void inOrderString(Node node, StringBuilder inOrder) {

    }

    /**
     * Returns a String containing the data
     * in post order
     * 
     * @return a String of data in post order
     */
    public String postOrderString() {
        return "\n";
    }

    /**
     * Helper method to postOrderString
     * Inserts the data in post order into a String
     * 
     * @param node      the current Node
     * @param postOrder a String containing the data
     */
    private void postOrderString(Node node, StringBuilder postOrder) {

    }

}
