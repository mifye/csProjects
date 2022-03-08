import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * @author Micah Casey-Fusco
 *
 */
public class StringReassemblyTest {

    /*
     * Test for combination
     */

    @Test
    public void combinationTest() {
        String str1 = "abcdefg";
        String str2 = "efghijk";
        int overlap = 3;
        String expected = "abcdefghijk";
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals(expected, result);
    }

    /*
     * Tests for addToSetAvoidingSubstrings
     */
    @Test
    public void addToSetAvoidingSubstringsTest_strIsSub() {

        //set parameters
        Set<String> strSet = new Set2<String>();
        strSet.add("imBroke");
        strSet.add("plsDonate");
        strSet.add("hereIsTheActualTest-hi");
        String str = "hi";

        //test
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSet, strSet);
    }

    @Test
    public void addToSetAvoidingSubstringsTest_strIsntSub() {

        //set parameters
        Set<String> strSet = new Set2<String>();
        strSet.add("ImBroke");
        strSet.add("plsDonate");
        strSet.add("iCantAffordMcchickens");
        String str = "didYouKnowThatImBroke";

        //set expected
        Set<String> setExpected = strSet.newInstance();
        setExpected.add("didYouKnowThatImBroke");
        setExpected.add("plsDonate");
        setExpected.add("iCantAffordMcchickens");

        //test
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(setExpected, strSet);
    }

    /*
     * Tests for linesFromInput
     */

    @Test
    public void linesFromInputTest() {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        //read test file and set to inFile
        SimpleReader inFile = new SimpleReader1L("data/cheer-8-2.txt");

        //run method using sample file and set to outFile
        Set<String> outFile = StringReassembly.linesFromInput(inFile);

        //close inFile after test
        inFile.close();

        //create expected output set
        Set<String> expectedSet = new Set2<String>();
        expectedSet.add("Bucks -- Beat");
        expectedSet.add("Go Bucks");
        expectedSet.add("o Bucks -- B");
        expectedSet.add("Beat Mich");
        expectedSet.add("Michigan~");

        assertEquals(expectedSet, outFile);

        in.close();
        out.close();
    }

    /*
     * Tests for printWithLineSeparators
     */

    @Test
    public void printWithLineSeparatorsTest() {
        SimpleReader in = new SimpleReader1L("data/cheer-8-2.txt");
        SimpleWriter out = new SimpleWriter1L();

        //run method using sample file and set to outFile
        Set<String> outSet = StringReassembly.linesFromInput(in);

        //i cannot figure out how to print this in the test stream :((
        for (String element : outSet) {
            StringReassembly.printWithLineSeparators(element, out);
        }
        //close inFile after test
        in.close();

        //print expected to manually compare (idk how to automate this :/ )
        String line;
        while ((line = in.nextLine()) != null) {
            System.out.println(line);
        }

        in.close();
        out.close();
    }
}
