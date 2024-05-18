package com.yqj.class09;

// 类荷兰国旗问题
public class Code03_SmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        int i = 0;
        Node cur = head;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] arr = new Node[i];
        cur = head;
        for (i = 0; i < arr.length; i++) {
            arr[i] = cur;
            cur = cur.next;
        }
        arrPartition(arr, pivot);
        for (i = 1; i < arr.length; i++) {
            arr[i - 1].next = arr[i];
        }
        arr[i - 1].next = null;
        return arr[0];
    }

    public static void arrPartition(Node[] arr, int pivot) {
        int lower = -1;
        int higher = arr.length;
        int index = 0;
        while (index < higher) {
            if (arr[index].value < pivot) {
                swap(arr, ++lower, index++);
            } else if (arr[index].value == pivot) {
                index++;
            } else {
                swap(arr, --higher, index);
            }
        }
    }

    public static void swap(Node[] arr, int i, int j) {
        Node tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static Node listPartition2(Node head, int pivot) {
        Node sH = null, sT = null;
        Node eH = null, eT = null;
        Node mH = null, mT = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = sT.next;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = eT.next;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = mT.next;
                }
            }
            head = next;
        }
        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }
        if (eT != null) {
            eT.next = mH;
        }
        return sH != null ? sH : eH != null ? eH : mH;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
//        head1 = listPartition1(head1, 5);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }

}
