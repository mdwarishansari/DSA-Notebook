import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int target = n * n;

        // Already at the final square on an empty/1-cell board.
        if (target <= 1) {
            return 0;
        }

        // Flatten board values by square number (1-indexed: 1..n*n) for O(1) lookup.
        // flatBoard[s] = destination square if square `s` has a snake/ladder, or -1 otherwise.
        int[] flatBoard = new int[target + 1];
        for (int square = 1; square <= target; square++) {
            int[] coords = toRowCol(square, n);
            flatBoard[square] = board[coords[0]][coords[1]];
        }

        boolean[] visited = new boolean[target + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        // Start at square 1.
        visited[1] = true;
        queue.offer(1);

        int rolls = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            rolls++;

            // Process all squares reachable with the current number of dice rolls.
            for (int i = 0; i < levelSize; i++) {
                int curr = queue.poll();

                // Try all possible dice outcomes (1 to 6).
                for (int dice = 1; dice <= 6; dice++) {
                    int next = curr + dice;
                    if (next > target) {
                        break;
                    }

                    // Follow a snake or ladder at most once per turn.
                    int dest = (flatBoard[next] != -1) ? flatBoard[next] : next;

                    if (dest == target) {
                        return rolls;
                    }

                    if (!visited[dest]) {
                        visited[dest] = true;
                        queue.offer(dest);
                    }
                }
            }
        }

        // Target square is unreachable.
        return -1;
    }

    // Converts a 1-indexed Boustrophedon square label (1..n*n) into {row, col}.
    private int[] toRowCol(int square, int n) {
        int idx = square - 1;
        int rowFromBottom = idx / n;
        int row = (n - 1) - rowFromBottom;

        int colInRow = idx % n;
        // Even rowFromBottom (0, 2, ...) moves left-to-right; odd moves right-to-left.
        int col = (rowFromBottom % 2 == 0) ? colInRow : (n - 1 - colInRow);

        return new int[]{row, col};
    }
}