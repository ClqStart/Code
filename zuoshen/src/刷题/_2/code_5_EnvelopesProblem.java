package 刷题._2;

import java.util.Arrays;
import java.util.Comparator;
//信封问题，可以装多少层信封，长大于，宽大于
//思路：将长正序排列，长相等将宽倒叙排列，求宽的最大上升序列；
public class code_5_EnvelopesProblem {

    public static class Envelope {
        private int w;
        private int h;

        public Envelope(int weight, int high) {
            this.w = weight;
            this.h = high;
        }
    }

    public static class EnvelopeComparator implements Comparator<Envelope> {
        @Override
        public int compare(Envelope o1, Envelope o2) {
            return o1.w == o2.w ? o2.h - o1.h : o1.w - o2.w;
        }
    }


    public static int maxEnvelopes(int[][] matrix) {
        Envelope[] res = new Envelope[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            res[i] = new Envelope(matrix[i][0], matrix[i][1]);
        }
        Arrays.sort(res, new EnvelopeComparator());

        int[] ends = new int[matrix.length];
        ends[0] = res[0].h;
        int l = 0;
        int r = 0;
        int right = 0;
        int m = 0;
        for (int i = 1; i < matrix.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if(res[i].h > ends[m] ){
                    l = m+1;
                }else {
                    r = m-1;
                }
            }
            right = Math.max(right,l);
            ends[l] = res[i].h;
        }
        return right+1;
    }

    public static void main(String[] args) {
        int[][] test = {{3, 4}, {2, 3}, {4, 5}, {1, 3}, {2, 2}, {3, 6}, {1, 2}, {3, 2}, {2, 4}};
        System.out.println(maxEnvelopes(test));
    }
}