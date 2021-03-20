package 第三期.class_1;


/**
 * 有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。
 * 现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色,这个正方形的颜色将
 * 会被覆盖。目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近。 返回最少需要涂染几个正方形。
 * 如样例所示: s = RGRGR 我们涂染之后变成RRRGG满足要求了,涂染的个数为2,没有比这个更好的涂染方案。
 */
public class _4_ColorLeftRight {

    public static int MinColor(String s) {
        if (s == null || s.length() < 1) return 0;
        char[] arr = s.toCharArray();
        int[] R = new int[arr.length];
        R[arr.length - 1] = arr[arr.length - 1] == 'R' ? 1 : 0;
        for (int i = arr.length - 2; i >= 0; i--) {
            R[i] = R[i + 1] + (arr[i] == 'R' ? 1 : 0);
        }
        int G = 0;
        int res = R[0]; //默认都为右侧可以当成全部变为G
        for (int i = 0; i < arr.length - 1; i++) {
            G += (arr[i] == 'G' ? 1 : 0);
            res = Math.min(res, G + R[i + 1]);
        }
        //彻底为左部分，右部分无的情况
        return Math.min(res, G + (arr[arr.length - 1] == 'G' ? 1 : 0));
    }

    public static int minPart1(String str) {
        if (str == null || str.length() < 1) return 0;
        char[] chars = str.toCharArray();
        int N = str.length();
        int R = 0;
        for (int i = N - 1; i >= 0; i--) {
            R += chars[i] == 'R' ? 1 : 0;
        }

        int G = 0;
        int res = R; //全默认都为右侧可以当成全部变为G
        for (int i = 0; i < N - 1; i++) {
            G += chars[i] == 'G' ? 1 : 0;
            res = Math.min(res, G + (R = chars[i] == 'R' ? --R : R));
        }
        return Math.min(res, G + (chars[N - 1] == 'G' ? 1 : 0));

    }


    public static void main(String[] args) {
        String test = "RGGGRRG";
        System.out.println(MinColor(test));
        System.out.println(minPart1(test));
    }
}
