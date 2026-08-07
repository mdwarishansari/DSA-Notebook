import java.util.*;

class Solution {

    public int[] topKFrequent(int[] nums, int k) {

        // ---------------- Step 1 ----------------
        // Count the frequency of every number
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {

            // Increase frequency by 1
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        /*
            Example

            nums = [1,1,1,2,2,3]

            map =
            {
                1 -> 3
                2 -> 2
                3 -> 1
            }
        */


        // ---------------- Step 2 ----------------
        // Min Heap based on frequency
        //
        // Each element in heap is a Map Entry
        // (Number, Frequency)
        //
        // Smaller frequency comes first.
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>(
                        (a, b) -> a.getValue() - b.getValue()
                );


        // Process every (Number, Frequency) pair
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            // Add current pair into heap
            minHeap.offer(entry);

            // If heap size becomes greater than k,
            // remove the least frequent element.
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }


        // ---------------- Step 3 ----------------
        // Heap now contains exactly k most frequent elements

        int[] ans = new int[k];

        // Remove elements from heap
        for (int i = k - 1; i >= 0; i--) {

            // getKey() gives the original number
            ans[i] = minHeap.poll().getKey();
        }

        return ans;
    }
}