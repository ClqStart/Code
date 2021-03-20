package video.二叉树;



public class Main {
    public static void main(String args[]) {
        Main so = new Main();
        int[]  res = {2,51,12,95,42,52,76,77,23,81,71,41,2,23,43,4,64,22,71,96,1,87,51,91,67,16,58,11,44,38,63,14,4,69,88,49,92,91,9,15,17,74,21,91,24,78,62,50,82,26,53,18,25,14,94,79,44,11,36,38,44,53,9,34,58,6,50,82,81,50,36,1,6,61,9,47,33,47,84,41,57,48,73,18};
        int i = so.solve(res);
        System.out.println(i);
        System.out.println(res[i]);
        System.out.println(res.length);
        System.out.println(res[82]);
        StringBuilder  s1=new StringBuilder();
    }

    public int solve (int[] a) {
        // write code here
        int len = a.length;
        int max = Integer.MIN_VALUE;
        int index= -1;
        for(int i =1;i < len-1;i++){
            if(a[i]>max && a[i+1]<=a[i]){
                max = a[i];
                index =i;
            }
        }
        return index;
    }


}
