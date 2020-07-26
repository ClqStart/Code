package com.company;

public class Main {

    static int partiton(int[] nums, int left, int right) {
        int key = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= key) {
                right--;
            }


            while (left<right && nums[left]< key){
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = key;
        return  left;
    }


    public static void main(String[] args) {
        int[] nums ={3,0,4,2,8,1,9,10,2};
        int ints = partiton(nums, 0, nums.length-1);
    }
}
