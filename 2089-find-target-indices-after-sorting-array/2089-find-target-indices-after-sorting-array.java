
import java.util.*;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {

        // Step 1: Sort the array in ascending order.
        Arrays.sort(nums);

        // Step 2: Create a list to store target indices.
        List<Integer> ans = new ArrayList<>();

        // Step 3: Traverse the sorted array.
        for (int i = 0; i < nums.length; i++) {

            // Step 4: Check whether the current element is the target.
            if (nums[i] == target) {

                // Store the current index if the target is found.
                ans.add(i);
            }
        }

        // Step 5: Return all target indices in increasing order.
        return ans;
    }
}
