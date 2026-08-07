import java.util.PriorityQueue;

class Solution {

    public int[][] kClosest(int[][] points, int k) {

        // Max Heap
        //
        // Heap stores entire point [x, y]
        //
        // Point with larger distance gets higher priority
        PriorityQueue<int[]> maxHeap =
                new PriorityQueue<>(
                        (a, b) -> {

                            // Distance of first point
                            int distA = a[0] * a[0] + a[1] * a[1];

                            // Distance of second point
                            int distB = b[0] * b[0] + b[1] * b[1];

                            // Larger distance comes first (Max Heap)
                            return distB - distA;
                        }
                );

        // Process every point
        for (int[] point : points) {

            // Add current point
            maxHeap.offer(point);

            // Keep only k closest points
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // Create answer array
        int[][] ans = new int[k][2];

        // Remove points from heap
        for (int i = 0; i < k; i++) {

            ans[i] = maxHeap.poll();
        }

        return ans;
    }
}