class Solution {

    public int smallestIndex(int[] nums) {

        // Check every index from left to right
        for (int i = 0; i < nums.length; i++) {

            int num = nums[i];
            int digitSum = 0;

            // Calculate the sum of digits of nums[i]
            while (num > 0) {

                // Get the last digit
                int digit = num % 10;

                // Add it to digit sum
                digitSum += digit;

                // Remove the last digit
                num = num / 10;
            }

            // If digit sum equals the current index,
            // this is the smallest valid index.
            if (digitSum == i) {
                return i;
            }
        }

        // No valid index found
        return -1;
    }
}