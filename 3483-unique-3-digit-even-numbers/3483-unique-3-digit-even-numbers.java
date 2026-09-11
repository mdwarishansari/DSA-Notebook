class Solution {

    public int totalNumbers(int[] digits) {

        // ---------------------------------------------------------
        // Step 1: Count how many times each digit appears.
        //
        // Example:
        // digits = [0, 2, 2]
        //
        // freq[0] = 1
        // freq[2] = 2
        // ---------------------------------------------------------
        int[] freq = new int[10];

        for (int digit : digits) {
            freq[digit]++;
        }

        int count = 0;

        // ---------------------------------------------------------
        // Step 2: Choose the hundreds digit.
        //
        // It cannot be 0 because a 3-digit number cannot
        // have a leading zero.
        // ---------------------------------------------------------
        for (int hundreds = 1; hundreds <= 9; hundreds++) {

            // If this digit doesn't exist, it cannot be used.
            if (freq[hundreds] == 0) {
                continue;
            }

            // -----------------------------------------------------
            // Step 3: Choose the tens digit.
            //
            // Tens digit can be anything from 0 to 9.
            // -----------------------------------------------------
            for (int tens = 0; tens <= 9; tens++) {

                // -------------------------------------------------
                // Step 4: Choose the units digit.
                //
                // The number must be EVEN, so the last digit
                // must be one of:
                //
                // 0, 2, 4, 6, 8
                // -------------------------------------------------
                for (int units = 0; units <= 8; units += 2) {

                    // -------------------------------------------------
                    // Now we have a candidate:
                    //
                    // hundreds tens units
                    //
                    // Example: 122
                    //
                    // We need to make sure that the required copies
                    // of every digit actually exist in the array.
                    // -------------------------------------------------

                    // Count how many times each selected digit is used.
                    int[] required = new int[10];

                    required[hundreds]++;
                    required[tens]++;
                    required[units]++;

                    // Check whether the input contains enough copies
                    // of every required digit.
                    boolean possible = true;

                    for (int digit = 0; digit <= 9; digit++) {

                        if (required[digit] > freq[digit]) {
                            possible = false;
                            break;
                        }
                    }

                    // If all three digits can actually be formed,
                    // this is one distinct valid number.
                    if (possible) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}