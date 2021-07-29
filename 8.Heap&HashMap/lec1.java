class lec1{

    public static int heightOfTree(int[] arr,int idx){
        //idx = parent idx
        if(idx >= arr.length){
            return -1;
        }
        
        int lh = heightOfTree(arr, 2*idx + 1);//left child
        int rh = heightOfTree(arr , 2*idx + 2);//right child

        return Math.max(rh,rh) + 1;
        //all questions can be done such as diameter of tree,size etc using this method
    }
    //implementation
    public static void main(String[] args){
        
    }

}