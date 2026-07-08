class Solution {

    public int[] searchRange(int[] nums, int target) {

        // Find the first occurrence of target
        int first = findFirst(nums, target);

        // Find the last occurrence of target
        int last = findLast(nums, target);

        // Return both indices
        return new int[]{first, last};
    }

    /*
     * Finds the FIRST (leftmost) occurrence of target.
     */
    private int findFirst(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        // Stores the first occurrence found so far
        int ans = -1;

        while (left <= right) {

            // Calculate middle safely
            int mid = left + (right - left) / 2;

            // If target is found OR middle is greater,
            // search in the left half.
            if (nums[mid] >= target) {

                // Save answer if target is found
                if (nums[mid] == target) {
                    ans = mid;
                }

                // Continue searching left
                right = mid - 1;
            }

            // Target must be on the right
            else {
                left = mid + 1;
            }
        }

        return ans;
    }

    /*
     * Finds the LAST (rightmost) occurrence of target.
     */
    private int findLast(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        // Stores the last occurrence found so far
        int ans = -1;

        while (left <= right) {

            // Calculate middle safely
            int mid = left + (right - left) / 2;

            // If target is found OR middle is smaller,
            // search in the right half.
            if (nums[mid] <= target) {

                // Save answer if target is found
                if (nums[mid] == target) {
                    ans = mid;
                }

                // Continue searching right
                left = mid + 1;
            }

            // Target must be on the left
            else {
                right = mid - 1;
            }
        }

        return ans;
    }
}