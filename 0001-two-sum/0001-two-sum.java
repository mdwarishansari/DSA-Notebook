import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {

        // Map to store number -> index
        HashMap<Integer, Integer> map = new HashMap<>();

        // Loop through array
        for (int i = 0; i < nums.length; i++) {

            int currentNumber = nums[i];

            // Find what number we need to reach target
            int neededNumber = target - currentNumber;

            // Check if we already saw the needed number
            if (map.containsKey(neededNumber)) {

                // Return indices of both numbers
                return new int[] { map.get(neededNumber), i };
            }

            // Store current number and its index
            map.put(currentNumber, i);
        }

        // If no solution found (just safety)
        return new int[] {-1, -1};
    }
}