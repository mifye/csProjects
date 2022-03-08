import java.util.Comparator;
import java.util.Iterator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 *
 * @author Micah Casey-Fusco
 *
 */
public class GlossarySearch {

    public static class Alpha implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return a.compareTo(b);
        }
    }

    /**
     *
     * @param s
     * @return set<string>
     */
    public static Set<String> alphabetize(Map<String, String> s) {

        Comparator<String> compare = new Alpha();

        Queue<String> a = new Queue1L<String>();
        for (Map.Pair<String, String> i : s) {
            a.enqueue(i.key());
        }
        a.sort(compare);

        //make into set for Junit
        Set<String> termSet = new Set1L<>();

        while (a.length() != 0) {
            termSet.add(a.dequeue());
        }

        return termSet;

    }

    /**
     *
     * @param output
     * @param map
     * @param titleTerm
     * @param definition
     */
    public static void printHTMLfile(SimpleWriter output,
            Map<String, String> map, String titleTerm, String definition,
            Set<Character> separatorSet) {

        //create term.html file using variables
        output.println("<!DOCTYPE html>");
        output.println("<html lang=\"en\">");
        output.println("<head>");
        output.println("<meta charset=\"UTF-8\" />");
        output.println(
                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />");
        output.println(
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
        output.println("<title>" + titleTerm + "</title>");
        output.println("<!-- css -->");
        output.println("<style>");
        output.println(".currentTerm {");
        output.println("font-weight: bold;");
        output.println("color: red;");
        output.println("font-style: italic;");
        output.println("display: block;");
        output.println("font-size: 1.5em;");
        output.println("margin-block-start: 0.83em;");
        output.println("margin-block-end: 0.83em;");
        output.println("margin-inline-start: 0px;");
        output.println("margin-inline-end: 0px;");
        output.println("}");
        output.println();
        output.println(".currentDefinition {");
        output.println("display: block;");
        output.println("margin-block-start: 0.83em;");
        output.println("margin-block-end: 0.83em;");
        output.println("margin-inline-start: 0px;");
        output.println("margin-inline-end: 0px;");
        output.println("}");
        output.println("</style>");
        output.println("</head>");
        output.println("<body>");
        output.println("<h2 class=\"currentTerm\">" + titleTerm + "</h2>");
        output.println("<blockquote class=\"currentDefinition\">");

        /**
         * currentIndex and currentWordLength were used to progress through the
         * definition string, but their values were parallel so we combined them
         */
        int indexAndLength = 0;

        while (indexAndLength < definition.length()) {

            String currentWord = nextWord(definition, indexAndLength,
                    separatorSet);
            int cwLength = currentWord.length();

            //find substring of separator or non-separator characters
            if (currentWord.charAt(0) != ' ' && currentWord.charAt(0) != ',') {
                if (map.hasKey(currentWord)) {
                    output.print("<a href=\"" + currentWord + ".html\">"
                            + currentWord + "</a>");
                } else {
                    output.print(currentWord);
                }
                output.print(" ");
            }

            indexAndLength = indexAndLength + cwLength;

        }

        output.println("</blockquote>");
        output.println("<hr />");
        output.println("<p>Return to <a href=\"index.html\">index</a>.</p>");
        output.println("</body>");
        output.println("</html>");
    }

    /**
     *
     * @param definition
     * @param position
     * @param separators
     * @return string
     */
    public static String nextWord(String definition, int position,
            Set<Character> separators) {

        String result = "";
        int i = position;

        //compare char at position with separator set
        if (!separators.contains(definition.charAt(position))) {
            while (i < definition.length()
                    && !separators.contains(definition.charAt(i))) {
                i++;
            }
            result = definition.substring(position, i);
        } else {
            while (i < definition.length()
                    && separators.contains(definition.charAt(i))) {
                i++;
            }
            result = definition.substring(position, i);
        }
        return result;

    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    public static void generateElements(String str, Set<Character> charSet) {

        Set<Character> temp = new Set1L<>();

        //check for empty case
        if (str.equals("")) {
            charSet.transferFrom(temp);
        } else {
            char[] strChars = str.toCharArray();
            for (int i = 0; i < str.length(); i++) {
                if (!temp.contains(strChars[i])) {
                    temp.add(strChars[i]);
                }

            }
        }

        charSet.transferFrom(temp);
    }

    /**
     *
     * @param output
     * @param title
     * @param map
     */
    public static void printIndexfile(SimpleWriter output, String title,
            Map<String, String> map) {
        Set<String> sortedTerms = alphabetize(map);
        Queue<String> termsQ = new Queue1L<>();

        for (String term : sortedTerms) {
            termsQ.enqueue(term);
        }

        //create index.html text using variables
        output.println("<html>");
        output.println("<head>");
        output.println("<title>" + title + "</title>");
        output.println("</head>");
        output.println("<body>");
        output.println("<h2>" + title + "</h2>");
        output.println("<hr />");
        output.println("<h3>Index</h3>");
        output.println("<ul>");
        while (termsQ.length() > 0) {
            String s = termsQ.dequeue();
            output.println("<li><a href=" + s + ".html>" + s + "</a></li>");
        }
        output.println("</ul>");
        output.println("</body>");
        output.println("</html>");
    }

    /**
     *
     * @param file
     * @return map<String, String>
     */
    public static Map<String, String> getTerm(SimpleReader file) {

        //initialize variables
        String term = "";
        String definition = "";
        Map<String, String> glossary = new Map1L<>();

        //loop through file and find terms + their definitions
        while (!file.atEOS()) {
            term = file.nextLine();
            definition = file.nextLine();

            String holder = file.nextLine();
            while (holder.length() != 0) {
                definition = definition.concat(holder);
                holder = file.nextLine();
            }
            glossary.add(term, definition);

        }

        return glossary;

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //input file name and output folder
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        //ask user for file and folder location
        out.print("Enter input filename: ");
        String inputFileName = in.nextLine();
        out.print("Enter output folder name: ");
        String outputFolderName = in.nextLine();

        //read glossary data from file
        SimpleReader inputData = new SimpleReader1L(inputFileName);
        Map<String, String> map = getTerm(inputData);

        //create output simplewriter for the index file, then run index method
        SimpleWriter output = new SimpleWriter1L(
                outputFolderName + "/index.html");
        printIndexfile(output, "Glossary", map);
        output.close();

        //create separator set for later use
        final String separatorStr = " \t, ";
        Set<Character> separatorSet = new Set1L<Character>();
        generateElements(separatorStr, separatorSet);

        //write html output files
        Iterator<Map.Pair<String, String>> iter = map.iterator();
        while (iter.hasNext()) {
            Pair<String, String> p = iter.next();
            SimpleWriter outputHTML = new SimpleWriter1L(
                    outputFolderName + "/" + p.key() + ".html");
            printHTMLfile(outputHTML, map, p.key(), p.value(), separatorSet);
            outputHTML.close();
        }

        out.close();
        in.close();
        inputData.close();
    }
}
