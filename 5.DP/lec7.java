import java.util.*;
class lec7{
    // https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1
    //LIS--left to right
        public static int[] LIS_LR(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            len = Math.max(len, dp[i]);
        }

        return dp;
    }
     // LDS (right to left)
    public static int[] LIS_RL(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int len = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            len = Math.max(len, dp[i]);
        }
        return dp;
    }
    public int LongestBitonicSequence(int[] arr){
        // Code here
        int[] LIS = LIS_LR(arr);
        int[] LDS = LIS_RL(arr);

        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            len = Math.max(len, LIS[i] + LDS[i] - 1);
        }

        return len;
    }
    
    // https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1
    public static int LBS_uphill(int[] arr) {
        int[] LIS = LIS_LR(arr);
        int[] LDS = LIS_RL(arr);

        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            len = Math.max(len, LIS[i] + LDS[i] - 1);
        }

        return len;
    }

    public static int[] LDS_LR(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            len = Math.max(len, dp[i]);
        }

        return dp;
    }

    public static int[] LDS_RL(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int len = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            len = Math.max(len, dp[i]);
        }

        return dp;
    }

    public static int LBS_downhill(int[] arr) {
        int[] LDS = LDS_LR(arr);
        int[] LIS = LDS_RL(arr);

        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            len = Math.max(len, LDS[i] + LIS[i] - 1);
        }

        return len;
    }

    // https://practice.geeksforgeeks.org/problems/maximum-sum-bitonic-subsequence/0

    // 673
    public int findNumberOfLIS(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int[] count = new int[n];

        int maxLen = 0;
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxCount = count[i];
            } else if (maxLen == dp[i]) {
                maxCount += count[i];
            }
        }

        return maxCount;
    }

    public void allLIS(ArrayList<ArrayList<Integer>> mapping, int[] arr, int idx, int len, String ans) {
        if (len == 1) {
            System.out.println(ans + arr[idx]);
            return;
        }

        for (Integer i : mapping.get(len - 1)) {
            if (i < idx && arr[i] < arr[idx]) {
                allLIS(mapping, arr, i, len - 1, ans + arr[idx] + ", ");
            }
        }
    }

    public void findAllLIS(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            len = Math.max(len, dp[i]);
        }

        ArrayList<ArrayList<Integer>> mapping = new ArrayList<>();
        for (int i = 0; i <= len; i++)
            mapping.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            mapping.get(dp[i]).add(i);
        }

        for (Integer i : mapping.get(len)) {
            allLIS(mapping, arr, i, len, "");
        }
    }

    // https://www.geeksforgeeks.org/dynamic-programming-building-bridges/

    // {{sp1,ep1},{sp2,ep2}.....}
    public static int buildingBridges(int[][] arr) {
        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0]; // this - other, default behaviour.
            // return b[0] - a[0]; // other - this, reverse default behaviour.
        });

        int n = arr.length;
        int[] dp = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j][0] < arr[i][0] && arr[j][1] < arr[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            len = Math.max(len, dp[i]);
        }

        return len;
    }

    // Leetcode 354

    public static void main(String[] args) {

    }
    public static void main(String[] args){

    }
}