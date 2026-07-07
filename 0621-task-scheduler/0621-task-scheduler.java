import java.util.*;

class Solution {
    public int leastInterval(char[] tasks, int n) {

        // Frequency array for tasks A-Z
        int[] freq = new int[26];

        // Count frequency of each task
        for (char task : tasks) {
            freq[task - 'A']++;
        }

        // Max Heap
        // Stores task frequencies in descending order
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // Add all non-zero frequencies to the heap
        for (int f : freq) {
            if (f > 0) {
                pq.offer(f);
            }
        }

        // Total CPU intervals
        int time = 0;

        // Continue until all tasks are completed
        while (!pq.isEmpty()) {

            // Stores tasks executed in the current cycle
            // that still have remaining frequency
            List<Integer> temp = new ArrayList<>();

            /*
                One cycle = n + 1 intervals

                Example:
                n = 2

                Cycle:

                A B idle

                OR

                A B C
            */

            // Execute at most (n + 1) tasks in one cycle
            for (int i = 1; i <= n + 1; i++) {

                // If no task is available, skip
                if (!pq.isEmpty()) {

                    // Take the task with highest remaining frequency
                    int currentFreq = pq.poll();

                    // Execute it once
                    currentFreq--;

                    // If still remaining,
                    // save it to add back later
                    temp.add(currentFreq);
                }
            }

            // Put unfinished tasks back into heap
            for (int remaining : temp) {

                if (remaining > 0) {
                    pq.offer(remaining);
                }
            }

            /*
                If heap is empty,
                we don't need idle time anymore.

                Only count the tasks executed
                in the last cycle.
            */

            if (pq.isEmpty()) {

                // Last cycle
                time += temp.size();

            } else {

                // Full cycle (tasks + idle)
                time += n + 1;
            }
        }

        return time;
    }
}