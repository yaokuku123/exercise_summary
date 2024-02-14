package com.yqj.class02;


public class Code01_Swap {

    public static void main(String[] args) {

        int a = 16;
        int b = 603;

        System.out.println(a);
        System.out.println(b);

        // 采用异或的方式完成变量的对调
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;


        System.out.println(a);
        System.out.println(b);


        int[] arr = {3, 1, 100};


        System.out.println(arr[0]);
        System.out.println(arr[2]);

        swap(arr, 0, 2);

        System.out.println(arr[0]);
        System.out.println(arr[2]);

        // 但指向同一个地址空间时，无法通过该方法完成对调，会将本身刷为0
        int i = 0;
        int j = 0;

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

        System.out.println(arr[i] + " , " + arr[j]);


    }


    public static void swap(int[] arr, int i, int j) {
        // arr[0] = arr[0] ^ arr[0];
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


}
