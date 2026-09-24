class Solution {

    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;

        int[] answer = new int[n];

        // ------------------------------------------
        // First pass:
        // Store product of everything to the LEFT
        // of each index.
        // ------------------------------------------

        int leftProduct = 1;

        for (int i = 0; i < n; i++) {

            // Everything before i
            answer[i] = leftProduct;

            // Include nums[i] for the next index
            leftProduct *= nums[i];
        }

        // ------------------------------------------
        // Second pass:
        // Store product of everything to the RIGHT
        // and multiply it with the left product.
        // ------------------------------------------

        int rightProduct = 1;

        for (int i = n - 1; i >= 0; i--) {

            // answer[i] already contains
            // product of everything on the left.
            //
            // Multiply it by product of everything
            // on the right.
            answer[i] *= rightProduct;

            // Include nums[i] for the next index
            rightProduct *= nums[i];
        }

        return answer;
    }
}