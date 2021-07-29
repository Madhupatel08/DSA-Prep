class lec4{
    /********380. Insert Delete GetRandom O(1)*********/
       /** Initialize your data structure here. */
    HashMap<Integer,Integer>map;
    Random rand = new Random();
    ArrayList<Integer>list;
    
    public RandomizedSet() {
       map = new HashMap<>(); 
       list = new ArrayList<>(); 
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }
       list.add(val);
       map.put(val,list.size() - 1); 
       return true; 
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val))
            return false;
       
        int idx = map.get(val);
        int lidx = list.size() - 1;
        int lval = list.get(lidx);
        
        list.set(idx,lval);
        map.put(lval,idx);
        
        list.remove(list.size() - 1);
        
        map.remove(val);
        
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int idx = rand.nextInt(list.size());
        return list.get(idx);
    }
    /**************************************************/
    // 895 PQ solution
    // 895
    class FreqStack {
        // val , freq
        HashMap<Integer, Integer> freq;
        ArrayList<Stack<Integer>> freqMap;
        int maxFreq = 0;

        public FreqStack() {
            freq = new HashMap<>();
            freqMap = new ArrayList<>();
            maxFreq = 0;

            freqMap.add(new Stack<>()); // dummy.
        }

        public void push(int val) { // O(1)
            freq.put(val, freq.getOrDefault(val, 0) + 1);
            maxFreq = Math.max(maxFreq, freq.get(val));

            if (freqMap.size() == maxFreq)
                freqMap.add(new Stack<>());
            freqMap.get(freq.get(val)).add(val);
        }

        public int pop() { // O(1)
            int rv = freqMap.get(maxFreq).pop();
            if (freqMap.get(maxFreq).size() == 0)
                freqMap.remove(maxFreq--);

            freq.put(rv, freq.get(rv) - 1);
            if (freq.get(rv) == 0)
                freq.remove(rv);

            return rv;
        }
    }

    // 895 PQ solution
    class FreqStack {
        private class pair implements Comparable<pair> {
            int val = 0;
            int freq = 0;
            int idx = 0;

            pair(int val, int freq, int idx) {
                this.val = val;
                this.freq = freq;
                this.idx = idx;
            }

            public int compareTo(pair o) {
                if (this.freq == o.freq)
                    return o.idx - this.idx; // other - this, for max PQ

                return o.freq - this.freq;
            }
        }

        private HashMap<Integer, Integer> freqMap;
        private PriorityQueue<pair> pq;
        private int idx = 0;

        public FreqStack() {
            this.freqMap = new HashMap<>();
            this.pq = new PriorityQueue<>();
            this.idx = 0;
        }

        public void push(int val) { // Log(n)
            freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
            pq.add(new pair(val, freqMap.get(val), idx++));
        }

        public int pop() { // Log(n)
            pair p = pq.remove();
            freqMap.put(p.val, freqMap.get(p.val) - 1);
            if (freqMap.get(p.val) == 0)
                freqMap.remove(p.val);

            return p.val;
        }
    }

    public static void main(String[] args){

    }
}