import java.util.Stack;

class Solution {

    public boolean isValid(String s) {

        /*
         * Stack stores the opening brackets.
         *
         * Example:
         * s = "([{}])"
         *
         * Whenever we find:
         * '(' or '[' or '{'
         *
         * we push it into the stack.
         */
        Stack<Character> stack = new Stack<>();


        // Traverse every character of the string.
        for (char ch : s.toCharArray()) {

            /*
             * If the current character is an opening bracket,
             * push it into the stack.
             */
            if (ch == '(' || ch == '[' || ch == '{') {

                stack.push(ch);

            } else {

                /*
                 * We have found a closing bracket.
                 *
                 * If the stack is empty, there is no opening
                 * bracket available to match this closing bracket.
                 *
                 * Example:
                 * s = ")"
                 *
                 * Therefore, the string is invalid.
                 */
                if (stack.isEmpty()) {
                    return false;
                }


                /*
                 * Remove the most recent opening bracket.
                 *
                 * Because Stack follows LIFO:
                 *
                 * Last In → First Out
                 *
                 * the most recently opened bracket must
                 * be closed first.
                 */
                char top = stack.pop();


                /*
                 * Check whether the closing bracket matches
                 * the opening bracket taken from the stack.
                 *
                 * Valid pairs:
                 *
                 * ( )
                 * [ ]
                 * { }
                 */

                if (ch == ')' && top != '(') {
                    return false;
                }

                if (ch == ']' && top != '[') {
                    return false;
                }

                if (ch == '}' && top != '{') {
                    return false;
                }
            }
        }


        /*
         * After processing the entire string,
         * the stack must be empty.
         *
         * If something remains in the stack,
         * some opening brackets were never closed.
         *
         * Example:
         * s = "((("
         *
         * Stack still contains: ( ( (
         *
         * Therefore → false.
         */
        return stack.isEmpty();
    }
}