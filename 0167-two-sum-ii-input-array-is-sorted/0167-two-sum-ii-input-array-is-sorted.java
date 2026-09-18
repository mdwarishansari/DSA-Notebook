class Solution {

    public int[] twoSum(int[] numbers, int target) {

        // Left pointer starts at the first element.
        int slow = 0;

        // Right pointer starts at the last element.
        int fast = numbers.length - 1;

        // Continue until the two pointers meet.
        while (slow < fast) {

            // Calculate the sum of the two elements.
            int sum = numbers[slow] + numbers[fast];

            // We found the required pair.
            if (sum == target) {

                // Problem requires 1-based indices,
                // so add 1 to both Java indices.
                return new int[]{slow + 1, fast + 1};
            }

            // If sum is too small, we need a larger value.
            // Since the array is sorted, move left pointer right.
            if (sum < target) {
                slow++;
            }

            // If sum is too large, we need a smaller value.
            // Move right pointer left.
            else {
                fast--;
            }
        }

        // The problem guarantees exactly one solution,
        // so this line should never be reached.
        return new int[]{-1, -1};
    }
}