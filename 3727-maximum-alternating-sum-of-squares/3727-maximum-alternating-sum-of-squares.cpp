#include <vector>
#include <numeric>
#include <algorithm>

class Solution {
public:
    long long maxAlternatingSum(vector<int>& nums) {
        int n = nums.size();
        vector<long long> sq(n);
        
        for (int i = 0; i < n; ++i) {
            sq[i] = (long long)nums[i] * nums[i];
        }
        
        sort(sq.begin(), sq.end());
        
        long long total_sum = 0;
        for (long long val : sq) {
            total_sum += val;
        }
        
        int k = n / 2;
        long long smallest_sum = 0;
        for (int i = 0; i < k; ++i) {
            smallest_sum += sq[i];
        }
        
        return total_sum - 2 * smallest_sum;
    }
};