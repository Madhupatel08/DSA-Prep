import java.util.Arrays;
class lec1{
    public static void print1D(int[] dp){
        for(int ele : dp){
            System.out.print(ele + " ");
        }
        System.out.println();
    }
    public static void print2D(int[][] dp){
        for(int[]ele : dp){
           print1D(ele); 
        }
    }
    public static int fibo_recursive(int n){
        if(n <= 1) return n;
        int ans = fibo_recursive(n - 1) + fibo_recursive(n - 2);
        return ans;
    }

    public static int fibo_mamoziation(int n , int[] dp){
        if(n <= 1){
             return dp[n] = n;//hum kabhi negative value store nhi hone de rahe so that will be our default value
        }
        if(dp[n] != -1){  //-1 is the default value //it should be corrcet//else it may lead to case when mamoization doesn't pass
            return dp[n];//-1 ans ka prt kabhi nhi ho sakta so its default
        }
        int ans = fibo_mamoziation(n - 1 , dp) + fibo_mamoziation(n - 2 , dp);
        return dp[n] = ans;
    }
    
    public static int fibo_tabulation(int N , int[] dp){
        for(int n = 0 ; n <= N ; n++){
            if(n <= 1){
                dp[n] = n;
                continue;
            }
            int ans = dp[n - 1] + dp[n - 2];//fibo_mamoziayio(n-1 , dp)+fibo_mamozation(n-2 , dp)

            dp[n] = ans;
        }
        return dp[N];
    }
    
    public static int fib_twoPointer(int N) {
        int a = 0, b = 1;
        for (int n = 0; n < N; n++) {
            // System.out.print(a + " ");

            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }

    public static void fibo() {
        int n = 8;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        System.out.println(fibo_mamoziation(n, dp));
        System.out.println(fibo_tabulation(n, dp));
        System.out.println(fib_twoPointer(n));

        print1D(dp);
    }
    public static int mazePath_Recu(int src , int dest , int i , int j){
        if(i > dest || i < 0 || j > dest || j < 0){
            return 0;
        }
        if(i == dest && j == dest){
            return 1;
        }
        int x = mazePath_Recu( src , dest , i + 1 , j);//down
        int y = mazePath_Recu( src , dest , i , j + 1);//right
        int z = mazePath_Recu( src , dest , i + 1 , j + 1);//diogonalu
        return x + y + z;
    }

    //sr = starting row // sc = starting colm // er = ending row //ec = ending coloumn
  public static int mazePath_memo(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }
        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        if (sc + 1 <= ec)
            count += mazePath_memo(sr, sc + 1, er, ec, dp);//
        if (sr + 1 <= er)
            count += mazePath_memo(sr + 1, sc, er, ec, dp);//
        if (sc + 1 <= ec && sr + 1 <= er)
            count += mazePath_memo(sr + 1, sc + 1, er, ec, dp);//dio

        return dp[sr][sc] = count;
    }
    public static int mazePath_Tabulation(int SR, int SC, int er, int ec, int[][] dp) {
        for (int sr = er; sr >= 0; sr--) {
            for (int sc = ec; sc >= 0; sc--) {

                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                if (sc + 1 <= ec)
                    count += dp[sr][sc + 1];// mazePath_memo(sr, sc + 1, er, ec, dp);
                if (sr + 1 <= er)
                    count += dp[sr + 1][sc];// mazePath_memo(sr + 1, sc, er, ec, dp);
                if (sc + 1 <= ec && sr + 1 <= er)
                    count += dp[sr + 1][sc + 1]; // mazePath_memo(sr + 1, sc + 1, er, ec, dp);

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }
    public static int mazePathInfi_memo(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        for (int jump = 1; sc + jump <= ec; jump++)
            count += mazePathInfi_memo(sr, sc + jump, er, ec, dp);
        for (int jump = 1; sr + jump <= er; jump++)
            count += mazePathInfi_memo(sr + jump, sc, er, ec, dp);
        for (int jump = 1; sc + jump <= ec && sr + jump <= er; jump++)
            count += mazePathInfi_memo(sr + jump, sc + jump, er, ec, dp);

        return dp[sr][sc] = count;
    }

    public static int mazePathInfi_DP(int SR, int SC, int er, int ec, int[][] dp) {

        for (int sr = er; sr >= SR; sr--) {
            for (int sc = ec; sc >= SC; sc--) {

                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }
                int count = 0;
                for (int jump = 1; sc + jump <= ec; jump++)
                    count += dp[sr][sc + jump]; // mazePathInfi_memo(sr, sc + jump, er, ec, dp);
                for (int jump = 1; sr + jump <= er; jump++)
                    count += dp[sr + jump][sc]; // mazePathInfi_memo(sr + jump, sc, er, ec, dp);
                for (int jump = 1; sc + jump <= ec && sr + jump <= er; jump++)
                    count += dp[sr + jump][sc + jump]; // mazePathInfi_memo(sr + jump, sc + jump, er, ec, dp);

                dp[sr][sc] = count;
            }
        }
        return dp[SR][SC];
    }

    public static void mazePath(){
        int src = 0 , dest = 3;
        System.out.println(mazePath_Recu( src , dest , 0 , 0 ));
        int n = 5;
        int m = 5;
        int[][] dp = new int[n][m];
        // System.out.println(mazePath_memo(0, 0, n - 1, m - 1, dp));
        // System.out.println(mazePathInfi_memo(0, 0, n - 1, m - 1, dp));
        System.out.println(mazePathInfi_DP(0, 0, n - 1, m - 1, dp));
        print2D(dp);
    }
    public static int dicee_recursive(int sp , int ep){
        if(sp == dest){
            return 1;
        }
        int count = 0;
        for(int dice = 1 ; dice <= 6 && sp + dice <= ep ; dice++){
            count += dicee_recursive(sp + dice , ep );   
        }
        return count;
    }
    public static int dice_memo(int sp, int ep, int[] dp) {
        if (sp == ep) {
            return dp[sp] = 1;
        }
        if (dp[sp] != 0)
            return dp[sp];

        int count = 0;
        for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
            count += dice_memo(sp + dice, ep, dp);
        }
        return dp[sp] = count;
    }
    public static int dice_tabulation(int SP, int ep, int[] dp) {
        for (int sp = ep; sp >= SP; sp--) {
            if (sp == ep) {
                dp[sp] = 1;
                continue;
            }
            int count = 0;
            for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
                count += dp[sp + dice];// boardPath_memo(sp + dice, ep, dp);
            }
            dp[sp] = count;
        }
        return dp[SP];
    }
    //using linkedList
    public static int dice_optimization(int sp, int ep) {
        LinkedList<Integer> list = new LinkedList<>();

        for (sp = ep; sp >= 0; sp--) {
            if (sp >= ep - 1)
                list.addFirst(1);
            else {
                if (list.size() <= 6)
                    list.addFirst(list.getFirst() * 2);
                else
                    list.addFirst(list.getFirst() * 2 - list.removeLast());
            }
        }
        return list.getFirst();
    }
    public static void diceGame(){
        int src = 1;
        int dest = 10; 

        System.out.println(dicee_recursive(src , dest ));
        
        int[] dp = new int[dest + 1];
        Arrays.fill(dp,-1);
        System.out.println(dicee_memo(0 ,dest, dp));
        print1D(dp);
         System.out.println(boardPath_DP(0, n, dp));
        System.out.println(boardPath_opti(0,n));

        print1D(dp);
    }
    public static void main(String[] args){
        // fibo();
        // mazePath();
        diceGame();
    }
}