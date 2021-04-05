package ie.gmit.dip;

import java.util.*;

/**
 * Abstract superclass for the loader program.
 * Contains principal methods for loading text.
 * Text Loader inherits from it.
 * @author Vladimir Novac
 * @version 1.0
 * @since 1.8
 */
public abstract class Loader {
    /**
     * A map that will hold Strings as both keys and values for the thesaurus.
     */
    static Map<String, String> wordThesaurus;
    /**
     * A set that will hold Strings for the google words.
     */
    static Set<String> keyWordsSet;
    /**
     * String that will hold the filename for the google words.
     */
    final String keyWordsFile;
    /**
     * String that wil hold the filename for the thesaurus.
     */
    final String thesaurusFile;

    /**
     * Constructor for loader class. Takes the file paths for google words and thesaurus file as Strings.
     * Initialises the map and the set.
     *
     * @param keyWordsFile file path for the google words file
     * @param thesaurusFile file path for the thesaurus file
     */
    Loader(String keyWordsFile, String thesaurusFile) {
        this.keyWordsFile = keyWordsFile;
        this.thesaurusFile = thesaurusFile;
        wordThesaurus = new TreeMap<>();
        keyWordsSet = new TreeSet<>();
    }

    /**
     * Generic method for running the loader program.
     * Multiple private methods can added inside to establish order of operations.
     */
    abstract void init();


}
