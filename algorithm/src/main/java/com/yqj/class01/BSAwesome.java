package com.yqj.class01;

public class BSAwesome {

    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int L = 1;
        int R = arr.length - 2;
        int mid = 0;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{10, 5, 2, 3, 9};
        System.out.println(getLessIndex(arr));
    }
}