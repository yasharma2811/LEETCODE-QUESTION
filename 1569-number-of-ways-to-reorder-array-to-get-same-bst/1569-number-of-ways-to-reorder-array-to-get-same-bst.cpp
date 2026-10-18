#include <vector>
#include <unordered_map>

using namespace std;

class Solution {
    long long MOD = 1e9 + 7;
    vector<vector<long long>> table;

    // Helper to compute combination C(n, k) using Pascal's triangle
    long long combination(int n, int k) {
        if (k > n || k < 0) return 0;
        if (k == 0 || k == n) return 1;
        if (table[n][k] != -1) return table[n][k];
        return table[n][k] = (combination(n - 1, k - 1) + combination(n - 1, k)) % MOD;
    }

    long long dfs(const vector<int>& nums) {
        int n = nums.size();
        if (n <= 2) return 1;

        vector<int> left, right;
        int root = nums[0];

        for (int i = 1; i < n; ++i) {
            if (nums[i] < root) {
                left.push_back(nums[i]);
            } else {
                right.push_back(nums[i]);
            }
        }

        long long leftWays = dfs(left) % MOD;
        long long rightWays = dfs(right) % MOD;

        // Choose left.size() positions out of (left.size() + right.size()) for the left subtree elements
        long long ways = combination(left.size() + right.size(), left.size());
        
        return (((ways * leftWays) % MOD) * rightWays) % MOD;
    }

public:
    int numOfWays(vector<int>& nums) {
        int n = nums.size();
        table = vector<vector<long long>>(n + 1, vector<long long>(n + 1, -1));
        // Subtract 1 because the original array ordering itself is not counted as a "reordering"
        return (dfs(nums) - 1 + MOD) % MOD;
    }
};