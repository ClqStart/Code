public class _归并__ {


    public static void main(String[] args) {
        int[] arr = {11, 44, 23, 67, 88, 65, 34, 48, 9, 12};
        int[] tmp = new int[arr.length];
        mergerSort(arr, 0, arr.length - 1, tmp);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    private static void mergerSort(int[] arr, int low, int high, int[] tmp) {
        if (low < high) {
            int mid = (low + high) >> 1;
            mergerSort(arr, low, mid, tmp);
            mergerSort(arr, mid + 1, high, tmp);
            sort(arr, low, mid, high, tmp);
        }

    }

    private static void sort(int[] arr, int low, int mid, int high, int[] tmp) {
        int first = 0;
        int i = low, j = mid + 1;
        while (i <= mid && j <= high) {
            if (arr[i] < arr[j]) {
                tmp[first++] = arr[i++];
            } else {
                tmp[first++] = arr[j++];
            }
        }
        while (i <= mid) {
            tmp[first++] = arr[i++];
        }
        while (j <= high) {
            tmp[first++] = arr[j++];
        }
        for (int k = 0; k < first; k++) {
            arr[low + k] = tmp[k];
        }

    }

}
