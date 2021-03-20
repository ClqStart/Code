import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class _闹钟__ {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Scanner scanner = new Scanner(System.in);

        int time = scanner.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < time; i++) {
            list.add(scanner.nextInt() * 60 + scanner.nextInt());
        }
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int Xtime = scanner.nextInt();
        int Onclass = scanner.nextInt() * 60 + scanner.nextInt();
        int StartTime = Onclass - Xtime;

        int l = 0, r = list.size() - 1;
        int m = (l+r) >> 1;
        while (l < r) {
            if (StartTime > list.get(m)) {
                l = m+1;
            } else {
                r = m-1;
            }

        }
        if (r < 0)
            System.out.println(list.get(0) / 60 + " " + list.get(0) % 60);
        else
            System.out.println(list.get(r) / 60 + " " + list.get(r) % 60);
        scanner.close();


    }
}
