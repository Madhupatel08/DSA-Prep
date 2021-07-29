//next video-->7 BST and BT
import java.util.*;
class l06{
//1. Do------------find ceil and floor of a no in binary tree
//2. predecessor and successor of BST and binary tree
//173 BSt iterator
//510 leetcode //https://github.com/grandyang/leetcode/issues/510

TreeNode prev = null;TreeNode next = null;boolean found = false;
    // public static TreeNode predecessorSuccessor(Treenode root,TreeNode data){
    //     if(root == null){
    //         return null;
    //     }
    //     if(found)
    //     if(root == data){
    //         boolean found = true;
    //         return prev ;
    //     }
    //     predecessorSuccessor(root.left,data);
    //     prev = root;
    //     predecessorSuccessor(root.right,data);
    // }

    //predecessor and successor in binary tree
     public static class allSolPair {
        TreeNode prev = null;
        TreeNode pred = null;
        TreeNode succ = null;

        int ceil = (int) 1e8;
        int floor = -(int) 1e8;
    }

    public static void allSolution(TreeNode node, int data, allSolPair pair) {
        if (node == null)
            return;

        if (node.val < data)
            pair.floor = Math.max(pair.floor, node.val);

        if (node.val > data)
            pair.ceil = Math.min(pair.ceil, node.val);

        allSolution(node.left, data, pair);

        if (node.val == data && pair.pred == null)
            pair.pred = pair.prev;
        if (pair.prev != null && pair.prev.val == data && pair.succ == null)
            pair.succ = node;

        pair.prev = node;

        allSolution(node.right, data, pair);
    }
    //predecessor and successor in BST
    public static void predSuccInBST(TreeNode node, int data) {

        TreeNode curr = node;
        TreeNode pred = null;
        TreeNode succ = null;
        boolean isDataPresent = false;

        while (curr != null) {

            if (curr.val == data) {
                isDataPresent = true;
                if (curr.left != null) {
                    pred = curr.left;
                    while (pred.right != null)
                        pred = pred.right;
                }

                if (curr.right != null) {
                    succ = curr.right;
                    while (succ.left != null)
                        succ = succ.left;
                }

                break;
            } else if (curr.val < data) {
                pred = curr;
                curr = curr.right;
            } else {
                succ = curr;
                curr = curr.left;
            }
        }
    }
   

    // 173//BSt iterator
    class BSTIterator {
        LinkedList<TreeNode> st = new LinkedList<>(); // addFirst(), removeFirst();

        public BSTIterator(TreeNode root) {
            addAllLeft(root);
        }

        public void addAllLeft(TreeNode node) {
            if (node == null)
                return;

            TreeNode curr = node;
            while (curr != null) {
                st.addFirst(curr);
                curr = curr.left;
            }

        }

        public int next() {
            TreeNode rn = st.removeFirst();
            addAllLeft(rn.right);
            return rn.val;
        }

        public boolean hasNext() {
            return st.size() != 0;
        }
    }

    //510// https://github.com/grandyang/leetcode/issues/510
    public Node inorderSuccessor(Node node) {
        
        if(node.right != null){
            node = node.right;
            while(node.left != null) node = node.left;
            
            return node;
        }else{
            while(node != null){
                if(node.parent != null && node.parent.left == node) {
                    return node.parent;    
                }
                node = node.parent;   
            }
            
            return null;
        } 
    }

    public static void main(String[] args){

    }
}

