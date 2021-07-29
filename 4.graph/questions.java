class questions{
//200 no of island
//695 max area of island
//463 land perimeter
//130 sorrounding region
// 1091 shortest binary path in matrix
//785check if graph is bipirtite
//multiple DFS -->994 //rotten oranges/286 walls and gate// https://tenderleo.gitbooks.io/leetcode-solutions-/content/GoogleMedium/286.html
//kahns Algo((topological dort by BFS)//course schedule 1 /course schedule 2 //
public static void main(String[] args){
}
//200 no of island
public void numIslandHelper(int i , int j , int n , int m, char[][] grid){
        grid[i][j] = '0';
        int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
        
        for(int k = 0 ; k < dir.length ; k++){
            int x = i + dir[k][0];
            int y = j + dir[k][1];
            if(x < n && x >= 0 && y < m && y >= 0 && grid[x][y] == '1')
                numIslandHelper(x , y , n , m , grid);
        }
    }
    public int numIslands(char[][] grid) {
        if(grid.length == 0 )
            return 0;
        int count = 0;
        for(int i = 0 ; i < grid.length ; i++){
            for(int j = 0 ; j < grid[0].length ; j++){
                if(grid[i][j] == '1'){
                    count++;
                    numIslandHelper(i , j , grid.length , grid[0].length , grid);
                }
            }
        }
        return count;
    }

//695 max area of perimeter
public int helper(int i , int j , int n , int m , int[][] grid) {
        grid[i][j] = 0;
       
        int[][] dir = {{1,0},{0,1},{0,-1},{-1,0}};
        
        int count = 1;
        
        for(int k = 0 ; k < dir.length ; k++){
            int x = i + dir[k][0];
            int y = j + dir[k][1];
            
            if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1){
              count += helper(x,y,n,m,grid);
            }
        }
       return count;
    }
    public int maxAreaOfIsland(int[][] grid) {
        int maxarea = 0;
        
        for(int i = 0 ; i < grid.length ; i++){
            for(int j = 0 ; j < grid[0].length ; j++){
                if(grid[i][j] == 1){
                    maxarea = Math.max(maxarea,helper(i,j,grid.length,grid[0].length,grid));
                }      
            }
        }
      return maxarea;  
    }
//463 land perimeter
int islandPerimeter(int[][]grid){
    if (grid.length == 0 || grid[0].length == 0)
        return 0;

    int nbrs = 0, count = 0;
    int n = grid.length;
    int m = grid[0].length;
    int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 1)
            {
                count++;
                for (int d = 0; d < dir.length; d++)
                {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
                        nbrs++;
                }
            }
        }
    }
    return count * 4 - nbrs;
}
//130 sorrounding region
void surroundedRegionDFS(int i, int j, int n, int m, int[][]grid, int[][]dir){
    grid[i][j] = '$';
    for (int d = 0; d < dir.length; d++)
    {
        int r = i + dir[d][0];
        int c = j + dir[d][1];

        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 'O')
            surroundedRegionDFS(r, c, n, m, grid, dir);
    }
}
public static void solve(int[][] grid){
    if (grid.length == 0 || grid[0].length == 0)
        return;

    int area = 0;
    int n = grid.length;
    int m = grid[0].length;
    int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    for (int i = 0; i < n; i++)
    {
        if (grid[i][0] == 'O')
            surroundedRegionDFS(i, 0, n, m, grid, dir);
        if (grid[i][m - 1] == 'O')
            surroundedRegionDFS(i, m - 1, n, m, grid, dir);
    }

    for (int j = 0; j < m; j++)
    {
        if (grid[0][j] == 'O')
            surroundedRegionDFS(0, j, n, m, grid, dir);
        if (grid[n - 1][j] == 'O')
            surroundedRegionDFS(n - 1, j, n, m, grid, dir);
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 'O')
                grid[i][j] = 'X';
            if (grid[i][j] == '$')
                grid[i][j] = 'O';
        }
    }
}
//1091//shortest binary path in matrix
public int shortestPathBinaryMatrix(int[][] grid) {
        
    int n = grid.length;
    int m = n;
    if(m == 0 || n == 0){
        return -1;
    }
    if(grid[0][0] == 1 || grid[n-1][n-1] == 1)
        return -1;
    
    Queue<Integer>qu = new LinkedList<>();
    int level = 1;
    
    int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
    grid[0][0] = 1;//mark true
    
    qu.add(0*m + 0);//row*m+colm
    
    while(qu.size() > 0){
        int size = qu.size();
        
        while(size-- > 0){
            int idx = qu.remove();
            int r = idx/m;
            int c = idx %m;
            
            if(r == n-1 && c == m-1){
                return level;
            }
            for(int i = 0 ; i < dir.length ; i++){
                int x = r + dir[i][0];//new position where it will go next
                int y = c + dir[i][1];
                //mark true in visited
                if(x >= 0 && y >= 0 && x < n && y < n && grid[x][y] == 0){
                    grid[x][y] = 1;
                    //add in queue
                    qu.add(x*m + y);
                }
            }
        }
        level++;
    }
    return -1;
}
//785//check if graph is bipirtite
public boolean isBipartite(int[][]graph, int[] vis, int src){
    Queue<Integer> qu = new LinkedList<>();
    qu.add(src);
    int color = 0;//red
    boolean isCycle = false;
    
    while(qu.size() > 0){
        int size = qu.size();
        
        while(size--> 0){
            int vrtx = qu.remove();
            if(vis[vrtx] != -1){
                isCycle = true;//cycle exists
                
                if(vis[vrtx] != color){
                    return false;
                }
            }
            vis[vrtx] = color;
            
            for(int i : graph[vrtx]){
                if(vis[i] == -1)
                     qu.add(i);
            }
        }
        color = (color + 1) % 2;
    }
    
    return true;
}
public boolean isBipartite(int[][] graph){
    int n = graph.length;
    // -1 : not visited,
    //  0 : red,
    //  1 : green
    int[] vis = new int[n];
    
    for(int i = 0 ; i < n ; i++)
        vis[i] = -1;
    
    boolean res = true;
    for (int i = 0; i < n; i++)
    {
        if (vis[i] == -1)
            res = res && isBipartite(graph, vis, i);
    }

    return res;
}
//994 //rotten oranges//
public int orangesRotting(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    
    Queue<Integer>qu = new LinkedList<>();
    int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    
    int freshOranges = 0; int time = 0;
    
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < m ; j++){
            if(grid[i][j] == 2){
                qu.add(i*m + j);
            }else if(grid[i][j] == 1){
                freshOranges++;
            }
        }
    }
    if (freshOranges == 0)
        return 0;
    
    while(qu.size() > 0){
        int size = qu.size();
        while(size-->0){
            int idx = qu.remove();
            int r = idx/m;
            int c = idx%m;
            
            for(int i = 0 ; i < dir.length ; i++){
                int x = r + dir[i][0];
                int y = c + dir[i][1];
                
                if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1 ){
                    freshOranges--;
                    grid[x][y] = 2;
                    qu.add(x*m + y);
                    if(freshOranges == 0){
                        return time + 1;
                    }
                }
            }
        }
        time++;
    }
    return -1;
}
//286 walls and gate// https://tenderleo.gitbooks.io/leetcode-solutions-/content/GoogleMedium/286.html
public void wallsAndGates(int[][] rooms){

    int n = rooms.length;
    int m = rooms[0].length;

    int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    Queue<Integer> que = new LinkedList<>();
    int countOfRooms = 0, distance = 0;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            if (rooms[i][j] == 0) // gates
                que.add(i * m + j);
            else if (rooms[i][j] == 2147483647)
                countOfRooms++;

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int idx = que.remove();
            
            int r = idx / m;
            int c = idx % m;

            for (int d = 0; d < 4; d++)
            {
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                if (x >= 0 && y >= 0 && x < n && y < m && rooms[x][y] == 2147483647)
                {
                    countOfRooms--;
                    rooms[x][y] = distance + 1;
                    que.add(x * m + y);

                    if (countOfRooms == 0)
                        return;
                }
            }
        }
        distance++;
    }
}
//kahns Algo//and course schedule..by BFS
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
//207 scourse schedule 1//DFS
public boolean canFinish(int N, int[][] prerequisites){
    ArrayList<Integer>[] graph = new ArrayList[N];
    
    for (int i = 0; i < N; ++i) 
        graph[i] = new ArrayList<Integer>();
    
    for (int[] ar : prerequisites) {
        graph[ar[1]].add(ar[0]);
    }
    return kahnsAlgo(N, graph).size() == N;
}
//course schedule 2//210
public int[] findOrder(int N, int[][] prerequisites) {
    ArrayList<Integer>[] graph = new ArrayList[N];
    
    for (int i = 0; i < N; ++i) 
        graph[i] = new ArrayList<Integer>();
    
    for (int[] ar : prerequisites) {
        graph[ar[1]].add(ar[0]);
    }
    ArrayList<Integer>ans = kahnsAlgo(N, graph);
    if(ans.size() != N){
        return new int[0];
    }
    int[] fArray = new int[ans.size()];
    
    for(int i = 0 ; i < ans.size() ; i++){
        fArray[i] = ans.get(i);
    }
    return fArray;
}
//and course schedule..by DFS
bool isCyclePresent_DFSTopo(int src, vector<int> &vis, vector<vector<int>> &graph)
{
    vis[src] = 0;
    bool res = false;
    for (int v : graph[src])
    {
        if (vis[v] == -1) // unvisited
            res = res || isCyclePresent_DFSTopo(v, vis, graph);
        else if (vis[v] == 0)
            return true; // there is cycle.
    }

    vis[src] = 1;
    return res;
}
//207 course schedule 1..by DFS
bool canFinish(int N, vector<vector<int>> &arr)
{
    vector<vector<int>> graph(N);
    for (vector<int> ar : arr)
    {
        graph[ar[0]].push_back(ar[1]);
    }
    vector<int> vis(N, -1);

    bool res = false;
    for (int i = 0; i < N; i++)
    {
        if (vis[i] == -1)
        {
            if (isCyclePresent_DFSTopo(i, vis, graph))
                return false;
        }
    }

    return true;
}
// 210 course schedule 1..by BFS
bool isCyclePresent_DFSTopo(int src, vector<int> &vis, vector<int> &ans, vector<vector<int>> &graph)
{
    vis[src] = 0;
    bool res = false;
    for (int v : graph[src])
    {
        if (vis[v] == -1) // unvisited
            res = res || isCyclePresent_DFSTopo(v, vis, ans, graph);
        else if (vis[v] == 0)
            return true; // there is cycle.
    }

    vis[src] = 1;
    ans.push_back(src);
    return res;
}
vector<int> findOrder(int N, vector<vector<int>> &arr)
{
    vector<vector<int>> graph(N);
    for (vector<int> ar : arr)
    {
        graph[ar[0]].push_back(ar[1]);
    }
    vector<int> vis(N, -1);
    vector<int> ans;

    bool res = false;
    for (int i = 0; i < N; i++)
        if (vis[i] == -1)
            res = res || isCyclePresent_DFSTopo(i, vis, ans, graph);

    if (res)
        ans.clear();
    return ans;
}
int longestIncreasingPath(vector<vector<int>> &matrix)
{
    int n = matrix.size();
    int m = matrix[0].size();
    vector<vector<int>> indegree(n, vector<int>(m, 0));

    vector<vector<int>> dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            for (int d = 0; d < 4; d++)
            {
                int x = i + dir[d][0];
                int y = j + dir[d][1];

                if (x >= 0 && y >= 0 && x < n && y < m && matrix[x][y] > matrix[i][j])
                    indegree[x][y]++;
            }

    queue<int> que;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            if (indegree[i][j] == 0)
                que.push(i * m + j);

    int level = 0;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int idx = que.front();
            que.pop();
            int r = idx / m;
            int c = idx % m;

            for (int d = 0; d < 4; d++)
            {
                int x = r + dir[d][0];
                int y = c + dir[d][1];

                if (x >= 0 && y >= 0 && x < n && y < m && matrix[x][y] > matrix[r][c] && --indegree[x][y] == 0)
                    que.push(x * m + y);
            }
        }

        level++;
    }

    return level;
}
//684 leetcode find Redundant Connection
int[] par ;
    public int findPar(int u){
        if(par[u] == -1)
            return u;
        
        par[u] = findPar(par[u]);
        
        return par[u];
    }
    public int[] findRedundantConnection(int[][]edges){
        int N = edges.length;
        par = new int[N+1];
        for(int i = 0 ; i < par.length ; i++){
            par[i] = -1;
        }
        for (int[] edge : edges)
        {
            int p1 = findPar(edge[0]);
            int p2 = findPar(edge[1]);

            if (p1 != p2)
            {
                par[p1] = p2;
            }
            else
            {
                return edge;
            }
        }
        return new int[0];
}
//ny union find
string smallestEquivalentString(string A, string B, string S)
{
    for (int i = 0; i < 26; i++)
        par.push_back(i);
    // par.resize(26,-1);

    for (int i = 0; i < A.length(); i++)
    {
        int p1 = findPar(A[i] - 'a');
        int p2 = findPar(B[i] - 'a');

        par[p1] = min(p1, p2);
        par[p2] = min(p1, p2);

        //     if(p1 < p2)
        //         par[p2] = p1;
        //     else if(p2 < p1)par[p1] = p2;
        //
    }

    string ans = "";
    for (int i = 0; i < S.length(); i++)
    {
        ans += (char)(findPar(S[i] - 'a') + 'a');
    }

    return ans;
}
//839///number of similar groups//using uniun find
    int[] par ;
    public int findParent(int i){  
        if(par[i] == i){
            return i;
        }
        par[i] = findParent(par[i]);
        return par[i];
    }
    public boolean isSimilarString(String X, String Y){
        
       int count = 0;
        for(int i = 0 ; i < X.length() ; i++){
            if(X.charAt(i) != Y.charAt(i))  
                count++;
        }
        if(count > 2){
            return false;
        }
        return true;
    }
    public int numSimilarGroups(String[] strs) {
       int count = strs.length;
       par = new int[count];
        
       for(int i = 0 ; i < strs.length ; i++){
           par[i] = i;
       } 
        
       for(int i = 0 ; i < strs.length ;i++){
           for(int j = i+1 ; j < strs.length ; j++){
               //agar string similar hai tohi kisi group me aegi
               if(isSimilarString(strs[i], strs[j])){
                   int p1 = findParent(i);
                   int p2 = findParent(j);
                   
                   if(p1 != p2){
                       par[p2] = p1;
                       count--;
                   }
               }
           }
       } 
        
       return count; 
    }
///////////////////////////
//305 using uniun find method 1 .with making grid
vector<int> numIslands2(int m, int n, vector<vector<int>> &positions)
{
    par.resize(m * n, -1);

    vector<vector<int>> dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    vector<vector<int>> grid(m, vector<int>(n, 0));
    int count = 0;
    vector<int> ans;
    for (vector<int> &pos : positions)
    {
        int i = pos[0];
        int j = pos[1];

        if (grid[i][j] != 1)
        {

            grid[i][j] = 1;
            count++;

            for (int d = 0; d < 4; d++)
            {
                int x = i + dir[d][0];
                int y = j + dir[d][1];

                if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == 1)
                {
                    int p1 = findPar(i * n + j);
                    int p2 = findPar(x * n + y);

                    if (p1 != p2)
                    {
                        count--;
                        par[p1] = p2;
                    }
                }
            }
        }

        ans.push_back(count);
    }

    return ans;
}
//305/using union find method 1 .without making grid
vector<int> numIslands2(int m, int n, vector<vector<int>> &positions)
{
    par.resize(m * n, -1);

    vector<vector<int>> dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    int count = 0;
    vector<int> ans;
    for (vector<int> &pos : positions)
    {
        int i = pos[0];
        int j = pos[1];

        if (par[i * n + j] == -1)
        {

            par[i * n + j] = (i * n + j);
            count++;

            for (int d = 0; d < 4; d++)
            {
                int x = i + dir[d][0];
                int y = j + dir[d][1];

                if (x >= 0 && y >= 0 && x < m && y < n && par[x * n + y] != -1)
                {
                    int p1 = findPar(i * n + j);
                    int p2 = findPar(x * n + y);

                    if (p1 != p2)
                    {
                        count--;
                        par[p1] = p2;
                    }
                }
            }
        }

        ans.push_back(count);
    }

    return ans;
}
//1168 using union find
public int minCostToSupplyWater(int N, ArrayList<int[]> Edges) {
    par = new int[N + 1];
    for (int i = 0; i <= N; i++) {
        par[i] = i;
    }

    int cost = 0;
    for (int[] edge : Edges) {
        int u = edge[0], v = edge[1], w = edge[2];
        int p1 = findPar(u);
        int p2 = findPar(v);

        if (p1 != p2) {
            par[p1] = p2;
            cost += w;
        }
    }
    return cost;
}
public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
    ArrayList<int[]> PIPES = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        PIPES.add(new int[] { 0, i + 1, wells[i] });
    }

    for (int[] p : pipes)
        PIPES.add(p);

    Collections.sort(PIPES, (a, b) -> {
        return a[2] - b[2];
    });

    return minCostToSupplyWater(n, PIPES);
}
//using union find
public int numIslands(char[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    
    par = new int[n*m];
    for(int i = 0 ; i < n*m; i++){
           par[i] = i;
    }
    int[][] dir = {{1,0},{0,1}};
    int count = 0;
    
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ;j < m ; j++){
            if(grid[i][j] == '1'){
                count++;
                for(int k = 0 ; k < dir.length ; k++){ 
                    int x = i + dir[k][0];
                    int y = j + dir[k][1];
                    if(x < n && y < m && x >= 0 && y >= 0 && grid[x][y] == '1'){
                        int p1 = findpar(i*m + j);
                        int p2 = findpar(x*m + y);
                        
                        if(p1 != p2){
                            par[p1] = p2;
                            count--;
                        }
                    }
                    
                }
                
            }
            
        }
    }

    return count;
}
//using union find 
public int maxAreaOfIsland(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;

    par = new int[n*m];
    int[] componentSize = new int[n*m];
    Arrays.fill(componentSize, 1);
    
    for(int i = 0 ; i < n*m; i++){
           par[i] = i;
    }
    int[][] dir = {{1,0},{0,1}};
    int maxArea = 0;

    
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ;j < m ; j++){
            if(grid[i][j] == 1){
                int p1 = findParent(i*m + j);
                for(int k = 0 ; k < dir.length ; k++){ 
                    int x = i + dir[k][0];
                    int y = j + dir[k][1];
                    if(x < n && y < m && x >= 0 && y >= 0 && grid[x][y] == 1){
                        
                        int p2 = findParent(x*m + y);

                        if(p1 != p2){
                            par[p] = p1;
                            componentSize[p1] += componentSize[p2];
                        }
                    }
                    maxArea = Math.max(maxArea, componentSize[p1]);
                }

            }else{
                componentSize[i*m + j] = 0;
            }

        }
    }

    return maxArea;   
}
//jouney to moon//https://www.hackerrank.com/challenges/journey-to-the-moon/problem
public static long journeyToMoon(int n, List<List<Integer>> astronaut) {
// Write your code here
    par = new int[n];
    int[] size = new int[n];
    for(int i = 0 ;i < n ; i++){
        par[i] = i;
        size[i] = 1;
    }
    
    for(List<Integer>list: astronaut){
        int p1 = findParent(list.get(0));
        int p2 = findParent(list.get(1));
        
        if(p1 != p2){
            par[p1] = p2;
            size[p2] += size[p1];
        }
    }
    long sum = 0;   long totalPairs = 0;
    
    for(int i = 0 ; i < n; i++){
        if(par[i] == i){
            totalPairs += size[i] * sum;
            sum += size[i];
        }   
    }
    return totalPairs;
}
// https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/practice-problems/algorithm/mr-president/
int mrPresident(int n, vector<vector<int>> &edges, long k)
{
    for (int i = 0; i <= n; i++)
        par.push_back(i);

    vector<int> mstEdgeWeight;
    long overallWeightSum = 0;
    for (vector<int> &e : edges)
    {
        int p1 = findPar(e[0]);
        int p2 = findPar(e[1]);

        if (p1 != p2)
        {
            par[p1] = p2;
            mstEdgeWeight.push_back(e[2]);
            overallWeightSum += e[2];
            n--;
        }
    }

    if (n > 1)
        return -1;
    if (overallWeightSum <= k)
        return 0;

    int transform = 0;
    for (int i = mstEdgeWeight.size() - 1; i >= 0; i--)
    {
        overallWeightSum = overallWeightSum - mstEdgeWeight[i] + 1;
        transform++;

        if (overallWeightSum <= k)
            break;
    }

    return overallWeightSum <= k ? transform : -1;
}
void mrPresident()
{
    ios_base::sync_with_stdio(false);
    long n, m, k;
    cin >> n >> m >> k;

    vector<vector<int>> edges;
    for (int i = 0; i < m; i++)
    {
        int u, v, w;
        cin >> u >> v >> w;
        edges.push_back({u, v, w});
    }

    sort(edges.begin(), edges.end(), [](vector<int> &a, vector<int> &b) {
        return a[2] < b[2];
    });

    cout << mrPresident(n, edges, k) << endl;
}
//815
int numBusesToDestination(vector<vector<int>> &routes, int src, int dest)
{

    if (src == dest)
        return 0;
    int n = routes.size();
    unordered_map<int, vector<int>> busStandMapping;
    int busNumber = 0;
    for (vector<int> &busStandList : routes)
    {
        for (int busStand : busStandList)
        {
            busStandMapping[busStand].push_back(busNumber);
        }
        busNumber++;
    }

    unordered_set<int> isBusStandSeen;
    vector<bool> isBusSeen(n, false);

    queue<int> que;
    que.push(src);
    isBusStandSeen.insert(src);

    int level = 0;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int busStand = que.front();
            que.pop();

            vector<int> allBuses = busStandMapping[busStand];
            for (int busNo : allBuses)
            {
                if (isBusSeen[busNo])
                    continue;

                for (int bs : routes[busNo]) // bs is bus stand
                {
                    if (isBusStandSeen.find(bs) == isBusStandSeen.end())
                    {
                        que.push(bs);
                        isBusStandSeen.insert(bs);

                        if (bs == dest)
                            return level + 1;
                    }
                }

                isBusSeen[busNo] = true;
            }
        }
        level++;
    }

    return -1;
}
//743
public int networkDelayTime(int[][] times, int n, int k) {

    ArrayList<int[]>[] graph = new ArrayList[n + 1];
    for (int i = 0; i < n + 1; i++)
        graph[i] = new ArrayList<>();

    // {u -> {v,w} }
    for (int[] ar : times) {
        graph[ar[0]].add(new int[] { ar[1], ar[2] });
    }

    int[] dis = new int[n + 1];
    Arrays.fill(dis, (int) 1e9);
    boolean[] vis = new boolean[n + 1];

    // {vtx,wsf}
    PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> {
        return a[1] - b[1];
    });

    que.add(new int[] { k, 0 });
    dis[k] = 0;
    int NoOfEdges = 0;
    int maxvalue = 0;
    while (que.size() != 0) {
        int[] p = que.remove();
        int vtx = p[0], wsf = p[1];

        if (vis[vtx])
            continue;
        if (vtx != k)
            NoOfEdges++;

        maxvalue = Math.max(maxvalue, wsf);

        vis[vtx] = true;
        for (int[] e : graph[vtx]) {
            if (!vis[e[0]] && e[1] + wsf < dis[e[0]]) {
                dis[e[0]] = e[1] + wsf;
                que.add(new int[] { e[0], e[1] + wsf });
            }
        }
    }

    if (NoOfEdges != n - 1)
        return -1;

    return maxvalue;

}
//787//check the solution that sir coded in cpp--> using dijistera. this sol is using bellmanFord
public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    int[] dis = new int[n];
    Arrays.fill(dis, (int) 1e9);
    dis[src] = 0;

    for (int EdgeCount = 1; EdgeCount <= K + 1; EdgeCount++) {
        int[] ndis = new int[n];
        for (int i = 0; i < n; i++)
            ndis[i] = dis[i];

        for (int[] e : flights) {
            int u = e[0], v = e[1], w = e[2];
            if (dis[u] != (int) 1e9 && dis[u] + w < ndis[v])
                ndis[v] = dis[u] + w;
        }

        dis = ndis;
    }

    return dis[dst] != (int) 1e9 ? dis[dst] : -1;
}
//924 
public int minMalwareSpread(int[][] graph, int[] initial) {
    int n = graph.length;
    size = new int[n];
    par = new int[n];

    for (int i = 0; i < n; i++) {
        par[i] = i;
        size[i] = 1;
    }

    Arrays.sort(initial);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i != j && graph[i][j] == 1) {
                int p1 = findPar(i);
                int p2 = findPar(j);

                if (p1 != p2) {
                    par[p1] = p2;
                    size[p2] += size[p1];
                }
            }
        }
    }

    int[] InfectedNodesInCity = new int[n];
    for (int i : initial) {
        int leader = findPar(i);
        InfectedNodesInCity[leader]++;
    }

    int ans = initial[0];
    int maxPopulatedCity = 0;
    for (int i : initial) {
        int NoOfNodesInfected = InfectedNodesInCity[findPar(i)];
        if (NoOfNodesInfected == 1 && size[findPar(i)] > maxPopulatedCity) {
            maxPopulatedCity = size[findPar(i)];
            ans = i;
        }
    }

    return ans;
}
//1192 Critical Connections in a Network
    static int[] dis, low;
    static boolean[] vis;
    static int time = 0, noOfCallsFromRoot = 0;
    List<List<Integer>> res = new ArrayList<>();
    void dfs(int src, int par, int n, List<Integer>[]graph)
    {
        dis[src] = low[src] = time++;
        vis[src] = true;

        for (int nbr : graph[src])
        {
            if (!vis[nbr])
            {
                dfs(nbr, src, n, graph);

                if (dis[src] < low[nbr]){
                    List<Integer>lAns = new ArrayList<>();
                    lAns.add(src);lAns.add(nbr);
                    res.add(lAns);
                }
                low[src] = Math.min(low[src], low[nbr]);
            }
            else if (nbr != par)
                low[src] = Math.min(dis[nbr], low[src]);
        }
    }
    public List<List<Integer>> criticalConnections(int N, List<List<Integer>> connections) {
        List<Integer>[]graph = new ArrayList[N];
        
        for (int i = 0; i < N; i++) 
            graph[i] = new ArrayList<>();
        
        for (List<Integer>ar : connections)
        {
            graph[ar.get(0)].add(ar.get(1));
            graph[ar.get(1)].add(ar.get(0));
        }
        low = new int[N];
        dis = new int[N];
        vis = new boolean[N];

        dfs(0, -1, N, graph);
        return res;
}
//859 regoins cut by slashesh
//redundant connections II
//721 Accounts merge
