package ie.gmit.dip;

import java.util.Scanner;


/**
 * Runner class that executes the program. Has methods for user interface and keyboard input.
 * @author Vladimir Novac
 * @version 1.0
 * @since 1.8
 */
public class Runner {

    /**
     * Starts the program.
     * @param args Program arguments. Not needed for this application.
     */
    public static void main(String[] args) {
        initUi();
    }

    /**
     * Initialises the user interface and calls the <code>start()</code> method.
     */
    private static void initUi() {
        System.out.println(ConsoleColour.BLUE_BRIGHT);
        System.out.println("***************************************************");
        System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
        System.out.println("*                                                 *");
        System.out.println("*             Text Simplifier V0.1                *");
        System.out.println("*       (AKA Orwellian Language Compliance)       *");
        System.out.println("*                                                 *");
        System.out.println("***************************************************");

        start();
    }

    /**
     * Enables keyboard input and asks the user for the location of the google words file and thesaurus.
     * Creates a new Text Loader and Text Parser object.
     * The text loader is passed as a parameter to the text parser ensuring encapsulation.
     * Starts the main program loop.
     * Closes the program when user types \quit
     */
    private static void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the google words filename: (include file extension)");
        String googleWordFile = "./" + scanner.nextLine(); // "google-1000.txt"
        System.out.println("Please enter the thesaurus filename: (include file extension)");
        String mobyThesaurus2File = "./" + scanner.nextLine(); //"MobyThesaurus2.txt";
        TextLoader textLoader = new TextLoader(googleWordFile, mobyThesaurus2File);
        textLoader.init();
        System.out.println("\nEnter text to be processed>");
        IPrintable textParser = new TextParser(textLoader);//pass textLoader into constructor instead of map
        while (scanner.hasNextLine()) {
            String inputText = scanner.nextLine();
            if (inputText.isEmpty()) {
                System.out.println("Please enter a line of text to be processed");
                continue;
            }
            if (inputText.equals("\\quit")) {
                System.out.println("Exiting program...");
                scanner.close();
                break;
            }
            textParser.print(inputText.toLowerCase());
            System.out.println();
        }

    }
}