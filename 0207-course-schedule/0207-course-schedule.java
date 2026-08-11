import java.util.*;

class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // graph[i] contains all courses that depend on course i.
        List<List<Integer>> graph = new ArrayList<>();

        // Create an empty list for every course.
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Build the directed graph.
        for (int[] pair : prerequisites) {

            // pair[0] = course we want to take.
            int course = pair[0];

            // pair[1] = prerequisite course.
            int prerequisite = pair[1];

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

        // Start DFS from every course.
        for (int course = 0; course < numCourses; course++) {

            // If DFS finds a cycle, we cannot finish all courses.
            if (hasCycle(course, graph, visited)) {
                return false;
            }
        }

        // No cycle was found, so all courses can be completed.
        return true;
    }

    /*
     * Returns true if a cycle exists starting from currentCourse.
     */
    private boolean hasCycle(
            int currentCourse,
            List<List<Integer>> graph,
            int[] visited) {

        // State 1 means this course is already
        // in our current DFS path.
        //
        // Reaching it again means we found a cycle.
        if (visited[currentCourse] == 1) {
            return true;
        }

        // State 2 means this course was already completely
        // explored earlier and no cycle was found from it.
        if (visited[currentCourse] == 2) {
            return false;
        }

        // Mark this course as currently being explored.
        visited[currentCourse] = 1;

        // Explore every course that depends on currentCourse.
        for (int nextCourse : graph.get(currentCourse)) {

            // Recursively explore the next course.
            if (hasCycle(nextCourse, graph, visited)) {
                return true;
            }
        }

        // We have completely explored this course
        // and none of its paths contained a cycle.
        visited[currentCourse] = 2;

        return false;
    }
}