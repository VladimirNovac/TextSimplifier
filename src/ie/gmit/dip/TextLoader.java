package ie.gmit.dip;

import java.io.*;
import java.util.Map;

/**
 * Implementation of Loader Class. Is responsible for loading the google words file and thesaurus file.
 * Inherits from Loader class. Class is not thread safe.
 * @author Vladimir Novac
 * @version 1.0
 * @since 1.8
 * @see Loader
 */
public class TextLoader extends Loader {

    /**
     * Initialises class and assigns the file paths for the google words and thesaurus as Strings.
     *
     * @param keyWordsFile  file path for the google words file
     * @param thesaurusFile file path for the thesaurus file
     */
    TextLoader(String keyWordsFile, String thesaurusFile) {
        super(keyWordsFile, thesaurusFile);
    }

    /**
     * Returns the thesaurus map.
     * @return the thesaurus map
     */
    Map<String, String> getWordThesaurus() {
        return wordThesaurus;
    }

    /**
     * Assigns order of operations for the Loader class. Can be called from the Runner class.
     * Should only be called once.
     */
    @Override
    void init() {
        addKeyWords(keyWordsFile);
        addThesaurus(thesaurusFile);
    }

    /**
     * Parses the google text file using a buffered reader. Maps all the words as keys and values in the Map.
     * Also adds the words to the Set. If it cannot find the file, it prints out a "File not found" message and exits the program.
     * To add data into wordThesaurus treeMap is a O(log n) operation.
     * To add data into keyWordsSet treeSet is a O(log n) operation.
     *
     * @param googleWordFile file path for the google words file
     */
    private void addKeyWords(String googleWordFile) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(googleWordFile))));
            String googleWord;
            while ((googleWord = reader.readLine()) != null) {
                wordThesaurus.put(googleWord.toLowerCase(), googleWord.toLowerCase());
                keyWordsSet.add(googleWord.toLowerCase());
            }
            System.out.println("Google words added to the library.");
            reader.close();
        } catch (IOException e) {
            System.out.println("Google words File not found, exiting program...");
            System.exit(1);
        }

    }

    /**
     * Parses the thesaurus file using a buffered reader. Reads each line of the thesaurus, splits the line into individual words and adds the words
     * to a String array. Checks if any of the words in the line matches any of the google words using <code>containsWord()</code>.
     * If one of the words matches, it calls <code>addAllWords()</code>.
     * If it cannot find the file, it prints out a "File not found" message and exits the program.
     *
     * @param mobyThesaurus2File file path for the thesaurus file
     */
    private void addThesaurus(String mobyThesaurus2File) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mobyThesaurus2File))));
            String wordLine;
            String[] splitWords;
            while ((wordLine = reader.readLine()) != null) {
                splitWords = wordLine.toLowerCase().split(",");
                String googleWord = containsWord(splitWords);
                if (googleWord != null) {
                    addAllWords(splitWords, googleWord);
                }
            }
            System.out.println("Thesaurus added to the library.");
            reader.close();
        } catch (IOException e) {
            System.out.println("Thesaurus File not found, exiting program...");
            System.exit(1);
        }
    }

    /**
     * Checks if the first word of the array (the root word in the thesaurus) matches any of the google words.
     * If true, then it returns the word. If false, it returns a null String.
     * To search inside the keyWordsSet is a O(log n) operation.
     *
     * @param splitWords a line of text from the thesaurus converted into an String array
     * @return a google word if true, a null string if false.
     */
    private String containsWord(String[] splitWords) {
        String googleWord = null;
        if (keyWordsSet.contains(splitWords[0])) googleWord = splitWords[0];
        return googleWord;
    }

    /**
     * Maps all the words in the read line as keys pointing the to the google word (value).
     * To search inside the keyWordsSet is a O(log n) operation.
     * To add data into wordThesaurus treeMap is a O(log n) operation.
     *
     * @param splitWords a line of text from the thesaurus converted into an String array.
     * @param googleWord a google word from the google words list.
     */
    private void addAllWords(String[] splitWords, String googleWord) {
        for (String word : splitWords) {
            if (keyWordsSet.contains(word)) continue;
            wordThesaurus.put(word, googleWord);
        }
    }

}
