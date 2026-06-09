/*
Allison Butt, CMSC 6380, 8 Jun 26
Replace placeholder return statements with working code

Once replaced, this class interfaces with Main to provide ordered results as maps and sets.
*/

import java.util.*;
import java.util.stream.Collectors;

public class NLPUtility {

    /**
     * Splits the given text into word tokens using one or more whitespace
     * or punctuation characters as delimiters.
     *
     * @param text the input string to be tokenized
     * @return an array of word tokens, excluding punctuation and whitespace
     */

     //splits string based off delimiers: [\\s\\p{P}]+
        
    public static String[] splitTextIntoTokens(String text) {
        String[] splitInput = text.split("[\\s\\p{P}]+");

        //TODO debug print statement
        System.out.print("split text: " + Arrays.toString(splitInput));
        //
        return splitInput;
    }

    /**
     * Counts the frequency of words in the given array, excluding those present in
     * the specified set of stop words.
     * The comparison is case-insensitive, and results are stored in a
     * {@link TreeMap} sorted alphabetically by word.
     *
     * @param words     An array of tokenized words to analyze.
     * @param stopWords A set of words to exclude from the frequency count (e.g.,
     *                  common stop words like "the", "and").
     * @return A {@link TreeMap} mapping each non-stop word to its frequency, sorted
     *         alphabetically.
     */
    public static TreeMap<String, Integer> countFilteredWords(String[] words, Set<String> stopWords) {

        //initialize treemap
        TreeMap<String, Integer> filteredMap = new TreeMap<>();
        //for every item in words
        for (String word : words) {
            String lowerWord = word.toLowerCase();
            //if item ! in treemap & item ! in stopWords 

            if (stopWords.contains(lowerWord)) {
                continue;
            }

            if (lowerWord.isEmpty()) {
                continue;
            }

            if (filteredMap.containsKey(lowerWord)) {
                //iterate it's value
                filteredMap.put(lowerWord, filteredMap.getOrDefault(lowerWord, 0) + 1);
                //go to next item
            }               

            else {
                //add to treemap
                filteredMap.put(lowerWord, 1);
                //go to next item
            }
            //if item is in treemap
            
        }
        //TODO debug print statement
        System.out.print(filteredMap);    
        return filteredMap;
        
    }

    /**
     * Sorts the entries of a map by their values in descending order.
     * The result is returned as a {@link LinkedHashMap} to preserve the order of
     * sorted entries.
     *
     * @param map A map containing keys and integer values to be sorted by value.
     * @return A {@link LinkedHashMap} containing the same entries as the input map,
     *         sorted in descending order by value.
     */
    public static LinkedHashMap<String, Integer> sortByValueDescending(Map<String, Integer> map) {
        LinkedHashMap<String, Integer> sortedMap = map.entrySet()
            .stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue,
                LinkedHashMap::new 
                ));

        //TODO debug print statement
        System.out.println(sortedMap);
        return sortedMap;

    }

    /**
     * Performs sentiment analysis by scanning the word-frequency map.
     * Adds up the total frequency of all words found in the predefined
     * positive and negative word sets.
     *
     * @param wordMap A map of words and their frequencies.
     * @return A summary string in the format: "Positive: X, Negative: Y"
     *         where X and Y are the total counts of positive and negative words.
     */
    public static String getSentiment(Map<String, Integer> wordMap, Set<String> positiveWords,
            Set<String> negativeWords) {
        Integer positiveSum = 0;
        Integer negativeSum = 0;

        //
        for (Map.Entry<String, Integer> word : wordMap.entrySet()) {
            String key = word.getKey().toLowerCase();
            int frequency = word.getValue();

            if (positiveWords.contains(key)) {
                positiveSum += frequency;
            }

            if (negativeWords.contains(key)) {
                negativeSum += frequency;
            }
        }
        return "Positive: " + positiveSum + ", Negative: " + negativeSum;
    }

    /**
     * Finds the words with the highest frequency in the given map and returns a map
     * containing a sorted word list along with the maximum frequency value.
     *
     * @param wordMap A map of words and their corresponding frequencies.
     * @return A map containing:
     *         - "words": A list of words with the highest frequency, sorted
     *         alphabetically.
     *         - "frequency": The highest frequency value.
     */
    public static Map<String, Object> getWordsWithMaxFrequency(Map<String, Integer> wordMap) {
        //initialize return map
        Map<String, Object> maxFrequencyMap = new HashMap<>();
        ArrayList<String> freqArray = new ArrayList<>();
        int maxFrequency = 0;

        //find max frequency value in input map
        for (Map.Entry<String, Integer> word : wordMap.entrySet()) {
            //String key = word.getKey().toLowerCase();
            
            if (word.getValue() > maxFrequency) {
                maxFrequency = word.getValue();
            }

        }

        //collect all words with that frequency
        for (Map.Entry<String, Integer> word : wordMap.entrySet()) {

            if (word.getValue() == maxFrequency) {
                freqArray.add(word.getKey());
            }

        }
        Collections.sort(freqArray);

        maxFrequencyMap.put("words", freqArray);
        maxFrequencyMap.put("frequency", maxFrequency);

        return maxFrequencyMap;

    }

}
