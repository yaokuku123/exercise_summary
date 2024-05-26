package com.yqj.class14;

import java.util.HashSet;

public class Code01_Light {

    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    // str[index....]位置，自由选择放灯还是不放灯
    // str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
    public static int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) {
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') {
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        }
        int no = process(str, index + 1, lights);
        int yes = Integer.MAX_VALUE;
        if (str[index] == '.') {
            lights.add(index);
            yes = process(str, index + 1, lights);
            lights.remove(index);
        }
        return Math.min(yes, no);
    }

    public static int minLight2(String road) {
        char[] strs = road.toCharArray();
        int light = 0;
        int i = 0;
        while (i < strs.length) {
            if (strs[i] == 'X') {
                i++;
            } else {
                light++;
                if (i + 1 == strs.length) {
                    break;
                } else {
                    if (strs[i + 1] == 'X') {
                        i = i + 2;
                    } else {
                        i = i + 3;
                    }
                }
            }
        }
        return light;
    }

    // 更简洁的解法
    // 两个X之间，数一下.的数量，然后除以3，向上取整
    // 把灯数累加
    public static int minLight3(String road) {
        char[] str = road.toCharArray();
        int cur = 0;
        int light = 0;
        for (char c : str) {
            if (c == 'X') {
                light += (cur + 2) / 3;
                cur = 0;
            } else {
                cur++;
            }
        }
        light += (cur + 2) / 3;
        return light;
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            int ans3 = minLight3(test);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}
