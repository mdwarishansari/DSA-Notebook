class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        
        // Initialize counts of prefix sums seen so far.
        // evenCount starts at 1 to represent an empty prefix (sum = 0, which is even),
        // allowing single-element or prefix-starting odd subarrays to be counted.
        int evenCount = 1; 
        int oddCount = 0;
        
        int currentPrefixSum = 0;
        long totalOddSubarrays = 0;

        for (int num : arr) {
            currentPrefixSum += num;

            // Check the parity (odd or even) of the current prefix sum
            if (currentPrefixSum % 2 == 0) {
                // Even - Odd = Odd
                // Any subarray starting right after a previous odd prefix sum will have an odd sum
                totalOddSubarrays = (totalOddSubarrays + oddCount) % MOD;
                evenCount++;
            } else {
                // Odd - Even = Odd
                // Any subarray starting right after a previous even prefix sum will have an odd sum
                totalOddSubarrays = (totalOddSubarrays + evenCount) % MOD;
                oddCount++;
            }
        }

        return (int) totalOddSubarrays;
    }
}