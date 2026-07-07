import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        // HashMap to store:
        // Key   -> Number
        // Value -> Frequency of that number
        HashMap<Integer, Integer> map = new HashMap<>();

        // Count the frequency of every number
        for (int num : nums) {

            // If number already exists, increase frequency by 1
            // Otherwise insert it with frequency = 1
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        /*
            Example:

            nums = [1,1,1,2,2,3]

            map =
            {
                1 = 3,
                2 = 2,
                3 = 1
            }
        */


        // Convert HashMap into a List of Map Entries
        // Each entry contains (Key, Value)
        List<Map.Entry<Integer, Integer>> list =
                new ArrayList<>(map.entrySet());

        /*
            List before sorting

            (1,3)
            (2,2)
            (3,1)
        */


        // Sort the list based on frequency (Value)
        // Descending Order (Highest frequency first)
        list.sort((a, b) -> b.getValue() - a.getValue());

        /*
            After sorting

            (1,3)
            (2,2)
            (3,1)
        */


        // Create answer array of size k
        int[] ans = new int[k];

        // Pick first k elements
        for (int i = 0; i < k; i++) {

            // getKey() returns the actual number
            ans[i] = list.get(i).getKey();
        }

        // Return answer
        return ans;
    }
}