//4.03.2021
//1168 optimize-water-distribution-in-a-village/
//https://github.com/azl397985856/leetcode/blob/master/problems/1168.optimize-water-distribution-in-a-village-en.md
//similar-string-groups------------(839)

    int[] par;

    //find parent
    public int findPar(int u) {
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }
    
    //check if two strings are similar
    public boolean isSimilar(String s1 , String s2){
        int count = 0;
        for(int i = 0; i < s1.length() ; i++){
            if(s1.charAt(i) != s2.charAt(i) && ++count > 2)
                return false;
        }
        return true;
    }
    
    public int numSimilarGroups(String[] strs) {
        int count = strs.length;
        int n = strs.length;
        
        par = new int[n];
        //make parent of itself
        for(int i = 0;i<n;i++){
            par[i] = i;
        }
        //iterate over the values and make groups
        for(int i=0;i<n;i++){
            for(int j = i+1;j<n;j++){
                if(isSimilar(strs[i],strs[j])){
                    int p1 = findPar(i);
                    int p2 = findPar(j);
                    
                    if(p1 != p2){
                        par[p1] = p2;
                        count--;
                    }
                }
            }
        }
        return count;
        
    }
    // ----https://www.lintcode.com/problem/number-of-islands-ii
    //(305)no of island 2(locked in leetcode)
//305
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

//305
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
