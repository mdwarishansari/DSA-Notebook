
class Solution {
    public double myPow(double x, int n) {
        long exp = n;

        // Convert the exponent to a positive long safely.
        if (exp < 0) {
            exp = -exp;
        }

        double ans = 1.0;
        double base = x;

        // Binary exponentiation.
        while (exp > 0) {
            if ((exp & 1) == 1) {
                ans *= base;
            }

            base *= base;
            exp >>= 1;
        }

        // Take reciprocal only after calculating the power.
        return n < 0 ? 1.0 / ans : ans;
    }
}
