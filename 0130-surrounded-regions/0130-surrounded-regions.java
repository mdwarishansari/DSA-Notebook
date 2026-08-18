class Solution {

    // 4-direction movement
    private int[] dr = {-1, 1, 0, 0};
    private int[] dc = {0, 0, -1, 1};

    // DFS to mark all boundary-connected O's
    private void dfs(char[][] board, int row, int col) {

        int m = board.length;
        int n = board[0].length;

        // Out of bounds
        if (row < 0 || col < 0 || row >= m || col >= n) {
            return;
        }

        // Stop if not an O
        if (board[row][col] != 'O') {
            return;
        }

        // Mark as safe
        board[row][col] = '#';

        // Visit all 4 neighbours
        for (int k = 0; k < 4; k++) {
            dfs(board, row + dr[k], col + dc[k]);
        }
    }

    public void solve(char[][] board) {

        int m = board.length;
        int n = board[0].length;

        // -----------------------------
        // Visit first and last column
        // -----------------------------
        for (int row = 0; row < m; row++) {

            if (board[row][0] == 'O') {
                dfs(board, row, 0);
            }

            if (board[row][n - 1] == 'O') {
                dfs(board, row, n - 1);
            }
        }

        // -----------------------------
        // Visit first and last row
        // -----------------------------
        for (int col = 0; col < n; col++) {

            if (board[0][col] == 'O') {
                dfs(board, 0, col);
            }

            if (board[m - 1][col] == 'O') {
                dfs(board, m - 1, col);
            }
        }

        // -----------------------------
        // Convert cells
        // -----------------------------
        for (int row = 0; row < m; row++) {

            for (int col = 0; col < n; col++) {

                // Trapped region
                if (board[row][col] == 'O') {
                    board[row][col] = 'X';
                }

                // Safe region
                else if (board[row][col] == '#') {
                    board[row][col] = 'O';
                }
            }
        }
    }
}