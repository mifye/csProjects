import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class WordCounter {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private WordCounter() {
    }

    /**
     * @param wordList
     * @param wordCount
     * @param title
     */
    private static void toHtml(ArrayList<String> wordList,
            ArrayList<Integer> wordCount, String title) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L("data/" + title);

        out.println("<html>");
        out.println("<head>");
        out.println("<title> Words counted in data/" + title + ".txt</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2> Words counted in data/" + title + "</h2>");
        out.println("<hr>");
        out.println("<table border=\"1\">");
        out.println("<tbody>");
        out.println("<tr>");
        out.println("<th>Words</th>");
        out.println("<th>Counts</th>");
        out.println("</tr>");

        for (int i = 0; i < wordList.size(); i++) {
            out.println("<tr>");
            out.println("<th>" + wordList.get(i) + "</th>");
            out.println("<th>" + wordCount.get(i) + "</th>");
            out.println("</tr>");
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");

        in.close();
        out.close();
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        boolean askUser = true;
        boolean askForOutput = true;

        while (askUser) {

            //ask for txt file
            out.println("Please enter a text file: ");
            String s = "data/" + in.nextLine();
            String txt = ".txt";

            //check for good format
            if (!s.endsWith(txt)) {
                out.println("You must use good format (include the .txt)! ");
            } else if (s.isEmpty()) {
                out.println("File name cannot be empty.");
            } else {

                //acceptable format used, no longer need to ask
                askUser = false;

                //Create list of words and corresponding count
                ArrayList<String> wordList = new ArrayList<String>();
                ArrayList<Integer> wordCount = new ArrayList<Integer>();

                //get file input
                try {

                    File file = new File(s);

                    Scanner input = new Scanner(file);

                    //scan file for words and repeats
                    while (input.hasNext()) {
                        String currentWord = input.next();

                        if (wordList.contains(currentWord)) {
                            //getting index of same word and increasing count by 1
                            int index = wordList.indexOf(currentWord);
                            wordCount.set(index, wordCount.get(index) + 1);
                        } else {
                            //add new word and its corresponding count (1)
                            wordList.add(currentWord);
                            wordCount.add(1);
                        }

                    }
                    input.close();

                    out.println(wordList);
                    out.println(wordCount);

                    //txt file has been analyzed, now need to create output file
                    while (askForOutput) {

                        //ask for txt file
                        out.println("Enter the output (html) file: ");
                        String outFileName = in.nextLine();
                        String html = ".html";

                        //check for good format
                        if (!outFileName.endsWith(html)) {
                            out.println(
                                    "You must use good format (include the .html)! ");
                        } else if (outFileName.isEmpty()) {
                            out.println("File name cannot be empty.");
                        } else {
                            askForOutput = false;
                            toHtml(wordList, wordCount, outFileName);
                        }

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        }

    }

}
