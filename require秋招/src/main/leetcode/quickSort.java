
//不稳定的
//可以看 one table sort 论文实现稳定性;

public class quickSort {


    public  static  void  randomQuickSort(int[] arr, int l , int r){
        if(l<r){
            //随机选择一个数和最后一个数进行交换，默认最后一个数作为比较数。
            swap(arr,l+(int)(Math.random())*(r-l+1),r);
            int[] p = partition1(arr, l, r);
            quickSort(arr,l,p[0]-1);
            quickSort(arr,p[1]+1,r);

        }

    }

    public  static  void  quickSort(int[] arr,int l ,int r){

       if(l<r){
           int[] p = partition1(arr, l, r);
           quickSort(arr,l,p[0]-1);
           quickSort(arr,p[1]+1,r);
       }

    }

    public  static  int[] partition1(int[] arr,int l,int r){
        //最后一个数作为最大值划分边界，作为比较的值，最后进行位置互换
        int less = l-1;
        int more = r;
        while (l < more){
            if(arr[l] < arr[r]){
                swap(arr,++less,l++); //换回来的一定是等于位置
            }else if(arr[l] > arr[r]){
                swap(arr,--more,l);
            }else {
                l++;
            }
        }
        swap(arr,more,r);
        return new int[]{less+1,more};
    }
    public  static  int[] partition2(int[] arr,int l,int r){
        //最后一个数作为最大值划分边界，作为比较的值，最后进行位置互换
        int less = l-1;
        int more = r+1;
        int num = arr[r];
        while (l < more){
            if(arr[l] < num){
                swap(arr,++less,l++); //换回来的一定是等于位置
            }else if(arr[l] > num){
                swap(arr,--more,l);
            }else {
                l++;
            }
        }
        return new int[]{less+1,more};
    }


    public  static  int[] partition(int[] arr,int l,int r,int num){
        int less = l-1;
        int more = r+1;
        int cur = l;
        while (cur < more){
            if(arr[l] < num){
                swap(arr,++less,cur++); //换回来的一定是等于位置
            }else if(arr[l] > num){
                swap(arr,--more,cur);
            }else {
                cur++;
            }
        }
        return new int[]{less+1,more-1};

    }

    public static void swap(int[] arr,int i , int j){
        int tmp = arr[i];
         arr[i] = arr[j];
         arr[j] = tmp;
    }



}
