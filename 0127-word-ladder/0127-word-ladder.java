import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert wordList to a HashSet for O(1) lookups and easy removal (marking as visited)
        Set<String> wordSet = new HashSet<>(wordList);
        
        // If the target word isn't in the dictionary, no valid transformation exists
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        
        // Initialize the BFS queue with the starting word
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        // Remove beginWord from the set if present to avoid revisiting it
        wordSet.remove(beginWord);
        
        // Track the number of words in the current transformation sequence (starts at 1 for beginWord)
        int level = 1;
        
        // Perform level-by-level BFS
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // Process all words at the current transformation distance
            for (int i = 0; i < levelSize; i++) {
                String currentWord = queue.poll();
                char[] wordChars = currentWord.toCharArray();
                
                // Try changing each character of the current word to every letter from 'a' to 'z'
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        // Skip replacing with the same character
                        if (c == originalChar) {
                            continue;
                        }
                        
                        wordChars[j] = c;
                        String nextWord = new String(wordChars);
                        
                        // If we reached the endWord, return the current length + 1
                        if (nextWord.equals(endWord)) {
                            return level + 1;
                        }
                        
                        // If the transformed word is in the dictionary, add it to the queue
                        // and remove it from wordSet so we don't visit it again
                        if (wordSet.contains(nextWord)) {
                            queue.offer(nextWord);
                            wordSet.remove(nextWord);
                        }
                    }
                    
                    // Restore the original character before moving to the next position
                    wordChars[j] = originalChar;
                }
            }
            
            // Increment the sequence length after finishing the current level
            level++;
        }
        
        // If the queue empties without reaching endWord, no transformation sequence exists
        return 0;
    }
}