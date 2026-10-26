class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int islandCount = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If it's land (positive value) and not visited yet
                if (grid[i][j] > 0 && !visited[i][j]) {
                    long currentIslandSum = 0;
                    
                    // BFS Queue to traverse the island
                    java.util.Queue<int[]> queue = new java.util.LinkedList<>();
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {
                        int[] cell = queue.poll();
                        int r = cell[0];
                        int c = cell[1];
                        
                        currentIslandSum += grid[r][c];

                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];

                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && 
                                grid[nr][nc] > 0 && !visited[nr][nc]) {
                                visited[nr][nc] = true;
                                queue.offer(new int[]{nr, nc});
                            }
                        }
                    }

                    // Check if total value of the island is divisible by k
                    if (currentIslandSum % k == 0) {
                        islandCount++;
                    }
                }
            }
        }

        return islandCount;
    }
}