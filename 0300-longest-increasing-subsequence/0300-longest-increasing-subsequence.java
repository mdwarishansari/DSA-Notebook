class Solution {
    public int lengthOfLIS(int[] nums) {

        int n = nums.length;

        // dp[i] = length of the longest increasing
        // subsequence that ends at index i.
        int[] dp = new int[n];

        // Every element by itself forms an
        // increasing subsequence of length 1.
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Consider each element as the ending element
        // of our increasing subsequence.
        for (int i = 0; i < n; i++) {

            // Check every element before nums[i].
            for (int j = 0; j < i; j++) {

                // If nums[j] is smaller than nums[i],
                // we can add nums[i] after nums[j].
                if (nums[j] < nums[i]) {

                    // Extend the subsequence ending at j.
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // Find the longest subsequence among
        // all possible ending positions.
        int answer = 0;

        for (int length : dp) {
            answer = Math.max(answer, length);
        }

        return answer;
    }
}