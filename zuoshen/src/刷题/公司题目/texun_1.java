package 刷题.公司题目;


/**
 * 给定义整数power,给定一个数组arr,给定一个数组reverse,含义如下：
 * arr的长度一定是2的Power次方的长度，reverse每一个值一定都在0-power范围
 * 例如  power = 2;, arr= {3,1,4,2}; reverse={0,1,0,2}
 * 2^0:  分别对arr进行个数逆转，求最大逆序数
 * 2^1:
 * 2^0:
 * 2^2:
 */
public class texun_1 {

    public static int[] reversePair(int[] originArr, int[] reverseArr, int power) {
        int[] ans = new int[reverseArr.length];
        for (int i = 0; i < reverseArr.length; i++) {
            reverseArray(originArr, 1 << reverseArr[i]);
            ans[i] = countReversePair(originArr);
        }
        return ans;
    }

    private static int countReversePair(int[] originArr) {
        int count=0;
        for (int i = 0; i < originArr.length; i++) {
            for (int j = i+1; j < originArr.length ; j++) {
                if(originArr[i]< originArr[j]){
                    count++;
                }
            }
        }
        return count;
    }

    private static void reverseArray(int[] originArr, int teamSize) {
        if (teamSize < 2) {
            return;
        }
        for (int i = 0; i < originArr.length; i += teamSize) {
            reverPart(originArr, i, i + teamSize - 1);
        }

    }

    private static void reverPart(int[] originArr, int L, int R) {
        while (L < R) {
            int tmp = originArr[L];
            originArr[L++] = originArr[R];
            originArr[R--] = tmp;
        }
    }


    public static void main(String[] args) {


    }
}
