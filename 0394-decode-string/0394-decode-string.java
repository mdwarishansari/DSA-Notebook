
import java.util.Stack;

class Solution {
    public String decodeString(String s) {
        // Stores repetition counts for nested expressions.
        Stack<Integer> countStack = new Stack<>();

        // Stores strings built before each opening bracket.
        Stack<String> stringStack = new Stack<>();

        // Builds the current decoded string.
        StringBuilder curr = new StringBuilder();

        // Builds repetition numbers, including multi-digit numbers.
        int num = 0;

        // Process each character from left to right.
        for (char ch : s.toCharArray()) {

            if (Character.isDigit(ch)) {
                // Build the complete number.
                num = num * 10 + (ch - '0');

            } else if (ch == '[') {
                // Save the repetition count and previous string.
                countStack.push(num);
                stringStack.push(curr.toString());

                // Start decoding the string inside the brackets.
                num = 0;
                curr = new StringBuilder();

            } else if (ch == ']') {
                // Get the repetition count and previous string.
                int count = countStack.pop();
                String prev = stringStack.pop();

                // Repeat the current decoded string 'count' times.
                StringBuilder temp = new StringBuilder(prev);

                for (int i = 0; i < count; i++) {
                    temp.append(curr);
                }

                // The decoded result becomes the new current string.
                curr = temp;

            } else {
                // Ordinary letters belong to the current string.
                curr.append(ch);
            }
        }

        // Return the completely decoded string.
        return curr.toString();
    }
}
