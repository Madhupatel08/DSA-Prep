class bst{
    //construction of bst from sorted array (to be done in recursive fasion)
    //1. find mid ..this will the node (root)
    //2. startIndex to mid - 1 --> will go in left part
    //3. mid + 1 to endIndex --> will go in right part

    public static void main(String[] args){
        createBST(ar, 0, n-1);
    }
    public TreeNode createBST(int[] arr, int si, int ei) {
        if(si > ei) return  null;

        int mid = (si + ei) / 2;
        TreeNode node = new TreeNode(arr[mid]);

        node.left = createBST(arr, si, mid - 1);
        node.right = createBST(arr, mid + 1, ei);

        return node;
    }
}