import java.util.Arrays;


public class StringTest {
    public void  test01() {
        String str = "sdheasbvzdx";
        String[] s = str.split("");

        Arrays.sort(s);

        for (String s1 : s) {
            System.out.print(s1+" ");
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,5,6,7,8,3,5,6,7,8,9,0};

        for (int i = 0; i < arr.length; i++) {
            int num=0;

            for (int j = 0; j < arr.length ; j++) {
                if(arr[i] == arr[j]){
                    num++;
                }

            }
            if(num == 1){
                System.out.println(arr[i]);
            }

        }

    }

}

