package com.yqj.class07;


import java.util.*;

/*
 * T一定要是非基础类型，有基础类型需求包一层
 */
public class HeapGreater<T> {

    private List<T> heap;
    private Map<T, Integer> indexMap;
    private int heapSize;
    private Comparator<T> comp;

    public HeapGreater(Comparator<T> c) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        comp = c;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T res = heap.get(0);
        swap(0, heapSize - 1);
        heap.remove(--heapSize);
        indexMap.remove(res);
        heapify(0);
        return res;
    }

    public void remove(T obj) {
        T replaceObj = heap.get(heapSize - 1);
        int index = indexMap.get(obj);
        heap.remove(--heapSize);
        indexMap.remove(obj);
        if (obj != replaceObj) {
            heap.set(index, replaceObj);
            indexMap.put(replaceObj, index);
            resign(replaceObj);
        }
    }

    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    // 请返回堆上的所有元素
    public List<T> getAllElements() {
        List<T> res = new ArrayList<>();
        for (T elem : heap) {
            res.add(elem);
        }
        return res;
    }

    private void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
            best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
            if (best == index) {
                break;
            }
            swap(best, index);
            index = best;
            left = index * 2 + 1;
        }
    }

    private void swap(int i, int j) {
        T obj1 = heap.get(i);
        T obj2 = heap.get(j);
        heap.set(i, obj2);
        heap.set(j, obj1);
        indexMap.put(obj2, i);
        indexMap.put(obj1, j);
    }

}

