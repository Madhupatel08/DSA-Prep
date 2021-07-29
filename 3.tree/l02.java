//1. diameter of tree
//2. 112 has Path 
//3 113 has pathSum


// https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/
------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------
    public int diameterOfBinaryTree_01(TreeNode root) {
        if (root == null)
            return -1;
        int leftTreeDia = diameterOfBinaryTree_01(root.left);
        int rightTreeDia = diameterOfBinaryTree_01(root.right);

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.max(Math.max(leftTreeDia, rightTreeDia), leftHeight + rightHeight + 2);
    }

    // {dia,height}
    public int[] diameterOfBinaryTree_02(TreeNode root) {
        if (root == null)
            return new int[] { -1, -1 };
        int[] leftAns = diameterOfBinaryTree_02(root.left);
         int[] rightAns = diameterOfBinaryTree_02(root.right);

        int[] ans = new int[2];
        ans[0] = Math.max(Math.max(leftAns[0], rightAns[0]), leftAns[1] + rightAns[1] + 2);
        ans[1] = Math.max(leftAns[1], rightAns[1]) + 1;

        return ans;
    }

    int maxDia = 0;

    public int diameterOfBinaryTree_03(TreeNode root) {
        if (root == null)
            return -1;
        int lh = diameterOfBinaryTree_03(root.left);
        int rh = diameterOfBinaryTree_03(root.right);

        maxDia = Math.max(maxDia, lh + rh + 2);

        return Math.max(lh, rh) + 1;
    }

    public int diameterOfBinaryTree_03(TreeNode root, int[] ans) {
        if (root == null)
            return -1;
        int lh = diameterOfBinaryTree_03(root.left);
        int rh = diameterOfBinaryTree_03(root.right);

        ans[0] = Math.max(ans[0], lh + rh + 2);

        return Math.max(lh, rh) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        // return diameterOfBinaryTree_01(root);
        // return diameterOfBinaryTree_02(root)[0];
        int[] ans = new int[1];
        diameterOfBinaryTree_03(root, ans);
        return ans[0];
    }
---------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------
    // 112
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return (targetSum - root.val == 0);

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        if(root.left == null && root.right == null){
            return (targetSum - root.val == 0 );
        }
        boolean lans = hasPathSum(root.left , targetSum - root.val);
        if(lans)return true ;
        boolean rans = hasPathSum(root.right , targetSum - root.val);
        if(rans) return true;
        return false;
    }
-----------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
    // 113
    public void pathSum(TreeNode root, int tar, List<List<Integer>> res, List<Integer> smallAns) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            if (tar - root.val == 0) {
                ArrayList<Integer> base = new ArrayList<>(smallAns);
                base.add(root.val);
                res.add(base);
            }

            return;
        }

        smallAns.add(root.val);

        pathSum(root.left, tar - root.val, res, smallAns);
        // print("hello");
        pathSum(root.right, tar - root.val, res, smallAns);

        smallAns.remove(smallAns.size() - 1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        pathSum(root, targetSum, res, new ArrayList<>());
        return res;

    }
================================================================================================
    // https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
    int maxLeafToLeaf = -(int) 1e9;

    int maxPathSum_(Node root) {
        if (root == null)
            return -(int) 1e9;
        if (root.left == null && root.right == null)
            return root.data;

        int leftNodeToLeafMaxSum = maxPathSum_(root.left);
        int rightNodeToLeafMaxSum = maxPathSum_(root.right);

        if (root.left != null && root.right != null)
            maxLeafToLeaf = Math.max(maxLeafToLeaf, leftNodeToLeafMaxSum + root.data + rightNodeToLeafMaxSum);

        return Math.max(leftNodeToLeafMaxSum, rightNodeToLeafMaxSum) + root.data;
    }

    int maxPathSum(Node root) {
        if (root == null)
            return Integer.MIN_VALUE;

        maxPathSum_(root);
        return maxLeafToLeaf;
    }   
===================================================================
}

}