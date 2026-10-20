
import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();

        backtrack(0, nums, cur, ans);

        return ans;
    }

    // Generate all subsets starting from index 'start'
    private void backtrack(int start, int[] nums,
                           List<Integer> cur,
                           List<List<Integer>> ans) {

        // Save a copy of the current subset
        ans.add(new ArrayList<>(cur));

        // Try choosing each remaining element
        for (int i = start; i < nums.length; i++) {

            // Include the current element
            cur.add(nums[i]);

            // Explore subsets containing this element
            backtrack(i + 1, nums, cur, ans);

            // Remove it to explore other possibilities
            cur.remove(cur.size() - 1);
        }
    }
}
