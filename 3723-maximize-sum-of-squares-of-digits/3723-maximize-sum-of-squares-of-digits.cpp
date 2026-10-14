#include <string>
#include <algorithm>

class Solution {
public:
    string maxSumOfSquares(int num, int sum) {
        // A valid sum can range from 0 up to 9 * num
        if (sum < 0 || sum > 9LL * num) {
            return "";
        }
        
        string result = "";
        result.reserve(num);
        
        for (int i = 0; i < num; ++i) {
            int remaining_digits = num - 1 - i;
            
            // To maximize the sum of squares, we want digits to be as large as possible.
            // Try digits from 9 down to 0 for the current position.
            int curr = min(9, sum);
            while (curr >= 0 && (sum - curr > remaining_digits * 9)) {
                curr--;
            }
            
            result += to_string(curr);
            sum -= curr;
        }
        
        return result;
    }
};