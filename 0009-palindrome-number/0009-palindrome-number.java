class Solution {
    public boolean isPalindrome(int x) {
        // Negative numbers and numbers ending in 0 (except 0 itself) cannot be palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // If the number is palindrome, the first half (x) should equal the reverted second half 
        // (or revertedNumber / 10 if the number of digits is odd)
        return x == revertedNumber || x == revertedNumber / 10;
    }
}