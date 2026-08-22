class Solution {
    public List<List<String>> suggestedProducts(
            String[] products,
            String searchWord) {

        // Sort products lexicographically.
        // This ensures that the first matching
        // products are the lexicographically smallest.
        Arrays.sort(products);

        // Store the final suggestions for every
        // character typed in searchWord.
        List<List<String>> result = new ArrayList<>();

        // Build the search prefix character by character.
        String prefix = "";

        for (char c : searchWord.toCharArray()) {

            // Add the current character to the prefix.
            prefix += c;

            // Store suggestions for this prefix.
            List<String> suggestions = new ArrayList<>();

            // Search through the sorted products.
            for (String product : products) {

                // Check whether this product starts
                // with the current prefix.
                if (product.startsWith(prefix)) {

                    // Add this product to suggestions.
                    suggestions.add(product);

                    // We only need at most 3 suggestions.
                    if (suggestions.size() == 3) {
                        break;
                    }
                }
            }

            // Add suggestions for the current prefix.
            result.add(suggestions);
        }

        return result;
    }
}