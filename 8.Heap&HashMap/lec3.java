class lec3{
    /////////longestConsecutive  128
    //349
    //350
    //128
    //347
    //973
    //kth smallest in sorted matrix
    public int longestConsecutive(int[] nums) {
        
        HashSet<Integer>map  = new HashSet<>();
        
        int ans = 0;
        
        for(int i : nums)
            map.add(i);
        
        for(int i : nums){
            if(!map.contains(i))
                continue;
            int pre = i + 1 , ple = i - 1;
            
            map.remove(i);
            
            while(map.contains(pre)) map.remove(pre++);
            
             while(map.contains(ple)) map.remove(ple--);
            
            ans = Math.max(ans,pre - ple - 1);
        }
        return ans;
    }
    // 347
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        // {val,freq}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        for (Integer key : map.keySet()) {
            pq.add(new int[] { key, map.get(key) });
            if (pq.size() > k)
                pq.remove();
        }

        int[] ans = new int[pq.size()];
        int i = 0;
        while (pq.size() != 0) {
            int[] p = pq.remove();
            int val = p[0];
            int freq = p[1];

            ans[i++] = val;
        }

        return ans;
    }
     // 973
    public int[][] kClosest(int[][] points, int k) {
        // {x,y}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int d1 = a[0] * a[0] + a[1] * a[1]; // x1^2 + y1^2
            int d2 = b[0] * b[0] + b[1] * b[1]; // x2^2 + y2^2

            return d2 - d1;
        });

        for (int[] p : points) {
            pq.add(new int[] { p[0], p[1] });
            if (pq.size() > k)
                pq.remove();
        }

        int[][] ans = new int[k][];
        int i = 0;
        while (pq.size() != 0) {
            int[] p = pq.remove();

            ans[i++] = p;
        }

        return ans;
    }
    //
    public int[][] kClosest(int[][] points, int k) {
        
        PriorityQueue<int[]>pq = new PriorityQueue<>((a,b)->{
           return b[2] - a[2]; 
        });
        
        for(int[] arr: points){
            int dis = (arr[0]*arr[0]) + (arr[1]*arr[1]);
            
            pq.add(new int[]{arr[0],arr[1],dis});
            if(pq.size() > k){
                pq.remove();
            }
            
        }
        int[][] ans = new int[k][2];
        int idx = 0;
        while(pq.size() != 0){
            int[] a = pq.remove();
            int a1 = a[0];
            int a2 = a[1];
            int a3 = a[2];
            
            ans[idx][0] = a1;
            ans[idx][1] = a2;
            idx++;
        }
        return ans;
    }
    //
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int r1 = a / m, c1 = a % m;
            int r2 = b / m, c2 = b % m;

            return matrix[r1][c1] - matrix[r2][c2];
        });

        for (int i = 0; i < n; i++) {
            pq.add(i * m + 0);
        }

        int ans = 0;
        while (k-- > 0) {
            int idx = pq.remove();
            int r = idx / m;
            int c = idx % m;

            ans = matrix[r][c];

            c++;
            if (c < m) {
                pq.add(r * m + c);
            }
        }

        return ans;
    }
    public static void main(String[] args){

    }
}