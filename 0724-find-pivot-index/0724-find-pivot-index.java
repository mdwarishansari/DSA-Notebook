class Solution {

    public int pivotIndex(int[] nums) {

        // First calculate the sum of the entire array.
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        // This represents the sum of elements
        // strictly to the left of the current index.
        int leftSum = 0;

        // Check every index as a possible pivot.
        for (int i = 0; i < nums.length; i++) {

            // Total sum consists of:
            //
            // leftSum + nums[i] + rightSum
            //
            // Therefore:
            // rightSum = totalSum - leftSum - nums[i]
            int rightSum = totalSum - leftSum - nums[i];

            // If both sides have the same sum,
            // we found the pivot index.
            if (leftSum == rightSum) {
                return i;
            }

            // Add the current element to leftSum
            // before moving to the next index.
            leftSum += nums[i];
        }

        // No pivot index exists.
        return -1;
    }
}