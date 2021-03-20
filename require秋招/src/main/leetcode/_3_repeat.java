import java.util.HashMap;

/*
有点动态规划的意思
li: 上一个字符的最长不重复位置
pi: 当前该字符上一个出现的位置
位置关系考虑一种情况，最长子串中间出现重复
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null) return 0;
        char[] chars = s.toCharArray();
        if(chars.length ==0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        map.put(chars[0],0);
        int li = 0;
        int max = 1;
        for (int i = 1; i <chars.length ; i++) {
            int pi = map.getOrDefault(chars[i],-1);
            if(li <= pi){
                li = pi +1;
            }
            map.put(chars[i],i);
            max = Math.max(max,i-li+1);
        }

        return max;
    }

}