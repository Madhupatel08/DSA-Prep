class l08{
    //morrisTraversal
    public static TreeNode rightMostNode(TreeNode next, TreeNode curr) {
        while (next.right != null && next.right != curr)
            next = next.right;

        return next;
    }

    public static void morrisInOrderTraversal(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode next = curr.left;
            if (next == null) {
                System.out.print(curr.val + " ");
                curr = curr.right;
            } else {
                TreeNode rightMost = rightMostNode(next, curr);
                if (rightMost.right == null) { // thread create
                    rightMost.right = curr;
                    curr = curr.left;
                } else { // thread break
                    rightMost.right = null;
                    System.out.print(curr.val + " ");
                    curr = curr.right;
                }
            }
        }
    }

    public static void morrisPreOrderTraversal(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode next = curr.left;
            if (next == null) {
                System.out.print(curr.val + " ");
                curr = curr.right;
            } else {
                TreeNode rightMost = rightMostNode(next, curr);
                if (rightMost.right == null) { // thread create
                    rightMost.right = curr;
                    System.out.print(curr.val + " ");
                    curr = curr.left;
                } else { // thread break
                    rightMost.right = null;
                    curr = curr.right;
                }
            }
        }
    }

    public static class tPair {
        TreeNode node = null;
        boolean selfDone = false;
        boolean leftDone = false;
        boolean rightDone = false;

        tPair(TreeNode node, boolean selfDone, boolean leftDone, boolean rightDone) {
            this.node = node;
            this.selfDone = selfDone;
            this.leftDone = leftDone;
            this.rightDone = rightDone;
        }
    }

    public static void IterTraversal(TreeNode root) {
        LinkedList<tPair> ll = new LinkedList<>();
        ll.addFirst(new tPair(root, false, false, false));
        while (ll.size() != 0) {
            tPair pair = ll.getFirst();
            if (!pair.leftDone) {
                pair.leftDone = true;
                if (pair.node.left != null)
                    ll.addFirst(new tPair(pair.node.left, false, false, false));

            } else if (!pair.selfDone) {
                pair.selfDone = true;
                System.out.print(pair.node.val + " ");
            } else if (!pair.rightDone) {
                pair.rightDone = true;
                if (pair.node.right != null)
                    ll.addFirst(new tPair(pair.node.right, false, false, false));
            } else {
                ll.removeFirst();
            }
        }
    }

    // 1008
    // lr -> left range, rr = right range
    int bst_idx = 0;

    public TreeNode constructBSTFromPreOrder(int[] arr, int lr, int rr) {
        if (bst_idx == arr.length || arr[bst_idx] < lr || arr[bst_idx] > rr)
            return null;

        TreeNode node = new TreeNode(arr[bst_idx++]);
        node.left = constructBSTFromPreOrder(arr, lr, node.val);
        node.right = constructBSTFromPreOrder(arr, node.val, rr);

        return node;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        return constructBSTFromPreOrder(preorder, -(int) 1e8, (int) 1e8);
    }

    public class levelPair {
        TreeNode par = null;
        int lb = -(int) 1e8;
        int rb = (int) 1e8;

        levelPair() {

        }

        levelPair(TreeNode par, int lb, int rb) {
            this.par = par;
            this.lb = lb;
            this.rb = rb;
        }
    }

    public TreeNode constructBSTFromLevel(int[] arr) {
        int idx = 0;
        LinkedList<levelPair> que = new LinkedList<>();
        que.add(new levelPair());
        TreeNode root = null;

        while (que.size() != 0 && idx < arr.length) {
            levelPair pair = que.removeFirst();

            if (arr[idx] < pair.lb || arr[idx] > pair.rb)
                continue;

            TreeNode node = new TreeNode(arr[idx++]);
            if (pair.par == null)
                root = node;
            else {
                if (node.val < pair.par.val)
                    pair.par.left = node;
                else
                    pair.par.right = node;
            }

            que.addFirst(new levelPair(node, pair.lb, node.val));
            que.addFirst(new levelPair(node, node.val, pair.rb));
        }

    }

    // 230
    public int kthSmallest(TreeNode root, int k) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode next = curr.left;

            if (next == null) {
                if (k == 1)
                    return curr.val;
                k--;
                curr = curr.right;

            } else {
                TreeNode rightMost = rightMostNode(next, curr);
                if (rightMost.right == null) { // thread create
                    rightMost.right = curr;
                    curr = curr.left;
                } else { // thread break
                    rightMost.right = null;
                    if (k == 1)
                        return curr.val;
                    k--;
                    curr = curr.right;

                }
            }
        }

        return -1;
    }

    // 437
    int ans = 0;

    public void pathSumIII(TreeNode node, HashMap<Integer, Integer> map, int tar, int prefixSum) {
        if (node == null)
            return;

        prefixSum += node.val;
        ans += map.getOrDefault(prefixSum - tar, 0);

        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);

        pathSumIII(node.left, map, tar, prefixSum);
        pathSumIII(node.right, map, tar, prefixSum);

        map.put(prefixSum, map.get(prefixSum) - 1);
        if (map.get(prefixSum) == 0)
            map.remove(prefixSum);
    }

    public int pathSum(TreeNode root, int K) {
        // prefix sum , Frequency
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        pathSumIII(root, map, K, 0);
        return ans;
    }

    public class pair {
        TreeNode node = null;
        long w = 0;

        pair(TreeNode node, long w) {
            this.node = node;
            this.w = w;
        }
    }

    // 662
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<pair> que = new LinkedList<>();
        que.addLast(new pair(root, 1));
        int ans = 0;

        while (que.size() != 0) {
            int size = que.size();
            long fi = que.getFirst().w;
            long li = que.getFirst().w;

            while (size-- > 0) {
                pair p = que.removeFirst();

                TreeNode node = p.node;
                long w = p.w;
                li = w;

                if (node.left != null)
                    que.addLast(new pair(node.left, 2 * w));
                if (node.right != null)
                    que.addLast(new pair(node.right, 2 * w + 1));

            }

            ans = Math.max(ans, (int) (li - fi + 1));
        }

        return ans;
    }
    public static void main(String[] args){

    }
}