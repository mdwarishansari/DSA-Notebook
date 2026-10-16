
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int findTheWinner(int n, int k) {
        // Store all friends, numbered from 1 to n.
        List<Integer> friends = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            friends.add(i);
        }

        // Begin counting from the first friend.
        int index = 0;

        // Continue until only one friend remains.
        while (friends.size() > 1) {

            // Counting includes the current friend,
            // so move forward by k - 1 positions.
            index = (index + k - 1) % friends.size();

            // Eliminate the friend at the calculated index.
            friends.remove(index);

            // The next count starts at the same index,
            // since the next friend shifts into that position.
        }

        // Return the winner's number.
        return friends.get(0);
    }
}
