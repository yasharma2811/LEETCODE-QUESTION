class Solution {
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num; // XOR operator cancels out numbers that appear twice
        }
        return single;
    }
}