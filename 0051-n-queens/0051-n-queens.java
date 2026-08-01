class Solution {

    // Stores all valid chess boards
    List<List<String>> result = new ArrayList<>();

    // Stores occupied columns
    HashSet<Integer> cols = new HashSet<>();

    // Stores occupied main diagonals (row + col)
    HashSet<Integer> diagonal = new HashSet<>();

    // Stores occupied anti diagonals (row - col)
    HashSet<Integer> antiDiagonal = new HashSet<>();

    // Size of board
    int N;

    // =====================================================
    // Backtracking Function
    // row -> Current row where we are placing a queen
    // =====================================================
    private void solve(char[][] board, int row) {

        // ============================
        // BASE CASE
        // ============================
        // If all rows are processed,
        // we found one valid board.
        if (row == N) {

            List<String> currentBoard = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                currentBoard.add(new String(board[i]));
            }

            result.add(currentBoard);
            return;
        }

        // Try placing queen in every column
        for (int col = 0; col < N; col++) {

            // Calculate diagonal values
            int diag = row + col;
            int antiDiag = row - col;

            // ===================================
            // O(1) VALIDITY CHECK
            // ===================================
            // If column OR diagonal OR anti-diagonal
            // already contains a queen,
            // skip this position.
            if (cols.contains(col)
                    || diagonal.contains(diag)
                    || antiDiagonal.contains(antiDiag)) {

                continue;
            }

            // ===================================
            // CHOOSE
            // ===================================

            // Mark current column occupied
            cols.add(col);

            // Mark current main diagonal occupied
            diagonal.add(diag);

            // Mark current anti diagonal occupied
            antiDiagonal.add(antiDiag);

            // Place Queen
            board[row][col] = 'Q';

            // ===================================
            // EXPLORE
            // ===================================
            solve(board, row + 1);

            // ===================================
            // BACKTRACK (UNDO)
            // ===================================

            // Remove queen
            board[row][col] = '.';

            // Free column
            cols.remove(col);

            // Free diagonal
            diagonal.remove(diag);

            // Free anti diagonal
            antiDiagonal.remove(antiDiag);
        }
    }

    public List<List<String>> solveNQueens(int n) {

        N = n;

        // Create empty chess board
        char[][] board = new char[n][n];

        // Fill with '.'
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        // Start from row 0
        solve(board, 0);

        return result;
    }
}