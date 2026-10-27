class Solution {
    public String[] createGrid(int m, int n) {
        // Initialize the grid with '#' (obstacles)
        StringBuilder[] grid = new StringBuilder[m];
        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append('#');
            }
            grid[i] = sb;
        }
        
        // Mark the top row as free cells (move right)
        for (int j = 0; j < n; j++) {
            grid[0].setCharAt(j, '.');
        }
        
        // Mark the last column as free cells (move down)
        for (int i = 0; i < m; i++) {
            grid[i].setCharAt(n - 1, '.');
        }
        
        // Convert StringBuilder array to String array
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = grid[i].toString();
        }
        
        return result;
    }
}