import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
有点动态规划的意思
li: 上一个字符的最长不重复位置
pi: 当前该字符上一个出现的位置
位置关系考虑一种情况，最长子串中间出现重复
*/
class _3_re {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        char[] chars = s.toCharArray();
        if (chars.length == 0) return 0;

        int l = 0, r = -1;
        int res = 0;

        Set<Character> status = new HashSet<>();

        while (r < chars.length) {

            while (r < chars.length) {
                r++;
                if(r == chars.length) break;
                if (!status.contains(chars[r])) {
                    status.add(chars[r]);
                    res = Math.max(res, status.size());
                } else {
                    break;
                }
            }
            if (r == chars.length) break;
            while (l < r) {
                if (chars[l] != chars[r]) {
                    status.remove(chars[l]);
                    l++;
                } else {
                    l++;
                    break;
                }

            }

        }
        return res;

    }

}