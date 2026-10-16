#include <vector>
#include <map>

using namespace std;

class Solution {
    struct Node {
        long long sum_val;
        long long lazy;
    };

    int n;
    vector<Node> tree;

    void push(int node, int l, int r) {
        if (tree[node].lazy != 0) {
            int mid = (l + r) / 2;
            long long lz = tree[node].lazy;

            // Left child
            tree[2 * node].sum_val += lz * (mid - l + 1);
            tree[2 * node].lazy += lz;

            // Right child
            tree[2 * node + 1].sum_val += lz * (r - mid);
            tree[2 * node + 1].lazy += lz;

            tree[node].lazy = 0;
        }
    }

    void update(int node, int l, int r, int ql, int qr, long long val) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            tree[node].sum_val += val * (r - l + 1);
            tree[node].lazy += val;
            return;
        }
        push(node, l, r);
        int mid = (l + r) / 2;
        update(2 * node, l, mid, ql, qr, val);
        update(2 * node + 1, mid + 1, r, ql, qr, val);
        tree[node].sum_val = (tree[2 * node].sum_val + tree[2 * node + 1].sum_val);
    }

    long long query(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return tree[node].sum_val;
        push(node, l, r);
        int mid = (l + r) / 2;
        return (query(2 * node, l, mid, ql, qr) + query(2 * node + 1, mid + 1, r, ql, qr));
    }

public:
    int sumCounts(vector<int>& nums) {
        n = nums.size();
        tree.assign(4 * n + 1, {0, 0});
        
        map<int, int> last_seen;
        long long current_sum_sq = 0;
        long long total_sum = 0;
        int MOD = 1e9 + 7;

        for (int i = 0; i < n; ++i) {
            int prev_idx = -1;
            if (last_seen.find(nums[i]) != last_seen.end()) {
                prev_idx = last_seen[nums[i]];
            }
            last_seen[nums[i]] = i;

            // Update segment tree for range (prev_idx + 1) to i
            long long current_linear_sum = query(1, 0, n - 1, prev_idx + 1, i);
            
            // Mathematical relation: sum of (cnt + 1)^2 = sum of (cnt^2 + 2*cnt + 1)
            current_sum_sq = (current_sum_sq + 2 * current_linear_sum + (i - prev_idx)) % MOD;
            
            update(1, 0, n - 1, prev_idx + 1, i, 1);
            
            total_sum = (total_sum + current_sum_sq) % MOD;
        }

        return total_sum;
    }
};