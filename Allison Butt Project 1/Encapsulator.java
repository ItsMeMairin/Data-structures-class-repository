/* 
Allison Butt, CMSC 315 Project 1, 19 May 26
This file describes the Encapsulator class. 
The Encapsulator class is responsible for receiving .java source files and processing characters
which appear in that file, checking for proper matching delimiters. 

The class "Encapsulator" contains 3  Public methods and X private methods.

Variables:
    CharArray: Contents
        the entirety of the file is parsed into a CharArray for processing
    
    int: Column
    int: Line
        Increment column count as characters are read, when a newline character is encountered, reset column and increment line
    

Public Method 1 - Construct
    The constructor method accepts the file name of the source file. If that file is not found
    it throws a File Not Found exception, only accepting files that exist in it's directory.


Public Method 2 - NextChar
    The NextChar method returns the next character in the file, excluding characters inside of comments
    or characters in character or String literals

        Read next char in chararray, if it is a comment char like //or //* it will not return any chars until an escape (newline or close comment)
        condition is met.

Public Method 3 - Position
    the Position method outputs the line and character numbers of the current character. Takes the current Col  + Line vars and passes them
    along with the approrpiate message. 

*/
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Encapsulator {

    private int currentIndex = 0;
    
    private  char[] inputArray;

    private  int colNum = 0;
    private  int lineNum = 1;



    public Encapsulator(String fileName) throws FileNotFoundException{

        System.out.println(
            Paths.get(fileName).toAbsolutePath()
        );

        try {
            String content = Files.readString(Paths.get(fileName));
            inputArray = content.toCharArray();
        }

        catch (IOException e) {
            throw new FileNotFoundException(fileName);
        }

    }

    public char  nextChar() {

        while (currentIndex < inputArray.length) {

            char ch = inputArray[currentIndex];

            //skip single comment
            if (ch == '/' && currentIndex + 1 < inputArray.length &&
                inputArray[currentIndex + 1] == '/') {
                skipSingleLineComment();
                continue;
            }
            //skip multi comment
            if (ch == '/' &&
                currentIndex + 1 < inputArray.length &&
                inputArray[currentIndex + 1] == '*') {
                skipMultiLineComment();
                continue;
            }
            //skip strings
            if (ch == '"') {
                skipStringLiteral();
                continue;
            }
            //skip chars
            if (ch == '\'') {
                skipCharLiteral();
                continue;
            }

            advancePosition(ch);
            currentIndex++;

            return ch;
        }

        return '\0';
        
    }

    public String position() {

       return("The current Character is located at line [" + lineNum + "], column [" + colNum + "].");

    }

    private void skipSingleLineComment() {

        currentIndex += 2;

        while (currentIndex < inputArray.length && inputArray[currentIndex] != '\n') {
            advancePosition(inputArray[currentIndex]);
            currentIndex++;
        }

        if (currentIndex < inputArray.length) {
            advancePosition(inputArray[currentIndex]);
            currentIndex++;
        }

    }

    private void skipMultiLineComment() {

        currentIndex += 2;

        while (currentIndex < inputArray.length - 1) {

            if (inputArray[currentIndex] == '*' &&
                inputArray[currentIndex + 1] == '/') {
                
                
                advancePosition(inputArray[currentIndex]);
                advancePosition(inputArray[currentIndex + 1]);
                
                currentIndex += 2;
                return;
            }
            
            advancePosition(inputArray[currentIndex]);
            currentIndex++;
        }
    }

    private void skipStringLiteral() {

        // consume opening quote
        advancePosition(inputArray[currentIndex]);
        currentIndex++;

        while (currentIndex < inputArray.length) {

            char ch = inputArray[currentIndex];

            advancePosition(ch);

            // closing quote not escaped
            if (ch == '"' &&
                inputArray[currentIndex - 1] != '\\') {

                currentIndex++;
                return;
            }

            currentIndex++;
            }
    }

    private void skipCharLiteral() {

        // consume opening quote
        advancePosition(inputArray[currentIndex]);
        currentIndex++;

        while (currentIndex < inputArray.length) {

            char ch = inputArray[currentIndex];

            advancePosition(ch);

            // closing quote not escaped
            if (ch == '\'' &&
                inputArray[currentIndex - 1] != '\\') {

                currentIndex++;
                return;
            }

            currentIndex++;
        }
    }

    private void advancePosition( char ch) {

        if (ch == '\n') {
          lineNum++;
            colNum = 0;
        }
        else {
            colNum++;
        }
    }
}