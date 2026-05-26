/* 
Allison Butt, CMSC 315 6380 Project 1, 26 May 26
This file describes the Encapsulator class. 
The Encapsulator class is responsible for receiving .java source files and processing characters
which appear in that file, checking for proper matching delimiters. 
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