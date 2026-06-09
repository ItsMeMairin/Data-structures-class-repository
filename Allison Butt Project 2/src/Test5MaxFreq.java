/*
Allison Butt, CMSC 6380, 8 Jun 26
Conduct tests for each method in NLPUtility.java
Test these methods in isolation with different classes
Test 5: Max Frequency
*/
import java.util.HashMap;
import java.util.Map;

public class Test5MaxFreq {
    public static void main(String[] args) {

        Map<String, Object> resultMap1;
        Map<String, Object> resultMap2;
        Map<String, Object> resultMap3;

        Map<String, Integer> testMap1 = new HashMap<>();
            testMap1.put("leastFrequent", 1);
            testMap1.put("midline", 2);
            testMap1.put("secondmid", 2);
            testMap1.put("mostFrequent", 4);

        Map<String, Integer> testMap2 = new HashMap<>();
            testMap2.put("leastFrequent", 1);
            testMap2.put("midline", 2);
            testMap2.put("tiedtop", 4);
            testMap2.put("mostFrequent", 4);

        Map<String, Integer> testMap3 = new HashMap<>();
            testMap3.put("oops", 0);
            testMap3.put("all", 0);
            testMap3.put("zeroes", 0);

        System.out.println("Input map (basic list): " + testMap1);
        resultMap1 = NLPUtility.getWordsWithMaxFrequency(testMap1);     
        System.out.println("Output map: ");
        System.out.println(resultMap1);
        

        System.out.println("Input map (tied list): " + testMap2);
        resultMap2 = NLPUtility.getWordsWithMaxFrequency(testMap2);     
        System.out.println("Output map: ");
        System.out.println(resultMap2);

        System.out.println("Input map (zero list): " + testMap3);
        resultMap3 = NLPUtility.getWordsWithMaxFrequency(testMap3);     
        System.out.println("Output map: ");
        System.out.println(resultMap3);
    }
}