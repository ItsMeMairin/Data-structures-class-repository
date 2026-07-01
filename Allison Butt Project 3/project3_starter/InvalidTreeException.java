/*
Allison Butt CMSC 315 6380 Project 3 
22 June 26
This file throws an InvalidTreeException if our user attempts to provide an invalid input.
*/
public class InvalidTreeException extends Exception {
    public InvalidTreeException(String message) {
        super(message);
    }
}
