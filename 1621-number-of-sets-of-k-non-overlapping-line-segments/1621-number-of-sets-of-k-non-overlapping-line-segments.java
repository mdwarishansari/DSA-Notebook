class Solution {

    // Modulo value given in the problem
    private static final long MOD = 1_000_000_007L;

    public int numberOfSets(int n, int k) {

        /*
         * Key observation:
         *
         * We need to choose 2*k endpoints for k segments.
         *
         * Because segments cannot overlap and each segment must contain
         * at least two points, the problem can be transformed into:
         *
         *     Answer = C(n + k - 1, 2*k)
         *
         * Example:
         * n = 4, k = 2
         *
         * C(4 + 2 - 1, 4)
         * = C(5, 4)
         * = 5
         *
         * Example:
         * n = 3, k = 1
         *
         * C(3 + 1 - 1, 2)
         * = C(3, 2)
         * = 3
         */

        int N = n + k - 1;
        int R = 2 * k;

        /*
         * We calculate:
         *
         *        N!
         * C(N,R) = ---------
         *        R!(N-R)!
         *
         * Since the answer is modulo 1e9+7, we cannot directly
         * calculate factorials because they become extremely large.
         *
         * Instead:
         *
         * C(N,R) = N! * inverse(R!) * inverse((N-R)!)
         *
         * Fermat's Little Theorem:
         *
         *     a^(MOD-2) % MOD
         *
         * gives the modular inverse of a because MOD is prime.
         */

        long[] factorial = new long[N + 1];

        // Calculate factorials modulo MOD
        factorial[0] = 1;

        for (int i = 1; i <= N; i++) {
            factorial[i] = factorial[i - 1] * i % MOD;
        }

        // N!
        long numerator = factorial[N];

        // R!
        long denominator1 = factorial[R];

        // (N-R)!
        long denominator2 = factorial[N - R];

        /*
         * Modular inverses of R! and (N-R)!
         */
        long inverseDenominator1 = modPow(denominator1, MOD - 2);
        long inverseDenominator2 = modPow(denominator2, MOD - 2);

        /*
         * C(N,R)
         */
        long answer = numerator;

        answer = answer * inverseDenominator1 % MOD;
        answer = answer * inverseDenominator2 % MOD;

        return (int) answer;
    }

    /*
     * Fast Modular Exponentiation
     *
     * Calculates:
     *     base^exponent % MOD
     *
     * in O(log exponent) time.
     */
    private long modPow(long base, long exponent) {

        long result = 1;

        while (exponent > 0) {

            // If exponent is odd, multiply result by base
            if ((exponent & 1) == 1) {
                result = result * base % MOD;
            }

            // Square the base
            base = base * base % MOD;

            // Divide exponent by 2
            exponent >>= 1;
        }

        return result;
    }
}