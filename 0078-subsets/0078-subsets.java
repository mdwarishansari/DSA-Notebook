import java.util.*;

class Solution {

    public List<List<Integer>> subsets(int[] nums) {

        // Final answer list
        List<List<Integer>> ans = new ArrayList<>();

        // Start recursion from index 0 with an empty subset
        helper(0, nums, new ArrayList<>(), ans);

        return ans;
    }

    /*
     * index   -> Current position in the array
     * nums    -> Input array
     * current -> Current subset being built
     * ans     -> Stores all subsets
     */
    private void helper(int index,
                        int[] nums,
                        List<Integer> current,
                        List<List<Integer>> ans) {

        // Store a COPY of the current subset.
        // We must create a new list because 'current'
        // will keep changing during backtracking.
        ans.add(new ArrayList<>(current));

        // Try every remaining element
        for (int i = index; i < nums.length; i++) {

            // -----------------------------
            // CHOOSE
            // Include nums[i] in the subset
            // -----------------------------
            current.add(nums[i]);

            // ----------------------------------------
            // EXPLORE
            // Move to the next index because
            // each element can be used only once
            // ----------------------------------------
            helper(i + 1, nums, current, ans);

            // -----------------------------
            // BACKTRACK
            // Remove the last chosen element
            // so we can try another choice
            // -----------------------------
            current.remove(current.size() - 1);
        }
    }
}