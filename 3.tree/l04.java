//date:7feb
//1.Diogonal level traversal ///////////////////////////////////////////////////////////////////////////////////
vector<vector<int>> DiogonalOrderTraversal(TreeNode *root)
{
    queue<verticalPair> que;
    que.push(verticalPair(root, 0));

    int minHL = 0, maxHL = 0;
    unordered_map<int, vector<int>> map;

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            verticalPair rp = que.front();
            que.pop();

            map[rp.hl].push_back(rp.node->val);
            minHL = min(minHL, rp.hl);
            maxHL = max(maxHL, rp.hl);

            if (rp.node->left != nullptr)
                que.push(verticalPair(rp.node->left, rp.hl - 1));
            if (rp.node->right != nullptr)
                que.push(verticalPair(rp.node->right, rp.hl));
        }
    }

    vector<vector<int>> res;
    while (minHL <= maxHL)
        res.push_back(map[minHL++]);

    return res;
}
//leetcode 987///////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static class verticalPair {
        TreeNode node = null;
        int hl = 0; // horizontal Level

        verticalPair(TreeNode node, int hl) {
            this.node = node;
            this.hl = hl;
        }
    }
     public static void width(TreeNode root, int hl, int[] ans) {
        if (root == null)
            return;

        ans[0] = Math.min(hl, ans[0]);
        ans[1] = Math.max(hl, ans[1]);

        width(root.left, hl - 1, ans);
        width(root.right, hl + 1, ans);
    }
    ////////////////////////////
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        PriorityQueue<verticalPair> que = new PriorityQueue<>((a, b) -> {
            return a.node.val - b.node.val; // this - other for default behaviour
        });
        PriorityQueue<verticalPair> childQue = new PriorityQueue<>((a, b) -> {
            return a.node.val - b.node.val;
        });

        int[] minMax = new int[2];
        width(root, 0, minMax);
        int length = minMax[1] - minMax[0] + 1;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < length; i++)
            ans.add(new ArrayList<>());

        que.add(new verticalPair(root, -minMax[0]));

        while (que.size() != 0) {
            verticalPair rp = que.remove();

            ans.get(rp.hl).add(rp.node.val);

            if (rp.node.left != null)
                childQue.add(new verticalPair(rp.node.left, rp.hl - 1));

            if (rp.node.right != null)
                childQue.add(new verticalPair(rp.node.right, rp.hl + 1));

            if (que.size() == 0) {
                PriorityQueue<verticalPair> temp = que;
                que = childQue;
                childQue = temp;
            }
        }

        return ans;

    }

////////////////////////////////////////////////////////////////////////////////////////////////////
