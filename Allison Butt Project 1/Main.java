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
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner sc = new Scanner(System.in);
        Stack<Character> stack = new Stack<>();
        char ch;

        Encapsulator encapsulator = null;

        while (encapsulator == null) {

            System.out.print("Enter file name: ");

            String fileName = sc.nextLine();

            try {
                encapsulator = new Encapsulator(fileName);
            }

            catch (FileNotFoundException e) {
                System.out.println("Invalid file.");
            }
        }

        while ((ch = encapsulator.nextChar()) != '\0') {

            System.out.println(ch + " " + encapsulator.position());

            if (isLeftDelimiter(ch)) {
                System.out.println("PUSH: " + ch);
                stack.push(ch);
            }

            else if (isRightDelimiter(ch)) {
                
                if (stack.isEmpty()) {
                System.out.println(
                        "Stack is empty"
                    );
                    break;
                }
                
                char left = stack.pop();
                System.out.println("POP: " + left + " with " + ch);

                if (!delimitersMatch(left, ch)) {
                    System.out.println(
                        "Delimiter mismatch: expected " +
                        expectedDelimiter(left) +
                        " but found " + ch +
                        " at " + encapsulator.position()
                    );
                    break;
                }

            }

        }

        if (!stack.isEmpty()) {
            System.out.println("Error: Missing closing delimiter for " + stack.peek());
}

    }

    private static char expectedDelimiter(char left) {
        if (left == '(') return ')';
        if (left == '[') return ']';
        if (left == '{') return '}';
        return '}';
    }

    private static boolean isLeftDelimiter(char ch) {

        return (ch ==  '(' || ch == '{' || ch == '[');
    }

    private static boolean isRightDelimiter(char ch) {
        return (ch ==  ')' || ch == '}' || ch == ']');
    }

    private static boolean delimitersMatch(char left, char right) {
        return ((left == '(' && right == ')') || (left == '{' && right == '}') || (left == '[' && right == ']'));
    }
}
