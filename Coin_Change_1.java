public int coinChange(int[] coins, int amt) {

    int[] dp = new int[amt + 1];

    dp[0] = 0;

    for (int i = 1; i <= amt; i++) {

        dp[i] = Integer.MAX_VALUE;

        for (int coin : coins) {

            if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {

                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
    }

    if (dp[amt] == Integer.MAX_VALUE)
        return -1;

    return dp[amt];
}
