# Dynamic Programming (DP) — Learning Notes

My notes while learning Dynamic Programming from basic problems to more advanced patterns.

The main goal is to understand **how to think about DP**, not just memorize solutions.

---

## 🧠 What is Dynamic Programming?

Dynamic Programming is a technique used when:

1. A problem can be divided into smaller subproblems.
2. The same subproblems are solved repeatedly.
3. We store the result of solved subproblems and reuse them.

In simple words:

> **Solve smaller problems → store their answers → use them to solve bigger problems.**

---

# 1. House Robber

### Problem

You have houses with different amounts of money.

You cannot rob two adjacent houses.

Find the **maximum amount of money** you can rob.

### DP State

```text
dp[i] = maximum money that can be robbed up to house i
```

### Choices

At house `i`:

**Take the house**

```text
dp[i - 2] + nums[i]
```

Because we cannot rob the previous house.

**Skip the house**

```text
dp[i - 1]
```

### Transition

```text
dp[i] = max(
    dp[i - 1],
    dp[i - 2] + nums[i]
)
```

### Pattern

```text
TAKE / SKIP
```

### Complexity

```text
Time  : O(n)
Space : O(1)   // optimized
```

---

# 2. Climbing Stairs

### Problem

A person wants to reach the top.

They can climb:

```text
1 step
2 steps
```

Find the number of different ways to reach the top.

### DP State

```text
dp[i] = number of ways to reach step i
```

### Choices

To reach step `i`, we can come from:

```text
i - 1
i - 2
```

Therefore:

```text
dp[i] = dp[i - 1] + dp[i - 2]
```

### Base State

```text
dp[0] = 1
dp[1] = 1
```

### Pattern

```text
COUNTING WAYS
```

### Complexity

```text
Time  : O(n)
Space : O(1)
```

---

# 3. Min Cost Climbing Stairs

### Problem

Each step has a cost.

You can move:

```text
1 step
2 steps
```

Find the minimum cost to reach the top.

### DP State

```text
dp[i] = minimum cost required to reach step i
```

### Choices

Reach `i` from:

```text
i - 1
i - 2
```

Cost of entering the current step is added.

```text
choice1 = dp[i - 1] + cost[i]

choice2 = dp[i - 2] + cost[i]
```

### Transition

```text
dp[i] = min(choice1, choice2)
```

### Important

The answer is:

```text
min(dp[n - 1], dp[n - 2])
```

because the top can be reached from either of the last two steps.

### Pattern

```text
MINIMUM COST
```

### Complexity

```text
Time  : O(n)
Space : O(1)
```

---

# 4. Frog Jump

### Problem

A frog starts at stone `0`.

It can jump:

```text
1 stone
2 stones
```

The energy required for a jump is:

```text
|height[current] - height[previous]|
```

Find the minimum energy required to reach the last stone.

### DP State

```text
dp[i] = minimum energy required to reach stone i
```

### Choices

The frog can reach `i` from:

```text
i - 1
i - 2
```

### Jump Costs

From `i - 1`:

```text
dp[i - 1] + |height[i] - height[i - 1]|
```

From `i - 2`:

```text
dp[i - 2] + |height[i] - height[i - 2]|
```

### Transition

```text
dp[i] = min(
    dp[i - 1] + |height[i] - height[i - 1]|,
    dp[i - 2] + |height[i] - height[i - 2]|
)
```

### Space Optimization

Only the previous two states are required:

```text
prev2 = dp[i - 2]
prev1 = dp[i - 1]
curr  = dp[i]
```

### Complexity

```text
Time  : O(n)
Space : O(1)
```

---

# 5. Frog K-Jump

### Problem

The frog can jump anywhere from:

```text
1 to K stones
```

### DP State

```text
dp[i] = minimum energy required to reach stone i
```

### Choices

For current stone `i`, check:

```text
i - 1
i - 2
i - 3
...
i - K
```

For every possible previous stone `j`:

```text
candidate =
    dp[i - j]
    + |height[i] - height[i - j]|
```

Take the minimum.

### Transition

```text
dp[i] =
    minimum of all valid previous K states
```

### Pattern

```text
PREVIOUS K STATES
```

### Complexity

If there are `n` stones and maximum jump is `k`:

```text
Time  : O(n × k)
Space : O(n)
```

---

# 6. Coin Change I

### Problem

Given coins and an amount, find the **minimum number of coins** required to make the amount.

A coin can be used unlimited times.

Example:

```text
coins = [1, 2, 5]
amount = 11
```

Answer:

```text
3

5 + 5 + 1
```

### DP State

```text
dp[i] = minimum number of coins required to make amount i
```

### Main Idea

For every amount `i`, try every coin.

If we choose coin `c`:

```text
remaining = i - c
```

Then:

```text
candidate = dp[i - c] + 1
```

The `+1` represents the current coin.

### Transition

```text
dp[i] = min(
    dp[i],
    dp[i - coin] + 1
)
```

### Base Case

```text
dp[0] = 0
```

Zero amount requires zero coins.

### Unreachable State

We use:

```text
Integer.MAX_VALUE
```

to represent:

```text
amount cannot currently be formed
```

Before doing:

```text
dp[i - coin] + 1
```

we check that `dp[i - coin]` is not `Integer.MAX_VALUE`.

This avoids integer overflow.

### Complexity

If:

```text
A = amount
K = number of coins
```

then:

```text
Time  : O(A × K)
Space : O(A)
```

---

# 7. Coin Change II

### Problem

Given coins and an amount, find the **number of different combinations** that make the amount.

Coins can be used unlimited times.

Important:

> **Order does NOT matter.**

For example:

```text
1 + 2 + 2
2 + 1 + 2
2 + 2 + 1
```

are the **same combination**.

---

## 🧠 Recursion Thinking

The state is:

```text
solve(index, amount)
```

Meaning:

> Number of combinations to make `amount` using coins from `index` onwards.

At every coin, there are two choices.

### Take

Use the current coin.

```text
solve(index, amount - coins[index])
```

The index stays the same because the same coin can be used again.

```text
TAKE → same index
```

### Skip

Don't use the current coin anymore.

```text
solve(index + 1, amount)
```

```text
SKIP → next index
```

### Combine

Since we are counting combinations:

```text
ways = take + skip
```

---

## Base Cases

If:

```text
amount == 0
```

we successfully created one valid combination.

```text
return 1
```

If:

```text
amount < 0
```

the path is invalid.

```text
return 0
```

If:

```text
index == coins.length
```

there are no coins left.

```text
return 0
```

---

# 🧠 Memoization

The recursive solution can calculate the same state repeatedly.

Example state:

```text
(index, amount)
```

can appear multiple times.

So we store the answer:

```text
dp[index][amount]
```

Meaning:

```text
dp[index][amount]
=
number of combinations to make amount
using coins from index onwards
```

Use:

```text
-1
```

to mean:

```text
Not calculated yet
```

### Complexity

```text
Time  : O(N × Amount)
Space : O(N × Amount)
```

---

# 🔥 DP Patterns Learned

| Problem | Goal | Main Pattern |
|---|---|---|
| House Robber | Maximum | Take / Skip |
| Climbing Stairs | Count | Previous states |
| Min Cost Climbing Stairs | Minimum | Previous states |
| Frog Jump | Minimum | Previous 2 states |
| Frog K-Jump | Minimum | Previous K states |
| Coin Change I | Minimum | Try every coin |
| Coin Change II | Count | Take / Skip |

---

# 🧠 How I Should Think About DP

When I see a new DP problem, I should **not immediately think about code**.

First ask:

### 1. What is the state?

What does:

```text
dp[i]
```

or

```text
dp[i][j]
```

represent?

---

### 2. What are the choices?

For example:

```text
Take / Skip
1 step / 2 steps
Previous K states
Try every coin
```

---

### 3. What happens after each choice?

For example:

```text
Take:
previous state + current value

Skip:
previous state
```

---

### 4. What do I want?

Is the problem asking for:

```text
Maximum?
Minimum?
Number of ways?
Can it be done?
```

This determines whether I use:

```text
max()
min()
+
boolean
```

---

### 5. What is the base case?

Ask:

> What is the smallest possible state where I already know the answer?

Examples:

```text
dp[0] = 0
dp[0] = 1
amount == 0 → 1
```

---

# ⭐ Most Important Lessons

### 1. DP is not about memorizing formulas

The important process is:

```text
Problem
   ↓
State
   ↓
Choices
   ↓
Transition
   ↓
Base Case
   ↓
Memoization / Tabulation
   ↓
Space Optimization
```

### 2. `dp[i]` does NOT always mean the same thing

It depends on the problem.

Examples:

```text
House Robber:
dp[i] = maximum money

Climbing Stairs:
dp[i] = number of ways

Frog Jump:
dp[i] = minimum energy

Coin Change:
dp[i] = minimum coins
```

Always define the state **before coding**.

### 3. Space optimization

If the current state only depends on the previous few states:

```text
dp[i - 1]
dp[i - 2]
```

we don't necessarily need the entire DP array.

We can use:

```text
prev2
prev1
curr
```

This changes:

```text
O(n) space
```

to:

```text
O(1) space
```

---

# 🚀 Current DP Progress

```text
Basic DP
   │
   ├── House Robber
   │
   ├── Climbing Stairs
   │
   ├── Min Cost Climbing Stairs
   │
   ├── Frog Jump
   │
   ├── Frog K-Jump
   │
   ├── Coin Change I
   │
   └── Coin Change II
        └── Recursion + Memoization
```

The next goal is to move from these basic patterns into **more challenging DP patterns**, while continuing to focus on understanding the state and transition instead of memorizing solutions.
