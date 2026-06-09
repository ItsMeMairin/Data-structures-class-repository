/*
Allison Butt, CMSC 6380, 8 Jun 26
Conduct tests for each method in NLPUtility.java
Test2: testing the countFilteredWords method
*/
import java.util.*;

/*
testing proper exclusion of following words:
 Set<String> stopWords = new HashSet<>(Arrays.asList("the", "is", "in", "at", "of", "and", "a", "to", "it", "or", "was", "so"));
*/
public class Test2Filter {

    public static void main(String[] args) {

    Set<String> stopWords = new HashSet<>( Arrays.asList(
        "the", "is", "in", "at", "of",
        "and", "a", "to", "it", "or",
        "was", "so"
    )

);

    TreeMap<String, Integer> testTreeMap;

    System.out.println("Test: Testing countFiltered Words");
    System.out.println("Test 1: Entering no stop words");
    System.out.println("Test 1: We should see all words return");
    String[] test1 = {"test", "for", "only", "good", "unfiltered", "words"};
    testTreeMap = NLPUtility.countFilteredWords(test1, stopWords);

    System.out.println(testTreeMap);

    System.out.println("Test 2: Entering only stop words");
    System.out.println("Test 2: We should see no words return");
    String[] test2 = {"the", "is", "in", "at", "of", "and", "a", "to", "it", "or", "was", "so"};
    testTreeMap = NLPUtility.countFilteredWords(test2, stopWords);
    System.out.println(testTreeMap);

    System.out.println("Test 3: Entering mixed words");
    System.out.println("Test 1: We should see only unfiltered words return");
    String[] test3 = {"the", "is", "test", "three", "of", "the", "biggest", "program"};
    testTreeMap = NLPUtility.countFilteredWords(test3, stopWords);
    System.out.println(testTreeMap);

    
    }
}
