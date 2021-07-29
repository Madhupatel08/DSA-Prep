import java.util.*;
class lec3{
    //134
    //135
    // 91
    // https://www.lintcode.com/problem/meeting-rooms/
    // https://www.lintcode.com/problem/meeting-rooms-ii/
    // https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    // https://leetcode.com/problems/merge-k-sorted-lists/
    /;
    int numDecodings_recursive(String s, int idx) {
        if (idx == s.length()) {
            return 1;
        }

        char ch1 = s.charAt(idx);
        if (ch1 == '0')
            return 0;

        int count = 0;
        count += numDecodings_recursive(s, idx + 1);

        if (idx < s.length() - 1) {
            char ch2 = s.charAt(idx + 1);
            int num = (ch1 - '0') * 10 + (ch2 - '0');//this is used to convert a number 
            if (num <= 26)
                count += numDecodings_recursive(s, idx + 2);
        }
        return count;
    }
    int numDecodings_memo(String s, int idx, int[] dp) {
        if (idx == s.length()) {
            return dp[idx] = 1;
        }

        if (dp[idx] != -1)
            return dp[idx];

        char ch1 = s.charAt(idx);
        if (ch1 == '0')
            return 0;

        int count = 0;
        count += numDecodings_memo(s, idx + 1, dp);

        if (idx < s.length() - 1) {
            char ch2 = s.charAt(idx + 1);
            int num = (ch1 - '0') * 10 + (ch2 - '0');
            if (num <= 26)
                count += numDecodings_memo(s, idx + 2, dp);
        }
        return dp[idx] = count;
    }

    int numDecodings_DP(String s, int IDX, int[] dp) {
        for (int idx = s.length(); idx >= 0; idx--) {
            if (idx == s.length()) {
                dp[idx] = 1;
                continue;
            }

            char ch1 = s.charAt(idx);
            if (ch1 == '0') {
                dp[idx] = 0;
                continue;
            }

            int count = 0;
            count += dp[idx + 1];// numDecodings_memo(s, idx + 1, dp);

            if (idx < s.length() - 1) {
                char ch2 = s.charAt(idx + 1);
                int num = (ch1 - '0') * 10 + (ch2 - '0');
                if (num <= 26)
                    count += dp[idx + 2];// numDecodings_memo(s, idx + 2, dp);
            }
            dp[idx] = count;
        }

        return dp[IDX];
    }
    int numDecodings_Opti(String s) {
        int a = 1, b = 0;
        for (int idx = s.length() - 1; idx >= 0; idx--) {

            int count = 0;
            char ch1 = s.charAt(idx);
            if (ch1 != '0') {

                count += a;

                if (idx < s.length() - 1) {
                    char ch2 = s.charAt(idx + 1);
                    int num = (ch1 - '0') * 10 + (ch2 - '0');
                    if (num <= 26)
                        count += b;
                }
            }
            b = a;
            a = count;
        }

        return a;
    }
    int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);
        int ans = numDecodings(s, 0, dp);

        for (int ele : dp)
            System.out.print(ele + " ");
        return ans;
    }

    // 639
   int mod = 1000000007;
   int mod = (int)1e9+7;
    public long helper(String s,int idx,long[] dp){
        if (idx == s.length()) {
                return dp[idx] = 1;
            }

            if (s.charAt(idx) == '0') {
               return 0;
            }
            if(dp[idx] != -1){
                return dp[idx];
            }
            long count = 0;
            char ch1 = s.charAt(idx);

            if (ch1 == '*') {
                count = (count + 9 * helper(s , idx + 1,dp) % mod);
                if (idx < s.length() - 1) {
                    char ch2 = s.charAt(idx + 1);
                    if (ch2 == '*')
                        count = (count + 15 * helper(s , idx + 2,dp)) % mod;
                    else if (ch2 >= '0' && ch2 <= '6')
                        count = (count + 2 * helper(s , idx + 2,dp)) % mod;
                    else if (ch2 > '6')
                        count = (count + helper(s , idx + 2,dp)) % mod;

                }
            } else {
                count = (count + helper(s , idx + 1,dp)) % mod;
                if (idx < s.length() - 1) {
                    if (s.charAt(idx + 1) != '*') {
                        char ch2 = s.charAt(idx + 1);
                        int num = (ch1 - '0') * 10 + (ch2 - '0');
                        if (num <= 26)
                            count = (count + helper(s , idx + 2,dp)) % mod;
                    } else {
                        if (ch1 == '1')
                            count = (count + 9 * helper(s , idx + 2,dp)) % mod;
                        else if (ch1 == '2')
                            count = (count + 6 * helper(s , idx + 2,dp)) % mod;
                        else
                            count = (count + helper(s,idx+2,dp) * 0)%mod;
                    }
                }
            }
        return dp[idx] = count;
    }
    public int numDecodings(String s) {
        //first remove the continous stars
        long[] dp = new long[s.length() + 1];
        Arrays.fill(dp,-1);
        return (int)helper(s,0,dp);
    }
     long numDecodings_memo(String s, int idx, long[] dp) {
            if (idx == s.length()) {
                return dp[idx] = 1;
            }

            if (s.charAt(idx) == '0') {
               return 0;
            }
            if(dp[idx] != -1){
                return dp[idx];
            }
            long count = 0;
            char ch1 = s.charAt(idx);

            if (s.charAt(idx) == '*') {
                count = (count + 9 * numDecodings_memo(s , idx + 1,dp) % mod);
                if (idx < s.length() - 1) {
                    char ch2 = s.charAt(idx + 1);
                    if (ch2 == '*')
                        count = (count + 15 * numDecodings_memo(s , idx + 2,dp)) % mod;
                    else if (ch2 >= '0' && ch2 <= '6')
                        count = (count + 2 * numDecodings_memo(s , idx + 2,dp)) % mod;
                    else if (ch2 > '6')
                        count = (count +numDecodings_memo(s , idx + 2,dp)) % mod;

                }
            } else {
                count = (count + numDecodings_memo(s , idx + 1,dp)) % mod;
                if (idx < s.length() - 1) {
                    if (s.charAt(idx + 1) != '*') {
                        char ch2 = s.charAt(idx + 1);
                        int num = (ch1 - '0') * 10 + (ch2 - '0');
                        if (num <= 26)
                            count = (count + numDecodings_memo(s , idx + 2,dp)) % mod;
                    } else {
                        if (s.charAt(idx) == '1')
                            count = (count + 9 * numDecodings_memo(s , idx + 2,dp)) % mod;
                        else if (s.charAt(idx) == '2')
                            count = (count + 6 * numDecodings_memo(s , idx + 2,dp)) % mod;
                    }
                }
            }
        return dp[idx] = count;
    }

    long numDecodings_dp(String s, int IDX, long[] dp) {
        for (int idx = s.length(); idx >= 0; idx--) {
            if (idx == s.length()) {
                dp[idx] = 1;
                continue;
            }

            if (s.charAt(idx) == '0') {
                dp[idx] = 0;
                continue;
            }

            long count = 0;
            char ch1 = s.charAt(idx);

            if (s.charAt(idx) == '*') {
                count = (count + 9 * dp[idx + 1]) % mod;
                if (idx < s.length() - 1) {
                    char ch2 = s.charAt(idx + 1);
                    if (ch2 == '*')
                        count = (count + 15 * dp[idx + 2]) % mod;
                    else if (ch2 >= '0' && ch2 <= '6')
                        count = (count + 2 * dp[idx + 2]) % mod;
                    else if (ch2 > '6')
                        count = (count + dp[idx + 2]) % mod;

                }
            } else {
                count = (count + dp[idx + 1]) % mod;
                if (idx < s.length() - 1) {
                    if (s.charAt(idx + 1) != '*') {
                        char ch2 = s.charAt(idx + 1);
                        int num = (ch1 - '0') * 10 + (ch2 - '0');
                        if (num <= 26)
                            count = (count + dp[idx + 2]) % mod;
                    } else {
                        if (s.charAt(idx) == '1')
                            count = (count + 9 * dp[idx + 2]) % mod;
                        else if (s.charAt(idx) == '2')
                            count = (count + 6 * dp[idx + 2]) % mod;
                    }
                }
            }

            dp[idx] = count;
        }

        return (int) dp[IDX];
    }
        long numDecodings_opti(String s) {
        long a = 1, b = 0;
        for (int idx = s.length() - 1; idx >= 0; idx--) {

            long count = 0;
            char ch1 = s.charAt(idx);
            if (ch1 == '0') {
                count = 0;
            } else if (ch1 == '*') {
                count = (count + 9 * a) % mod;
                if (idx < s.length() - 1) {
                    char ch2 = s.charAt(idx + 1);
                    if (ch2 == '*')
                        count = (count + 15 * b) % mod;
                    else if (ch2 >= '0' && ch2 <= '6')
                        count = (count + 2 * b) % mod;
                    else if (ch2 > '6')
                        count = (count + b) % mod;

                }
            } else {
                count = (count + a) % mod;
                if (idx < s.length() - 1) {
                    if (s.charAt(idx + 1) != '*') {
                        char ch2 = s.charAt(idx + 1);
                        int num = (ch1 - '0') * 10 + (ch2 - '0');
                        if (num <= 26)
                            count = (count + b) % mod;
                    } else {
                        if (s.charAt(idx) == '1')
                            count = (count + 9 * b) % mod;
                        else if (s.charAt(idx) == '2')
                            count = (count + 6 * b) % mod;
                    }
                }
            }

            b = a;
            a = count;
        }

        return (int) a;
    }
    int numDecodings_II(String s) {
        long[] dp = new long[s.length() + 1];
        Arrays.fill(dp, -1);
        long ans = numDecodings_memo(s, 0, dp);
        return (int) ans;
    }
     // https://www.geeksforgeeks.org/count-number-of-ways-to-partition-a-set-into-k-subsets/
    //recursive 
    public static int helper(int n,int k){
        if(n == k){
            return 1;
        }
        if(n < k){
            return 0;
        }
        if(k == 1){
            return 1;
        }
        int count = 0;
        count = count + helper(n - 1 , k - 1);
        count = count + k * helper(n - 1 , k);
        return count;
    }
    //memoization
    public static int noOfWays(int n, int k, int[][] dp) {
        if (k == 1) {
            return dp[n][k] = 1;
        }
        if (n == k) {
            return dp[n][k] = 1;
        }

        if (dp[n][k] != 0)
            return dp[n][k];

        int uniqueGroup = noOfWays(n - 1, k - 1, dp);
        int partOfExisGroup = noOfWays(n - 1, k, dp) * k;

        return dp[n][k] = uniqueGroup + partOfExisGroup;
    }
    //dp
    public static int helper(int N,int K,int[][] dp){
        for(int n = 1 ; n <= N ; n++){
            for(int k = 1 ; k <= K ; k++){
                if (k == 1 ) {
                    dp[n][k] = 1;
                    continue;
                }
                if (n == k) {
                    dp[n][k] = 1;
                    continue;
                }
                if(n < k){
                    dp[n][k] = 0;
                    continue;
                }
                   int uniqueGroup =  dp[n-1][k-1] ;
                   int partOfExisGroup = dp[n-1][k]*k;
                   dp[n][k] = uniqueGroup + partOfExisGroup;
            }
        }
        return dp[N][K];
    }
    public static void main(String[] args){

    }
}