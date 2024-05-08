package com.yqj.class03;


public class Code04_RingArray {

    public static class MyQueue {
        int[] arr;
        int pushi;
        int polli;
        int size;
        int limit;

        public MyQueue(int limit) {
            this.arr = new int[limit];
            this.pushi = 0;
            this.polli = 0;
            this.size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("queue is full");
            }
            size++;
            arr[pushi] = value;
            pushi = nextIndex(pushi);
        }

        public int poll() {
            if (size == 0) {
                throw new RuntimeException("queue is empty");
            }
            size--;
            int val = arr[polli];
            polli = nextIndex(polli);
            return val;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }

}

