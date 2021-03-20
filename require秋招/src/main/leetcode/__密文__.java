import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class __密文__ {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int K = scanner.nextInt();
        String secret = scanner.nextLine();
        int len = N+K-1;
        int[] start = new int[len];
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            start[i] = secret.charAt(i) - '0';
        }
         res[0]= start[0];
        for (int i = 1; i < K ; i++) {
            res[i] = start[i-1]^start[i];
        }
        for (int i = K+1; i <N ; i++) {
            res[i] = res[i-1] ^ start[i-1]^ start[i];
        }
        scanner.close();
    }


}
