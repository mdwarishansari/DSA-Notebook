class Solution {

    public int distinctSubseqII(String s) {

        // The answer can be very large.
        // We need to return it modulo 10^9 + 7.
        final long MOD = 1_000_000_007L;

        /*
         * dp = number of DISTINCT subsequences so far,
         *      INCLUDING the empty subsequence.
         *
         * Initially, before processing any character:
         *
         *     ""
         *
         * So dp = 1.
         */
        long dp = 1;

        /*
         * last[c] stores the value of dp BEFORE the previous
         * occurrence of character c was processed.
         *
         * Why do we need this?
         *
         * When we see the same character again, simply doing
         *
         *     dp * 2
         *
         * creates some subsequences that already existed.
         *
         * last[c] tells us exactly how many old subsequences
         * caused those duplicates.
         *
         * There are only 26 lowercase English letters.
         */
        long[] last = new long[26];

        /*
         * Process every character one by one.
         */
        for (char ch : s.toCharArray()) {

            // Convert character into index: 'a' -> 0, 'b' -> 1, ...
            int index = ch - 'a';

            /*
             * Before processing this character, save the current
             * number of distinct subsequences.
             *
             * We need this value because after updating dp,
             * last[index] must represent the dp value from
             * BEFORE this occurrence.
             */
            long oldDp = dp;

            /*
             * Every existing subsequence gives us TWO choices:
             *
             * 1. Don't take the current character.
             * 2. Take the current character.
             *
             * Therefore:
             *
             *     dp * 2
             *
             * But if this character appeared before, some of the
             * newly created subsequences are duplicates.
             *
             * Those duplicates are exactly last[index].
             */
            dp = (2 * dp - last[index] + MOD) % MOD;

            /*
             * Remember the old dp value for this character.
             *
             * If we see this character again later, this value
             * will be used to remove duplicates.
             */
            last[index] = oldDp;
        }

        /*
         * dp includes the empty subsequence "".
         *
         * The problem asks for NON-EMPTY subsequences,
         * so remove the empty subsequence.
         */
        return (int) ((dp - 1 + MOD) % MOD);
    }
}