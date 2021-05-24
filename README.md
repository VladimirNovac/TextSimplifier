# Text Simplifier V 1.0
This is a simple text simplifier written in java that works in console mode. 
It allows the user to enter text that will dinamically swapped with a simpler version.

# Main Features
- At the start of the program, users can manually enter the file names for the key words file and thesaurus. The program will then use files for parsing text.

- When user writtes a line of text and hits enter, the application will immediately parse the line word per word, swap any words if a suitable replacement is found and display the line in the console.

- Colour coding: All the words that are changes are displayed using red, the rest of the words will retain the original colour.

- The application has two main parts. 
A text loader which reads the key words file and thesaurus and loads them into memory.
A text parser which reads the user input text and compares the words with the thesaurus stored in memory. Additionally, the text parser implements a print function that allows the processed text to be displayed in the console.
This approach allows for more flexibility if additional features should be added.

# How to use
To program can be run from the jar file using the following command:
java -Xmx1G –cp ./simplifier.jar ie.gmit.dip.Runner
(-Xmx1G - this command allocates a minimum 1gb memory for the application)

To exit the program, use the following command in the console:
\quit

# Classes and methods
### Runner Class
---------------------
Runner class that executes the program. Has methods for user interface and keyboard input.
Methods:
main() - Starts the program.
initUi() - Initialises the user interface and calls the start() method.
start() - Enables keyboard input and asks the user for the location of the google words file and thesaurus. Creates a new Text Loader and Text Parser object. Starts the main program loop.

### Abstract Class Loader
-----------------------
Abstract superclass for the loader program.
Methods:
init() - Generic method for running the loader program.

TextLoader Class
-----------------------
Implementation of Loader Class. Is responsible for loading the google words file and thesaurus file. Inherits from Loader class.
Methods:
addKeyWords() - Parses the google text file. Maps all the words as keys and values in the Map. Also adds the words to the Set.
addThesaurus() - Parses the thesaurus text file. Maps all the words as keys and values in the Map using containsWord() and addAllWords() methods.
containsWord() - Checks the thesaurus for instances of the key words in the thesaurus.
addAllWords() - Maps all the words in the read line as keys pointing the to the google word (value).

Abstract Class Parser
------------------------
Abstract superclass for the parser program.
Methods:
loadInputText() - Generic method for loading the user input text into memory.
processText() - Generic method for processing the user text.
compareText() - Generic method for comparing user input to words stored in the thesaurus.

IPrintable Interface
------------------------
Interface for the print method.
Methods:
print() - Generic print method that will allow outputting text to the console.

TextParser Class
------------------------
Implementation of the parser class. It is responsible for processing text from user input. Inherits from Parser and implements IPrintable.
Methods:
print() - Provides order of operations for the text parser.
loadInputText() - Takes the input text line and splits it into a String array.
processText() - Loops through each word in the original text array and compares it to the thesaurus using compareText() method.
compareText() - Compares the word from the user input to the thesaurus. If it finds a match, it replaces the word with the google equivalent.
printText() - Takes the original text and the processed text in String array form. Compares original text versus processed text word per word. If the word is unchanged, it gets printed using standard colour. If the word has changed, it is printed in red colour to show the change.

ConsoleColour Enum Class
--------------------------
Enum class containing the colour codes to change the output text colour in command line application.
