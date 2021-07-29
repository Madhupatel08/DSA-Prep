//directed graph
//topological sort --> DFS & BFS
class l04_directed_Graph{
    public static class Edge{
        int v = 0;
        int w = 0;
        Edge(int v,int w){
            this.v = v;
            this.w = w;
        }
    }    
    static int N = 7;
    @SuppressWarnings("unchecked")//--->this is used to supress the warning bcs we haven't declared the arraylist type in right hand side
    //array of arrayList
    static ArrayList<Edge>[] graph = new ArrayList[N];
    //construct graph
    public static void constructGraph(){
        for(int i = 0 ; i < N ; i++){
            graph[i] = new ArrayList<>();//allocated the sapce for lists in array indices
        }
        addEdge(5, 0, 10);
        addEdge(4, 0, 10);
        addEdge(5, 1, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 10);
        addEdge(0, 6, 10);
        addEdge(6, 7, 10);
        addEdge(7, 3, 10);
        addEdge(4, 8, 10);
        addEdge(8, 9, 10);
        addEdge(9, 10, 10);
        addEdge(10, 3, 10);

        topologicalOrder_DFS();
    }
    //display graph
    public static void  displayGraph(ArrayList<Edge>[] graph ){
        for(int i = 0; i < N ; i++){
            System.out.print(i+"->"+" "); 
            for(int j = 0 ; j < graph[i].size() ; j++){
                System.out.print("("+graph[i].get(j).v +","+graph[i].get(j).w+")" +" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        constructGraph();
        // displayGraph(graph);
    }
    //Topological sort --> DFS 
    public static void dfs_topo(int src, boolean[]vis, ArrayList<Integer>ans){
        vis[src] = true;
        for (Edge e : graph[src])
        {
            if (!vis[e.v])
                dfs_topo(e.v, vis, ans);
        }

        ans.add(src);
    }
    public static void topologicalOrder_DFS(){
            boolean[] vis = new int[N];//(N, false);
            ArrayList<Integer>ans = new ArrayList<>();
            for (int i = 0; i < N; i++)
            {
                if (!vis[i])
                    dfs_topo(i, vis, ans);
            }

            for (Integer ele : ans)
                System.out.print(ele + " ");
    }
    //kahns algo
    public static ArrayList<Integer> kahnsAlgo(int N, ArrayList<Integer>[]graph){
        int[] indegree = new int[N];//(N, 0);
        for (int i = 0; i < N; i++)
            for (int e : graph[i])
                indegree[e]++;
    
        Queue<Integer> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < N; i++)
            if (indegree[i] == 0)
                que.add(i);
    
        int level = 0;
        while (que.size() != 0)
        {
            int size = que.size();
            while (size-- > 0)
            {
                int rvtx = que.remove();
                
                ans.add(rvtx);
                for (int e : graph[rvtx])
                {
                    if (--indegree[e] == 0)
                        que.add(e);
                }
            }
            level++;
        }
        return ans;
    }
    //check if cycle is present using DFS topo sort
    public boolean  isCyclePresent_DFSTopo(int src, boolean[]vis, ArrayList<Integer>ans){
        vis[src] = 0;
        bool res = false;
        for (Edge e : graph[src]){
            if (vis[e.v] == -1)
            { // unvisited
                res = res || isCyclePresent_DFSTopo(e.v, vis, ans);
            }
            else if (vis[e.v] == 0)
            {
                return true; // there is cycle.
            }
        }

        vis[src] = 1;
        ans.add(src);
        return res;
    }
    public ArrayList<Integer>isCyclePresent_DFS(){
        int[] visited = new int[N];//(N, -1);
        for(int i = 0 ; i < N ; i++)
            visited[i] = -1;

        ArrayList<Integer>ans = new ArrayList<>();
        boolean res = false;

        for (int i = 0; i < N; i++){
            if (vis[i] == -1){
                res = res || isCyclePresent_DFSTopo(i, vis, ans);
            }
        }
        if (res)
            return new int[0];
        return ans;
    }
    //strongly connected component
    public void dfs_SCC(ArrayList<Edge>[] ngraph, int src, boolean[]vis, ArrayList<Integer>ans){
        vis[src] = true;
        for (Edge e : ngraph[src]){
            if (!vis[e.v])
                dfs_SCC(ngraph, e.v, vis, ans);
        }
        ans.add(src);
    }
    //Kosaraju
    public static void kosaraju(){
        boolean[] vis = new boolean[N];
        ArrayList<Integer>ans = new ArrayList<>();
        for (int i = 0; i < N; i++)
        {
            if (! vis[i])
                dfs_topo(i, vis, ans);
        }
        // Graph inverse.
        ArrayList<Edge>[] ngraph = new ArrayList[N];//ngraph(N);
        for (int i = 0; i < N; i++)
        {
            for (Edge e : graph[i])
            {
                ngraph[e.v].add(Edge(i, 1));
            }
        }
        vis.clear();
        for (int i = ans.size() - 1; i >= 0; i--){
            int ele = ans[i];
            if (!vis[ele]){
                ArrayList<Integer>scc = new ArrayList<>();
                dfs_SCC(ngraph, ele, vis, scc);

                for (int e : scc)
                    System.out.print(e + " ");

                System.out.println();
            }
        }
    }
}



