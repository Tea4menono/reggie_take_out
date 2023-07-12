class Solution {
    public int rob(int[] nums) {

        int[] dp = new int[nums.length - 1];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        int max = dp[dp.length - 1];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i + 1]);
        }

        System.out.println(123);
        return Math.max(max, dp[dp.length - 1]);
    }
}