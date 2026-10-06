import java.util.Arrays;

public class DPProblems {

    // ============================================================
    // 1. HOUSE ROBBER
    // ============================================================
    // dp[i] = maximum money that can be robbed up to house i
    //
    // Choice:
    // 1. Take current house -> dp[i - 2] + nums[i]
    // 2. Skip current house -> dp[i - 1]
    //
    // Time: O(n)
    // Space: O(1)
    // ============================================================

    public static int houseRobber(int[] nums) {

        if (nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {

            int take = nums[i] + prev2;
            int skip = prev1;

            int curr = Math.max(take, skip);

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }


    // ============================================================
    // 2. CLIMBING STAIRS
    // ============================================================
    // dp[i] = number of ways to reach step i
    //
    // We can take:
    // 1 step
    // 2 steps
    //
    // dp[i] = dp[i - 1] + dp[i - 2]
    //
    // Time: O(n)
    // Space: O(1)
    // ============================================================

    public static int climbingStairs(int n) {

        if (n <= 1)
            return 1;

        int prev2 = 1;
        int prev1 = 1;

        for (int i = 2; i <= n; i++) {

            int curr = prev1 + prev2;

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }


    // ============================================================
    // 3. MIN COST CLIMBING STAIRS
    // ============================================================
    // dp[i] = minimum cost to reach step i
    //
    // We can reach i from:
    // i - 1
    // i - 2
    //
    // dp[i] = min(
    //     dp[i - 1] + cost[i],
    //     dp[i - 2] + cost[i]
    // )
    //
    // Time: O(n)
    // Space: O(1)
    // ============================================================

    public static int minCostClimbingStairs(int[] cost) {

        if (cost.length <= 2)
            return Math.min(cost[0], cost[1]);

        int prev2 = cost[0];
        int prev1 = cost[1];

        for (int i = 2; i < cost.length; i++) {

            int choice1 = prev1 + cost[i];
            int choice2 = prev2 + cost[i];

            int curr = Math.min(choice1, choice2);

            prev2 = prev1;
            prev1 = curr;
        }

        return Math.min(prev1, prev2);
    }


    // ============================================================
    // 4. FROG JUMP
    // ============================================================
    // Frog can jump:
    // 1 stone OR 2 stones
    //
    // dp[i] = minimum energy needed to reach stone i
    //
    // Jump cost:
    // |height[i] - height[previous]|
    //
    // Time: O(n)
    // Space: O(1)
    // ============================================================

    public static int frogJump(int[] height) {

        if (height.length <= 1)
            return 0;

        int prev2 = 0;

        int prev1 = Math.abs(height[1] - height[0]);

        for (int i = 2; i < height.length; i++) {

            int choice1 =
                    prev1 + Math.abs(height[i] - height[i - 1]);

            int choice2 =
                    prev2 + Math.abs(height[i] - height[i - 2]);

            int curr = Math.min(choice1, choice2);

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }


    // ============================================================
    // 5. FROG K-JUMP
    // ============================================================
    // Frog can jump 1 to K stones.
    //
    // dp[i] = minimum energy needed to reach stone i
    //
    // For every stone:
    // Check previous K stones.
    //
    // dp[i] =
    // minimum(
    //     dp[i-j] + |height[i] - height[i-j]|
    // )
    //
    // Time: O(n * k)
    // Space: O(n)
    // ============================================================

    public static int frogKJump(int[] height, int k) {

        int[] dp = new int[height.length];

        dp[0] = 0;

        for (int i = 1; i < height.length; i++) {
            int minCurr = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j < 0)
                    break;
                int curr =
                        dp[i - j]
                        + Math.abs(height[i] - height[i - j]);
                minCurr = Math.min(minCurr, curr);
            }
            dp[i] = minCurr;
        }
        return dp[height.length - 1];
    }


    

    // ============================================================
    // MAIN
    // ============================================================

    public static void main(String[] args) {

        // House Robber
        int[] houses = {2, 7, 9, 3, 1};
        System.out.println("House Robber: "
                + houseRobber(houses));


        // Climbing Stairs
        System.out.println("Climbing Stairs: "
                + climbingStairs(5));


        // Min Cost Climbing Stairs
        int[] cost = {10, 15, 20, 5};
        System.out.println("Min Cost Climbing Stairs: "
                + minCostClimbingStairs(cost));


        // Frog Jump
        int[] height = {10, 30, 20, 40};
        System.out.println("Frog Jump: "
                + frogJump(height));


        // Frog K-Jump
        int[] heights = {10, 30, 40, 20, 50};
        System.out.println("Frog K-Jump: "
                + frogKJump(heights, 3));


      
    }
}
