class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        // Check if already sorted
        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;
        
        // Strings of length 2 cannot be sorted if they aren't already sorted
        if (n == 2) return -1;
        
        int min_c = 0;
        int max_c = 0;
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) < s.charAt(min_c)) {
                min_c = i;
            }
            if (s.charAt(i) >= s.charAt(max_c)) {
                max_c = i;
            }
        }
        
        // If min char's first occurrence is at index 0, or max char's last occurrence is at index n - 1
        if (min_c == 0 || max_c == n - 1) {
            return 1;
        }
        
        // If completely reversed (min at the very end, max at the very beginning)
        if (min_c == n - 1 && max_c == 0) {
            return 3;
        }
        
        return 2;
    }
}