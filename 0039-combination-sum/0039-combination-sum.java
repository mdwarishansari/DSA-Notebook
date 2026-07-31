class Solution {

    // Stores all valid combinations
    List<List<Integer>> result = new ArrayList<>();

    /*
     * index   -> Current candidate we are considering
     * candidates -> Input array
     * target  -> Remaining target to achieve
     * current -> Current combination being built
     */
    private void solve(int index,
                       int[] candidates,
                       int target,
                       List<Integer> current) {

        // ==========================
        // BASE CASE 1
        // ==========================
        // Target becomes exactly zero.
        // We found one valid combination.
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // ==========================
        // BASE CASE 2
        // ==========================
        // Either target becomes negative
        // OR we used all candidates.
        if (target < 0 || index == candidates.length) {
            return;
        }

        // =====================================
        // CHOICE 1 : TAKE current candidate
        // =====================================

        current.add(candidates[index]);

        // Notice:
        // We pass the SAME index because
        // this number can be chosen again.
        solve(index,
              candidates,
              target - candidates[index],
              current);

        // Undo the choice
        current.remove(current.size() - 1);

        // =====================================
        // CHOICE 2 : DON'T TAKE current candidate
        // =====================================

        // Skip this candidate forever
        // Move to next index.
        solve(index + 1,
              candidates,
              target,
              current);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        // Empty combination
        List<Integer> current = new ArrayList<>();

        // Start recursion
        solve(0, candidates, target, current);

        return result;
    }
}