import java.util.*;

class Solution {

    // This list stores ALL the subsets.
    List<List<Integer>> result = new ArrayList<>();

    /*
     * index   -> Current element we are deciding on.
     * nums    -> Original input array.
     * current -> Subset we are currently building.
     */
    private void solve(int index, int[] nums, List<Integer> current) {

        // ===========================
        // BASE CASE
        // ===========================
        // If we have processed every element,
        // then the current subset is complete.
        if (index == nums.length) {

            // Store a COPY of the current subset.
            result.add(new ArrayList<>(current));

            return;
        }

        // ==================================================
        // CHOICE 1 : TAKE the current element
        // ==================================================

        // Include nums[index] in our subset.
        current.add(nums[index]);

        // Move to the next element.
        solve(index + 1, nums, current);

        // ==================================================
        // BACKTRACK
        // ==================================================
        // Remove the last element so that
        // we can try the "Don't Take" choice.
        current.remove(current.size() - 1);

        // ==================================================
        // CHOICE 2 : DON'T TAKE the current element
        // ==================================================

        // Move to the next element WITHOUT adding it.
        solve(index + 1, nums, current);
    }

    public List<List<Integer>> subsets(int[] nums) {

        // Empty subset at the beginning.
        List<Integer> current = new ArrayList<>();

        // Start recursion from index 0.
        solve(0, nums, current);

        return result;
    }
}