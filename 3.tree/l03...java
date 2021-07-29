import java.util.*;
class l03..{
/*------------------------------------------------------------------------------------------------------------
            ----------124 leetcode   Binary Tree Maximum Path sum
---------------------------------------------------------------------------------------------------------
 */
    int maxNTN = -(int) 1e9;

    public int maxPathSum_(TreeNode node) {
        if (node == null)
            return 0;

        int leftMaxPathSum = maxPathSum_(node.left);
        int rightMaxPathSum = maxPathSum_(node.right);

        int maxSumTillRoot = Math.max(leftMaxPathSum, rightMaxPathSum) + node.val;
        maxNTN = Math.max(Math.max(maxNTN, maxSumTillRoot),
                Math.max(node.val, leftMaxPathSum + node.val + rightMaxPathSum));

        return Math.max(maxSumTillRoot, node.val);

    }

    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;

        maxPathSum_(root);
        return maxNTN;
    }
// -----------2nd Method -----------------

    public class maxNodeToNodePair {
        int NTN_sum = -(int) 1e9; // Node to Node sum
        int NTR_sum = 0; // Node to root sum

        maxNodeToNodePair(int NTN_sum, int NTR_sum) {
            this.NTN_sum = NTN_sum;
            this.NTR_sum = NTR_sum;
        }

        maxNodeToNodePair() {

        }
    }

    public int maxValue(int... arr) {
        int max = arr[0];
        for (int ele : arr) {
            max = Math.max(max, ele);
        }

        return max;
    }

    // {nodeToNode, rootToNode}
    public maxNodeToNodePair maxPathSum2_(TreeNode node) {
        if (node == null)
            return new maxNodeToNodePair();

        maxNodeToNodePair lpair = maxPathSum2_(node.left);
        maxNodeToNodePair rpair = maxPathSum2_(node.right);

        maxNodeToNodePair myAns = new maxNodeToNodePair();

        myAns.NTN_sum = maxValue(lpair.NTN_sum, rpair.NTN_sum, node.val, lpair.NTR_sum + node.val,
                rpair.NTR_sum + node.val, lpair.NTR_sum + node.val + rpair.NTR_sum);
        myAns.NTR_sum = maxValue(node.val, lpair.NTR_sum + node.val, rpair.NTR_sum + node.val);

        return myAns;
    }

    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;

        return maxPathSum2_(root).NTN_sum;
    }
  


/*----------------------------------------------------------------------------------------------------------
--------------------- 98  VALID BST 
--------------------------------------------------------------------------------------------------------------
*/    // 98
    public class BSTpair {
        boolean isBST = true;
        long max = -(long) 1e13;
        long min = (long) 1e13;

        BSTpair(long max, long min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }

        BSTpair() {

        }

    }

    public BSTpair isValidBST_(TreeNode root) {
        if (root == null)
            return new BSTpair();

        BSTpair lres = isValidBST_(root.left);
        BSTpair rres = isValidBST_(root.right);

        BSTpair myRes = new BSTpair();
        myRes.isBST = lres.isBST && rres.isBST && lres.max < root.val && root.val < rres.min;
        if (!myRes.isBST)
            return myRes;

        myRes.max = Math.max(rres.max, root.val);
        myRes.min = Math.min(lres.min, root.val);

        return myRes;
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST_(root).isBST;
    }

    long prevBSTNode = -(long) 1e13;

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        if (!isValidBST(root.left))
            return false;

        if (root.val <= prevBSTNode) {
            return false;
        }

        prevBSTNode = root.val;
        if (!isValidBST(root.right))
            return false;

        return true;
    }
/*-----------------------------------------------------------------------------------------------------------
---------- 99 RECOVER BINARY TREE---
-------------------------------------------------------------------------------------------------------------
 */   // 99
    TreeNode a = null, b = null, prev = null;

    public boolean recoverTree_(TreeNode root) {

        if (root == null)
            return true;

        if (!recoverTree_(root.left))
            return false;

        if (prev != null && prev.val > root.val) {
            b = root;
            if (a == null)
                a = prev;
            else
                return false;
        }

        prev = root;
        if (!recoverTree_(root.right))
            return false;

        return true;

    }

    public void recoverTree(TreeNode root) {
        recoverTree_(root);
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }
}