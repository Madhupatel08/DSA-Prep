// public class client {
//     public static void test() {
//         int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
//         heap pq = new heap(arr, false);

//         while (pq.size() != 0) {
//             System.out.println(pq.remove());
//         }

//     }

//     public static void main(String[] args) {
//         test();

//     }
// }
public class Heap {
    int N = 0;//capacity //or size
    int idx = 0;
    int[] arr = new int[N];
    void constructHeap() { // O(nLogn) (n for array taversal O(log n) for downheapify) -> O(n) //---- proof in copy ---- ///
        for (int i = N - 1; i >= 0; i--) {
            downHeapify(i);
        }
    }
    void defaultValue(boolean isMaxHeap) {
        this.isMaxHeap = isMaxHeap;
    }

    heap() {
        defaultValue(true);
    }

    heap(boolean isMaxHeap) {
        defaultValue(isMaxHeap);
    }

    heap(int[] arr, boolean isMaxHeap) {
        defaultValue(isMaxHeap);

        for (int ele : arr){
            this.arr[idx] = ele;
            idx++;
        }

        constructHeap();
    }

    boolean isEmpty() {
        return N;

    }
    void add(int data) { // O(logn)
        if(idx == N){
            System.out.println("Error");
            return ;
        }
        this.arr[idx] == data;
        
        int n = idx;
        idx++;
        upHeapify(n - 1);
    }
    int getMax() { // O(1)//called when is isMaxHeap is true;
        return this.arr[0];
    }
    int getMax() { // O(1)//called when is isMaxHeap is true;
        return this.arr[0];
    }
    static void downHeapify(int arr[], int n, int i){
        int maxElement = i; 
        int l = 2 * i + 1; 
        int r = 2 * i + 2; 

        if (l < n && arr[l] > arr[maxElement])
            maxElement = l;
  
        if (r < n && arr[r] > arr[maxElement])
            maxElement = r;
  
        if (maxElement != i) {
            int swap = arr[i];
            arr[i] = arr[maxElement];
            arr[maxElement] = swap;
  
            downHeapify(arr, n, maxElement);
        }
    }

    static int RemoveMax(int arr[], int n){//RemoveMin will be same as this
    
        int lastElement = arr[n - 1];

        arr[0] = lastElement;

        n = n - 1;

        heapify(arr, n, 0);
  
        return n;
    }
    static void printArray(int arr[], int n){
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
  
        System.out.println();
    }
    public static void main(String args[]){
        N = 10;

        int arr[] = { 1, 3, 5, 2, 4 };
  
        int n = arr.length;
  
        n = deleteMax(arr, n);
  
        printArray(arr, n);
    }
}