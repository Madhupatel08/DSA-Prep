class lec9{
    // https://classroom.pepcoding.com/data-structures-and-algorithms---levelup-pitampura-jun-01-2020/54/classvideos/2570/
    // https://www.geeksforgeeks.org/subset-sum-problem-dp-25/

    public static int targetSum(int[] arr, int n, int tar, int[][] dp) {
        if (n == 0 || tar == 0) {
            return dp[n][tar] = (tar == 0) ? 1 : 0;
        }

        if (dp[n][tar] != -1)
            return dp[n][tar];

        boolean res = false;
        if (tar - arr[n - 1] >= 0)
            res = res || (targetSum(arr, n - 1, tar - arr[n - 1], dp) == 1);

        res = res || (targetSum(arr, n - 1, tar, dp) == 1);

        return dp[n][tar] = res ? 1 : 0;
    }
      static int subset(int[] arr , int Tar,int[][] dp){
       int N = arr.length;
        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if(tar == 0){
                    dp[n][tar] = 1;
                    continue;
                }
                if( n == 0 ) {
                    dp[n][tar] = 0;
                    continue;
                }
                dp[n][tar] = dp[n - 1][tar];
                if (tar - arr[n - 1] >= 0 && dp[n][tar] == 0)
                    dp[n][tar] =  dp[n - 1][tar - arr[n - 1]];
                
            }
        }
        return dp[N][Tar];
    }
      static int total_subset(int[] arr , int Tar,int[][] dp){
       int N = arr.length;
        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if(tar == 0){
                    dp[n][tar] = 1;
                    continue;
                }
                if( n == 0 ) {
                    dp[n][tar] = 0;
                    continue;
                }
                dp[n][tar] += dp[n - 1][tar];
                if (tar - arr[n - 1] >= 0 && dp[n][tar] == 0)
                    dp[n][tar] +=  dp[n - 1][tar - arr[n - 1]];
                
            }
        }
        return dp[N][Tar];
    }
    // public static boolean targetSum_DP(int[] arr, int Tar) {
    //     int N = arr.length;
    //     boolean[][] dp = new boolean[N + 1][Tar + 1];

    //     for (int n = 0; n <= N; n++) {
    //         for (int tar = 0; tar <= Tar; tar++) {
    //             if (n == 0 || tar == 0) {
    //                 dp[n][tar] = (tar == 0);
    //                 continue;
    //             }

    //             if (tar - arr[n - 1] >= 0)
    //                 dp[n][tar] = dp[n][tar] || dp[n - 1][tar - arr[n - 1]];
    //             dp[n][tar] = dp[n][tar] || dp[n - 1][tar];
    //         }
    //     }

    //     return dp[N][Tar];
    // }

    public static int targetSumTotalWays_DP(int[] arr, int Tar) {
        int N = arr.length;
        int[][] dp = new int[N + 1][Tar + 1];

        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0) ? 1 : 0;
                    continue;
                }
  
                if (tar - arr[n - 1] >= 0)
                    dp[n][tar] += dp[n - 1][tar - arr[n - 1]];
                dp[n][tar] += dp[n - 1][tar];
            }
        }

        return dp[N][Tar];
    }

    public static void targetSum(int[] arr, int tar) {
        int n = arr.length;
        int[][] dp = new int[n + 1][tar + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        boolean res = targetSum(arr, n, tar, dp) == 1;
        System.out.println(res);
        print2D(dp);
    }
    static int subset(int[] arr , int target,int idx){
       if(idx == arr.length)
         return 0; 
       if(target == 0)
            return 1;
       int ans =  subset(arr,target - arr[idx],idx+1);
       if(ans == 1)
        return 1;
       return subset(arr,target,idx+1);
    }
    static int subset(int[] arr , int target,int idx,int[][] dp){
        if(target < 0)
           return 0;
        if(idx >= arr.length)
         return dp[target][idx] = 0; 
       if(target == 0)
            return dp[target][idx] = 1;
       if(dp[target][idx] != -1)
           return dp[target][idx];
        int ans =  subset(arr,target - arr[idx] , idx+1 , dp);
       if(ans == 1)
         return dp[target][idx] = 1;
       return dp[target][idx] = subset(arr , target , idx+1 , dp);
    }
    // 416
    public boolean canPartition(int[] arr) {
        int sum = 0;
        for (int ele : arr)
            sum += ele;

        if (sum % 2 != 0)
            return false;

        int Tar = sum / 2;
        int N = arr.length;
        boolean[][] dp = new boolean[N + 1][Tar + 1];

        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0);
                    continue;
                }

                if (tar - arr[n - 1] >= 0)
                    dp[n][tar] = dp[n][tar] || dp[n - 1][tar - arr[n - 1]];
                dp[n][tar] = dp[n][tar] || dp[n - 1][tar];
            }
        }

        return dp[N][Tar];
    }
//494
//recursive
    public int findTargetSumWays2(int[] arr, int idx,int sum,int tar) {
        if (idx == arr.length) {
            if(tar == sum)
                return 1;
            return  0;
        }
        int count = 0;
        count += findTargetSumWays2(arr, idx + 1,sum + arr[idx],tar); // as a positive number
        count += findTargetSumWays2(arr, idx + 1,sum - arr[idx],tar); // as a Negative number

        return count;
    }
    public int findTargetSumWays(int[] nums, int tar) {
        int ans = findTargetSumWays2(nums,0,0,tar);
        return ans;
    }
    //memoization

    ///////////////////second method
    public int findTargetSumWays2(int[] arr, int n, int tar, int sum, int[][] dp) {
        if(tar < 0 || tar >= (2 * sum + 1) )
            return 0;
        if (n == 0) {
            if(sum == tar)
                return dp[n][tar] = 1;
            return dp[n][tar] = 0;
        }

        if (dp[n][tar] != -1)
            return dp[n][tar];

        int count = 0;
        count += findTargetSumWays2(arr, n - 1, tar + arr[n-1], sum, dp); // as a positive number
        count += findTargetSumWays2(arr, n - 1, tar - arr[n-1], sum, dp); // as a Negative number

        return dp[n][tar] = count;
    }

    public int findTargetSumWays(int[] nums, int tar) {
        int sum = 0;
        for (int ele : nums)
            sum += ele;

        if (sum < tar || tar < -sum)
            return 0;
        int n = nums.length;
        int[][] dp = new int[n + 1][2 * sum + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = findTargetSumWays2(nums, n, sum + tar, sum, dp);
        return ans;
    }
      // By using 2D_DP
    public int findTargetSumWays2(int[] arr, int n, int tar, int sum, int[][] dp) {
        if (n == 0) {
            return dp[n][sum] = (tar == sum) ? 1 : 0;
        }

        if (dp[n][sum] != -1)
            return dp[n][sum];

        int count = 0;
        count += findTargetSumWays2(arr, n - 1, tar, sum + arr[n - 1], dp); // as a positive number
        count += findTargetSumWays2(arr, n - 1, tar, sum - arr[n - 1], dp); // as a Negative number

        return dp[n][sum] = count;
    }

    public int findTargetSumWays(int[] nums, int tar) {
        int sum = 0;
        for (int ele : nums)
            sum += ele;

        if (sum < tar || tar < -sum)
            return 0;
        ``````````````````````````````````````````````````````````    sw3

        int ans = findTargetSumWays2(nums, n, sum + tar, sum, dp);
        return ans;
    }
    // https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
    public static int knapsack(int[] weight, int[] value, int n, int bagWeight, int[][] dp) {
        if (n == 0 || bagWeight == 0) {
            return dp[n][bagWeight] = 0;
        }

        if (dp[n][bagWeight] != 0)
            return dp[n][bagWeight];

        int maxCost = 0;
        if (bagWeight - weight[n - 1] >= 0)
            maxCost = knapsack(weight, value, n - 1, bagWeight - weight[n - 1], dp) + value[n - 1];
        maxCost = Math.max(maxCost, knapsack(weight, value, n - 1, bagWeight, dp));

        return dp[n][bagWeight] = maxCost;
    }

    public static int knapsack_DP(int[] weight, int[] value, int BagWeight) {
        int N = weight.length;
        int[][] dp = new int[N + 1][BagWeight + 1];

        for (int n = 0; n <= N; n++) {
            for (int bagWeight = 0; bagWeight <= BagWeight; bagWeight++) {
                if (n == 0 || bagWeight == 0) {
                    dp[n][bagWeight] = 0;
                    continue;
                }

                if (bagWeight - weight[n - 1] >= 0)
                    dp[n][bagWeight] = dp[n - 1][bagWeight - weight[n - 1]] + value[n - 1];
                dp[n][bagWeight] = Math.max(dp[n][bagWeight], dp[n - 1][bagWeight]);
            }
        }

        return dp[N][BagWeight];
    }

    public static int knapsack_unbounded(int[] weight, int[] value, int BagWeight) {
        // combination
        int n = weight.length;
        int[] dp = new int[BagWeight + 1];
        for (int i = 0; i < n; i++) {
            for (int bagWeight = weight[i]; bagWeight <= BagWeight; bagWeight++) {
                dp[bagWeight] = Math.max(dp[bagWeight], dp[bagWeight - weight[i]] + value[i]);
            }
        }

        return dp[BagWeight];
    }

    // 698
    public boolean canPartitionKSubsets(int[] arr, int k, int idx, int sumSF, int tar, boolean[] vis) {
        if (k == 0)
            return true;
        if (sumSF > tar)
            return false;
        if (sumSF == tar) {
            return canPartitionKSubsets(arr, k - 1, 0, 0, tar, vis);
        }

        boolean res = false;
        for (int i = idx; i < arr.length; i++) {
            if (vis[i])
                continue;
            vis[i] = true;
            res = res || canPartitionKSubsets(arr, k, i + 1, sumSF + arr[i], tar, vis);
            vis[i] = false;
        }
        return res;
    }

    public boolean canPartitionKSubsets(int[] arr, int k) {
        int n = arr.length;
        int sum = 0;
        int maxEle = 0;
        for (int ele : arr) {
            sum += ele;
            maxEle = Math.max(maxEle, ele);
        }

        if (sum % k != 0 || maxEle > sum / k)
            return false;
        boolean[] vis = new boolean[n];

        return canPartitionKSubsets(arr, k, 0, 0, sum / k, vis);
    }

    public static void main(String[] args) {
        // coinChnage();
        // numberOfSolution();

        targetSum(new int[] { 2, 3, 5, 7 }, 10);
    }

}
    public static void main(String[] args){
        
    }

}