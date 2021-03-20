package 刷题._1;

/**
 * RRRGGG字符串修改最少次数达到左边R右边都是G
 * 思路：用一个数组从左往右存放R从右到左的个数
 * 2、分界线以此从左往右进行划分
 */
public class code_4 {
    public static int minPart(String str) {
        if (str == null || str.length() < 1) return 0;
        char[] chars = str.toCharArray();
        int N = str.length();
        int[] R = new int[N];
        R[N - 1] = chars[N - 1] == 'R' ? 1 : 0;
        for (int i = N - 2; i >= 0; i--) {
            R[i] += R[i + 1] + (chars[i] == 'R' ? 1 : 0);
        }
        int G = 0;
        int res = R[0];
        for (int i = 0; i < N - 1; i++) {
            G += chars[i] == 'G' ? 1 : 0;
            res = Math.min(res, G + R[i + 1]);
        }
        return Math.min(res, G + (chars[N - 1] == 'G' ? 1 : 0));

    }

    public static int minPart1(String str) {
        if (str == null || str.length() < 1) return 0;
        char[] chars = str.toCharArray();
        int N = str.length();
        int R =0;
        for (int i = N-1; i >=0; i--) {
           R += chars[i] == 'R'? 1 :0;
        }

        int G = 0;
        int res = R;
        for (int i = 0; i < N - 1; i++) {
            G += chars[i] == 'G' ? 1 : 0;
            res = Math.min(res, G + (R = (chars[i] == 'R'?--R:R)));
        }
        return Math.min(res, G + (chars[N - 1] == 'G' ? 1 : 0));

    }


    public static void main(String[] args) {
        String test = "RGGGGGG";
        System.out.println(minPart(test));
        System.out.println(minPart1(test));
    }
}
