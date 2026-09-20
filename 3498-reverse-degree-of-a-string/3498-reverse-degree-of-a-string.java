class Solution {

    public int reverseDegree(String s) {

        // Stores the final reverse degree
        int answer = 0;

        // Traverse every character of the string
        for (int i = 0; i < s.length(); i++) {

            // Get the current character
            char ch = s.charAt(i);

            /*
             * Calculate the character's position
             * in the reversed alphabet.
             *
             * Normal alphabet:
             * a = 1, b = 2, ..., z = 26
             *
             * Reversed alphabet:
             * a = 26, b = 25, ..., z = 1
             *
             * (ch - 'a') gives:
             * a -> 0
             * b -> 1
             * c -> 2
             * ...
             *
             * Therefore:
             * 26 - (ch - 'a')
             *
             * gives:
             * a -> 26
             * b -> 25
             * c -> 24
             * ...
             */
            int reversedValue = 26 - (ch - 'a');

            /*
             * Java uses 0-based indexing,
             * but the problem uses 1-based positions.
             *
             * Therefore:
             * i = 0 -> position 1
             * i = 1 -> position 2
             * i = 2 -> position 3
             */
            int position = i + 1;

            // Calculate this character's contribution
            int contribution = reversedValue * position;

            // Add it to the total answer
            answer += contribution;
        }

        // Return the final reverse degree
        return answer;
    }
}