class Solution {

    // Stores all the permutations
    List<List<Integer>> result = new ArrayList<>();

    // Stores the current permutation being built
    List<Integer> temp = new ArrayList<>();

    // Keeps track of which indices have already been used
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {

        // Create used array of same size as nums
        // Initially every value is false
        used = new boolean[nums.length];

        // Start recursion
        solve(nums);

        return result;
    }

    private void solve(int[] nums) {

        // ==========================
        // BASE CASE
        // ==========================
        // If current permutation contains all numbers,
        // store it into answer.
        if (temp.size() == nums.length) {

            // Make a copy because temp will change later
            result.add(new ArrayList<>(temp));

            return;
        }

        // ==========================
        // TRY EVERY NUMBER
        // ==========================
        for (int i = 0; i < nums.length; i++) {

            // If current number is already used,
            // skip it.
            if (used[i]) {
                continue;
            }

            // ==========================
            // CHOOSE
            // ==========================

            // Put current number into permutation
            temp.add(nums[i]);

            // Mark as used
            used[i] = true;

            // ==========================
            // EXPLORE
            // ==========================

            solve(nums);

            // ==========================
            // BACKTRACK (UNDO)
            // ==========================

            // Remove last inserted number
            temp.remove(temp.size() - 1);

            // Make it available again
            used[i] = false;
        }
    }
}