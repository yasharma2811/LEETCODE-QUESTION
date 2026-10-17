class Solution {
public:
    int getDigitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    int minSwaps(vector<int>& nums) {
        int n = nums.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {getDigitSum(nums[i]), nums[i]};
        }

        // Create a vector of indices to perform a stable sort based on custom criteria
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        stable_sort(p.begin(), p.end(), [&](int i, int j) {
            if (arr[i].first != arr[j].first)
                return arr[i].first < arr[j].first;
            return arr[i].second < arr[j].second;
        });

        // Cycle detection to find minimum swaps
        vector<bool> visited(n, false);
        int cycles = 0;

        for (int i = 0; i < n; ++i) {
            if (visited[i] || p[i] == i) {
                continue;
            }
            int cycle_size = 0;
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                curr = p[curr];
                cycle_size++;
            }
            if (cycle_size > 0) {
                cycles += (cycle_size - 1);
            }
        }

        return cycles;
    }
};