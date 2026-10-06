import java.util.Arrays;

public class CoinChangeII {

    // Memoization table
    // dp[index][amount] = number of combinations
    private int[][] dp;

    public int change(int amount, int[] coins) {

        // dp[index][amount]
        dp = new int[coins.length][amount + 1];

        // -1 means: state has not been calculated yet
        for (int i = 0; i < coins.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        return solve(0, amount, coins);
    }

    private int solve(int index, int amount, int[] coins) {

        // Exact amount formed
        if (amount == 0) {
            return 1;
        }

        // Invalid path
        if (amount < 0 || index == coins.length) {
            return 0;
        }

        // Already calculated
        if (dp[index][amount] != -1) {
            return dp[index][amount];
        }

        // Take the current coin
        // Same index because a coin can be used multiple times
        int take = solve(
                index,
                amount - coins[index],
                coins
        );

        // Skip the current coin
        // Move to the next coin
        int skip = solve(
                index + 1,
                amount,
                coins
        );

        // Total combinations
        dp[index][amount] = take + skip;

        return dp[index][amount];
    }

    public static void main(String[] args) {

        CoinChangeII solution = new CoinChangeII();

        int[] coins = {1, 2, 5};
        int amount = 5;

        int result = solution.change(amount, coins);

        System.out.println("Number of combinations: " + result);
    }
}
