
import java.util.Stack;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {

        // Store the asteroids that survive all collisions.
        Stack<Integer> stack = new Stack<>();

        // Process asteroids from left to right.
        for (int asteroid : asteroids) {

            // Assume the current asteroid survives unless
            // a collision destroys it.
            boolean alive = true;

            // A collision occurs only when:
            // 1. The previous asteroid moves right (positive).
            // 2. The current asteroid moves left (negative).
            while (alive && asteroid < 0
                    && !stack.isEmpty()
                    && stack.peek() > 0) {

                // Get the size of the asteroid moving right.
                int previous = stack.peek();

                if (previous < -asteroid) {
                    // The previous asteroid is smaller.
                    // It explodes, and the current asteroid
                    // may collide with another asteroid.
                    stack.pop();

                } else if (previous == -asteroid) {
                    // Both asteroids have equal size.
                    // Both explode.
                    stack.pop();
                    alive = false;

                } else {
                    // The previous asteroid is larger.
                    // The current asteroid explodes.
                    alive = false;
                }
            }

            // Add the current asteroid only if it survives.
            if (alive) {
                stack.push(asteroid);
            }
        }

        // Convert the surviving asteroids into the required array.
        int[] result = new int[stack.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = stack.get(i);
        }

        return result;
    }
}
