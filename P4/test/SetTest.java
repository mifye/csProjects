import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size
    // CONSTRUCTOR -------------------------------------------------------------
    /**
     * Testing no argument constructor.
     */
    @Test
    public final void testNoArugementConstructor() {
        // Setup
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        // Call
        // Eval
        assertEquals(sExpected, s);
    }

    /**
     * Testing two argument constructor.
     */
    @Test
    public final void testTwoArugementConstructor() {
        // Setup
        Set<String> s = this.createFromArgsTest("a", "b");
        Set<String> sExpected = this.createFromArgsRef("a", "b");
        // Call
        // Eval
        assertEquals(sExpected, s);
    }

    // ADD ---------------------------------------------------------------------
    /**
     * Testing add(); to empty.
     */
    @Test
    public final void testAddToEmpty() {
        // Setup
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("a");
        // Call
        s.add("a");
        // Eval
        assertEquals(sExpected, s);
    }

    /**
     * Testing add(); to non-empty.
     */
    @Test
    public final void testAddToNonEmpty() {
        // Setup
        Set<String> s = this.createFromArgsTest("a", "b", "c");
        Set<String> sExpected = this.createFromArgsRef("a", "b", "c", "e");
        // Call
        s.add("e");
        // Eval
        assertEquals(sExpected, s);
    }

    // REMOVE ------------------------------------------------------------------
    /**
     * Testing remove(); to empty.
     */
    @Test
    public final void testRemoveToEmpty() {
        // Setup
        Set<String> s = this.createFromArgsTest("a");
        Set<String> sExpected = this.createFromArgsRef();
        // Call
        s.remove("a");
        // Eval
        assertEquals(sExpected, s);
    }

    /**
     * Testing remove(); to non-empty.
     */
    @Test
    public final void testRemoveToNonEmpty() {
        // Setup
        Set<String> s = this.createFromArgsTest("a", "b", "c", "e");
        Set<String> sExpected = this.createFromArgsRef("a", "b", "c");
        // Call
        s.remove("e");
        // Eval
        assertEquals(sExpected, s);
    }

    // REMOVEANY ---------------------------------------------------------------
    /**
     * Testing removeAny() with set.size() == 1.
     */
    @Test
    public final void testRemoveAnySizeOne() {
        // Setup
        Set<String> s = this.createFromArgsTest("a");
        Set<String> sExpected = this.createFromArgsRef("a");
        String removedEntry;
        // Call
        removedEntry = s.removeAny();
        // Eval
        assertEquals(sExpected.contains(removedEntry), true);
        sExpected.remove(removedEntry);
        assertEquals(sExpected, s);
    }

    /**
     * Testing removeAny() with set.size() == 3.
     */
    @Test
    public final void testRemoveAnySizeThree() {
        // Setup
        Set<String> s = this.createFromArgsTest("a", "b", "c");
        Set<String> sExpected = this.createFromArgsRef("a", "b", "c");
        String removedEntry;
        // Call
        removedEntry = s.removeAny();
        // Eval
        assertEquals(sExpected.contains(removedEntry), true);
        sExpected.remove(removedEntry);
        assertEquals(sExpected, s);
    }
    

    // CONTAINS ----------------------------------------------------------------
    /**
     * Testing contains(); return true.
     */
    @Test
    public final void testContainsTrue() {
        // Setup
        Set<String> s = this.createFromArgsTest("a", "b", "c", "e");
        // Call
        // Eval
        assertEquals(true, s.contains("a"));
    }

    /**
     * Testing contains(); return false.
     */
    @Test
    public final void testContainsFalse() {
        // Setup
        Set<String> s = this.createFromArgsTest("a", "b", "c", "e");
        // Call
        // Eval
        assertEquals(false, s.contains("d"));
    }
    // SIZE --------------------------------------------------------------------

    /**
     * Testing size(); set = {}.
     */
    @Test
    public final void testSize0() {
        // Setup
        Set<String> s = this.createFromArgsTest();
        // Call
        // Eval
        assertEquals(0, s.size());
    }

    /**
     * Testing size(); non empty set.
     */
    @Test
    public final void testSizeNonEmpty() {
        // Setup
        Set<String> s = this.createFromArgsTest("a", "b", "c", "e");
        // Call
        // Eval
        assertEquals(4, s.size());
    }

}
