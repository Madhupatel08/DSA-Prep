import java.util.*;
class lec5{
    public int maxUncrossedLines(int[] A, int[] B) {

        int N = A.length;
        int M = B.length;
        int[][] dp = new int[N + 1][M + 1];

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {

                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (A[n - 1] == B[m - 1])
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                else
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
            }
        }

        return dp[N][M];
    }

    // 1458
    public int maxDotProduct_memo(int[] nums1, int[] nums2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = -(int) 1e7;
        }

        if (dp[n][m] != -(int) 1e8)
            return dp[n][m];

        int val = nums1[n - 1] * nums2[m - 1];
        int acceptBothNumber = maxDotProduct_memo(nums1, nums2, n - 1, m - 1, dp) + val;
        int a = maxDotProduct_memo(nums1, nums2, n - 1, m, dp);
        int b = maxDotProduct_memo(nums1, nums2, n, m - 1, dp);

        return dp[n][m] = Math.max(Math.max(acceptBothNumber, val), Math.max(a, b));
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -(int) 1e8);
        return maxDotProduct_memo(nums1, nums2, n, m, dp);
    }

    // 72
    public int minDistance(String word1, String word2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (n != 0) ? n : m;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int insert = minDistance(word1, word2, n, m - 1, dp);
        int delete = minDistance(word1, word2, n - 1, m, dp);
        int replace = minDistance(word1, word2, n - 1, m - 1, dp);

        if (word1.charAt(n - 1) == word2.charAt(m - 1))
            return dp[n][m] = replace;
        else
            return dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
    }

    // follow up
    // cost {inserCost,deleteCost,replaceCost}
    public int minDistanceWithCost(String word1, String word2, int[] cost, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (n != 0) ? n * cost[1] : m * cost[0];
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int insert = minDistanceWithCost(word1, word2, cost, n, m - 1, dp);
        int delete = minDistanceWithCost(word1, word2, cost, n - 1, m, dp);
        int replace = minDistanceWithCost(word1, word2, cost, n - 1, m - 1, dp);

        if (word1.charAt(n - 1) == word2.charAt(m - 1))
            return dp[n][m] = replace + 0;
        else
            return dp[n][m] = Math.min(Math.min(insert + cost[0], delete + cost[1]), replace + cost[2]);
    }

    public int minDistance_dp(String word1, String word2, int N, int M, int[][] dp) {

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = (n != 0) ? n : m;
                    continue;
                }

                int insert = dp[n][m - 1];// minDistance(word1, word2, n, m - 1, dp);
                int delete = dp[n - 1][m];// minDistance(word1, word2, n - 1, m, dp);
                int replace = dp[n - 1][m - 1];// minDistance(word1, word2, n - 1, m - 1, dp);

                if (word1.charAt(n - 1) == word2.charAt(m - 1))
                    dp[n][m] = replace;
                else
                    dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
            }
        }

        return dp[N][M];
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return minDistance(word1, word2, n, m, dp);
    }

    
    // 44
    // -1 -> default, 0 -> false, 1 -> true
    public int isMatch(String s, String p, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0)
                return dp[n][m] = 1;
            else if (m == 1 && p.charAt(m - 1) == '*')
                return dp[n][m] = 1;
            else
                return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        char ch1 = s.charAt(n - 1);
        char ch2 = p.charAt(m - 1);

        if (ch1 == ch2 || ch2 == '?')
            return dp[n][m] = isMatch(s, p, n - 1, m - 1, dp);
        else if (ch2 == '*') {
            boolean res = false;
            res = res || (isMatch(s, p, n - 1, m, dp) == 1); // map to character
            res = res || (isMatch(s, p, n, m - 1, dp) == 1); // map to empty String

            return dp[n][m] = res ? 1 : 0;
        } else
            return dp[n][m] = 0;
    }

    public String removeStars(String s) {
        if (s.length() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));

        int i = 1;
        while (i < s.length()) {
            while (i < s.length() && sb.charAt(sb.length() - 1) == '*' && s.charAt(i) == '*')
                i++;
            if (i < s.length())
                sb.append(s.charAt(i));
            i++;
        }

        return sb.toString();
    }

    public boolean isMatch(String s, String p) {
        p = removeStars(p);
        int n = s.length();
        int m = p.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return isMatch(s, p, n, m, dp) == 1;
    }
     // https://practice.geeksforgeeks.org/problems/count-palindromic-subsequences/1#
    long helperCountPS(String str , int i , int j){
        if( i >= str.length() || j < 0){
            return 0;
        }
        // if(i == j)
        long x , y , z;
        x = helperCountPS(str , i + 1 , j);
        y = helperCountPS(str , i , j - 1);
        z = helperCountPS(str , i + 1 , j - 1);
        if(str.charAt(i) == str.charAt(j)){
            return x + y + 1;
        }else{
            return x + y - z;
        }
    }
    int mod = (int)1e9 + 7 ;
    long helperCountPS_memo(String str , int i , int j , long[][] dp){
       if( i == j){
            return dp[i][j] = 1;
        }
        if( i > j){
            return dp[i][j] = 0;
        }
        
        long x , y , z ;
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        x = helperCountPS_memo(str , i + 1 , j , dp);
        y = helperCountPS_memo(str , i , j - 1,dp);
        z = helperCountPS_memo(str , i + 1 , j - 1,dp);
        if(str.charAt(i) == str.charAt(j)){
            return dp[i][j] = (x + y + 1)%mod;
        }else{
            return dp[i][j] = (x + y - z + mod)%mod;
        }
    }
    long countPS(String str)
    {
        // Your code here
        int n = str.length();
        long [][] dp = new long[n][n];
        for(long[] d : dp){
            Arrays.fill(d,-1);
        }
        return helperCountPS_memo(str , 0 , n - 1 , dp);
    }
    public static void main(String[] args){

    }
}