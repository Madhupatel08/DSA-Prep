// {{u,v,w}.....}
public static void bellmanFord(int src, int[][] edges, int N) {
    int[] dis = new int[N];
    Arrays.fill(dis, (int) 1e9);

    dis[src] = 0;
    boolean isNegativeCycle = false;

    for (int EdgeCount = 1; EdgeCount <= N; EdgeCount++) {
        int[] ndis = new int[N];
        for (int i = 0; i < N; i++)
            ndis[i] = dis[i];

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            if (dis[u] != (int) 1e9 && dis[u] + w < ndis[v]) {
                if (EdgeCount == N) {
                    isNegativeCycle = true;
                    break;
                }

                ndis[v] = dis[u] + w;
            }
        }

        dis = ndis;
    }

    
}