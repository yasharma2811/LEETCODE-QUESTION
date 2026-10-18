class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int i = 0;
        int n = s.length();

        // Step 1: Skip leading whitespace
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // If string is all whitespace
        if (i == n) {
            return 0;
        }

        // Step 2: Check sign
        int sign = 1;
        if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (s.charAt(i) == '+') {
            i++;
        }

        // Step 3: Convert digits and handle overflow/underflow
        long result = 0; // Using long to catch overflow before clamping
        while (i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');

            // Step 4: Check boundaries and clamp if out of 32-bit signed integer range
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