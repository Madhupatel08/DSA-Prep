class lec2{
    //kth largest element --> 215
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> que = new PriorityQueue<>(); // By Default Min.
        for (int ele : nums) {
            que.add(ele);
            if (que.size() > k)
                que.remove();
        }

        return que.peek();
    }
    //optimized/
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void downheapify(int[] arr, int pi, int li) {
        int maxIdx = pi, lci = 2 * pi + 1, rci = 2 * pi + 2;
        if (lci <= li && arr[lci] > arr[maxIdx])
            maxIdx = lci;
        if (rci <= li && arr[rci] > arr[maxIdx])
            maxIdx = rci;
        if (maxIdx != pi) {
            swap(arr, pi, maxIdx);
            downheapify(arr, maxIdx, li);
        }
    }

    public int findKthLargest_Btr(int[] nums, int k) {
        int li = nums.length - 1;
        for (int i = li; i >= 0; i--)
            downheapify(nums, i, li);

        while (k-- > 1) {
            swap(nums, 0, li--);
            downheapify(nums, 0, li);
        }

        return nums[0];
    }
/*******************kth smallest ************************/
    public int findKthLargest_Btr(int[] nums, int k) {
        int li = nums.length - 1;
        for (int i = li; i >= 0; i--)
            downheapify(nums, i, li);

        while (k-- > 1) {
            swap(nums, 0, li--);
            downheapify(nums, 0, li);
        }

        return nums[0];
    }

    public int findKthSmallest(int[] nums, int k) {
        PriorityQueue<Integer> que = new PriorityQueue<>((a, b) -> { // max PQ.
            return b - a;
        }); // By Default Min.

        for (int ele : nums) {
            que.add(ele);
            if (que.size() > k)
                que.remove();
        }

        return que.peek();
    }
    //703 kth largest stream

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int K = 0;

        public KthLargest(int k, int[] nums) {
            this.K = k;
            for (int ele : nums) {
                this.pq.add(ele);
                if (this.pq.size() > this.K)
                    this.pq.remove();

            }
        }

        public int add(int val) {
            this.pq.add(val);
            if (this.pq.size() > this.K)
                this.pq.remove();

            return this.pq.peek();
        }
/////////longestConsecutive  128
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