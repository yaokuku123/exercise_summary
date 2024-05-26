package com.yqj.class14;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code04_IPO {

    // 最多K个项目
    // W是初始资金
    // Profits[] Capital[] 一定等长
    // 返回最终最大的资金
    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        PriorityQueue<Program> costQueue = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> profitQueue = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < Capital.length; i++) {
            costQueue.add(new Program(Profits[i], Capital[i]));
        }
        for (int i = 0; i < K; i++) {
            while (!costQueue.isEmpty() && costQueue.peek().c <= W) {
                profitQueue.add(costQueue.poll());
            }
            if (profitQueue.isEmpty()) {
                break;
            }
            W += profitQueue.poll().p;
        }
        return W;
    }

    public static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }

    }

    public static class MaxProfitComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }

    }

}
