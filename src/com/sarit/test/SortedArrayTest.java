package com.sarit.test;

import com.sarit.sequence_set.SortedArray;

public class SortedArrayTest{

    public static void main(String[] args) throws Exception {
        SortedArray arr = new SortedArray();
        System.out.println(arr.len());
        arr.insert(10);
        arr.insert(20);
        arr.insert(15);
        TestUtility.displayIterator(arr.iter_set());
//        arr.delete(10);
        TestUtility.displayIterator(arr.iter_set());
        arr.delete(20);
        arr.delete(15);
        arr.delete(10);
        TestUtility.displayIterator(arr.iter_set());

        arr.insert(20);
        arr.insert(30);
        arr.insert(10);

        TestUtility.displayIterator(arr.iter_set());

        System.out.println(arr.min());
        System.out.println(arr.max());
        System.out.println(arr.find(10));
        System.out.println(arr.find(50));
        System.out.println(arr.find(15));
        System.out.println(arr.find_next(15));
        System.out.println(arr.find_prev(15));
    }
}
