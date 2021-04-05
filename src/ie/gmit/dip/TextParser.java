package ie.gmit.dip;

/**
 * Implementation of the parser class. It is responsible for processing text from user input.
 * Inherits from Parser and implements IPrintable. Class is not thread safe.
 *
 * @author Vladimir Novac
 * @version 1.0
 * @see Parser
 * @see IPrintable
 * @since 1.8
 */
public class TextParser extends Parser implements IPrintable {

    /**
     * Initialises the class and assigns the thesaurus file.
     *
     * @param textLoader the text loader object from which the map is used.
     */
    TextParser(TextLoader textLoader) {
        super(textLoader);
    }

    /**
     * Provides order of operations for the text parser.
     *
     * @param inputText text input from the user in the form of a String.
     */
    @Override
    public void print(String inputText) {
        String[] originalText = loadInputText(inputText);
        String[] processedText = processText(originalText);
        printText(originalText, processedText);
    }

    /**
     * Takes the input text line and splits it into a String array.
     *
     * @param inputText text input from the user in the form of a String.
     * @return a String array with the user text line split into words.
     */
    @Override
    protected String[] loadInputText(String inputText) {
        return inputText.split(" ");
    }

    /**
     * Loops through each word in the original text array and compares it to the thesaurus using <code>compareText()</code> method.
     * Adds the result to a StringBuilder.
     *
     * @param originalText a String array with the user text line split into words.
     * @return a String array with the processed text split into words.
     */
    @Override
    protected String[] processText(String[] originalText) {
        StringBuilder processedText = new StringBuilder();
        for (String word : originalText) {
            String processedWord = compareText(word);
            processedText.append(processedWord);
            processedText.append(" ");
        }
        return processedText.toString().split(" ");
    }

    /**
     * Compares the word from the user input to the thesaurus. If it finds a match, it replaces the word with the google equivalent.
     * If it doesn't find a match, it returns the original word.
     * To search inside the wordThesaurus treeMap is a O(log n) operation.
     * @param word a word from the user input text line.
     * @return a new word if it finds a match or the same word if it doesn't.
     */
    @Override
    protected String compareText(String word) {
        if (wordThesaurus.containsKey(word)) {
            return (String) wordThesaurus.get(word);
        }
        return word;
    }

    /**
     * Takes the original text and the processed text in String array form. Compares original text versus processed text word per word.
     * If the word is unchanged, it gets printed using standard colour. If the word has changed, it is printed in red colour to show the change.
     *
     * @param originalText  a String array with the user text line split into words.
     * @param processedText a String array with the processed text line split into words.
     */
    private void printText(String[] originalText, String[] processedText) {
        for (int i = 0; i < originalText.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            if (originalText[i].equals(processedText[i])) {
                System.out.print(ConsoleColour.RESET);
                System.out.print(processedText[i]);
            } else {
                System.out.print(ConsoleColour.RED);
                System.out.print(processedText[i]);
            }
            System.out.print(ConsoleColour.RESET);
        }
    }
}
