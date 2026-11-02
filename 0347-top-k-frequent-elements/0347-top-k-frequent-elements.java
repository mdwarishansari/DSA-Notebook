
import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        // Step 1: Create a HashMap to store element frequencies
        HashMap<Integer, Integer> map = new HashMap<>();

        // Count the frequency of each element
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Step 2: Create buckets based on frequency
        // Maximum possible frequency is nums.length
        List<Integer>[] bucket = new List[nums.length + 1];

        // Step 3: Place each element into its frequency bucket
        for (int num : map.keySet()) {

            // Get the frequency of the current element
            int freq = map.get(num);

            // Initialize the bucket if it is empty
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }

            // Add the element to its frequency bucket
            bucket[freq].add(num);
        }

        // Step 4: Create an array to store the top k elements
        int[] result = new int[k];
        int index = 0;

        // Step 5: Traverse buckets from highest to lowest frequency
        for (int i = bucket.length - 1; i >= 0; i--) {

            // Skip empty buckets
            if (bucket[i] == null) {
                continue;
            }

            // Add elements from the current bucket
            for (int num : bucket[i]) {

                result[index++] = num;

                // Stop when we have collected k elements
                if (index == k) {
                    return result;
                }
            }
        }

        // Return the result
        return result;
    }
}
