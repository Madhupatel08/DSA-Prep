//1.height,max,min,
//node to root path
//2. k far distance
//k level down
//find target

 public class lo1{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
      }
    public int size( TreeNode node){
        return node == null ? 0: size(node.lefft) + size(node.right) + 1;
    }
    //edges for nodes-> return 0 
    public int height(TreeNode node){
        return node == null ? -1 : Math.max(height(node.left),height(node.right))+1;
    }

    public int max(TreeNode node){
        if(node == null){
            return -(int)le9;
        }
        int leftmax =max(node.left);//left max val
        int rightmax = max(node.right);//right max val

        return Math.max(Math.max(leftmax,rightmax),root.val);
    }

    public boolean find(TreeNode node, int data){
        if(node == null){
            return false;
        }
        if(node.val == data){
            return true;
        }
        return find(node.left,data) || find(node.right,data);
    }
// ArrayList<Node> ans = new ArrayList<>();
    public ArrayList<Integer> rootToNodePath(TreeNode node,int data){
        if(node == null){
            return null;
        }
        if(node.val == data){
            ArrayList<Node> ans = new ArrayList<>();
            ans.add(node);
            return ans;
        }
        ArrayList<node>lans = rootToNodePath(node.left);
        if(left.size()>0){
            lans.add(node);
            return lans;
        }
        ArrayList<node>rans = rootToNodePath(node.right);
        if(rans.size() > 0){
            rans.add(node);
            return rans;
        } 
        return new ArrayList<>();
    }
    public boolean rootToNodePath2(TreeNode node ,int data,ArrayList<TreeNode>ans){
        if(node == null){
            return false;
        }
        if(node.val == data){
            ans.add(node);
            return true;
        }
        boolean res =  rootToNodePath2(node.left,data,ans) || rootToNodePath2(node.right,data,ans);
        if(res){
            ans.add(node);
        }
        return res;
    }
    public boolean rootToNodePath3(TreeNode node ,int data,ArrayList<TreeNode>ans){
        if(node == null){
            return false;
        }
        if(node.val == data){
            ans.add(node);
            return true;
        }
        boolean lans =  rootToNodePath3(node.left,data,ans) ;
        if(lans){
            ans.add(node);
            return true;
        }
        bool rans = rootToNodePath3(node.right,data,ans);
        if(rans){
            ans.add(node);
            return true;
        }
        return false;
    }
    //lowest common ancestor//
    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p ,TreeNode q){
        ArrayList<TreeNode>list1 = new ArrayList<>();
        ArrayList<TreeNode>list2 = new ArrayList<>();

        rootToNodePath2(root,p.val,list1);
        rootToNodePath2(root,q.val,list2);

        int i = list1.size() - 1;
        int j = list2.size() - 1;

        TreeNode lca = null;
        while(i>=0 && j>=0){
            if(list2.get(j) != list1.get(i)){
                break;
            }
            lca = list1.get(i);
            i--;j--;
        }
        return lca;
    }
    public static int rootToNodeDistance(TreeNode node , TreeNode data){
           if(node == null){
               return -1;
           }
            if(node == data){
                return 0;
            }
            int lans = rootToNodeDistance(node.left,data);
            if(lans != -1){
                return lans + 1;
            }

            int rans = rootToNodeDistance(node.right , data);
            if(rans != -1){
                return rans+1;
            }
            return -1;
    }
    public void printKDown(TreeNode node , TreeNode block ,int depth ,ArrayList<Integer>ans){
        if(node == null || node == blockNode || depth < 0){
            return;
        }
        if(depth == 0){
            ans.add(node.val);
            return n;
        }
        printKDown(node.left , block,depth - 1,ans);
        printKDown(node.right , block,depth -1 ,ans);
    }
    //863
    public List<Integer> distanceK(TreeNode root , TreeNode target ,int K){
        ArrayList<TreeNode>list = new ArrayList<>();
        rootToNodePath2(root , target,list);

        List<Integer> ans = new ArrayList<>();
        TreeNode blockNode = null;

        for(int i=0;i<list.size();i++){
            printKDown(list.get(i) , blockNode , K- i);
            blockNode = list.get(i);
        }
        return ans;
    }
    //better solution for k far 
    public int distanceK2(TreeNode node,TreeNode target,int K,List<Integer>ans){
        if(node == null){
            return -1;
        }
        if(node == target){
            printKDown(node,null,K,ans);
            return 1;
        }

        int lans = distanceK2(node.left,target,K,ans);
        if(lans != -1){
            printKDown(node,node.left,k-lans,ans);
            return lans+1;
        }
        int rans = distanceK2(node.right,target,K,ans);
        if(rans != -1){
            printKDown(node,node.right,k-rans,ans);
            return rans+1;
        }
        return -1;
    }
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
        List<Integer> ans = new ArrayList<>();
        distanceK2(root, target, K, ans);
        return ans;
    }
    // https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/
}