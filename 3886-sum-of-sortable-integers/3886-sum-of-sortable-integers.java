import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        
        int totalSum = 0;
        
        // Iterate over all divisors k of n
        for (int k = 1; k * k <= n; k++) {
            if (n % k == 0) {
                if (checkDivisor(nums, sortedNums, k)) {
                    totalSum += k;
                }
                int k2 = n / k;
                if (k2 != k) {
                    if (checkDivisor(nums, sortedNums, k2)) {
                        totalSum += k2;
                    }
                }
            }
        }
        
        return totalSum;
    }
    
    private boolean checkDivisor(int[] nums, int[] sortedNums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i += k) {
            if (!canRotateMatch(nums, sortedNums, i, i + k)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean canRotateMatch(int[] nums, int[] sortedNums, int start, int end) {
        int k = end - start;
        
        // If all elements in this block are identical, a cyclic rotation is trivially true
        if (nums[start] == nums[end - 1] && nums[start] == sortedNums[start]) {
            // Quick check if the entire block matches
            boolean allEqual = true;
            for (int i = 0; i < k; i++) {
                if (nums[start + i] != sortedNums[start + i]) {
                    allEqual = false;
                    break;
                }
            }
            if (allEqual) return true;
        }
        
        // General case: find valid shift candidates efficiently
        int targetVal = sortedNums[start];
        for (int shift = 0; shift < k; shift++) {
            if (nums[start + shift] == targetVal) {
                // Optimization: quickly verify boundary or skip redundant starts
                if (nums[start + (shift + k - 1) % k] != sortedNums[end - 1]) {
                    continue;
                }
                boolean match = true;
                for (int i = 0; i < k; i++) {
                    if (nums[start + (shift + i) % k] != sortedNums[start + i]) {
                        match = false;
                        break;
                    }
                }
                if (match) return true;
            }
        }
        
        return false;
    }
}