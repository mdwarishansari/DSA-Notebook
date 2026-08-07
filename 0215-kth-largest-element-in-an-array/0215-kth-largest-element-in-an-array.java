import java.util.PriorityQueue;

class Solution {

    public int findKthLargest(int[] nums, int k) {

        // Min Heap
        // The smallest element among the current k largest elements
        // will always remain at the top.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Process every number in the array
        for (int num : nums) {

            // Add current number into the heap
            minHeap.offer(num);

            // If heap size becomes greater than k,
            // remove the smallest element.
            // This keeps only the k largest elements.
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // After processing all elements,
        // the heap contains exactly the k largest elements.
        // The smallest among them (heap top)
        // is the kth largest element.
        return minHeap.peek();
    }
}