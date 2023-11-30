import java.util.Arrays;

public class Redactor {
    /*
    *
    Implement an algorithm in Java which given a String as input, redacts all words from a given set of “redactable”
    words (an array of Strings), and returns the result as a String. For example, given the String:

    The quick brown fox jumps over the lazy dog!

and the redactable set of words:

    Fox, jumps, dog

the output String should be:

    The quick brown *** ***** over the lazy ***!

Rules:

Your implementation must use the public static String redact(...) method signature
The number of stars in the redacted text is the same as the number of letters in the word that has been redacted
Capitalization of redacted words is ignored (i.e., "the" in the redacted words list would redact "The", "THE" etc.)
Only whole words that match one of the redacted words should be redacted, e.g., given the redacted word "pass",
 the word "password" would not be redacted.
Hyphenated words and words with apostrophes (e.g. Chris' or Chris's) will not be tested.
Capitalization of unredacted words in the input text should be maintained in the output text.
    * */

    public static String redact(String content, String[] redactWords) {
        //Do a check for - and ' in the redactWords array
        System.out.println("content: " + content);
        System.out.println("redactWords: " + Arrays.toString(redactWords));
        if (!content.matches("[-']*")) {
            //String redactedString = content;
            System.out.println("content contains no - or '");
            for (String word : redactWords) {
                //Use regex and String.matches() method
                if (content.matches(("(?i)^" + word + "$|(?i) *" + word + " *"))) {
                    return "true";
                } else {
                    System.out.println("No match from regex");
                }
            }
        }
        return "false";
    }

}
