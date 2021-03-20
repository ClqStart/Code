package 刷题._1;

public class code_18_LCSubstring {

    public static String lcst1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = new int[chs1.length][chs2.length];
        dp[0][0] = chs1[0] == chs2[0] ? 1 : 0;
        for (int i = 1; i < chs1.length; i++) {
            dp[i][0] = chs1[i] == chs2[0] ? 1 : 0;
            for (int j = 1; j < chs2.length; j++) {
                dp[0][j] = chs1[0] == chs2[j] ? 1 : 0;
                if (chs1[i] == chs2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int end = 0;
        int max = 0;
        for (
                int i = 0;
                i < chs1.length; i++) {
            for (int j = 0; j < chs2.length; j++) {
                if (dp[i][j] > max) {
                    end = i;
                    max = dp[i][j];
                }
            }
        }

        return str1.substring(end - max + 1, end + 1);
    }

    public static String lcst2(String str1, String str2){
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        //右上方往左再往下，进行对角线求解
        int row = 0; // 出发点的行号
        int col = str2.length() - 1; // 出发点的列号
        int max = 0;
        int end =0;
        while (row < str1.length()){
            int i = row;
            int j = col;
            int len = 0;
            //对角线滑行
            while (i<chs1.length &&  j < chs2.length){
                if(chs1[i] !=chs2[j]){
                    len = 0;
                }else {
                    end =i;
                    len++;
                }
                if(len > max){
                    max =len;
                }
                i++;
                j++;
            }
            //[0][col]->[0][0]->[row][0]结束
            //往左走
            if(col >0){
                col--;
            }else {  //往下走
                row++;
            }

        }

        return str1.substring(end-max+1,end+1);

    }

    public static void main(String[] args) {
        String str1 = "ABC1234567DEFG";
        String str2 = "HIJKL1234567MNOP";
        System.out.println(lcst1(str1, str2));
        System.out.println(lcst2(str1, str2));

    }
}
