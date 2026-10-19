class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int lessCount = 0;
        int targetCount = 0;
        
        // Count numbers strictly less than target and numbers equal to target
        for (int num : nums) {
            if (num < target) {
                lessCount++;
            } else if (num == target) {
                targetCount++;
            }
        }
        
        // Construct the result list of indices
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < targetCount; i++) {
            result.add(lessCount + i);
        }
        
        return result;
    }
}