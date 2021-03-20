import java.util.Scanner;

public class _最大最小间距__ {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int dis = scanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        int hight =Integer.MAX_VALUE;
        int low =0;
        for (int i = 0; i < N; i++) {
            low = Math.min(arr[i]-dis,low);
            hight = Math.min(arr[i]+dis,hight);
        }
        System.out.println(low +" "+hight);
        int time=0;
        for (int i = 0; i < N; i++) {
            if(arr[i]>hight || arr[i]<low){
                time++;
            }
        }
        System.out.println(time);

    }


}
