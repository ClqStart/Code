import java.util.Arrays;
import java.util.Scanner;

public class __发工资__ {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int m = scanner.nextInt();
        int[] worker = new int[m];
        int[] salary = new int[m];
        int result=0;
        Arrays.fill(salary,100);
        for (int i = 0; i < m; i++) {
            for (int j = i - 1; j > 0; j--) {
                if (worker[j] < worker[j + 1]) break;
                salary[j] = Math.max(salary[j], salary[j + 1] + 100);
            }

            for (int j = i + 1; j < m; j++) {
                if (worker[j - 1] < worker[j]) break;
                salary[j] = Math.max(salary[j], salary[j - 1] + 100);
            }
        }
        for (int i = 0; i < m; i++) {
            result += salary[i];
        }
    }
}
