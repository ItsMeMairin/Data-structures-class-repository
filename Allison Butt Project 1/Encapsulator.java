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

Public Method 2 - Position
    the Position method outputs the line and character numbers of the current character. Takes the current Col  + Line vars and passes them
    along with the approrpiate message. 

*/