import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // If the character is already in the window, shift the left pointer past its last occurrence
            if (map.containsKey(currentChar) && map.get(currentChar) >= left) {
                left = map.get(currentChar) + 1;
            }
            
            // Update the latest index of the character
            map.put(currentChar, right);
            
            // Calculate maximum length of the substring without repeating characters
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}