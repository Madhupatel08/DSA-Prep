//bfs//level order all cases//vertical order //right view //left view//vertical sum
//bottom view in cpp file--> vertical order ki last values
//top view-->vertical order ki 1st values
//boundary traversal 
//sum of all boundaries
import java.util.LinkedList;
public class l03{

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val){
            this.val = val;
        }
    }
    //by making our own queque using linkedList print in single line 
    //OUTPUT WOULD BE 8 3 10 1 6 14 4 7 13

    public static void levelOrderSimple(TreeNode ){
        LinkedList<TreeNode>queue = new LinkedList<>();
        queue.addLast(root);

        while(queue.size() != 0){
            TreeNode removedNode = queue.removeFirst();
            System.out.print(removedNode.val + " ");

            if(removedNode.left != null)
                queue.addLast(removedNode.left);
            if(removedNode.right != null)
                queue.addLast(removedNode.right);
        }
    }
    //print level order using two queues and level wise 
    public static void levelOrderSimple(TreeNode ){
         LinkedList<TreeNode>queue = new LinkedList<>();
        LinkedList<TreeNode>childQueue = new LinkedList<>();

        queue.addLast(root);
        int level = 0;
        System.out.print("level "+level +"= ");
        while(queue.size() != 0){
            TreeNode removedNode = queue.removeFirst();
            System.out.print(removedNode.val + " ");
            if(removedNode.left != null)
                childQueue.addLast(removedNode.left);
            if(removedNode.right != null)
                childQueue.addLast(removedNode.right);
            if(queue.size() == 0){
                System.out.println();
                if(childQueue.size() != 0)
                    System.out.print("level "+ (++level) +"= ");
                LinkedList<TreeNode> temp = queue;
                queue = childQueue;
                childQueue = temp;
            }
        }
    }
    //method 3 using deleminator ..esi value put kardo do queue me jo exist nhi karti
    //it also print level order level wise//here b=null denotes that hum next level me aa chuke hai
    public static void levelOrderM2(TreeNode ){
         LinkedList<TreeNode>queue = new LinkedList<>();

        queue.addLast(root);
        queue.addLast(null);
        int level = 0;
        System.out.print("level "+level +"= ");
        while(queue.size() != 1){
            TreeNode removedNode = queue.removeFirst();
            System.out.print(removedNode.val + " ");
            if(removedNode.left != null)
                queue.addLast(removedNode.left);
            if(removedNode.right != null)
                queue.addLast(removedNode.right);
            if(queue.getFirst() == null){
                System.out.println();
                System.out.print("level "+ (++level) +"= ");
                queue.addLast(queue.removeFirst());
            }
        }
    }
    //method 4 most simple
    public static void levelOrderM2(TreeNode ){
        LinkedList<TreeNode>queue = new LinkedList<>();

        queue.addLast(root);
        int level = 0;
        System.out.print("level " + level +"= ");
        while(queue.size() != 1){
            int size = queque.size();
            while(size-- != 0){
                TreeNode removedNode = queue.removeFirst();
                System.out.print(removedNode.val + " ");
                if(removedNode.left != null)
                    queue.addLast(removedNode.left);
                if(removedNode.right != null)
                    queue.addLast(removedNode.right);
            } 
            level++;
            System.out.println();
        }
    }
     // 102
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        LinkedList<TreeNode> que = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();

        que.addLast(root);

        int level = 0;
        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {
                TreeNode rn = que.removeFirst(); // rn : remove Node
                if (level == ans.size())
                    ans.add(new ArrayList<>());
                ans.get(level).add(rn.val);

                if (rn.left != null)
                    que.addLast(rn.left);
                if (rn.right != null)
                    que.addLast(rn.right);
            }

            level++;
        }

        return ans;
    }

    public static void leftView(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            System.out.print(que.getFirst().val + " ");
            while (size-- > 0) {
                TreeNode rn = que.removeFirst(); // rn : remove Node

                if (rn.left != null)
                    que.addLast(rn.left);
                if (rn.right != null)
                    que.addLast(rn.right);
            }

            level++;
            System.out.println();
        }
    }

    public static void rightView(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            System.out.print(que.getFirst().val + " ");
            while (size-- > 0) {
                TreeNode rn = que.removeFirst(); // rn : remove Node

                if (rn.right != null)
                    que.addLast(rn.right);
                if (rn.left != null)
                    que.addLast(rn.left);
            }

            level++;
            System.out.println();
        }
    }
    //vertical level
     public static class verticalPair {
        TreeNode node = null;
        int hl = 0; // horizontal Level

        verticalPair(TreeNode node, int hl) {
            this.node = node;
            this.hl = hl;
        }
    }
    ///using hashmap APPROACH 1
    public static List<List<Integer>> verticalOrderTraversal(TreeNode root) {
        LinkedList<verticalPair> que = new LinkedList<>();
        que.addLast(new verticalPair(root, 0));
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        int minHL = 0;
        int maxHL = 0;

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                verticalPair rp = que.removeFirst();

                map.putIfAbsent(rp.hl, new ArrayList<>());
                // if (!map.containsKey(rp.hl))
                //      map.put(rp.hl, new ArrayList<>());

                map.get(rp.hl).add(rp.node.val);

                minHL = Math.min(minHL, rp.hl);
                maxHL = Math.max(maxHL, rp.hl);

                if (rp.node.left != null)
                    que.addLast(new verticalPair(rp.node.left, rp.hl - 1));

                if (rp.node.right != null)
                    que.addLast(new verticalPair(rp.node.right, rp.hl + 1));
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        while (minHL <= maxHL) {
            ans.add(map.get(minHL));
            minHL++;
        }

        return ans;
    }
    ///using width wla approach APPROACH 2
     public static void width(TreeNode root, int hl, int[] ans) {
        if (root == null)
            return;

        ans[0] = Math.min(hl, ans[0]);
        ans[1] = Math.max(hl, ans[1]);

        width(root.left, hl - 1, ans);
        width(root.right, hl + 1, ans);
    }

    public static List<List<Integer>> verticalOrderTraversal2(TreeNode root) {
        LinkedList<verticalPair> que = new LinkedList<>();

        int[] minMax = new int[2];
        width(root, 0, minMax);
        int length = minMax[1] - minMax[0] + 1;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < length; i++)
            ans.add(new ArrayList<>());

        que.addLast(new verticalPair(root, -minMax[0]));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                verticalPair rp = que.removeFirst();

                ans.get(rp.hl).add(rp.node.val);

                if (rp.node.left != null)
                    que.addLast(new verticalPair(rp.node.left, rp.hl - 1));

                if (rp.node.right != null)
                    que.addLast(new verticalPair(rp.node.right, rp.hl + 1));
            }
        }

        return ans;
    }
    public static int[] verticalSum(TreeNode root) {
        LinkedList<verticalPair> que = new LinkedList<>();

        int[] minMax = new int[2];
        width(root, 0, minMax);
        int length = minMax[1] - minMax[0] + 1;
        int[] ans = new int[length];

        que.addLast(new verticalPair(root, -minMax[0]));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                verticalPair rp = que.removeFirst();

                ans[rp.hl] += rp.node.val; // ans.set(rp.hl,ans.get(rp.hl) + rp.node.val);

                if (rp.node.left != null)
                    que.addLast(new verticalPair(rp.node.left, rp.hl - 1));

                if (rp.node.right != null)
                    que.addLast(new verticalPair(rp.node.right, rp.hl + 1));
            }
        }

        return ans;
    }
    // -------------------------------------------------------------
    // printBoundary --->https://practice.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1#
    /* variation of this--> Sum of all the Boundary Nodes of a Binary Tree
    https://www.geeksforgeeks.org/sum-of-all-the-boundary-nodes-of-a-binary-tree/
                Input:
                    20
                    /   \
                   8     22
                 /   \    \
                4    12   25
                    /  \ 
                   10   14 

            Output: 20 8 4 10 14 25 22 */
    // ---------------------------------------------------------------
        public static void leftPart(Node node, ArrayList<Integer>ans){
        if(node == null){
            return;
        }
        if(node.left != null){
            ans.add(node.data);
            leftPart(node.left, ans);
        }else if(node.right != null){
            ans.add(node.data);
            leftPart(node.right, ans);
        }
    }
    public static void rightPart(Node node, ArrayList<Integer>ans){
        if(node == null){
            return;
        }
        if(node.right != null){
            rightPart(node.right, ans);
            ans.add(node.data);
        }
        else if(node.left != null){
            rightPart(node.left, ans);
            ans.add(node.data);
        } 
    }
    public static void leafPart(Node node, ArrayList<Integer>ans){
        if(node == null){
            return;
        }
        
        leafPart(node.left, ans);
        if(node.left == null && node.right == null){
            ans.add(node.data);
        }
        leafPart(node.right, ans);
        
    }
	ArrayList <Integer> printBoundary(Node node){
	    ArrayList<Integer>ans = new ArrayList<>();
	    ans.add(node.data);
	    leftPart(node.left, ans);
	    leafPart(node, ans);
	    rightPart(node.right, ans);
	    return ans;
	}
    public static void main(String[] args){

    }
}