class Solution {

    public int[][] matrixBlockSum(int[][] mat, int k) {

        int m = mat.length;
        int n = mat[0].length;

        /*
         * ---------------------------------------------------------
         * STEP 1: Build 2D Prefix Sum
         * ---------------------------------------------------------
         *
         * prefix[i][j] represents the sum of the rectangle
         * from (0, 0) to (i-1, j-1) in the original matrix.
         *
         * We use (m + 1) x (n + 1) instead of m x n.
         *
         * The extra first row and first column contain 0.
         *
         * This makes rectangle-sum calculations much easier.
         */
        int[][] prefix = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                /*
                 * Prefix Sum Formula:
                 *
                 *                  current row
                 *                       ↓
                 * prefix[i][j] = prefix[i-1][j]
                 *              + prefix[i][j-1]
                 *              - prefix[i-1][j-1]
                 *              + mat[i-1][j-1]
                 *
                 * Why subtract prefix[i-1][j-1]?
                 *
                 * Because it was included twice:
                 * once from the top and once from the left.
                 */
                prefix[i][j] =
                        prefix[i - 1][j]
                        + prefix[i][j - 1]
                        - prefix[i - 1][j - 1]
                        + mat[i - 1][j - 1];
            }
        }

        /*
         * ---------------------------------------------------------
         * STEP 2: Calculate answer for every cell
         * ---------------------------------------------------------
         */
        int[][] answer = new int[m][n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                /*
                 * Find the boundaries of the block around (i, j).
                 *
                 * The block should contain:
                 *
                 *       i-k <= row <= i+k
                 *       j-k <= col <= j+k
                 *
                 * But these positions may go outside the matrix.
                 *
                 * Therefore:
                 *
                 * top    cannot be less than 0
                 * bottom cannot be greater than m-1
                 * left   cannot be less than 0
                 * right  cannot be greater than n-1
                 */

                int top = Math.max(0, i - k);
                int bottom = Math.min(m - 1, i + k);

                int left = Math.max(0, j - k);
                int right = Math.min(n - 1, j + k);

                /*
                 * -------------------------------------------------
                 * STEP 3: Get rectangle sum using prefix sum
                 * -------------------------------------------------
                 *
                 * Remember that prefix has one extra row/column.
                 *
                 * Rectangle:
                 *
                 * (top, left) ---------------- (top, right)
                 *       |                            |
                 *       |         REQUIRED            |
                 *       |         RECTANGLE           |
                 *       |                            |
                 * (bottom, left) ------------ (bottom, right)
                 *
                 * Formula:
                 *
                 * sum =
                 *     prefix[bottom + 1][right + 1]
                 *   - prefix[top][right + 1]
                 *   - prefix[bottom + 1][left]
                 *   + prefix[top][left]
                 *
                 * The last + prefix[top][left] is needed because
                 * that area was subtracted twice.
                 */
                answer[i][j] =
                        prefix[bottom + 1][right + 1]
                        - prefix[top][right + 1]
                        - prefix[bottom + 1][left]
                        + prefix[top][left];
            }
        }

        return answer;
    }
}