
import java.util.Arrays;

public class __排列_ {

    public static void arrangementSelect(String[] dataList, int n) {
        arrangementSelect(dataList, new String[n], 0);
    }

    private static void arrangementSelect(String[] dataList, String[] resultList, int resultIndex) {
        int resultLen = resultList.length;
        if (resultIndex >= resultLen) {
            System.out.println(Arrays.asList(resultList));
            return;
        }
        for (int i = 0; i < dataList.length; i++) {
            boolean exit = false;
            for (int j = 0; j < resultList.length; j++) {
                if (dataList[i].equals(resultList[j])) {
                    exit = true;
                    break;
                }
            }
            if (!exit) {
                resultList[resultIndex] = dataList[i];
                arrangementSelect(dataList, resultList, resultIndex + 1);
            }

        }
    }

    public static void main(String[] args) {
        arrangementSelect(new String[]{"1", "2", "3", "4"}, 2);
    }
}
