import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int minSum = Integer.MAX_VALUE;
        int n = nums.size();
        
        // Iterate through all possible subarray lengths from 'l' to 'r' (inclusive)
        for (int len = l; len <= r; len++) {
            int currentSum = 0;
            
            // Calculate the sum of the first window of the current length
            for (int i = 0; i < len; i++) {
                currentSum += nums.get(i);
            }
            
            // If the sum is positive, update our minimum sum found so far
            if (currentSum > 0) {
                minSum = Math.min(minSum, currentSum);
            }
            
            // Slide the window across the rest of the array
            for (int i = len; i < n; i++) {
                // Add the new element entering the window and subtract the one leaving
                currentSum += nums.get(i) - nums.get(i - len);
                
                // If the updated window sum is positive, consider it
                if (currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                }
            }
        }
        
        // If minSum was never updated, no valid positive sum subarray exists; return -1
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}