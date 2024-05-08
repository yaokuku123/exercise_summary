package com.yqj.class03;


import java.util.Stack;

public class Code06_TwoStacksImplementQueue {

    public static class TwoStacksQueue {
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public TwoStacksQueue() {
            pushStack = new Stack<>();
            popStack = new Stack<>();
        }

        public void pushToPop() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
        }

        public void add(int value) {
            pushStack.push(value);
            pushToPop();
        }

        public int poll() {
            if (pushStack.isEmpty() && popStack.isEmpty()) {
                throw new RuntimeException("queue is empty");
            }
            pushToPop();
            return popStack.pop();
        }

        public int peek() {
            if (pushStack.isEmpty() && popStack.isEmpty()) {
                throw new RuntimeException("queue is empty");
            }
            pushToPop();
            return popStack.peek();
        }
    }

    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }

}

