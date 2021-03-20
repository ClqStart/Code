package 刷题.公司题目;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * 数组为{3,2,2,3,1}，查询为（0,3,2）；
 * 查询0-3上有几个2
 */

public class  TodayTop_1 {

    public static int QueryNumberTime(int[] arr, int[] query) {
        if (arr.length < 1 || query.length != 3) return 0;
        //建立预处理结构
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], new ArrayList<>());
            }
            map.get(arr[i]).add(i);
        }
        int L = query[0];
        int R = query[1];
        int value = query[2];
        if (L > R) {
            return 0;
        }
        if (!map.containsKey(arr[2])) {
            return 0;
        }
        //小于 L 的下表
        int LeftIndex = TwoPart(map.get(value), L);

        //小于 R+1的坐标
        int RightIndex = TwoPart(map.get(value), R + 1);
        return RightIndex - LeftIndex;
    }

    public static int TwoPart(ArrayList<Integer> arr, int limit) {
        int l = 0;
        int r = arr.size() - 1;
        int mostRight = -1; //邵兵
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr.get(mid) < limit) {
                mostRight = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return mostRight + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,2,3,4,5,2,6,2};
        int[] part = {0,12,2};
        int time = QueryNumberTime(arr, part);
        System.out.println(time);

    }
}
