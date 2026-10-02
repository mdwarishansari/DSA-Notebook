class Solution {

    public int[] searchRange(int[] nums, int target) {

        // Find the first occurrence of target
        int first = findFirst(nums, target);

        // Find the last occurrence of target
        int last = findLast(nums, target);

        return new int[] { first, last };
    }

    // ---------------------------------------------------------
    // Finds the FIRST occurrence of target
    // ---------------------------------------------------------
    private int findFirst(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        // Stores the best answer found so far
        int answer = -1;

        while (left <= right) {

            // Avoids possible integer overflow
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {

                // We found target.
                // Store this index as a possible first position.
                answer = mid;

                // But there might be another target on the LEFT.
                // So continue searching in the left half.
                right = mid - 1;

            } 
            else if (nums[mid] < target) {

                // Target is greater than nums[mid],
                // so search on the RIGHT side.
                left = mid + 1;

            } 
            else {

                // Target is smaller than nums[mid],
                // so search on the LEFT side.
                right = mid - 1;
            }
        }

        return answer;
    }

    // ---------------------------------------------------------
    // Finds the LAST occurrence of target
    // ---------------------------------------------------------
    private int findLast(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        // Stores the best answer found so far
        int answer = -1;

        while (left <= right) {

            // Avoids possible integer overflow
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {

                // We found target.
                // Store this index as a possible last position.
                answer = mid;

                // But there might be another target on the RIGHT.
                // So continue searching in the right half.
                left = mid + 1;

            } 
            else if (nums[mid] < target) {

                // Target is greater than nums[mid],
                // so search on the RIGHT side.
                left = mid + 1;

            } 
            else {

                // Target is smaller than nums[mid],
                // so search on the LEFT side.
                right = mid - 1;
            }
        }

        return answer;
    }
}