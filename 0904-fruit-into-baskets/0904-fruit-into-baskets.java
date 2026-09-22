import java.util.HashMap;
import java.util.Map;

class Solution {

    public int totalFruit(int[] fruits) {

        /*
         * We can have only TWO baskets.
         *
         * Each basket can contain only ONE type of fruit.
         *
         * Therefore our sliding window can contain
         * at most 2 distinct fruit types.
         */

        Map<Integer, Integer> frequency = new HashMap<>();

        int left = 0;

        // Stores the maximum valid window length found so far.
        int maxLength = 0;

        /*
         * right expands the sliding window.
         */
        for (int right = 0; right < fruits.length; right++) {

            int fruit = fruits[right];

            /*
             * Add the current fruit to the window.
             *
             * If it was not present before, this introduces
             * a new distinct fruit type.
             */
            frequency.put(
                fruit,
                frequency.getOrDefault(fruit, 0) + 1
            );


            /*
             * We are allowed to have at most 2 different
             * fruit types.
             *
             * If we have 3 or more, shrink from the left.
             */
            while (frequency.size() > 2) {

                int leftFruit = fruits[left];

                /*
                 * Remove one occurrence of the fruit at left.
                 */
                frequency.put(
                    leftFruit,
                    frequency.get(leftFruit) - 1
                );

                /*
                 * If its frequency becomes zero,
                 * this fruit type no longer exists
                 * inside our current window.
                 */
                if (frequency.get(leftFruit) == 0) {
                    frequency.remove(leftFruit);
                }

                /*
                 * Move the left boundary forward.
                 */
                left++;
            }


            /*
             * Now the window is guaranteed to contain
             * at most 2 distinct fruit types.
             *
             * Current window:
             *
             * [left ........ right]
             *
             * Its length is:
             *
             * right - left + 1
             */
            int currentLength = right - left + 1;

            /*
             * We want the LONGEST valid window.
             */
            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }
}