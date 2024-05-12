package com.yqj.class03;


public class Code08_GetMax {

    // 求arr中的最大值
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]范围上求最大值  L ... R   N
    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        System.out.println(getMax(new int[]{12, 2, 443, 1, 21, 1, 1, 12, 4}));
    }

}