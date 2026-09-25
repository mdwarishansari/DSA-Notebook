import java.util.*;

class Solution {

    // Global expression and pointer.
    // 'index' tells us which character we are currently processing.
    private String expression;
    private int index;

    public List<String> braceExpansionII(String expression) {

        this.expression = expression;
        this.index = 0;

        // Parse the complete expression.
        Set<String> result = parseExpression();

        // TreeSet keeps everything sorted automatically.
        return new ArrayList<>(result);
    }


    /*
     * ---------------------------------------------------------
     * parseExpression()
     *
     * Handles UNION / comma.
     *
     * Example:
     *
     * a,b,c
     *
     * becomes:
     *
     * {"a", "b", "c"}
     * ---------------------------------------------------------
     */
    private Set<String> parseExpression() {

        // First parse the first term.
        Set<String> result = parseTerm();

        // Continue while we find commas.
        while (index < expression.length()
                && expression.charAt(index) == ',') {

            // Skip comma.
            index++;

            // Parse the next term.
            Set<String> next = parseTerm();

            // Union:
            // Add all possibilities from the next term.
            result.addAll(next);
        }

        return result;
    }


    /*
     * ---------------------------------------------------------
     * parseTerm()
     *
     * Handles CONCATENATION.
     *
     * Example:
     *
     * {a,b}c
     *
     * First factor:
     * {"a", "b"}
     *
     * Second factor:
     * {"c"}
     *
     * Result:
     * {"ac", "bc"}
     * ---------------------------------------------------------
     */
    private Set<String> parseTerm() {

        /*
         * Start with an empty string.
         *
         * Why?
         *
         * "" + "a" = "a"
         * "" + "b" = "b"
         *
         * This makes the first concatenation work naturally.
         */
        Set<String> result = new TreeSet<>();
        result.add("");

        /*
         * A term continues as long as the next character
         * starts a factor.
         *
         * A factor can start with:
         *
         * 1. lowercase letter
         * 2. '{'
         */
        while (index < expression.length()) {

            char current = expression.charAt(index);

            // Stop if this is not the beginning of a factor.
            if (current == ',' || current == '}') {
                break;
            }

            // Parse one factor.
            Set<String> factor = parseFactor();

            /*
             * Cartesian product.
             *
             * Example:
             *
             * result = {"a", "b"}
             * factor = {"c", "d"}
             *
             * We create:
             *
             * ac
             * ad
             * bc
             * bd
             */
            Set<String> newResult = new TreeSet<>();

            for (String first : result) {

                for (String second : factor) {

                    newResult.add(first + second);
                }
            }

            // The newly created combinations become our result.
            result = newResult;
        }

        return result;
    }


    /*
     * ---------------------------------------------------------
     * parseFactor()
     *
     * A factor can be:
     *
     * 1. A single lowercase letter
     * 2. A complete expression inside braces
     *
     * Examples:
     *
     * a
     *
     * OR
     *
     * {a,b}
     * ---------------------------------------------------------
     */
    private Set<String> parseFactor() {

        char current = expression.charAt(index);

        /*
         * Case 1:
         * Current character is a lowercase letter.
         */
        if (current >= 'a' && current <= 'z') {

            Set<String> result = new TreeSet<>();

            // Add the current letter.
            result.add(String.valueOf(current));

            // Move to the next character.
            index++;

            return result;
        }


        /*
         * Case 2:
         * Current character is '{'.
         *
         * Example:
         *
         * {a,b}
         */
        if (current == '{') {

            // Skip '{'
            index++;

            /*
             * Recursively parse everything inside the braces.
             *
             * For:
             *
             * {a,{b,c}}
             *
             * this will recursively handle the inner
             * {b,c} as well.
             */
            Set<String> result = parseExpression();

            // Skip closing '}'
            index++;

            return result;
        }

        // This should never happen for a valid expression.
        return new TreeSet<>();
    }
}