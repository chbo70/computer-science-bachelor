package JavaFiles;

public class BipartiterGraph {
    //bipartite graph
    public static void main(String[] args) {
        int[][] graph = new int[][] {
            {0, 1, 0, 1},
            {1, 0, 1, 0},
            {0, 1, 0, 1},
            {1, 0, 1, 0}
        };
        int[] color = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == 0) {
                if (dfs(graph, color, i, 1)) {
                    System.out.println("Graph is bipartite");
                } else {
                    System.out.println("Graph is not bipartite");
                }
            }
        }
    }

    public static boolean dfs(int[][] graph, int[] color, int i, int c) {
        if (color[i] != 0) {
            return color[i] == c;
        }
        color[i] = c;
        for (int j = 0; j < graph[i].length; j++) {
            if (graph[i][j] == 1) {
                if (!dfs(graph, color, j, -c)) {
                    return false;
                }
            }
        }
        return true;
    }
}
