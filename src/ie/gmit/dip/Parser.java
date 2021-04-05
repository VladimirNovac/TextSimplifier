package ie.gmit.dip;

import java.util.*;

/**
 * Abstract superclass for the parser program.
 * Has the principal methods for running a text parser.
 * Class Text Parser inherits from it.
 * @author Vladimir Novac
 * @version 1.0
 * @since 1.8
 */
public abstract class Parser {
    Map wordThesaurus;

    /**
     * Constructor for parser class. Takes a text loader as parameters and uses the the thesaurus map from that object.
     *
     * @param textLoader text loader object
     */
    Parser(TextLoader textLoader) {
        this.wordThesaurus = textLoader.getWordThesaurus();
    }


    /**
     * Generic method for loading the user input text into memory.
     *
     * @param inputText text input from the user in the form of a String.
     * @return a String array with the user text line split into words.
     */
    protected abstract String[] loadInputText(String inputText);

    /**
     * Generic method for processing the user text.
     *
     * @param originalText a String array with the user text line split into words.
     * @return a String array with the processed text split into words.
     */
    protected abstract String[] processText(String[] originalText);

    /**
     * Generic method for comparing user input to words stored in the thesaurus.
     *
     * @param word a word from the user input text line.
     * @return a new word if it finds a match or the same word if it doesn't.
     */
    protected abstract String compareText(String word);

}
