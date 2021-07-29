//28/3/2021
///////////////////////////////////////////////////////////////////----------------------//////////////
public class l004_TargetSet {
    public static void print1D(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void print2D(int[][] arr) {
        for (int[] ar : arr) {
            print1D(ar);
        }
    }
     public static int coin_permutaion_rec(int amount , int[] coin){
       if(amount == 0){
            return 1;
        }
        
        int ans = 0;
        for(int i = 0 ; i < coins.length ; i++){
            if(amount- coins[i] >= 0 )   
                ans += coin_permutaion_rec(amount - coins[i] ,coins);
        }
        return ans;
    }
    public static int coinChangePermutation_memo(int[] arr, int tar, int[] dp) {
        if (tar == 0) {
            return dp[tar] = 1;
        }

        if (dp[tar] != -1)
            return dp[tar];

        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0) {
                count += coinChangePermutation_memo(arr, tar - ele, dp);
            }
        }

        return dp[tar] = count;
    }

    public static int coinChangePermutation_DP(int[] arr, int Tar, int[] dp) {
        dp[0] = 1;
        for (int tar = 0; tar <= Tar; tar++) {
            for (int ele : arr) {
                if (tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }

        return dp[Tar];
    }
// -----------------------2-d DP MEMO solution-------------------
    public static int coinChangeCombination_memo(int[] arr, int tar, int li, int[][] dp) {
        if (tar == 0) {
            return dp[li][tar] = 1;
        }
        int count = 0;
        for (int i = li; i >= 0; i--)
            if (tar - arr[i] >= 0) {
                count += coinChangeCombination_memo(arr, tar - arr[i], i, dp);
            }

        return dp[li][tar] = count;
    }
//--------------------2-d DP solution   DP----------------
    public static int coinChangeCombination_2DP(int[] arr, int Tar, int LI, int[][] dp) {

        for (int li = 0; li <= LI; li++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (tar == 0) {
                    dp[li][tar] = 1;
                    continue;
                }

                for (int i = li; i >= 0; i--)
                    if (tar - arr[i] >= 0) {
                        dp[li][tar] += dp[i][tar - arr[i]];
                    }
            }
        }

        return dp[LI][Tar];
    }
//---------------------------1 DP solution-------------------------------
//TC : O(target*n)
    public static int coinChangeCombination_1DP(int[] arr, int Tar, int[] dp) {
        dp[0] = 1;
        for (int ele : arr) {
            for (int tar = ele; tar <= Tar; tar++) {
                if (tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }
        return dp[Tar];

    }

    public static void coinChnage() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;

        int[][] dp = new int[arr.length][tar + 1];
        System.out.println(coinChangeCombination_2DP(arr, tar, arr.length - 1, dp));

        print2D(dp);
    }

    // Leetcode 518,322,377
    // 377
    public int combinationSum4(int[] arr, int Tar) {
        int[] dp = new int[Tar + 1];
        dp[0] = 1;
        for (int tar = 0; tar <= Tar; tar++) {
            for (int ele : arr) {
                if (tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }

        return dp[Tar];
    }

    // 518
    public int change(int Tar, int[] arr) {
        int[] dp = new int[Tar + 1];
        dp[0] = 1;
        for (int ele : arr) {
            for (int tar = ele; tar <= Tar; tar++) {
                if (tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }

        return dp[Tar];

    }

    // 322
    public int coinChange(int[] coins, int amount, int[] dp) {
        if (amount == 0) {
            return 0;
        }

        if (dp[amount] != (int) 1e9)
            return dp[amount];

        int minCoins = (int) 1e8;
        for (int ele : coins) {
            if (amount - ele >= 0) {
                minCoins = Math.min(minCoins, coinChange(coins, amount - ele, dp) + 1);

            }
        }

        return dp[amount] = minCoins;
    }

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, (int) 1e9);

        int ans = coinChange(coins, amount, dp);

        return ans != (int) 1e8 ? ans : -1;
    }

    // https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/
    public static int numberOfSolution(int[] arr, int Tar) {
        int[] dp = new int[Tar + 1];

        for (int tar = 0; tar <= Tar; tar++) {
            for (int ele : arr) {
                if (tar - ele >= 0)
                    dp[tar] += dp[tar - ele];
            }
        }

        return dp[Tar];
    }

    public static int numberOfSolution(int[] arr, int Tar, int aTar, int idx, int[] coff) {
        if (Tar == 0) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + "(" + coff[i] + ")");
                if (i != arr.length - 1)
                    System.out.print(" + ");
            }

            System.out.println(" = " + aTar);

            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (Tar - arr[i] >= 0) {
                coff[i]++;
                count += numberOfSolution(arr, Tar - arr[i], aTar, i, coff);
                coff[i]--;
            }
        }

        return count;
    }

    public static void numberOfSolution() {
        int[] arr = { 2, 3, 5 , 7};
        int tar = 10;
        System.out.println(numberOfSolution(arr, tar, tar, 0, new int[arr.length]));
    }

    public static void main(String[] args) {
        // coinChnage();
        numberOfSolution();

    }

    public static void main(String[] args){
        int[] arr = {2,3,5,7};
        int target = 10;
    }
}