class Solution {

    public int splitArray(int[] nums, int k) {

        // The minimum possible answer is the largest
        // element in the array.
        // We cannot make a subarray sum smaller than
        // its largest individual element.
        int low = 0;

        // The maximum possible answer is the sum of
        // the entire array if we use only one subarray.
        int high = 0;

        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }

        // Stores the best minimum largest sum found.
        int answer = high;

        // Binary search for the minimum possible
        // value of the largest subarray sum.
        while (low <= high) {

            // Try the middle value as the maximum
            // allowed sum for any subarray.
            int mid = low + (high - low) / 2;

            // Check whether we can split the array into
            // at most k subarrays with each sum <= mid.
            if (canSplit(nums, k, mid)) {

                // mid is possible, so it can be our answer.
                answer = mid;

                // Try to find an even smaller maximum sum.
                high = mid - 1;

            } else {

                // mid is too small, so we need to allow
                // a larger maximum subarray sum.
                low = mid + 1;
            }
        }

        return answer;
    }

    // Checks whether the array can be divided into
    // at most k subarrays where each subarray's sum
    // is no greater than 'limit'.
    private boolean canSplit(int[] nums, int k, int limit) {

        // Start with one subarray.
        int subarrays = 1;

        // Sum of the current subarray.
        int currentSum = 0;

        // Process every element.
        for (int num : nums) {

            // If adding this number would make the
            // current subarray exceed the limit,
            // we must start a new subarray.
            if (currentSum + num > limit) {

                subarrays++;

                // Start the new subarray with this number.
                currentSum = num;

                // If we already need more than k subarrays,
                // this limit cannot work.
                if (subarrays > k) {
                    return false;
                }

            } else {

                // Otherwise, add the number to the
                // current subarray.
                currentSum += num;
            }
        }

        // We successfully divided the array into
        // at most k subarrays.
        return true;
    }
}