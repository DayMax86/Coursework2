
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
        //Turn the content string into a string array
        String[] splitContent = content.split("");
        String tempString = "";
        StringBuilder output = new StringBuilder();
        //Iterate over the char list checking for valid characters
        for (String s : splitContent) {
            if (String.valueOf(s).matches("^[a-zA-Z0-9]+$")) {
                //The character is alphanumeric
                tempString += s;
            } else { //Must be non-alphanumeric
                for (String word : redactWords) {
                    if (tempString.equalsIgnoreCase(word)) {
                        //This set of characters appears in the redact words list
                        output.append("*".repeat(tempString.length()));
                        tempString = "";
                        break;
                    }
                }
                output.append(tempString).append(s);
                tempString = "";
            }
        }
        //Check the last value of tempString
        for (String word : redactWords) {
            if (tempString.equalsIgnoreCase(word)) {
                //This set of characters appears in the redact words list
                output.append("*".repeat(tempString.length()));
                tempString = "";
            }
        }
        output.append(tempString);
        return output.toString();
    }


}
