/* 
--> Construct Binarytree From :--> 1.Preorder And Inorder Traversal 
                                   2.Postorder And Inorder Traversal 
                                   3. Levelorder And Inorder Traversal 
                                   4.Preorder And Postorder Traversal  
--> construct BST from Preorder , postOrder, InOrder ,  levelOrder
--> flatten BT
--> 426 //convert BT to sorted doubly LL(gfg)
convert BT to circular LL(gfg)
--> serialize and deserialize 
--> convert a array to tree..
--> tree to array
--> 1003
*/
 ---------------------------------------------------------------------------------------------------------------------------------------
------------106. Construct Binary Tree from Inorder and Postorder Traversal
   // T -> avg : O(nlogn), worst : O(n^2).
--------------------------------------------------------------------------------------------------------------------------------------------
public TreeNode postInTree(int[] post , int psi,int pei,int[] in,int isi,int iei ){
     if(psi > pei)
         return null;
        
        TreeNode node = new TreeNode (post[pei]);
        int idx = isi;
        while(in[idx] != post[pei]){
            idx++;
        }
        int tnel = idx - isi;
        
        node.left = postInTree(post , psi , psi+tnel -1 , in , isi , isi -1);
        node.right = postInTree(post , psi+tnel , pei-1 , in , idx+1 , iei);
        return node;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return postInTree(postorder , 0 , postorder.length - 1 , inorder , 0 , inorder.length - 1);
    }
 /*  
-----------------------------------------------------------------------------------------------------------------------------
------------------------------889. Construct Binary Tree from Preorder and Postorder Traversal
   // T -> avg : O(nlogn), worst : O(n^2).
-----------------------------------------------------------------------------------------------------------------------------
*/
public TreeNode postPreTree(int[] post , int ppsi,int ppei,int[] pre ,int psi , int pei){
        if(psi > pei)
            return null;
        TreeNode node = new TreeNode (pre[psi]);
        
        if(psi == pei)
            return node;
        int idx = ppsi;
        
        while(post[idx] != pre[psi + 1]){
            idx++;
        }
        int tne = idx - ppsi + 1;
        node.left = postPreTree(post , ppsi , idx , pre , psi + 1 , psi + tne);
        node.right = postPreTree(post , idx + 1 , ppei-1 , pre , psi + tne + 1 , pei);
        return node;
    }
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int n = pre.length ;
        return postPreTree(post , 0,n-1,pre,0,n-1);
    }
------------------------------------------------------------------------------------------------------------------------------
----------------105. Construct Binary Tree from Preorder and Inorder Traversal
   // T -> avg : O(nlogn), worst : O(n^2).
--------------------------------------------------------------------------------------------------------------------------------
  public TreeNode helper(int[] pre, int[] in, int psi, int pei, int isi, int iei){
        if(pei < psi)
            return null;
        
        TreeNode root = new TreeNode(pre[psi]);
        
        int x = pre[psi] ; int idx = isi ; int count = 0;
        while(in[idx] != x){
            idx++;
            count++;
        }
    
       root.left =  helper(pre , in , psi + 1 , psi + count , isi , idx - 1 );
       root.right = helper(pre , in , psi + count + 1 , pei , idx + 1 , iei);
       return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);   
    }
------------------------------------------------------------------------------------------------------------------------------------
----------------------------------114 flatten Binary tree
---------------1. O(n^2)
public TreeNode getTail(TreeNode root) {
        if (root == null)
            return null;
        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }

    public void flatten(TreeNode root) {
        if (root == null)
            return;

        flatten(root.left);
        flatten(root.right);

        TreeNode tail = getTail(root.left);
        if (tail != null) {
            tail.right = root.right;
            root.right = root.left;
            root.left = null;
        }
    }
/**************************************************************************************************
---------2nd approach --O(n)*/

 public TreeNode flattern_(TreeNode node) {
        if (node == null || (node.left == null && node.right == null))
            return node;

        TreeNode leftTail = flattern_(node.left);
        TreeNode rightTail = flattern_(node.right);

        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        return rightTail != null ? rightTail : leftTail;
    }

    public void flatten(TreeNode root) {
        if (root == null)
            return;
        flattern_(root);
    }
----------------------------------------------------------------------------------------------------------------------------
----Binary tree to doubly linked list--------------------------
----------------------------------------------------------------------------------------------------------
Node dummy = new Node(-1);
    Node prev = dummy;

    public void treeToDoublyList_(Node root) {
        if (root == null)
            return;

        treeToDoublyList_(root.left);

        prev.right = root;
        root.left = prev;

        prev = root;

        treeToDoublyList_(root.right);

    }

    public Node treeToDoublyList(Node root) {

        if (root == null)
            return root;
        treeToDoublyList_(root);

        Node head = dummy.right;
        head.left = null;
        dummy.right = null;

        prev.right = head;
        head.left = prev;
        return head;

    }
----------------------------------------------------------------------------------------------------------------------------
 297       CONTRUCT TREE FROM ARRAY or prepare inorder using Binary tree
------------------------------------------------------------------------------------------------
 //method 1
 // Encodes a tree to a single string.
    public void helper_Serialize(TreeNode root, StringBuilder str){
        if(root == null){
            str.append("null,");
            return;
        }
        str.append(root.val + ",");
        helper_Serialize(root.left, str);
        helper_Serialize(root.right, str);
    }
    public String serialize(TreeNode root) {
        StringBuilder str = new StringBuilder();
        helper_Serialize(root, str);
        return str.toString();
    }
    int idx = 0;
    public TreeNode helper_DeSerialize(String[] arr){
        if(idx >= arr.length || arr[idx].equals("null")){
            idx++;
            return null;
        }
          
        TreeNode node = new TreeNode(Integer.parseInt(arr[idx]));
        idx++;
        node.left = helper_DeSerialize(arr);
        node.right = helper_DeSerialize(arr);
        return node;
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
       String[] arr = data.split(",");
       return helper_DeSerialize(arr); 
    }
// method 2
    int idx = 0;
public TreeNode createTree(int[] arr) {
    if (idx == arr.length || arr[idx] == -1) {
        idx++;
        return null;
    }
    TreeNode node = new TreeNode(arr[idx]);
    idx++;
    node.left = createTree(arr);
    node.right = createTree(arr);

    return node;
}

ArrayList<Integer>ans = new ArrayList<>();
public static void makeArrayFromTree(TreeNode root){
    if(root == null){
        ans.add(-1);
        return;
    }
    ans.add(root.val);
    makeArrayFromTree(root.left);
    makeArrayFromTree(root.right);
}

public void serializeTree(TreeNode node, ArrayList<Integer> arr) {
        if (node == null) {
            arr.add(-1);
            return;
        }

        arr.add(node.val);
        serializeTree(node.left, arr);
        serializeTree(node.right, arr);
}
// --------------------------------
// 1. construct BST from Preorder

// 1008 leetcode | https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
int idx = 0;
    
    public TreeNode bstFromPreorder_(int[] preorder, int lr, int rr){
        if(idx >= preorder.length || preorder[idx] > rr || preorder[idx] < lr){
            return null;
        }
        
        TreeNode node = new TreeNode(preorder[idx]);
        idx++;
        node.left = bstFromPreorder_(preorder, lr, node.val);
        node.right = bstFromPreorder_(preorder, node.val, rr);
        return node;
    }
    
    public TreeNode bstFromPreorder(int[] preorder) {
        int lr = -(int)1e9;
        int rr = (int)1e9;
        
        return bstFromPreorder_(preorder, lr, rr);
    }
-------------------------------------
                // BST from inOrder  //submit on pep website
------------
public static TreeNode constructFromInOrder(int[] inOrder, int si, int ei ) {
        if(si > ei){
            return null;
        }
    
        int mid = (si+ei)/2;
        TreeNode node = new TreeNode(inOrder[mid]);
        
        node.left = constructFromInOrder(inOrder, si, mid-1);
        node.right = constructFromInOrder(inOrder, mid+1, ei);
        
        return node;
    }
        
    public static TreeNode constructFromInOrder(int[] inOrder) {
        return constructFromInOrder(inOrder, 0, inOrder.length-1);
    }
---------------
        // BST from postOrder
-----
static int idx = 0;
    public static TreeNode bstFromPostorder(int[] postorder, int lr, int rr){
      if(idx < 0 || postorder[idx] < lr || postorder[idx] > rr){
          return null;
      }
       TreeNode node = new TreeNode(postorder[idx]);
       idx--;
       node.right = bstFromPostorder(postorder, node.val, rr);
       node.left = bstFromPostorder(postorder, lr, node.val);
       return node;
    }
    public static TreeNode bstFromPostorder(int[] preorder) {
        int lr = -(int)1e9;
        int rr = (int)1e9;
        idx = preorder.length - 1;
        return bstFromPostorder(preorder, lr, rr);
    }
-------------
        // BST from level order
------
   public static class Pair{
        TreeNode parent = null;
        int lr = -(int)1e9;
        int rr = (int)1e9;
        Pair(TreeNode parent, int lr, int rr){
            this.parent = parent;
            this.lr = lr;
            this.rr = rr;
        }
        Pair(){
            
        }
    }
    
    public static TreeNode constructBSTFromLevelOrder(int[] LevelOrder) {
        int idx = 0;
        
        Queue<Pair>qu = new LinkedList<>();
        qu.add(new Pair());
        TreeNode root = null;
        
        while(idx < LevelOrder.length && qu.size() > 0){
           Pair p =  qu.remove();
           int ele = LevelOrder[idx];
           
           if(ele > p.rr || ele < p.lr) continue;
           
           TreeNode node = new TreeNode(ele);
           
           if(p.parent == null) root = node;
           
           else{
              TreeNode par = p.parent;
              if(par.val > ele) par.left = node;
              else par.right = node;
           }
           qu.add(new Pair(node, p.lr, node.val));
           qu.add(new Pair(node, node.val, p.rr));
           idx++;
        }
      return root;  
    }
-----------

--------