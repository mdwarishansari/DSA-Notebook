class Solution {
    public int singleNumber(int[] nums) {

        // This will store the final unique number.
        int result = 0;

        // An integer has 32 bits.
        for (int bit = 0; bit < 32; bit++) {

            // Count how many numbers have the current bit set.
            int count = 0;

            for (int num : nums) {

                // Check whether the current bit is set.
                // Example: if bit = 2, we check the 3rd bit.
                if ((num & (1 << bit)) != 0) {
                    count++;
                }
            }

            // Every repeated number appears 3 times,
            // so its bits contribute a multiple of 3.
            //
            // If remainder is 1, this bit belongs to
            // the number that appears only once.
            if (count % 3 != 0) {
                result |= (1 << bit);
            }
        }

        return result;
    }
}