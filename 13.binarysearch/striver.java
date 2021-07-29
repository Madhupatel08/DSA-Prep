class striver{
    //1. nth root of a number 
    //root = 3; n = 127  
    
    //search space  1 to 27  ==> mid = (1+27)/2 == 14 but 14*14*14 is greater than 27 so search space is reduced
    //new serach space 1 to 14 ==>mid=(1+14)/2==7.5 but 7.5*7.5*7.5 is greater than 14 so search space is reduced
    //new search space 1 to 7.5 --> mid 4.25
    //TC --> 
    public static double multiply(double no, int n){
        double ans = 1.0;
        for(int i = 1 ; i <= n ; i++){
            ans = ans * no;
        }
        return ans;
    }
    public static void getNthRoot(int n, int m){
        double low = 1;
        double high = m;
        double limit = 1e-6;//10^-6

        while((high - low) > limit){
            double mid = (low + high)/2.0;
            if(multiply(mid,n) < m){
                low = mid;
            }else{
                high = mid;
            }
        }
        System.out.println(low + " " + high);
    }
    //aggresive cows 
    //n = 5 []
    public static void aggresiveCows(int[]distance, int n){
        int n = distance.length;
        int low = 1;
        int high = distance[n-1];
    }
    public static void main(String[] args){
        int n = 3; int m = 27;
        getNthRoot(n,m);
    }

}