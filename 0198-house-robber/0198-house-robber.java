class Solution {

    public int rob(int[] nums) {

        // Maximum money we can rob up to two positions ago.
        int prev2 = 0;

        // Maximum money we can rob from the previous house.
        int prev1 = 0;

        // Process every house.
        for (int money : nums) {

            // Option 1: Skip the current house
            //            -> keep prev1.
            //
            // Option 2: Rob the current house
            //            -> add its money to prev2 because
            //               we cannot rob the adjacent house.
            int current = Math.max(prev1, prev2 + money);

            // Move the previous values forward.
            prev2 = prev1;
            prev1 = current;
        }

        // prev1 contains the maximum money we can rob.
        return prev1;
    }
}