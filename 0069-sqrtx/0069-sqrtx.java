class Solution {

    public int mySqrt(int x) {

        // 0 and 1 are their own square roots
        if (x < 2) {
            return x;
        }

        // Possible square root lies between 1 and x
        int left = 1;
        int right = x;

        // Stores the largest valid square root found so far
        int answer = 0;

        while (left <= right) {

            // Find middle safely
            int mid = left + (right - left) / 2;

            // Use long to prevent overflow when calculating mid * mid
            long square = (long) mid * mid;

            if (square == x) {
                // Exact square root found
                return mid;
            }

            else if (square < x) {
                // mid is a valid answer.
                // But there may be a larger valid value.
                answer = mid;
                left = mid + 1;
            }

            else {
                // mid is too large, so search the left half
                right = mid - 1;
            }
        }

        // Returns floor(sqrt(x))
        return answer;
    }
}