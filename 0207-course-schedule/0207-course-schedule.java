import java.util.*;

class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // graph[i] contains all courses that depend on course i.
        List<List<Integer>> graph = new ArrayList<>();

        // Create an empty adjacency list for every course.
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // indegree[i] tells us how many prerequisites
        // course i still needs before we can take it.
        int[] indegree = new int[numCourses];

        // Build the graph and calculate indegrees.
        for (int[] pair : prerequisites) {

            // pair[0] = course we want to take.
            int course = pair[0];

            // pair[1] = prerequisite course.
            int prerequisite = pair[1];

            // prerequisite -> course
            // We can take 'course' after completing 'prerequisite'.
            graph.get(prerequisite).add(course);

            // course now has one prerequisite.
            indegree[course]++;
        }

        // Store courses that currently have no prerequisites.
        Queue<Integer> queue = new LinkedList<>();

        // Find all courses that can be taken immediately.
        for (int course = 0; course < numCourses; course++) {

            if (indegree[course] == 0) {
                queue.offer(course);
            }
        }

        // Count how many courses we successfully complete.
        int completedCourses = 0;

        // Process available courses using BFS.
        while (!queue.isEmpty()) {

            // Take one available course.
            int currentCourse = queue.poll();

            // We successfully completed this course.
            completedCourses++;

            // Find all courses that depend on currentCourse.
            for (int nextCourse : graph.get(currentCourse)) {

                // Since currentCourse is completed,
                // one prerequisite of nextCourse is now satisfied.
                indegree[nextCourse]--;

                // If no prerequisites remain,
                // nextCourse is now available.
                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // If every course was completed, there is no cycle.
        // If some courses could not be completed, a cycle exists.
        return completedCourses == numCourses;
    }
}