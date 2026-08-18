class Solution {

    // DFS function to visit all cities
    // connected to the current city
    private void dfs(int city, int[][] isConnected, boolean[] visited) {

        // Mark current city as visited
        visited[city] = true;

        // Check all possible neighboring cities
        for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {

            // If there is a connection and neighbor is not visited
            if (isConnected[city][neighbor] == 1 && !visited[neighbor]) {

                // Visit that neighbor
                dfs(neighbor, isConnected, visited);
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {

        int n = isConnected.length;

        // Tracks whether a city is already explored
        boolean[] visited = new boolean[n];

        int provinces = 0;

        // Try starting DFS from every city
        for (int city = 0; city < n; city++) {

            // New unvisited city means
            // a new province is discovered
            if (!visited[city]) {

                provinces++;

                // Visit the entire connected component
                dfs(city, isConnected, visited);
            }
        }

        return provinces;
    }
}