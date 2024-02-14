package com.yqj.class02;

public class Code02_EvenTimesOddTimes {

    // arr中，只有一种数，出现奇数次
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int item : arr) {
            eor ^= item;
        }
        System.out.println(eor);
    }

    // arr中，有两种数，出现奇数次
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int item : arr) {
            eor ^= item;
        }
        // eor = a ^ b
        // eor != 0
        // eor必然有一个位置上是1
        // 0110010000
        // 0000010000
        int rightOne = eor & (~eor + 1); // 提取出最右的1
        int onlyOne = 0; // eor'
        for (int item : arr) {
            //  arr[1] =  111100011110000
            // rightOne=  000000000010000
            if ((item & rightOne) != 0) {
                onlyOne ^= item;
            }
        }
        System.out.println(String.format("num1: %s,num2: %s", onlyOne, eor ^ onlyOne));
    }


    public static int bit1counts(int N) {
        int count = 0;
        //   011011010000
        //   000000010000     1
        //   011011000000
        while (N != 0) {
            int rightOne = N & ((~N) + 1);
            count++;
            N ^= rightOne;
            // N -= rightOne
        }
        return count;
    }


    public static void main(String[] args) {
        int a = 5;
        int b = 7;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);

        int[] arr1 = {3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1};
        printOddTimesNum1(arr1);

        int[] arr2 = {4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2};
        printOddTimesNum2(arr2);

        System.out.println(bit1counts(11));
    }

}

