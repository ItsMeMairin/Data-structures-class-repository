/*
Allison Butt, CMSC 6380, 8 Jun 26
Conduct tests for each method in NLPUtility.java
Test these methods in isolation with different classes
Ensure each test sufficiently demonstrates method
Test 1: Split into tokens
*/
import java.util.Arrays;



/**
     * Splits the given text into word tokens using one or more whitespace
     * or punctuation characters as delimiters.
     *
     * @param text the input string to be tokenized
     * @return an array of word tokens, excluding punctuation and whitespace
     */

     //splits string based off delimiers: [\\s\\p{P}]+

public class Test1Split {

    public static void main(String[] args) {

        String test1 = "testing whitespace delimiters";
        String test2 = "testing.punctuation,delimiters?there!are(a)lot";
        String test3 = "testing . multiple.,, delimiters!!!";
        String test4 = "testingnodelimiters";

        System.out.println("Test 1: " + test1);
        System.out.println(Arrays.toString(NLPUtility.splitTextIntoTokens(test1)));

        System.out.println("Test 2: " + test2);
        System.out.println(Arrays.toString(NLPUtility.splitTextIntoTokens(test2)));

        System.out.println("Test 3: " + test3);
        System.out.println(Arrays.toString(NLPUtility.splitTextIntoTokens(test3)));

        System.out.println("Test 4: " + test4);
        System.out.println(Arrays.toString(NLPUtility.splitTextIntoTokens(test4)));
    }
}
