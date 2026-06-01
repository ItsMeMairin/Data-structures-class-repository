
import java.util.Scanner;

public class Exercise22_2 {

    //class variables
    static String s1;
    static String s2;

    public static void main(String[] args) {
        
        //prompt user for two strings
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a string s1: ");
        s1 = input.nextLine();
        
        System.out.print("Enter a string s2: ");
        s2 = input.nextLine();

        //pass these strings to the match method 
        int result = match(s1, s2); 

        if (result > -1) {
            System.out.print("Matched at index " + result);
        }
        else {
            System.out.print("No match found");
        }

        input.close();
    }

    public static int match(String s, String pattern) {

        //i = s index
        //x = pattern index
        for(int i = 0; i <= s.length() - pattern.length(); i++) {

            int x = 0;

            while (x < pattern.length() && s.charAt(i + x) == pattern.charAt(x)) {
             x++;   
            }

            if (x == pattern.length()) {
                return i;
            }

        }

    return -1;

    }
  
}