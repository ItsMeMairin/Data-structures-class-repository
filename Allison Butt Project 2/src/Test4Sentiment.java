/*
Allison Butt, CMSC 6380, 8 Jun 26
Conduct tests for each method in NLPUtility.java
Test these methods in isolation with different classes
Test 4: Sentiment Analaysis
*/
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test4Sentiment {
    public static void main(String[] args) {
        String[] stringArray1 = {"Test", "Wow", "very", "nice", "and", "cool"};
        String[] stringArray2 = {"good", "great", "happy", "love", "like"};
        String[] stringArray3 = {"bad", "terrible", "horrible", "sad", "hate"};
        String[] stringArray4 = {"bad", "test", "horrible", "love", "cool"};

        Set<String> positiveWords = new HashSet<>(Arrays.asList("good", "great", "happy", "love", "like"));
        Set<String> negativeWords = new HashSet<>(Arrays.asList("bad", "terrible", "horrible", "sad", "hate"));


        Map<String, Integer> testMap1 = toHashMap(stringArray1);
        Map<String, Integer> testMap2 = toHashMap(stringArray2);
        Map<String, Integer> testMap3 = toHashMap(stringArray3);
        Map<String, Integer> testMap4 = toHashMap(stringArray4);

        System.out.println("Test 1: no pos/neg words:");
        System.out.println("Test 1 initial array:" + Arrays.toString(stringArray1));
        System.out.println(NLPUtility.getSentiment(testMap1, positiveWords, negativeWords));

        System.out.println("Test 2: only pos words:");
        System.out.println("Test 2 initial array:" + Arrays.toString(stringArray2));
        System.out.println(NLPUtility.getSentiment(testMap2, positiveWords, negativeWords));

        System.out.println("Test 3: only neg words:");
        System.out.println("Test 3 initial array:" + Arrays.toString(stringArray3));
        System.out.println(NLPUtility.getSentiment(testMap3, positiveWords, negativeWords));

        System.out.println("Test 4: mixed words:");
        System.out.println("Test 4 initial array:" + Arrays.toString(stringArray4));
        System.out.println(NLPUtility.getSentiment(testMap4, positiveWords, negativeWords));

    }
    
    public static Map<String, Integer> toHashMap(String[] stringArray) {

         Map<String, Integer> constructorMap = new HashMap<>();
        Integer loopCounter = 0;
        for (String s : stringArray) {
            constructorMap.put(s, loopCounter);
            loopCounter++;
        }

        return constructorMap;
    }
}
