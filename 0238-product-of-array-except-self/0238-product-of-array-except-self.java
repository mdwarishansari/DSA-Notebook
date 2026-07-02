class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n= nums.length;
        int[] ans = new int [n];

        //Prefix
        ans[0] = 1 ;
        for ( int i =1; i<n; i++){
            ans[i] = ans[i-1] * nums [i-1];
        }

        //Suffix
        int right=1;
        for(int i =n-1; i>=0; i--){
            ans[i] = ans[i]*right;
            right = right * nums[i];
        }
        return ans;
    }
}
// 1 2 3 4 nums
// 1 1 2 6 ans
// 0 0 0 6

// | Iteration | i | Current nums[i] | right BEFORE | ans[i] = ans[i] × right | right AFTER = right × nums[i] |
// | --------- | - | --------------- | ------------ | ----------------------- | ----------------------------- |
// | Start     | - | -               | **1**        | -                       | -                             |
// | 1         | 3 | 4               | **1**        | 6 × 1 = 6               | **4**                         |
// | 2         | 2 | 3               | **4**        | 2 × 4 = 8               | **12**                        |
// | 3         | 1 | 2               | **12**       | 1 × 12 = 12             | **24**                        |
// | 4         | 0 | 1               | **24**       | 1 × 24 = 24             | **24** (unused after loop)    |
