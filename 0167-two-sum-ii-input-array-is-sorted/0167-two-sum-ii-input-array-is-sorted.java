class Solution {

    public int[] twoSum(int[] numbers, int target) {

        /*
         * Since the array is already sorted in non-decreasing order,
         * we can use the Two Pointer approach.
         *
         * left  -> starts from the beginning
         * right -> starts from the end
         */
        int left = 0;
        int right = numbers.length - 1;

        /*
         * Keep moving the two pointers until they meet.
         */
        while (left < right) {

            /*
             * Calculate the sum of the elements
             * pointed to by left and right.
             */
            int sum = numbers[left] + numbers[right];

            /*
             * Case 1:
             * We found the required pair.
             *
             * The problem requires 1-based indices,
             * so we return left + 1 and right + 1.
             */
            if (sum == target) {
                return new int[] { left + 1, right + 1 };
            }

            /*
             * Case 2:
             * The sum is smaller than the target.
             *
             * Because the array is sorted, increasing 'left'
             * will increase the sum.
             *
             * Therefore, move left forward.
             */
            if (sum < target) {
                left++;
            }

            /*
             * Case 3:
             * The sum is greater than the target.
             *
             * Because the array is sorted, decreasing 'right'
             * will decrease the sum.
             *
             * Therefore, move right backward.
             */
            else {
                right--;
            }
        }

        /*
         * The problem guarantees exactly one solution,
         * so normally this line will never execute.
         *
         * It is included as a fallback.
         */
        return new int[] { -1, -1 };
    }
}