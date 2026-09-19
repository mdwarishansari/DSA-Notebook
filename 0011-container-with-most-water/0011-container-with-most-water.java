class Solution {

    public int maxArea(int[] height) {

        // Two pointers:
        // i starts from the left end of the array.
        // j starts from the right end of the array.
        int i = 0;
        int j = height.length - 1;

        // Stores the maximum area found so far.
        int maxArea = 0;

        // Continue until the two pointers meet.
        while (i < j) {

            /*
             * Width of the container:
             * The distance between the two vertical lines.
             */
            int width = j - i;

            /*
             * The amount of water is limited by the
             * shorter of the two vertical lines.
             *
             * Example:
             * height[i] = 8
             * height[j] = 5
             *
             * Water height = 5, not 8.
             */
            int waterHeight = Math.min(height[i], height[j]);

            // Calculate the current container's area.
            int currentArea = width * waterHeight;

            // Update maximum area if the current area is larger.
            maxArea = Math.max(maxArea, currentArea);

            /*
             * Move the pointer pointing to the shorter line.
             *
             * Why?
             * The shorter line is limiting our water height.
             * Moving the taller line would only decrease the width
             * without giving us a chance to increase the limiting height.
             */
            if (height[i] < height[j]) {
                i++;       // Move left pointer to the right.
            } else {
                j--;       // Move right pointer to the left.
            }
        }

        // Return the largest area found.
        return maxArea;
    }
}