package com.yqj.class15;

// 本题为leetcode原题
// 测试链接：https://leetcode.com/problems/friend-circles/
// 可以直接通过
public class Code01_FriendCircles {

    public static int findCircleNum(int[][] M) {
        int N = M.length;
        UnionFind uf = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (M[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.sets();
    }


    public static class UnionFind {
        private int[] father;
        private int[] size;
        private int[] help;
        private int sets;

        public UnionFind(int N) {
            father = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                father[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            int hi = 0;
            while (i != father[i]) {
                help[hi++] = i;
                i = father[i];
            }
            for (hi--; hi >= 0; hi--) {
                father[help[hi]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int fi = find(i);
            int fj = find(j);
            if (fi != fj) {
                if (size[fi] >= size[fj]) {
                    size[fi] += size[fj];
                    size[fj] = 0;
                    father[fj] = fi;
                } else {
                    size[fj] += size[fi];
                    size[fi] = 0;
                    father[fi] = fj;
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }

}
