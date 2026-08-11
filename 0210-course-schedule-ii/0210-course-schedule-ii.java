import java.util.*;

class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // graph[i] contains all courses that depend on course i.
        List<List<Integer>> graph = new ArrayList<>();

        // Create an empty adjacency list for every course.
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Build the directed graph.
        for (int[] pair : prerequisites) {

            // pair[0] = course we want to take.
            int course = pair[0];

            // pair[1] = prerequisite course.
            int prerequisite = pair[1];

            // Create dependency:
            // prerequisite -> course
            graph.get(prerequisite).add(course);
        }

        /*
         * visited[i] represents the state of course i:
         *
         * 0 = not visited
         * 1 = currently being visited
         * 2 = completely visited
         */
        int[] visited = new int[numCourses];

        // Stores the courses in DFS finishing order.
        List<Integer> result = new ArrayList<>();

        // Run DFS from every course.
        for (int course = 0; course < numCourses; course++) {

            // If a cycle is found, no valid ordering exists.
            if (hasCycle(course, graph, visited, result)) {
                return new int[0];
            }
        }

        // DFS gives the ordering in reverse.
        Collections.reverse(result);

        // Convert ArrayList<Integer> to int[].
        int[] answer = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    /*
     * Performs DFS and returns true if a cycle is found.
     */
    private boolean hasCycle(
            int currentCourse,
            List<List<Integer>> graph,
            int[] visited,
            List<Integer> result) {

        // State 1 means this course is already in
        // the current DFS path.
        //
        // Reaching it again means there is a cycle.
        if (visited[currentCourse] == 1) {
            return true;
        }

        // State 2 means this course has already been
        // completely explored and no cycle was found.
        if (visited[currentCourse] == 2) {
            return false;
        }

        // Mark the course as currently being explored.
        visited[currentCourse] = 1;

        // Explore every course that depends on currentCourse.
        for (int nextCourse : graph.get(currentCourse)) {

            // If a cycle exists in any neighboring path,
            // immediately return true.
            if (hasCycle(nextCourse, graph, visited, result)) {
                return true;
            }
        }

        /*
         * All dependent courses have been completely explored.
         *
         * Mark this course as completely visited.
         */
        visited[currentCourse] = 2;

        /*
         * Add the course AFTER exploring its neighbors.
         *
         * This creates reverse topological order.
         * We reverse the list later.
         */
        result.add(currentCourse);

        return false;
    }
}