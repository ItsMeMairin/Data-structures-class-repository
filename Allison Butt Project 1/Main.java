/* 
Allison Butt, CMSC 315 Project 1, 19 May 26
This file describes the Main class. 
The Main class is responsible for reading user input until a valid file is entered.
Once a valid file is found, it creates an object of the Encapsulator class

It will repeatedly call the Nextchar method until it receives a null or a delimiter mismatch.
    If the delimiter is a left delimiter, push to deliminator stack
    If it's right, pop the stack and check if it matches the other deliminator

    In case of error - display what deliminator was encountered and its position

Main method - Repeatedly prompts user for input and checks for valid files of that name.
    Once a valid file is found, creates Encapsulator class to handle file

    Calls next char and checks if it's a delimiter. 

        Not delimiter: go to next char

        Left delimiter: push to stack 
        Right delimiter: pop stack and check if they match.
            If they do match: discard and move to next char
            If they do NOT match: display error message containing:
                What delimiter was read
                Position of that delimiter
                expected correct delimiter based off of popped left delimiter
    
*/