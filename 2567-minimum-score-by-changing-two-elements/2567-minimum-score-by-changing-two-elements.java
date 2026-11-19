import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        
        // Edge case: If there are 3 or fewer elements, we can change 2 of them
        // to match the remaining element, making all elements identical.
        // Both low score (min diff) and high score (max diff) become 0.
        if (n <= 3) {
            return 0;
        }

        // Sort the array in ascending order to easily identify extreme values
        Arrays.sort(nums);

        // Key Insight:
        // 1. We can ALWAYS force the 'low' score (minimum absolute difference) to be 0
        //    by setting the two modified elements equal to any existing element in the array.
        // 2. To minimize the 'high' score (max - min), we only need to consider removing
        //    extremes from the sorted array (since modifying interior elements does not shrink
        //    the overall range [min, max]).
        //
        // There are 3 optimal ways to remove/modify 2 extreme elements:

        // Option 1: Change the 2 largest elements to match nums[n - 3] (or any valid interior value).
        // New range becomes [nums[0], nums[n - 3]].
        int option1 = nums[n - 3] - nums[0];

        // Option 2: Change the 2 smallest elements to match nums[2].
        // New range becomes [nums[2], nums[n - 1]].
        int option2 = nums[n - 1] - nums[2];

        // Option 3: Change the 1 smallest element and 1 largest element to match interior values.
        // New range becomes [nums[1], nums[n - 2]].
        int option3 = nums[n - 2] - nums[1];

        // Return the minimum high score among the three choices (since low score is 0)
        return Math.min(option1, Math.min(option2, option3));
    }
}