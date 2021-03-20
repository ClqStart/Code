//完全二叉树是包括满二叉树，满二叉树子节点依次补齐过程都是完全二叉树

//堆排序  找父节点 （i-1)/2, 上溢


public class __堆排序__ {
    public static void main(String[] args) {
        heapSort(new int[] {3,6,8,2,6,0,2,3,5,9,1});


    }
    public  static  void heapSort(int[] arr){
        if(arr ==null || arr.length <2) return;

        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr,i);
        }
//        for (int i = 0; i < arr.length; i++) {
//            swap(arr,0,arr.length-i-1);
//            heapfy(arr,0,arr.length-i-1);
//        }


        int heapSize = arr.length;
        swap(arr,0,--heapSize);
        while (heapSize >0){
            heapfy(arr,0,heapSize);
            swap(arr,0,--heapSize);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }

    }

    public  static  void heapfy(int[] arr,int index,int heapSize){
        int left = index*2+1;
        while (left < heapSize){
            int largest = left+1 < heapSize && arr[left]<arr[left+1] ? left+1: left;
            largest = arr[largest] > arr[index] ? largest: index;
            if(largest == index){
                break;
            }else {
                swap(arr,index,largest);
                index = largest;
                left = index*2+1;
            }
        }

    }



    public static  void heapInsert(int[] arr,int index){
        while(arr[index]> arr[ (index-1)/2]){  //根节点自动会判断，
            swap(arr,index, (index-1)/2);
            index =  (index-1)/2;
        }


    }

    public  static  void swap(int[] arr,int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;


    }

}
