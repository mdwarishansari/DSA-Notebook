class Solution {

    public int[][] matrixBlockSum(int[][] mat, int k) {

        int m = mat.length;
        int n = mat[0].length;

        // --------------------------------------------------
        // STEP 1: Build 2D Prefix Sum
        //
        // prefix[i][j] = sum of rectangle from (0,0)
        //                 to (i-1, j-1)
        //
        // We use m+1 and n+1 to avoid boundary checks.
        // --------------------------------------------------

        int[][] prefix = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                prefix[i][j] =
                        mat[i - 1][j - 1]
                        + prefix[i - 1][j]
                        + prefix[i][j - 1]
                        - prefix[i - 1][j - 1];
            }
        }

        // --------------------------------------------------
        // STEP 2: Calculate answer for every cell
        // --------------------------------------------------

        int[][] answer = new int[m][n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                // Find the boundaries of the block
                int top = Math.max(0, i - k);
                int bottom = Math.min(m - 1, i + k);

                int left = Math.max(0, j - k);
                int right = Math.min(n - 1, j + k);

                // Prefix uses +1 indexing,
                // so convert matrix coordinates.
                top++;
                left++;
                bottom++;
                right++;

                // Rectangle sum using 2D prefix sum
                answer[i][j] =
                        prefix[bottom][right]
                        - prefix[top - 1][right]
                        - prefix[bottom][left - 1]
                        + prefix[top - 1][left - 1];
            }
        }

        return answer;
    }
}