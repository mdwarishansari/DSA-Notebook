class Solution {
    public String convert(String s, int numRows) {
        // Base case: if 1 row or string length is less than/equal to numRows, zigzag cannot form
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        // Initialize a StringBuilder for each row
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean goingDown = false;

        // Traverse each character and simulate the zigzag path
        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);

            // Reverse direction when hitting the top or bottom boundary
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }

            // Move to the next row depending on the direction
            currentRow += goingDown ? 1 : -1;
        }

        // Combine all rows into a single resulting string
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}