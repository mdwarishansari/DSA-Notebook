import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        // Sort the array so we can process elements in non-decreasing order
        Arrays.sort(nums);
        
        // dp.get(x) stores the maximum length of a consecutive sequence ending at value x
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 1;
        
        for (int num : nums) {
            // IMPORTANT: Compute the (num + 1) transition BEFORE updating (num)
            // so that the same element is not counted twice in the same sequence.
            
            // Option 1: Increase current element by 1 (becomes num + 1)
            // It can extend any consecutive sequence ending at (num)
            int lenPlusOne = dp.getOrDefault(num, 0) + 1;
            
            // Option 2: Keep current element as is (remains num)
            // It can extend any consecutive sequence ending at (num - 1)
            int lenAsIs = dp.getOrDefault(num - 1, 0) + 1;
            
            // Update the DP map with the best lengths found for (num + 1) and (num)
            dp.put(num + 1, Math.max(dp.getOrDefault(num + 1, 0), lenPlusOne));
            dp.put(num, Math.max(dp.getOrDefault(num, 0), lenAsIs));
            
            // Track the overall maximum consecutive length found so far
            maxLen = Math.max(maxLen, Math.max(dp.get(num), dp.get(num + 1)));
        }
        
        return maxLen;
    }
}