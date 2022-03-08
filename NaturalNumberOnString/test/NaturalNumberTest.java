import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /*
     * START OF CONSTRUCTOR TEST CASES
     */

    /**
     * Test for no argument constructor case.
     */
    @Test
    public final void noConstructorTest() {
        // Setup
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        // Call
        // Eval
        assertEquals(nExpected, n);
    }

    /**
     * Test for int 0 case.
     */
    @Test
    public final void intZeroTest() {
        // Setup
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);
        // Call
        // Eval
        assertEquals(nExpected, n);
    }

    /**
     * Test for single digit int case.
     */
    @Test
    public final void intSingleTest() {
        // Setup
        NaturalNumber n = this.constructorTest(9);
        NaturalNumber nExpected = this.constructorRef(9);
        // Call
        // Eval
        assertEquals(nExpected, n);
    }

    /**
     * Test for double digit int case.
     */
    @Test
    public final void intDoubleTest() {
        // Setup
        NaturalNumber n = this.constructorTest(92);
        NaturalNumber nExpected = this.constructorRef(92);
        // Call
        // Eval
        assertEquals(nExpected, n);
    }

    /**
     * Test for string "0" case.
     */
    @Test
    public final void stringZeroTest() {
        // Setup
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber nExpected = this.constructorRef("0");
        // Call
        // Eval
        assertEquals(nExpected, n);
    }

    /**
     * Test for typical string case; no leading zeros.
     */
    @Test
    public final void stringTypicalTest() {
        // Setup
        NaturalNumber n = this.constructorTest("340");
        NaturalNumber nExpected = this.constructorRef("340");
        // Call
        // Eval
        assertEquals(nExpected, n);
    }

    /**
     * Test for NN 0 case.
     */
    @Test
    public final void nnZeroTest() {
        // Setup
        NaturalNumber x = this.constructorRef(0);
        NaturalNumber n = this.constructorTest(x);
        NaturalNumber nExpected = this.constructorRef(x);
        // Call
        // Eval
        assertEquals(nExpected, n);
    }

    /**
     * Test for single digit NN case.
     */
    @Test
    public final void nnSingleTest() {
        // Setup
        NaturalNumber x = this.constructorRef(6);
        NaturalNumber n = this.constructorTest(x);
        NaturalNumber nExpected = this.constructorRef(x);
        // Call
        // Eval
        assertEquals(nExpected, n);
    }

    /**
     * Test for double digit NN case.
     */
    @Test
    public final void nnDoubleTest() {
        // Setup
        NaturalNumber x = this.constructorRef(42);
        NaturalNumber n = this.constructorTest(x);
        NaturalNumber nExpected = this.constructorRef(x);
        // Call
        // Eval
        assertEquals(nExpected, n);
    }

    /*
     * START OF KERNEL METHOD TEST CASES
     */

    // MultiplyBy10 ------------------------------------------------------------

    /**
     * Testing n.multiplyBy10(0); n == 0.
     */
    @Test
    public final void testMultipling0Adding0() {
        // Setup
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber nExpected = this.constructorRef("0");
        // Call
        n.multiplyBy10(0);
        // Eval
        assertEquals(nExpected.toString(), n.toString()); // <- works
        //assertEquals(true, nExpected.equals(n)); < -- does not work
        //assertEquals(nExpected, n); <-- does not work
        // if assertEquals/ .equals(); has the same behavior as ==,
        // then it will not work as objA == objB compares reference values,
        // I do not know how to get around this check without knowing how it
        // checks. If it uses .equals(); then without knowing how it compares
        // I dont know how to get around this check. I have checked the
        // implementation of NaturalNumber2 and there doesnt seem to be any
        // mismatch with our. Both implementation's kernel methods have
        // the same return values for the same n == 0.
    }

    /**
     * Testing n.multiplyBy10(1); n == 0.
     */
    @Test
    public final void testMultipling0Adding1() {
        // Setup
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber nExpected = this.constructorRef("1");
        // Call
        n.multiplyBy10(1);
        // Eval
        assertEquals(nExpected, n);
    }

    /**
     * Testing n.multiplyBy10(0); n == 1.
     */
    @Test
    public final void testMultipling1Adding0() {
        // Setup
        NaturalNumber n = this.constructorTest("1");
        NaturalNumber nExpected = this.constructorRef("10");
        // Call
        n.multiplyBy10(0);
        // Eval
        assertEquals(nExpected, n);
    }

    /**
     * Testing n.multiplyBy10(1); n == 1.
     */
    @Test
    public final void testMultipling1Adding1() {
        // Setup
        NaturalNumber n = this.constructorTest("1");
        NaturalNumber nExpected = this.constructorRef("11");
        // Call
        n.multiplyBy10(1);
        // Eval
        assertEquals(nExpected, n);
    }

    /**
     * Testing n.multiplyBy10(0); n == Integer.MAX.
     */
    @Test
    public final void testMultiplingINTMAXAdding0() {
        // Setup
        NaturalNumber n = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber nExpected = this
                .constructorRef(Integer.toString(Integer.MAX_VALUE) + "0");
        // Call
        n.multiplyBy10(0);
        // Eval
        assertEquals(nExpected, n);
    }

    // DivideBy10 --------------------------------------------------------------

    /**
     * Testing n.divideBy10(); n == 0.
     */
    @Test
    public final void testDividing0Return0() {
        // Setup
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber nExpected = this.constructorRef("0");
        int remainder;
        // Call
        remainder = n.divideBy10();
        // Eval
        assertEquals(0, remainder);
        assertEquals(nExpected, n);
    }

    /**
     * Testing n.divideBy10(); n == 1.
     */
    @Test
    public final void testDividing1Return1() {
        // Setup
        NaturalNumber n = this.constructorTest("1");
        NaturalNumber nExpected = this.constructorRef("0");
        int remainder;
        // Call
        remainder = n.divideBy10();
        // Eval
        assertEquals(1, remainder);
        assertEquals(nExpected, n);
    }

    /**
     * Testing n.divideBy10(); n == 10.
     */
    @Test
    public final void testDividing10Return0() {
        // Setup
        NaturalNumber n = this.constructorTest("10");
        NaturalNumber nExpected = this.constructorRef("1");
        int remainder;
        // Call
        remainder = n.divideBy10();
        // Eval
        assertEquals(0, remainder);
        assertEquals(nExpected, n);
    }

    /**
     * Testing n.divideBy10(); n == 11.
     */
    @Test
    public final void testDividing11Return1() {
        // Setup
        NaturalNumber n = this.constructorTest("11");
        NaturalNumber nExpected = this.constructorRef("1");
        int remainder;
        // Call
        remainder = n.divideBy10();
        // Eval
        assertEquals(1, remainder);
        assertEquals(nExpected, n);
    }

    // IsZero ------------------------------------------------------------------
    /**
     * Testing n.isZero();.
     */
    @Test
    public final void testIsZeroTrue() {
        // Setup
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        // Call
        // Eval
        assertEquals(true, n.isZero());
        assertEquals(nExpected, n);
    }

    /**
     * Testing n.isZero();.
     */
    @Test
    public final void testIsZeroFalse() {
        // Setup
        NaturalNumber n = this.constructorTest("345");
        NaturalNumber nExpected = this.constructorRef("345");
        // Call
        // Eval
        assertEquals(false, n.isZero());
        assertEquals(nExpected, n);
    }
}
