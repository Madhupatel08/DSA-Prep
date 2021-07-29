/* total class -> 11

---gwc--                          |--must do interview ques---
------------------------------------------------------------------------------------
| Level Order of Binary Tree      |   Depth First Traversal | 
|Word Search Board                |   Breadth First Traversal |
|Number of Islands                |   Detect cycle in undirected graph|
|M-Coloring Problem               |   Detect cycle in a directed graph |
|Capture Surrounded Regions       |   Topological sort |
|Knight's Journey On A Chessboard |   Find the number of islands |
|Stepping Numbers                 |   Implementing Dijkstra
|Valid Path                       |   Minimum Swaps |
                                  |   Strongly Connected Components |
                                  |   Shortest Source to Destination Path |
                                  |   Find whether path existMinimum Cost PathCircle of Strings
                                  |   Floyd Warshall 
                                  |   Alien Dictionary 
                                  |   Snake and Ladder Problem  |
----------------------------------------------------------------------------------------------------
--foundation------------------------------------=------------level up--------------------------
|Has Path?                                      | Bellman Ford 
|Print All Paths                                | Negative Weight Cycle Detection
|Smallest, Longest, Ceil, Floor, Kthlargest Path| Pepcoder And Reversing 
|Get Connected Components Of A Graph            | Pepcoding Course Schedule 
|Is Graph Connected                             | Kosaraju Algorithm 
|Number Of Islands                              | Mother Vertex 
|Perfect Friends                                | Minimum Cost To Connect All Cities 
|Hamiltonian Path And Cycle                     | Number Of Enclaves 
|Knights Tour                                   | Zero One Matrix 
|Breadth First Traversal                        | Number Of Distinct Island 
|Is Graph Cyclic                                | Rotting Oranges
|Is Graph Bipartite                             |
|Spread Of Infection                            |
|Shortest Path In Weights                       |
|Minimum Wire Required To Connect All Pcs       |
|Order Of Compilation                           |
|Iterative Depth First Traversal                |
-----------------------------------------------------------------------------------------------------
*/
//graph construction
//addEdge/removeEdge/findEdge/removevertex/
//hashpath/print all paths
//print heaviest path
//is cycle (BFS/DFS) //shortest path
// 1. find hamiltonian path //hamiltonian cycle
// 2. get connected elements 
//BFS cycle
//BFS shortest path
//BFS print longest path --> using parent array
//BFS GCC

import java.util.*;
class l01{

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
    
    //add new edge
    public static void addEdge(int u,int v,int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    //construct graph
    public static void constructGraph(){
        for(int i = 0 ; i < N ; i++){
            graph[i] = new ArrayList<>();//allocated the sapce for lists in array indices
        }
        addEdge(0,1,10);
        addEdge(0,3,10);
        addEdge(1,2,10);
        addEdge(2,3,40);
        addEdge(3,4,2);
        addEdge(4,5,2);
        addEdge(4,6,8);
        addEdge(5,6,3);
        addEdge(0,6,3);
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

    //remove egde
    public static void removeEdge(int ouru , int ourv){
        for(int i = 0 ; i < graph[ouru].size() ; i++){
            if(graph[ouru].get(i).v == ourv){
                graph[ouru].remove(i);
            }      
        }
        for(int i = 0 ; i < graph[ourv].size() ; i++){
            if(graph[ourv].get(i).v == ouru){
                graph[ourv].remove(i);
            }      
        }
    }

    //remove vertex
    public static void removeVertex(int ouru){
        //age se remove karege to index change ho jaege and ek value remove nahi hogi.... so last se remove karo
        for(int i =  graph[ouru].size()-1; i >=0 ; i--){
            removeEdge(graph[ouru].get(i).v , ouru);
        }
    }

    //has path //if only sorce to dest check karna hai if path exists ..no need to backtrack
    public static boolean hasPath(ArrayList<Edge>[] graph , int src , int dest , boolean[] visited){
        if(src == dest){
            return true;
        }
        visited[src] = true;

        for(int i = 0 ; i < graph[src].size() ; i++){
            Edge ce = graph[src].get(i);
            if( visited[ce.v] == false ){
                boolean recAns = hasPath(graph , ce.v , dest , visited);
                if(recAns)
                    return true;
            }
        }
        return false;
    }
   ///sir's solution--------of hasPath 
    public static boolean hasPath(int src, int dest, vector<bool> &vis){
        if (src == dest)
            return true;

        vis[src] = true;
        bool res = false;
        for (Edge e : graph[src])
        {
            if (!vis[e.v])
                res = res || hasPath(e.v, dest, vis);
        }

        return res;
    }

    //print all path
    public static void printAllPath(ArrayList<Edge>[]graph , int src , int dest , boolean[] visited , String path ){
        if(src == dest){
            System.out.println(path + src);
            return ;
        }
        visited[src] = true;

        for(int i = 0 ; i < graph[src].size() ; i++){
            Edge ce = graph[src].get(i);
            if( visited[ce.v] == false ){
                 printAllPath(graph , ce.v , dest , visited , path + src + "->");
            }
        }
        visited[src] = false;
    }

    // //print heaviest path
    static int maxCost = -(int)1e9;
    static String maxPath = "";
    public static void printheaviestPath(ArrayList<Edge>[]graph , int src , int dest , boolean[] visited , String path ,int cost){
        if(src == dest){
            path = path + src;
            if(cost > maxCost){
                maxCost = cost;
                maxPath = path;
            }
            return;
        }
        visited[src] = true;

        for(int i = 0 ; i < graph[src].size() ; i++){
            Edge ce = graph[src].get(i);
            if( visited[ce.v] == false ){
                 printheaviestPath(graph , ce.v , dest , visited , path + src + "->", cost + ce.w);
            }
        }
        visited[src] = false;
    }
    // ---------------------sirs' method of finding weighted path
        // heavy Path -> print : path and weight

    public static heavyPair heavyPath(int src, int dest, boolean[] vis) {
        if (src == dest) {
            heavyPair base = new heavyPair(0, dest + "");
            return base;
        }

        vis[src] = true;

        heavyPair myAns = new heavyPair(-(int) 1e8, "");

        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                heavyPair recAns = heavyPath(e.v, dest, vis);
                if (recAns.weight != -1e8 && recAns.weight + e.w > myAns.weight) {
                    myAns.weight = recAns.weight + e.w;
                    myAns.path = src + " " + recAns.path;
                }
            }
        }

        vis[src] = false;
        return myAns;
    }

    public static int findEdge(int u, int v) {
        int idx = -1;
        for (int i = 0; i < graph[u].size(); i++) {
            if (graph[u].get(i).v == v) {
                idx = i;
                break;
            }
        }

        return idx;
    }
     public boolean helper_isCycle(ArrayList<ArrayList<Integer>> adj,boolean[] isVisited, int src ){
        // boolean[] isVisited = new inbooleant[adj.size()];
        //remove //mark //add neighbours
        if(adj.size() ==0 ){
            return false;
        }
        Queue<Integer>qu = new LinkedList<>();
        qu.add(src);
        
        while(qu.size() > 0){
            int r_ele = qu.remove();
            if(isVisited[r_ele] == true)
                return true;
            isVisited[r_ele] = true;
            for(int j = 0 ; j < adj.get(r_ele).size() ; j++){
                ArrayList<Integer> a = adj.get(r_ele);
                if(isVisited[a.get(j)] == false)
                     qu.add(a.get(j));
            }
        }
        return false;
    }
    
    //Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj)
    {
        boolean[] isVisited = new boolean[adj.size()];

        isVisited[src] = true;

        for(int i = 0 ; i < adj.size() ; i++){
            if(isVisited[i] == false){
              if(helper_isCycle(adj, isVisited, i))
                return true;
            }
        }
        return false;
    }
    //print hamiltonian path and cycles the source is given
    public static int hamiltonianPath( ArrayList<Edge>[]graph , int src , boolean[] visited , String path ,int totalNofEdges , int originalSource){
        if(totalNofEdges == N - 1){
            int idx = findEdge(originalSource , src);//ye check karega direct edge between the lst node and first node exist or not //last node jahaa aake ruka hai
            if(idx == -1){
                System.out.println("Hamiltonian Cycle" + path + src);
            }else{
                System.out.println("Hamiltonian path" + path + src);
            }
            return 1 ;
        }
        visited[src] = true;

        int count = 0;
        for(int i = 0 ; i < graph[src].size() ; i++){
            Edge ce = graph[src].get(i);
            if( visited[ce.v] == false ){
                 hamiltonianPath(graph , ce.v , visited , path + src + "->" , totalNofEdges++ , originalSource);
            }
        }
        visited[src] = false;
        return count;
    } 
    public static void dfs(int src, boolean[]vis, ArrayList<Integer>ans){
       //depth first search //no of element-1
        vis[src] = true;
        // ans.add(src);//can be done in pre or post order
        for (int i = 0 ; i < graph[src].size() ; i++){
            Edge ce = graph[src].get(i);
            if (vis[ce.v] == false)
                dfs(ce.v, vis, ans);
        }
        ans.add(src);
    }

    // get conected components//O(V+E)
    public static void int gcc(){
        ArrayList<ArrayList<int>> res;
        boolean[] vis = new boolean[N];
        int components = 0;
        for (int i = 0; i < N; i++){
            if (vis[i] == false){
                ArrayList<Integer>ans = new ArrayList<>();
                dfs(i, vis, ans);//ye sare apne se jude logo ko true mark kardega
                res.add(ans);
                components++;
            }
        }
        return components;
    }

    public void BFS_Cycle(int src, boolean[] vis){
        queue<Integer> que = new LinkedList<>();
        que.add(src);

        int dest = 6;
        int atLevel = -1;

        boolean isCycle = false;
        int level = 0;

        while (que.size() != 0)
        {
            int size = que.size();
            System.out.println(level << " -> ");
            while (size-- > 0)
            {
                int rvtx = que.remove();
    
                cout << rvtx << " ";
                if (vis[rvtx])
                {
                    isCycle = true;
                    continue;
                }

                if (rvtx == dest)
                {
                    atLevel = level;
                }

                vis[rvtx] = true;
                for (int i = 0 ; i < graph[rvtx].size() ; i++)//[]
                {
                    Edge ce = graph[rvtx].get(i);
                    if (!vis[ce.v])
                        que.add(ce.v);

                }
            }
            level++;
            cout << endl;
        }

        System.out.println(dest + " present at : " + atLevel);
        System.out.println("isCycle : " + boolalpha + isCycle);
    }

    public static void BFS_shortestPath(int src, boolean[] vis)
    {
        queue<Integer> que = new LinkedList<>();
        que.add(src);

        int dest = 6;
        int atLevel = -1;

        int level = 0;
        vis[src] = true;

        while (que.size() != 0)
        {
            int size = que.size();
            while (size-- > 0)
            {
                int rvtx = que.remove();
            
                for (int i = 0; graph[rvtx].size() ; i++)
                {
                    Edge e = graph[rvtx].get(i);
                    if (!vis[e.v])
                    {
                        vis[e.v] = true;
                        que.add(e.v);
                    }

                    if (e.v == dest)
                        atLevel = level + 1;
                }
            }
            level++;
        }

        System.out.println(dest + " present at : " + atLevel);
    }

    public static void BFS_printshortestPath(int src, int[] vis)
    {
        queue<Integer> que = new LinkedList<>();
        que.add(src);

        int dest = 6;
        int atLevel = -1;

        int level = 0;
        vis[src] = true;

        int[] par = new int[N];
        for(int i = 0 ; i < N ; i++)
            par[i] = -1;

        while (que.size() != 0)
        {
            int size = que.size();
            while (size-- > 0)
            {
                int rvtx = que.remove();
                
                for (int i = 0 ; i < graph[rvtx].size(); i++)
                {
                    Edge e =  graph[rvtx].size();
                    if (!vis[e.v])
                    {
                        vis[e.v] = true;
                        que.add(e.v);
                        par[e.v] = rvtx;
                    }

                    if (atLevel == -1 && e.v == dest)
                        atLevel = level + 1;
                }
            }
            level++;
        }

        System.out.println(dest + " present at : " + atLevel );// endl;
        int idx = dest;
        while (idx != -1)
        {
            System.out.println(idx + " -> ");
            idx = par[idx];
        }
    }

    public static void BFS_GCC()
    {
        boolean[]vis = new boolean[N];//(N, 0);
        int components = 0;
        for (int i = 0; i < N; i++)
        {
            if (!vis[i])
            {
                components++;
                //BFS_Cycle(i, vis);
                BFS_printshortestPath(i,vis);
            }
        }
    }
    public static void main(String[] args){
        constructGraph();
        // displayGraph(graph);
        // removeEdge(0,1);
        // displayGraph(graph);
        // removeVertex(3);
        // displayGraph(graph);
        boolean[] visited = new boolean[N];
        // System.out.println(hasPath(graph,0,5,visited));
        // printAllPath(graph,0,6,visited,"");
        // printheaviestPath(graph , 0,6,visited ,"",0);
        // System.out.println(maxCost);
        // System.out.println(maxPath);

        int x = hamiltonianPath(graph , 0 , visited , " ", N , 0);
        System.out.println("count no of paths and cycle -> " + x);
 
    }
}