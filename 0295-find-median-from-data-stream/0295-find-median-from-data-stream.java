import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {

    // Max Heap
    // Stores the smaller half of numbers
    private PriorityQueue<Integer> maxHeap;

    // Min Heap
    // Stores the larger half of numbers
    private PriorityQueue<Integer> minHeap;

    public MedianFinder() {

        // Max Heap
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Min Heap
        minHeap = new PriorityQueue<>();
    }

    // Add a new number
    public void addNum(int num) {

        // If Max Heap is empty
        // OR current number belongs to smaller half
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {

            maxHeap.offer(num);

        } else {

            minHeap.offer(num);
        }

        // ---------------- Balance Heaps ----------------

        // Max Heap should never have
        // more than one extra element
        if (maxHeap.size() > minHeap.size() + 1) {

            minHeap.offer(maxHeap.poll());
        }

        // Min Heap became larger
        else if (minHeap.size() > maxHeap.size()) {

            maxHeap.offer(minHeap.poll());
        }
    }

    // Return current median
    public double findMedian() {

        // Even number of elements
        if (maxHeap.size() == minHeap.size()) {

            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }

        // Odd number of elements
        return maxHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */