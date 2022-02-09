
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
        if (array == null) {
            return;
        }
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
        return getSize(root);
    }

    /**
     * Helper method for the getSize method
     * 
     * @param node the current node to count
     * @return the size of the tree
     */
    private int getSize(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + getSize(node.left) + getSize(node.right);
    }

    /**
     * Returns the height of tree by
     * counting edges.
     * 
     * @return the height of the tree
     */
    public int getHeight() {
        if (root == null) {
            return -1;
        }
        return getHeight(root);
    }

    /**
     * Helper method for getHeight method
     * 
     * @param node the current
     *             node whose height to count
     * @return the height of the tree
     */
    private int getHeight(Node node) {
        if (node == null) {
            return -1;
        }

        int rhs = getHeight(node.right); // 0
        int lhs = getHeight(node.left); // 0

        if (rhs > lhs) {
            return rhs + 1;
        } else {
            return lhs + 1;
        }
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
        if (root == null) {
            throw new NoSuchElementException("findMin(): empty list...");
        }
        return findMin(root);
    }

    /**
     * Helper method to findMin method
     * 
     * @param node the current node to check
     *             if it is the smallest
     * @return the smallest value in the tree
     */
    private T findMin(Node node) {
        if (node.left == null) {
            return node.data;
        }
        return findMin(node.left);
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
        if (root == null) {
            throw new NoSuchElementException("findMax(): empty list...");
        }
        return findMax(root);
    }

    /**
     * Helper method to findMax method
     * 
     * @param node the current node to check
     *             if it is the largest
     * @return the largest value in the tree
     */
    private T findMax(Node node) {
        if (node.right == null) {
            return node.data;
        }
        return findMax(node.right);
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
        if (data.compareTo(node.data) < 0) {
            if (node.left == null) {
                node.left = new Node(data);
            } else {
                insert(data, node.left);
            }
        } else if (data.compareTo(node.data) > 0) {
            if (node.right == null) {
                node.right = new Node(data);
            } else {
                insert(data, node.right);
            }
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

        if (root.data.compareTo(data) == 0) {
            if (root.left == null && root.right == null) { // if no children
                root = null;
            }

            else if (root.right == null && root.left != null) { // left child
                root = root.left;
            } else if (root.left == null && root.right != null) { // right child
                root = root.right;
            } else { // two children
                Node temp = root.right;
                if (temp.left == null) {
                    root.right.left = root.left;
                    root = root.right;
                    System.out.print("1");
                } else {
                    while (temp.left.left != null) {
                        temp = temp.left;
                    }
                    root.data = temp.left.data;
                    System.out.print("2");
                    temp.left = temp.left.right;
                }
            }
        } else {
            remove(data, root);
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
        if (node == null) {
            // お茶 あれあれなに はいそうですね(what?)
            return node;
        }

        if (data.compareTo(node.data) < 0) {
            node.left = remove(data, node.left);
        } else if (data.compareTo(node.data) > 0) {
            node.right = remove(data, node.right);
        } else { // when data matches and we need to remove the node
            if (node.left == null && node.right == null) { // if no children
                node = null;
            }

            else if (node.right == null && node.left != null) { // left child
                node = node.left;

            } else if (node.left == null && node.right != null) { // right child
                node = node.right;
            } else { // two children
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

        if (data.compareTo(node.data) == 0) {
            return true;
        } else if (data.compareTo(node.data) < 0) {
            return search(data, node.left);
        } else {
            return search(data, node.right);
        }

    }

    /**
     * Returns a String containing the data
     * in depth order
     * 
     * @return a String of data in post order
     */
    public String depthOrderString() {
        StringBuilder depthOrder = new StringBuilder("");
        depthOrderString(root, depthOrder);
        return depthOrder.toString();
    }

    /**
     * Helper method to depthOrderString
     * Inserts the data in depth order into a String
     * 
     * @param node       the current Node
     * @param depthOrder a String containing the data
     */
    private void depthOrderString(Node node, StringBuilder depthOrder) {

    }

    /**
     * Returns a String containing the data
     * in post order
     * 
     * @return a String of data in post order
     */
    public String preOrderString() {
        StringBuilder preOrder = new StringBuilder("");
        preOrderString(root, preOrder);
        return preOrder.toString() + "\n";
    }

    /**
     * Helper method to preOrderString
     * Inserts the data in pre order into a String
     * 
     * @param node     the current Node
     * @param preOrder a String containing the data
     */
    private void preOrderString(Node node, StringBuilder preOrder) {
        if (node == null) {
            return;
        } else {
            preOrder.append(node.data + " ");
            preOrderString(node.left, preOrder);
            preOrderString(node.right, preOrder);
        }
    }

    /**
     * Returns a String containing the data
     * in order
     * 
     * @return a String of data in order
     */
    public String inOrderString() {
        StringBuilder inOrder = new StringBuilder("");
        inOrderString(root, inOrder);
        return inOrder.toString() + "\n";
    }

    /**
     * Helper method to inOrderString
     * Inserts the data in order into a String
     * 
     * @param node    the current Node
     * @param inOrder a String containing the data
     */
    private void inOrderString(Node node, StringBuilder inOrder) {
        if (node == null) {
            return;
        } else {
            inOrderString(node.left, inOrder);
            inOrder.append(node.data + " ");
            inOrderString(node.right, inOrder);
        }
    }

    /**
     * Returns a String containing the data
     * in post order
     * 
     * @return a String of data in post order
     */
    public String postOrderString() {
        StringBuilder postOrder = new StringBuilder("");
        postOrderString(root, postOrder);
        return postOrder.toString() + "\n";
    }

    /**
     * Helper method to postOrderString
     * Inserts the data in post order into a String
     * 
     * @param node      the current Node
     * @param postOrder a String containing the data
     */
    private void postOrderString(Node node, StringBuilder postOrder) {
        if (node == null) {
            return;
        } else {
            postOrderString(node.left, postOrder);
            postOrderString(node.right, postOrder);
            postOrder.append(node.data + " ");
        }
    }

    public static void main(String[] args) {
        BST<String> states = new BST<>();

        states.insert("HI");
        System.out.print(states.inOrderString());
        System.out.println(states.getSize());
        states.insert("MN");
        System.out.print(states.inOrderString());
        System.out.println(states.getSize());
        states.insert("CA");
        System.out.print(states.inOrderString());
        System.out.println(states.getSize());
        states.insert("IA");
        System.out.print(states.inOrderString());
        System.out.println(states.getSize());
        states.insert("MI");
        System.out.print(states.inOrderString());
        System.out.println(states.getSize());
        states.insert("AK");
        System.out.print(states.inOrderString());
        System.out.println(states.getSize());
        states.remove("HI");
        System.out.print(states.inOrderString());
        System.out.println(states.getSize());
    }

}
