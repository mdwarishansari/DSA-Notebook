
import java.util.*;

class Solution {
    public String frequencySort(String s) {

        // Step 1: Create a HashMap to store character frequencies
        HashMap<Character, Integer> map = new HashMap<>();

        // Count the frequency of each character
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Step 2: Create buckets based on frequency
        // Maximum frequency cannot exceed string length
        List<Character>[] bucket = new List[s.length() + 1];

        // Step 3: Place characters into their frequency buckets
        for (char ch : map.keySet()) {

            // Get the frequency of the current character
            int freq = map.get(ch);

            // Initialize the bucket if it is empty
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }

            // Add character to its corresponding bucket
            bucket[freq].add(ch);
        }

        // Step 4: Build the final sorted string
        StringBuilder result = new StringBuilder();

        // Traverse buckets from highest to lowest frequency
        for (int i = bucket.length - 1; i >= 1; i--) {

            // Skip empty buckets
            if (bucket[i] == null) {
                continue;
            }

            // Append each character according to its frequency
            for (char ch : bucket[i]) {

                for (int j = 0; j < i; j++) {
                    result.append(ch);
                }
            }
        }

        // Return the frequency-sorted string
        return result.toString();
    }
}
