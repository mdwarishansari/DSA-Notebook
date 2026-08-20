class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {

        int n = gas.length;

        // Calculate total gas available
        int totalGas = 0;

        // Calculate total cost required
        int totalCost = 0;

        for (int i = 0; i < n; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }

        // If total gas is less than total cost,
        // completing the circuit is impossible.
        if (totalGas < totalCost) {
            return -1;
        }

        // Candidate starting station
        int resultIndex = 0;

        // Current gas balance from resultIndex
        int total = 0;

        // Find the valid starting station
        for (int i = 0; i < n; i++) {

            // Gain gas[i] and spend cost[i]
            total += gas[i] - cost[i];

            // If we run out of gas,
            // current starting point cannot work.
            if (total < 0) {

                // None of the stations from resultIndex
                // through i can be a valid starting point.
                resultIndex = i + 1;

                // Start calculating the balance
                // from the new candidate.
                total = 0;
            }
        }

        return resultIndex;
    }
}