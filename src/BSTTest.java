
/**
 * JUnit Test File for BST.java
 * @author Jennifer Parrish
 * CIS 22C, Lab 4
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class BSTTest {

    @Test
    void testBST() {
        BST<String> bst = new BST<>();
        assertTrue(bst.isEmpty());
        assertEquals(0, bst.getSize());
        assertThrows(NoSuchElementException.class, () -> {
            bst.getRoot();
        });
    }

    @Test
    void testBSTBSTOfT() {
        BST<String> nullBst = null;
        BST<String> b = new BST<>(nullBst);
        assertTrue(b.isEmpty());

        BST<String> bst4 = new BST<>();
        bst4.insert("5");
        bst4.insert("1");
        bst4.insert("2");
        bst4.insert("4");
        bst4.insert("6");
        bst4.insert("8");
        BST<String> bst = new BST<>(bst4);
        assertEquals("5", bst.getRoot());
        assertEquals(3, bst.getHeight());
        bst.insert("3");
        assertEquals(4, bst.getHeight());
        assertEquals(3, bst4.getHeight());

    }

    @Test
    void testBSTArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            BST<String> bst1 = new BST<>(new String[] { "C", "B", "A" });
        });
        String[] array = null;
        BST<String> bst2 = new BST<>(array);
        assertTrue(bst2.isEmpty());
        BST<String> bst3 = new BST<>(new String[] { "A", "B", "C", "D", "E" });
        assertEquals("C", bst3.getRoot());
        assertEquals(2, bst3.getHeight());
        BST<String> bst4 = new BST<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" });
        assertEquals("5", bst4.getRoot());
        assertEquals(3, bst4.getHeight());

    }

    @Test
    void testGetRoot() {
        BST<String> bst = new BST<>();
        assertThrows(NoSuchElementException.class, () -> {
            bst.getRoot();
        });
        bst.insert("F");
        bst.insert("A");
        bst.insert("C");
        bst.insert("P");
        bst.insert("Q");
        bst.insert("Z");
        bst.insert("L");
        assertEquals("F", bst.getRoot());
        bst.remove("F");
        assertEquals("L", bst.getRoot());
    }

    @Test
    void testIsEmpty() {
        BST<String> bst = new BST<>();
        assertTrue(bst.isEmpty());
        bst.insert("C");
        assertFalse(bst.isEmpty());
        bst.insert("A");
        bst.insert("B");
        bst.insert("D");
        assertFalse(bst.isEmpty());
        bst.remove("C");
        bst.remove("D");
        bst.remove("A");
        bst.remove("B");
        assertTrue(bst.isEmpty());
    }

    @Test
    void testGetSize() {
        BST<String> bst = new BST<>();
        assertEquals(0, bst.getSize());
        bst.insert("C");
        assertEquals(1, bst.getSize());
        bst.insert("A");
        bst.insert("B");
        bst.insert("D");
        assertEquals(4, bst.getSize());
        bst.remove("C");
        bst.remove("D");
        bst.remove("A");
        bst.remove("B");
        assertEquals(0, bst.getSize());
    }

    @Test
    void testGetHeight() {
        BST<String> bst = new BST<>();
        assertEquals(-1, bst.getHeight());
        bst.insert("C");
        assertEquals(0, bst.getHeight());
        bst.insert("A");
        bst.insert("D");
        assertEquals(1, bst.getHeight());
        bst.insert("B");
        assertEquals(2, bst.getHeight());
    }

    @Test
    void testFindMin() {
        BST<String> bst = new BST<>();
        assertThrows(NoSuchElementException.class, () -> {
            bst.findMin();
        });
        BST<String> bst4 = new BST<>(new String[] { "10", "2", "3", "4", "5", "6", "7", "8", "9" });
        assertEquals("10", bst4.findMin());
    }

    @Test
    void testFindMax() {
        BST<String> bst = new BST<>();
        assertThrows(NoSuchElementException.class, () -> {
            bst.findMax();
        });
        BST<String> bst4 = new BST<>(new String[] { "10", "2", "3", "4", "5", "6", "7", "8", "9" });
        assertEquals("9", bst4.findMax());
    }

    @Test
    void testSearch() {
        BST<String> bst1 = new BST<>();
        assertFalse(bst1.search("A"));
        BST<String> bst4 = new BST<>(new String[] { "10", "2", "3", "4", "5", "6", "7", "8", "9" });
        assertTrue(bst4.search("10"));
        assertFalse(bst4.search("1"));
    }

    @Test
    void testInsert() {
        BST<String> bst = new BST<>();
        bst.insert("ME");
        bst.insert("MAY");
        bst.insert("MAH");
        bst.insert("MO");
        bst.insert("MOO");
        assertEquals("ME", bst.getRoot());
        assertEquals(5, bst.getSize());
        assertEquals(2, bst.getHeight());

    }

    @Test
    void testRemove() {
        BST<String> bst = new BST<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" });
        bst.remove("5");
        assertEquals("6", bst.getRoot());
        BST<String> states = new BST<>();
        states.insert("HI");
        states.insert("MN");
        states.insert("CA");
        states.insert("IA");
        states.insert("MI");
        states.insert("AK");
        states.remove("HI");
        assertEquals("IA", states.getRoot());
        states.remove("MN");
        assertEquals("IA", states.getRoot());
        assertEquals(4, states.getSize());

        states.remove("CT");
        assertEquals(4, states.getSize());
        states.remove("IA");
        assertEquals("MI", states.getRoot());
        states.remove("AK");
        assertEquals(2, states.getSize());
        states.remove("CA");
        assertFalse(states.isEmpty());
        states.remove("MI");
        assertTrue(states.isEmpty());
        assertThrows(IllegalStateException.class, () -> {
            states.remove("MI");
        });
    }

    @Test
    void testIsBalanced() {
        BST<String> a = new BST<>();
        assertTrue(a.isBalanced());
        a = new BST<>(new String[] { "A", "B", "C", "D", "E", "F", "G" });
        assertTrue(a.isBalanced());
        a = new BST<>();
        a.insert("C");
        a.insert("B");
        a.insert("A");
        assertFalse(a.isBalanced());
        a.insert("D");
        a.insert("E");
        assertTrue(a.isBalanced());
        a.insert("F");
        assertFalse(a.isBalanced());
    }

    @Test
    void testPreOrderString() {
        BST<String> empty = new BST<>();
        assertEquals("\n", empty.preOrderString());

        BST<String> states = new BST<>();
        states.insert("HI");
        states.insert("MN");
        states.insert("CA");
        states.insert("IA");
        states.insert("MI");
        states.insert("AK");

        assertEquals("HI CA AK MN IA MI \n", states.preOrderString());
    }

    @Test
    void testInOrderString() {
        BST<String> empty = new BST<>();
        assertEquals("\n", empty.inOrderString());

        BST<String> states = new BST<>();
        states.insert("HI");
        states.insert("MN");
        states.insert("CA");
        states.insert("IA");
        states.insert("MI");
        states.insert("AK");

        assertEquals("AK CA HI IA MI MN \n", states.inOrderString());
    }

    @Test
    void testPostOrderString() {
        BST<String> empty = new BST<>();
        assertEquals("\n", empty.postOrderString());

        BST<String> states = new BST<>();
        states.insert("HI");
        states.insert("MN");
        states.insert("CA");
        states.insert("IA");
        states.insert("MI");
        states.insert("AK");

        assertEquals("AK CA MI IA MN HI \n", states.postOrderString());
    }

}