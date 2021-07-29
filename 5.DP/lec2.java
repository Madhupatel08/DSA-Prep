import java.util.*;
class lec2{
//70 leetcode climbing stairs
        public int helper(int n , int i){
        if(i > n){
            return 0;
        }
       if(i == n){
           return 1;
       }
        int count = 0;
        count += helper(n,i+1);
      
        count += helper (n,i+2);
        return count;
    }
     public int helper_memo(int n , int i ,int[] dp){
        if(i > n){
            return 0;
        }
       if(i == n){
           return 1;
       }
         if(dp[i] != -1){
             return dp[i];
         }
        int count = 0;
        count += helper_memo(n, i+1 , dp);
      
        count += helper_memo(n, i+2 , dp);
         dp[i] = count;
        return count;
    }
    public int helper_tabulation(int n ,int[] dp){
        for(int i = 0; i <= n;i++ ){
            if(i<=1){
                dp[i] = 1;
                continue;
            }
           dp[i] = dp[i - 1] + dp[i - 2]; 
        }
        return dp[n];
    }
     public int climbStairs_opti(int N){//twp pointer
        int a = 1;
        int b = 1;
        for(int i = 2 ; i <= N ; i++){
            int sum = a+b;
            a = b;
            b = sum;
        }
        return b;
    }
    public int climbStairs(int n) {
        // return helper(n , 0);
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        // return helper_memo(n , 0, dp);
        return helper_tabulation(n ,dp);
    }
//746. Min Cost Climbing Stairs
    public int climbingStairs_recursive(int n ,int[]cost){
        if(n <= 1){
            return cost[n];
        }
        
        int x = climbingStairs_recursive(n - 1 , cost);
        int y = climbingStairs_recursive(n - 2 , cost);
        return Math.min(x,y) + (n == cost.length ? 0 : cost[n]);
    }
    public int climbingStairs_memo(int n ,int[]cost ,int[] dp){
        if(n <= 1){
            return cost[n];
        }
        if(dp[n] != -1)
            return dp[n];
        int x = climbingStairs_memo(n - 1 , cost , dp);
        int y = climbingStairs_memo(n - 2 , cost ,dp );
        return dp[n]= Math.min(x,y) + (n == cost.length ? 0 : cost[n]);
    }
    public int climbingStairs_tabulation(int n ,int[]cost ,int[] dp){
        for(int i = 0 ; i<= n ; i++ ){
            if(i <= 1){
                dp[i] = cost[i];
                continue;
            }
            int mincost = Math.min(dp[i - 1 ] , dp[i - 2]);
           
            dp[i]= mincost+ (i == cost.length ? 0 : cost[i]);
        }
        
        return dp[n];
    }
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        // return climbingStairs_memo(n , cost , dp);
        return climbingStairs_tabulation(n , cost , dp);
    }
        public static int climbStairs_memo(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = 1;
        }

        if (dp[n] != 0)
            return dp[n];

        return dp[n] = climbStairs_memo(n - 1, dp) + climbStairs_memo(n - 2, dp);
    }

    public static int climbStairs_dp(int N, int[] dp) {

        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }

            dp[n] = dp[n - 1] + dp[n - 2]; // climbStairs_memo(n - 1, dp) + climbStairs_memo(n - 2, dp);
        }

        return dp[N];
    }

    public static int climbStairs(int n) {
        int[] dp = new int[n + 1];
        // int ans = climbStairs_memo(n, dp);
        int ans = climbStairs_dp(n, dp);
        print1D(dp);

        return ans;
    }

    public int minCostClimbingStairs(int n, int[] cost, int[] dp) {
        if (n <= 1)
            return dp[n] = cost[n];

        if (dp[n] != -1)
            return dp[n];

        int minCost = Math.min(minCostClimbingStairs(n - 1, cost, dp), minCostClimbingStairs(n - 2, cost, dp));

        return dp[n] = minCost + (n == cost.length ? 0 : cost[n]);
    }

    public int minCostClimbingStairs_dp(int N, int[] cost, int[] dp) {

        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = cost[n];
                continue;
            }

            int minCost = Math.min(dp[n - 1], dp[n - 2]);

            dp[n] = minCost + (n == cost.length ? 0 : cost[n]);
        }

        return dp[N];
    }

    public int minCostClimbingStairs_Opti(int N, int[] cost) {
        int a = cost[0];
        int b = cost[1];
        for (int i = 2; i < N; i++) {
            int minCost = Math.min(a, b) + cost[i];
            a = b;
            b = minCost;
        }

        return Math.min(a, b);
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        // Arrays.fill(dp,-1);

        return minCostClimbingStairs_dp(n, cost, dp);
    }

    // https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1
    long mod = (int) 1e9 + 7;

    public long countFriendsPairings_memo(int n, long[] dp) {

        if (n <= 1) {
            return dp[n] = 1;
        }

        if (dp[n] != 0)
            return dp[n];

        long single = countFriendsPairings_memo(n - 1, dp);
        long pairup = countFriendsPairings_memo(n - 2, dp) * (n - 1);

        return dp[n] = (single % mod + pairup % mod) % mod;
    }

    public long countFriendsPairings_dp(int N, long[] dp) {

        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }

            long single = dp[n - 1]; // countFriendsPairings_memo(n-1,dp);
            long pairup = dp[n - 2] * (n - 1); // countFriendsPairings_memo(n-2,dp) * (n-1);

            dp[n] = (single % mod + pairup % mod) % mod;
        }

        return dp[N];
    }

    public static long printFriendsPairing(String friends, String ans) {
        if (friends.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        char ch = friends.charAt(0);
        long count = 0;
        count += printFriendsPairing(friends.substring(1), ans + ch + " ");
        for (int i = 1; i < friends.length(); i++) {
            String rstr = friends.substring(1, i) + friends.substring(i + 1);
            count += printFriendsPairing(rstr, ans + ch + friends.charAt(i) + " ");
        }

        return count;
    }

    public static void countFriendsPairings(int n) {
        // long[] dp = new long[n + 1];
        // return countFriendsPairings_memo(n, dp);
        System.out.println(printFriendsPairing("ABCDEF", ""));
    }
77
    public static int goldMine_memo(int r, int c, int[][] mat, int[][] dp, int[][] dir) {
        if (c == mat[0].length - 1) {
            return dp[r][c] = mat[r][c];
        }

        if (dp[r][c] != -1)
            return dp[r][c];

        int maxGold = 0;
        for (int d = 0; d < 3; d++) {
            int x = r + dir[d][0];
            int y = c + dir[d][1];
            if (x >= 0 && x < mat.length) {
                maxGold = Math.max(maxGold, goldMine_memo(x, y, mat, dp, dir));
            }
        }

        return dp[r][c] = maxGold + mat[r][c];
    }

    public static int goldMine_dp(int[][] mat, int[][] dp, int[][] dir) {
        int n = mat.length;
        int m = mat[0].length;

        for (int c = m - 1; c >= 0; c--) {
            for (int r = n - 1; r >= 0; r--) {

                if (c == mat[0].length - 1) {
                    dp[r][c] = mat[r][c];
                    continue;
                }

                int maxGold = 0;
                for (int d = 0; d < 3; d++) {
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
                    if (x >= 0 && x < n) {
                        maxGold = Math.max(maxGold, dp[x][y]);
                    }
                }

                dp[r][c] = maxGold + mat[r][c];
            }
        }

        int maxGold = 0;
        for (int i = 0; i < mat.length; i++) {
            maxGold = Math.max(maxGold, dp[i][0]);
        }

        return maxGold;
    }

    public static void goldMine() {
        int[][] mat = { { 1, 3, 1, 5 }, { 2, 2, 4, 1 }, { 5, 0, 2, 3 }, { 0, 6, 1, 2 } };

        int[][] dp = new int[mat.length][mat[0].length];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };

        int maxGold = 0;
        for (int i = 0; i < mat.length; i++) {
            maxGold = Math.max(maxGold, goldMine_memo(i, 0, mat, dp, dir));
        }

        System.out.println(goldMine_dp(mat, dp, dir));
        print2D(dp);
        // System.out.println(maxGold);
    }
    //gold mine --> my code
    static int maxGold_helper(int n , int m , int M[][] , int i , int j ){
        if( i >= n || j >= m || i < 0 || j < 0 ){
            return 0;
        }
        if( j == m - 1){
            return M[i][j];
        }
        
        int x = maxGold_helper ( n , m , M , i + 1 , j + 1);
        int y = maxGold_helper ( n , m , M , i - 1 , j + 1);
        int z = maxGold_helper ( n , m , M , i , j + 1 );//right
        
        return Math.max(Math.max(x,y),z ) + M[i][j];
    }
    static int maxGold(int n, int m, int M[][])
    {
        // code here
        int maxGold = 0;
        for(int i = 0 ; i < n ; i++){
            maxGold = Math.max(maxGold,maxGold_helper( n , m , M , i , 0 ));
        }
       return maxGold;
    }
    //memoization
    static int maxGold_helper(int n , int m , int M[][] , int i , int j  , int[][] dp){
        if( i >= n || j >= m || i < 0 || j < 0 ){
            return 0;
        }
        if( j == m - 1){
            return dp[i][j] =  M[i][j];
        }
        
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        
        int x = maxGold_helper ( n , m , M , i + 1 , j + 1 , dp);
        int y = maxGold_helper ( n , m , M , i - 1 , j + 1 , dp);
        int z = maxGold_helper ( n , m , M , i , j + 1  , dp);//right
        
        return dp[i][j] = Math.max(Math.max(x,y),z ) + M[i][j];
    }
    static int maxGold(int n, int m, int M[][])
    {
        // code here
        int maxGold = 0;
        int[][] dp = new int[n][m];
        for(int[] d : dp){
            Arrays.fill(d , -1 );
        }
        for(int i = 0 ; i < n ; i++){
            maxGold = Math.max(maxGold,maxGold_helper( n , m , M , i , 0 , dp ));
        }
       return maxGold;
    }
}