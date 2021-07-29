class lec6{
    //875 koko eating banana

    //question can be asked in this format 
    //cost = (capacity of ship*p)//minimize the cost
    //and tou can choose in any order
        // 875
    // O(n)
    public boolean isPossibleToEat(int[] arr, int eatingSpeed, int hour) {
        int hr = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            hr += Math.ceil(arr[i] / (eatingSpeed * 1.0));
            if (hr > hour)
                return false;
        }

        return true;
    }

    public int minEatingSpeed(int[] piles, int h) {
        // Arrays.sort(piles);
        int n = piles.length, si = 1, ei = (int) 1e9;

        while (si < ei) {
            int eatingSpeed = (si + ei) / 2;
            if (!isPossibleToEat(piles, eatingSpeed, h))
                si = eatingSpeed + 1;
            else
                ei = eatingSpeed;
        }

        return si;
    }

    // 1011
    public boolean isPossibleToShip(int[] weight, int capacity, int days) {
        int d = 1;
        int totalWeightPerDay = 0;
        for (int w : weight) {
            totalWeightPerDay += w;
            if (totalWeightPerDay > capacity) {
                d++;
                totalWeightPerDay = w;
            }

            if (d > days)
                return false;
        }

        return true;
    }

    public int shipWithinDays(int[] weights, int days) {
        int maxEle = 0, sum = 0;
        for (int w : weights) {
            maxEle = Math.max(maxEle, w);
            sum += w;
        }

        int si = maxEle, ei = sum;
        while (si < ei) {
            int capacity = (si + ei) / 2;
            if (!isPossibleToShip(weights, capacity, days))
                si = capacity + 1;
            else
                ei = capacity;
        }

        return si;
    }

    public boolean isPossibleToShip2(int[] weight, int capacity, int days) {
        int d = 1;
        int totalWeightPerDay = 0;
        for (int w : weight) {
            if (w > capacity)
                return false;
            totalWeightPerDay += w;
            if (totalWeightPerDay > capacity) {
                d++;
                totalWeightPerDay = w;
            }

            if (d > days)
                return false;
        }

        return true;
    }

    public int shipWithinDays2(int[] weights, int days) {
        int si = 1, ei = (int) 1e7;
        while (si < ei) {
            int capacity = (si + ei) / 2;
            if (!isPossibleToShip(weights, capacity, days))
                si = capacity + 1;
            else
                ei = capacity;
        }

        return si;
    }
    //https://leetcode.com/discuss/interview-question/348510/Google-or-OA-2019-or-Maximum-Area-Serving-Cake
    public static boolean isPossibleToServeCake(int[] radiusArray, double cakeArea, int guest) {
        int g = 0;
        for (int i = radiusArray.length - 1; i >= 0; i--) {
            double area = Math.PI * radiusArray[i] * radiusArray[i];
            g += Math.floor(area / cakeArea);
            if (g >= guest)
                return true;

        }
        return false;
    }

    public static double maximumAreaCake(int[] radius, int guest) {
        // Arrays.sort(radius);
        int n = radius.length;
        // double si = (Math.PI * radius[0] * radius[0]) / guest, ei = Math.PI *
        // radius[n - 1] * radius[n - 1];
        double si = 0.0, ei = 1e7;
        while ((ei - si) > 1e-5) {
            double cakeArea = (si + ei) / 2.0;
            if (!isPossibleToServeCake(radius, cakeArea, guest))
                ei = cakeArea - 1e-5;
            else
                si = cakeArea;

        }

        return si;
    }

    public static boolean itIsCorrectPenatly(int[] arr, double distance, int k) {
        int noOfGasStation = 0, n = arr.length;
        for (int i = 1; i < n; i++) {
            noOfGasStation += (arr[i] - arr[i - 1]) / distance;
            if (noOfGasStation > k)
                return false;
        }

        return true;
    }
    //774 minimize max distance to gas Station
   
    public static double minmaxGasDist(int[] stations, int k) {
        double si = 0.0, ei = 1e9;
        while ((ei - si) > 1e-6) {
            double distance = (ei + si) / 2.0;
            if (!itIsCorrectPenatly(stations, distance, k))
                si = distance + 1e-6;
            else
                ei = distance;
        }

        return ei;
    }

    public static void main(String[] args) {
        int[] arr = { 23,24,36,39,46,56,57,65,84,98};
        System.out.println(minmaxGasDist(arr, 1));
    }
     public static void main(String[] args){
        
    }
}