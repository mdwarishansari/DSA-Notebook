/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) {
 *         this.val = val;
 *     }
 *     ListNode(int val, ListNode next) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */

class Solution {

    public int[] nodesBetweenCriticalPoints(ListNode head) {

        // We need at least 3 nodes to have a critical point.
        // Example:
        //     previous -> current -> next
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        /*
         * We will maintain three pointers:
         *
         * previous -> current -> next
         *
         * This allows us to determine whether 'current'
         * is a local minimum or local maximum.
         */
        ListNode previous = head;
        ListNode current = head.next;

        // The first node has index 0.
        // We are currently at index 1.
        int index = 1;

        /*
         * Index of the first critical point we encounter.
         *
         * Initially -1 means:
         * "We haven't found any critical point yet."
         */
        int firstCriticalIndex = -1;

        /*
         * Index of the most recently found critical point.
         *
         * We need this to calculate the distance between
         * consecutive critical points.
         */
        int previousCriticalIndex = -1;

        /*
         * Start with the largest possible integer because
         * we want to find the minimum distance.
         */
        int minDistance = Integer.MAX_VALUE;

        /*
         * We can initialize maxDistance to -1.
         * If we never find two critical points,
         * it will remain -1.
         */
        int maxDistance = -1;


        /*
         * We stop when current becomes the last node.
         *
         * Why?
         *
         * The last node cannot be a critical point because
         * it doesn't have a next node.
         */
        while (current.next != null) {

            // Get the next node so that we can compare
            // previous, current and next.
            ListNode next = current.next;

            /*
             * A node is a critical point if it is either:
             *
             * 1. Local maximum:
             *      previous < current > next
             *
             * OR
             *
             * 2. Local minimum:
             *      previous > current < next
             */
            boolean isCriticalPoint =
                    (current.val > previous.val && current.val > next.val)
                    ||
                    (current.val < previous.val && current.val < next.val);


            // If current node is a critical point...
            if (isCriticalPoint) {

                /*
                 * If this is the FIRST critical point,
                 * simply store its index.
                 */
                if (firstCriticalIndex == -1) {

                    firstCriticalIndex = index;
                    previousCriticalIndex = index;

                } else {

                    /*
                     * We already found at least one critical point.
                     *
                     * Calculate distance between:
                     *
                     * previous critical point
                     *             and
                     * current critical point
                     */
                    int distance = index - previousCriticalIndex;

                    // Update minimum distance.
                    minDistance = Math.min(minDistance, distance);

                    /*
                     * The distance from the FIRST critical point
                     * to the CURRENT critical point can give us
                     * the maximum distance.
                     */
                    maxDistance = index - firstCriticalIndex;

                    /*
                     * Current critical point now becomes the
                     * previous critical point for the next iteration.
                     */
                    previousCriticalIndex = index;
                }
            }

            /*
             * Move the three-node window forward.
             *
             * Before:
             *
             * previous -> current -> next
             *
             * After:
             *
             * previous
             *     ↓
             * current -> next
             */
            previous = current;
            current = next;
            index++;
        }

        /*
         * If fewer than two critical points were found,
         * we cannot calculate a distance.
         *
         * minDistance would still be Integer.MAX_VALUE.
         */
        if (minDistance == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        }

        return new int[]{minDistance, maxDistance};
    }
}