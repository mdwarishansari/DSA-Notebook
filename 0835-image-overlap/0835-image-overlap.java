import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        List<int[]> a = new ArrayList<>();
        List<int[]> b = new ArrayList<>();

        int n = img1.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    a.add(new int[]{i, j});
                }

                if (img2[i][j] == 1) {
                    b.add(new int[]{i, j});
                }
            }
        }

        Map<String, Integer> map = new HashMap<>();
        int answer = 0;

        for (int[] p1 : a) {
            for (int[] p2 : b) {
                int dr = p2[0] - p1[0];
                int dc = p2[1] - p1[1];

                String key = dr + "," + dc;

                int count = map.getOrDefault(key, 0) + 1;
                map.put(key, count);

                answer = Math.max(answer, count);
            }
        }

        return answer;
    }
}