
class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        // Number of tickets required by the target person.
        int target = tickets[k];

        // Total time required for the target to finish.
        int time = 0;

        for (int i = 0; i < tickets.length; i++) {

            if (i <= k) {
                // People before or at k get a turn during
                // the target's final second.
                time += Math.min(tickets[i], target);
            } else {
                // People after k do not get a turn during
                // the target's final second.
                time += Math.min(tickets[i], target - 1);
            }
        }

        return time;
    }
}
