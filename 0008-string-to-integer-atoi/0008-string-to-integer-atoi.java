class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int i = 0;
        int n = s.length();
        
        // Step 1: Skip leading whitespaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        
        // If the string is all whitespace
        if (i == n) {
            return 0;
        }
        
        // Step 2: Check for sign (+ or -)
        int sign = 1;
        if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (s.charAt(i) == '+') {
            i++;
        }
        
        // Step 3: Convert digits and handle overflow
        long result = 0; // Use long to detect overflow beyond 32-bit limits during multiplication
        
        while (i < n) {
            char c = s.charAt(i);
            // Stop if character is not a digit
            if (c < '0' || c > '9') {
                break;
            }
            
            int digit = c - '0';
            result = result * 10 + digit;
            
            // Step 4: Handle 32-bit signed integer overflow/rounding
            if (sign == 1 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && -result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            
            i++;
        }
        
        return (int) (result * sign);
    }
}