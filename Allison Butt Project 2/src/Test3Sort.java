/*
Allison Butt, CMSC 6380, 8 Jun 26
Conduct tests for each method in NLPUtility.java
Test these methods in isolation with different classes
Test3: sort by value descending
*/
import java.util.Arrays;
import java.util.LinkedHashMap;

//testing proper descending order

public class Test3Sort {

    public static void main(String[] args) {

        LinkedHashMap<String, Integer> test2Map= new LinkedHashMap<>();

        String[] stringArray = {"t", "u", "v", "f", "c", "w", "x", "y", "n", "q", "r", "z", "a","o", "p", "b", "d", "e", "k", "l", "m", "s", "g", "h", "i", "j"};


        Integer loopCounter = 0;
        for (String s : stringArray) {
            test2Map.put(s, loopCounter);
            loopCounter++;
        }

        System.out.println("Test: Given a hash map of jumbled values, sort descending");
        System.out.println("the given values are:");
        System.out.println(Arrays.toString(stringArray));
        System.out.println("The unsorted list is: ");
        System.out.println(test2Map);

       test2Map = NLPUtility.sortByValueDescending(test2Map);
        System.out.println("The sorted list is: ");
        System.out.println(test2Map);


    }

}
