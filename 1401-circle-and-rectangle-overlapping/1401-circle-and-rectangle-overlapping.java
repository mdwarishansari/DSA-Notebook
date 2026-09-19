class Solution {

    public boolean checkOverlap(
            int radius,
            int xCenter,
            int yCenter,
            int x1,
            int y1,
            int x2,
            int y2) {

        /*
         * Find the closest x-coordinate of the rectangle
         * to the circle's center.
         *
         * If xCenter is already inside [x1, x2],
         * then xCenter itself is the closest x.
         *
         * Otherwise, the closest point is one of the
         * rectangle's vertical boundaries: x1 or x2.
         */
        int closestX = Math.max(x1, Math.min(xCenter, x2));

        /*
         * Same logic for the y-coordinate.
         *
         * closestY will be the y-coordinate on/in the
         * rectangle that is closest to the circle's center.
         */
        int closestY = Math.max(y1, Math.min(yCenter, y2));

        /*
         * Calculate the squared distance between:
         *
         *     Circle center = (xCenter, yCenter)
         *     Closest point = (closestX, closestY)
         *
         * We use long to avoid integer overflow.
         */
        long dx = (long) xCenter - closestX;
        long dy = (long) yCenter - closestY;

        long distanceSquared = dx * dx + dy * dy;

        /*
         * Circle equation:
         *
         *     distance <= radius
         *
         * Instead of calculating sqrt(distanceSquared),
         * compare the squared values:
         *
         *     distanceSquared <= radius²
         *
         * <= is important because touching the rectangle
         * at exactly one point also counts as overlap.
         */
        long radiusSquared = (long) radius * radius;

        return distanceSquared <= radiusSquared;
    }
}