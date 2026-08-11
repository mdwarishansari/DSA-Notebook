import java.util.*;

class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // graph[i] contains all courses that depend on course i.
        List<List<Integer>> graph = new ArrayList<>();

        // Create an empty list for every course.
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // indegree[i] = number of prerequisites required by course i.
        int[] indegree = new int[numCourses];

        // Build the graph and calculate indegrees.
        for (int[] pair : prerequisites) {

            // pair[0] = course we want to take.
            int course = pair[0];

            // pair[1] = course that must be completed first.
            int prerequisite = pair[1];

            // Create the dependency:
            // prerequisite -> course
            graph.get(prerequisite).add(course);

            // course has one more prerequisite.
            indegree[course]++;
        }

        // Queue contains courses that currently have
        // no remaining prerequisites.
        Queue<Integer> queue = new LinkedList<>();

        // Add all courses that can be taken immediately.
        for (int course = 0; course < numCourses; course++) {

            if (indegree[course] == 0) {
                queue.offer(course);
            }
        }

        // Store the valid course ordering.
        int[] result = new int[numCourses];

        // Keeps track of where to insert the next course.
        int index = 0;

        // Process courses using BFS.
        while (!queue.isEmpty()) {

            // Take a course whose prerequisites are completed.
            int currentCourse = queue.poll();

            // Add this course to the final ordering.
            result[index] = currentCourse;
            index++;

            // Process all courses depending on currentCourse.
            for (int nextCourse : graph.get(currentCourse)) {

                // currentCourse is now completed,
                // so one prerequisite of nextCourse is satisfied.
                indegree[nextCourse]--;

                // If all prerequisites are completed,
                // this course is now available.
                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        /*
         * If we could not place all courses into result,
         * some courses are stuck because of a cycle.
         *
         * Example:
         * 0 -> 1
         * 1 -> 0
         *
         * Neither course can become indegree 0.
         */
        if (index != numCourses) {
            return new int[0];
        }

        // Every course was successfully ordered.
        return result;
    }
}