package 刷题._1;

/**
 * 给定一个正整数，请构造一个长度为M的数组arr,要求对任意i、j、k， 如果i<j<k , aar[i] + arr[j] != 2 * arr[j]
 * 返回构造的arr
 * 解决问题
 *  递归分治
 *  左边奇数，右边偶数， 递归从{1}开始 -》{1,2}-》{1,3,4}-》{1,5,7,2,6,8}
 */
public class code_6 {

    private static int[] makeNo(int size) {

        if (size == 1) {
            return new int[]{1};
        }

        int haftSize = (size + 1) / 2;
        int[] base = makeNo(haftSize);
        int[] res = new int[size];
        for (int i = 0; i < haftSize; i++) {
            res[i] = base[i] * 2 - 1;
        }
        for (int i = haftSize; i < size; i++) {
            res[i] = base[i-haftSize]*2;
        }
        return res;
    }


    // 检验函数
    public static boolean isValid(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int k = i + 1; k < N; k++) {
                for (int j = k + 1; j < N; j++) {


                    if (arr[i] + arr[j] == 2 * arr[k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println("test begin");
        for (int N = 1; N < 1000; N++) {
            int[] arr = makeNo(N);
            if (!isValid(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");

        System.out.println(isValid(makeNo(1042)));
        System.out.println(isValid(makeNo(2981)));
    }


}
