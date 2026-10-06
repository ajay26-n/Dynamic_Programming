import java.util.Arrays;

public class CoinChange {

    public int coinChange(int[] coins, int amount) {

        // dp[i] = minimum number of coins needed
        // to make amount i
        int[] dp = new int[amount + 1];

        // -1 means amount cannot be formed yet
        Arrays.fill(dp, -1);

        // 0 amount requires 0 coins
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {

            int minCoins = Integer.MAX_VALUE;

            for (int coin : coins) {

                if (coin <= i && dp[i - coin] != -1) {

                    minCoins = Math.min(
                        minCoins,
                        dp[i - coin] + 1
                    );
                }
            }

            if (minCoins != Integer.MAX_VALUE) {
                dp[i] = minCoins;
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {

        CoinChange solution = new CoinChange();

        int[] coins = {1, 2, 5};
        int amount = 11;

        int result = solution.coinChange(coins, amount);

        System.out.println("Minimum coins: " + result);
    }
}
