import java.util.*;

class Solution {

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        // Sorting is important because:
        // 1. It allows us to use the two-pointer technique.
        // 2. It makes removing duplicate triplets easy.
        Arrays.sort(nums);

        /*
         * Fix the first element at index i.
         *
         * Then use two pointers:
         * left  -> i + 1
         * right -> last index
         *
         * We need:
         * nums[i] + nums[left] + nums[right] == 0
         */
        for (int i = 0; i < nums.length - 2; i++) {

            // Skip duplicate values for the first element.
            //
            // Example:
            // [-1, -1, 0, 1]
            // We only need to process the first -1.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                // We found a valid triplet.
                if (sum == 0) {

                    result.add(Arrays.asList(
                        nums[i],
                        nums[left],
                        nums[right]
                    ));

                    /*
                     * Move both pointers because the current
                     * pair has already been used.
                     */
                    left++;
                    right--;

                    // Skip duplicate left values.
                    while (left < right &&
                           nums[left] == nums[left - 1]) {
                        left++;
                    }

                    // Skip duplicate right values.
                    while (left < right &&
                           nums[right] == nums[right + 1]) {
                        right--;
                    }
                }

                // Sum is too small.
                // We need a larger value.
                else if (sum < 0) {
                    left++;
                }

                // Sum is too large.
                // We need a smaller value.
                else {
                    right--;
                }
            }
        }

        return result;
    }
}