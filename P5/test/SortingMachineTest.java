import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    /*
     * Add test cases
     */
    
    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddNonempty() {
        //setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true, "green", "blue");
        //call
        m.add("blue");
        //eval
        assertEquals(mExpected, m);
    }
    
    /*
     * Extraction mode test cases
     */
    
    @Test
    public final void testExtractionMode() {
        //setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        //call
        m.changeToExtractionMode();
        //eval
        assertEquals(mExpected, m);
    }
    
    /*
     * Remove first test cases
     */
    
    @Test
    public final void testRemoveFirstEmpty() {
        //setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        //call
        m.removeFirst();
        //eval
        assertEquals(mExpected, m);
    }
    
    @Test
    public final void testRemoveFirst() {
        //setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "green",
                "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false, 
                "blue");
        //call
        m.removeFirst();
        //eval
        assertEquals(mExpected, m);
    }
    
    /*
     * Insertion mode test cases
     */
    
    @Test
    public final void testIsInsertTrue() {
        //setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        boolean mExpected = true;
        //call
        boolean mActual = m.isInInsertionMode();
        //eval
        assertEquals(mExpected, mActual);
    }
    
    @Test
    public final void testIsInsertFalse() {
        //setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        boolean mExpected = false;
        //call
        boolean mActual = m.isInInsertionMode();
        //eval
        assertEquals(mExpected, mActual);
    }
    
    /*
     * Order test cases
     */
    
    @Test
    public final void testOrder() {
        //setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        Comparator<String> mExpected = ORDER;
        //call
        Comparator<String> mActual = m.order();
        //eval
        assertEquals(mExpected, mActual);
    }
    
    /*
     * Size test cases
     */
    
    @Test
    public final void testSizeInsertionMode() {
        //setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green");
        int mExpected = 1;
        //call
        int mActual = m.size();
        //eval
        assertEquals(mExpected, mActual);
    }
    
    @Test
    public final void testSizeExtractionMode() {
        //setup
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "green");
        int mExpected = 1;
        //call
        int mActual = m.size();
        //eval
        assertEquals(mExpected, mActual);
    }
    

    // TODO - add test cases for, order, and size
    


}
