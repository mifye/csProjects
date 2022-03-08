import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

public class GlossarySearchTest {

    /*
     * Test for complete (correct) list of terms + definition
     */

    @Test
    public void getTerms_termsTxt() {

        //create expected map
        Map<String, String> expectedMap = new Map1L<>();
        expectedMap.add("meaning",
                "something that one wishes to convey, especially by language");
        expectedMap.add("term", "a word whose definition is in a glossary");
        expectedMap.add("word",
                "a string of characters in a language, which has at least one character");
        expectedMap.add("definition",
                "a sequence of words that gives meaning to a term");
        expectedMap.add("glossary",
                "a list of difficult or specialized terms, with their definitions usually near the end of a book");
        expectedMap.add("language",
                "a set of strings of characters, each of which has meaning");
        expectedMap.add("book", "a printed or written literary work");

        //get actual map
        SimpleReader testFile = new SimpleReader1L("terms.txt");
        Map<String, String> actualMap = GlossarySearch.getTerm(testFile);

        //compare the two and return whether they are equal or not
        boolean areEqual = compareMaps(expectedMap, actualMap);

        //run assert to check if true
        assertEquals(true, areEqual);

        //close streams
        testFile.close();
    }

    //function for checking if maps are ==
    public static boolean compareMaps(Map<String, String> expected,
            Map<String, String> actual) {

        boolean isEqual = true;

        if (expected.size() == actual.size()) {

            Iterator<Map.Pair<String, String>> iter = actual.iterator();
            while (iter.hasNext()) {
                Pair<String, String> p = iter.next();

                if (!expected.hasKey(p.key())) {
                    isEqual = false;
                }
            }

        } else {
            isEqual = false;
        }

        return isEqual;
    }

    /*
     * Test for alphabetize
     */

    @Test
    public void alphabetize_termsTxt() {

        Set<String> expected = new Set1L<>();
        expected.add("book");
        expected.add("definition");
        expected.add("glossary");
        expected.add("language");
        expected.add("meaning");
        expected.add("term");
        expected.add("word");

        Map<String, String> expectedMap = new Map1L<>();
        expectedMap.add("meaning",
                "something that one wishes to convey, especially by language");
        expectedMap.add("term", "a word whose definition is in a glossary");
        expectedMap.add("word",
                "a string of characters in a language, which has at least one character");
        expectedMap.add("definition",
                "a sequence of words that gives meaning to a term");
        expectedMap.add("glossary",
                "a list of difficult or specialized terms, with their definitions usually near the end of a book");
        expectedMap.add("language",
                "a set of strings of characters, each of which has meaning");
        expectedMap.add("book", "a printed or written literary work");

        Set<String> actual = GlossarySearch.alphabetize(expectedMap);

        assertEquals(expected, actual);
    }

    /*
     * Test for correct separator set
     */

    @Test
    public void generateElements_termsTxt() {

        Set<Character> expected = new Set1L<>();
        expected.add(' ');
        expected.add(',');
        expected.add('\t');

        Set<Character> actual = new Set1L<>();
        String test = " \t, ";

        GlossarySearch.generateElements(test, actual);

        assertEquals(expected, actual);
    }

    /*
     * Test for nextWord functionality
     */

    @Test
    public void nextWord1() {

        String test = "This is a test";
        int position = 0;
        Set<Character> sep = new Set1L<>();
        sep.add(' ');
        sep.add(',');
        sep.add('\t');

        String actual = GlossarySearch.nextWord(test, position, sep);
        String expected = "This";

        assertEquals(expected, actual);
    }

    @Test
    public void nextWord2() {

        String test = "This is a test";
        int position = 13;
        Set<Character> sep = new Set1L<>();
        sep.add(' ');
        sep.add(',');
        sep.add('\t');

        String actual = GlossarySearch.nextWord(test, position, sep);
        String expected = "t";

        assertEquals(expected, actual);

    }

}
