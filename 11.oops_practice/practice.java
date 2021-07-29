import java.util.*;
public class practice{
    public static void main(String args[]){
        Scanner scn = new Scanner(System.in);
        long t = scn.nextInt();
        while(t-->0){
                long ans = 0;
                int n  = scn.nextInt();
                int[] a = new int[n];
                for(int i = 0; i < n; i++){
                    a[i] = scn.nextInt();
                }
                
                HashMap<Integer,Integer>map = new HashMap<>();
                long maxEle = 0;

                for(int i = 0 ; i < n ; i++){
                    map.put(a[i] - i , map.getOrDefault(a[i] - i,0)+1);
                    maxEle = Math.max(maxEle,map.get(a[i]-i));
                }
                 ans = (maxEle*(maxEle-1))/2;
                if(maxEle == 1){
                    ans = 0;
                }
                System.out.println(ans);
           }   
        }
}