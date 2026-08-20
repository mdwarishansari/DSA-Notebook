import java.util.Arrays;

class Solution {
    public int findContentChildren(int[] g, int[] s) {

        // Sort children by their greed factor
        Arrays.sort(g);

        // Sort cookies by their size
        Arrays.sort(s);

        // i -> current child
        int i = 0;

        // j -> current cookie
        int j = 0;

        // Number of satisfied children
        int count = 0;

        // Continue while we still have children
        // and cookies to consider.
        while (i < g.length && j < s.length) {

            // If the current cookie can satisfy
            // the current child...
            if (s[j] >= g[i]) {

                // This child is satisfied
                count++;

                // Move to the next child
                i++;

                // This cookie has been used
                j++;

            } else {

                // Cookie is too small for this child.
                // Since cookies are sorted, this cookie
                // cannot satisfy any more demanding child.
                j++;
            }
        }

        return count;
    }
}