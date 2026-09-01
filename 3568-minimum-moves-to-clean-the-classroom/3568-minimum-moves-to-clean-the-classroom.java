import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        // ------------------------------------------------------------
        // 1. Find the starting position and assign an index to
        //    every litter cell.
        //
        //    Example:
        //      First litter  -> bit 0
        //      Second litter -> bit 1
        //      ...
        // ------------------------------------------------------------

        int startR = 0;
        int startC = 0;

        // litterId[r][c] tells us which bit represents litter at (r,c).
        int[][] litterId = new int[m][n];

        int litterCount = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                char cell = classroom[r].charAt(c);

                if (cell == 'S') {
                    startR = r;
                    startC = c;
                }

                else if (cell == 'L') {
                    litterId[r][c] = litterCount;
                    litterCount++;
                }
            }
        }

        // ------------------------------------------------------------
        // If there is no litter, the classroom is already clean.
        // ------------------------------------------------------------

        if (litterCount == 0) {
            return 0;
        }

        // ------------------------------------------------------------
        // targetMask has all litter bits set.
        //
        // If litterCount = 3:
        //
        //      1 << 3 = 1000
        //      - 1    = 0111
        //
        // So targetMask = 111.
        // ------------------------------------------------------------

        int targetMask = (1 << litterCount) - 1;

        // ------------------------------------------------------------
        // State:
        //
        //      (row, col, currentEnergy, mask)
        //
        // Number of possible states:
        //
        //      m * n * (energy + 1) * 2^litterCount
        //
        // We flatten these 4 dimensions into one boolean array.
        // ------------------------------------------------------------

        int maskCount = 1 << litterCount;

        int totalStates = m * n * (energy + 1) * maskCount;

        boolean[] visited = new boolean[totalStates];

        // ------------------------------------------------------------
        // Encode a state into one integer index.
        // ------------------------------------------------------------

        // State stored as:
        //      [row, col, currentEnergy, mask]
        //
        // int[] is enough because all values are small.

        ArrayDeque<int[]> queue = new ArrayDeque<>();

        // Initially:
        //
        // - We are at S
        // - We have full energy
        // - We have collected nothing
        //
        // Therefore mask = 0.

        queue.offer(new int[] {
            startR,
            startC,
            energy,
            0
        });

        visited[getId(startR, startC, energy, 0,
                      n, energy + 1, maskCount)] = true;

        // Four possible movements:
        //
        // up
        // down
        // left
        // right

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // BFS level = number of moves made.
        int moves = 0;

        // ------------------------------------------------------------
        // BFS
        // ------------------------------------------------------------

        while (!queue.isEmpty()) {

            // Every state currently in the queue requires exactly
            // 'moves' moves to reach.
            //
            // Processing one complete level lets us avoid storing
            // the move count inside every state.

            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {

                int[] state = queue.poll();

                int r = state[0];
                int c = state[1];
                int currentEnergy = state[2];
                int mask = state[3];

                // ----------------------------------------------------
                // If all litter has been collected, BFS guarantees
                // that 'moves' is the minimum possible answer.
                // ----------------------------------------------------

                if (mask == targetMask) {
                    return moves;
                }

                // ----------------------------------------------------
                // With zero energy, we cannot make another move.
                //
                // A reset cell would have already restored the energy
                // when we entered it.
                // ----------------------------------------------------

                if (currentEnergy == 0) {
                    continue;
                }

                // ----------------------------------------------------
                // Try all four directions.
                // ----------------------------------------------------

                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // ------------------------------------------------
                    // Check boundaries.
                    // ------------------------------------------------

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    // ------------------------------------------------
                    // X = obstacle.
                    // We cannot enter it.
                    // ------------------------------------------------

                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    // ------------------------------------------------
                    // Calculate energy after entering the next cell.
                    // ------------------------------------------------

                    int nextEnergy;

                    if (classroom[nr].charAt(nc) == 'R') {

                        // Entering R completely restores energy.
                        nextEnergy = energy;

                    } else {

                        // Normal movement costs one energy.
                        nextEnergy = currentEnergy - 1;
                    }

                    // ------------------------------------------------
                    // Update the litter mask.
                    //
                    // If this cell is L, mark its corresponding bit.
                    // ------------------------------------------------

                    int nextMask = mask;

                    if (classroom[nr].charAt(nc) == 'L') {

                        int bit = litterId[nr][nc];

                        nextMask = mask | (1 << bit);
                    }

                    // ------------------------------------------------
                    // Have we already visited this exact state?
                    //
                    // Exact state consists of:
                    //
                    //   row
                    //   column
                    //   energy
                    //   collected litter
                    //
                    // If yes, reaching it again cannot give us
                    // anything new.
                    // ------------------------------------------------

                    int id = getId(
                        nr,
                        nc,
                        nextEnergy,
                        nextMask,
                        n,
                        energy + 1,
                        maskCount
                    );

                    if (visited[id]) {
                        continue;
                    }

                    visited[id] = true;

                    // Add the new state to BFS.
                    queue.offer(new int[] {
                        nr,
                        nc,
                        nextEnergy,
                        nextMask
                    });
                }
            }

            // --------------------------------------------------------
            // We have finished all states requiring 'moves' moves.
            // The next level requires one additional move.
            // --------------------------------------------------------

            moves++;
        }

        // BFS explored every reachable state but never collected
        // all litter.
        return -1;
    }

    // ------------------------------------------------------------
    // Converts:
    //
    // (row, col, energy, mask)
    //
    // into a unique index in the 1D visited array.
    // ------------------------------------------------------------

    private int getId(
        int row,
        int col,
        int currentEnergy,
        int mask,
        int n,
        int energyStates,
        int maskCount
    ) {

        return (((row * n + col) * energyStates)
                + currentEnergy) * maskCount
                + mask;
    }
}