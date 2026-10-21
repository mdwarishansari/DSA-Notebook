
class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] a = s.toCharArray();

        // Check whether the string is already sorted.
        boolean sorted = true;

        for (int i = 1; i < n; i++) {
            if (a[i] < a[i - 1]) {
                sorted = false;
                break;
            }
        }

        if (sorted) return 0;

        // An unsorted string of length 2 cannot be sorted,
        // because selecting the entire string is not allowed.
        if (n == 2) return -1;

        // Find the minimum and maximum characters.
        char min = a[0], max = a[0];

        for (char c : a) {
            min = (char) Math.min(min, c);
            max = (char) Math.max(max, c);
        }

        // One operation is enough if the minimum is first
        // or the maximum is last.
        if (a[0] == min || a[n - 1] == max) {
            return 1;
        }

        // Check whether the minimum or maximum is in the middle.
        for (int i = 1; i < n - 1; i++) {
            if (a[i] == min || a[i] == max) {
                return 2;
            }
        }

        // Neither extreme is at an end or in the middle.
        return 3;
    }
}
