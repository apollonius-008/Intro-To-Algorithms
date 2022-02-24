package com.sarit.test;

import com.sarit.linked_list.SinglyLinkedList;
import com.sarit.stack_queue.CircularQueue;
import com.sarit.stack_queue.MDeque;
import com.sarit.static_arr.StaticArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DEQueueTest {

    private static void new_queue_has_size_zero(MDeque q) {
        assert q.len() == 0 : "new queue has length 0";
    }

    private static void test_insert_beg(MDeque q, Random r) {
        int numInsert = r.nextInt(100) + 20;

        for (int i = 0; i < numInsert; i++) {
            int sizeBefore = q.len();

            Integer integer = r.nextInt();
            q.insert_beg(integer);

            assert q.len() == sizeBefore + 1;
            try {
                assert q.getFrontElement().equals(integer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void test_insert_end(MDeque q, Random r) {
        int numInsert = r.nextInt(100) + 20;

        for (int i = 0; i < numInsert; i++) {
            int sizeBefore = q.len();

            Integer integer = r.nextInt();
            q.insert_end(integer);

            assert q.len() == sizeBefore + 1;
            try {
                assert q.getRearElement().equals(integer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void test_delete_beg(MDeque q, Random r) {
        int numInsert = r.nextInt(100) + 20;

        for (int i = 0; i < numInsert; i++)
            q.insert_end(r.nextInt());

        for (int i = 0; i < numInsert; i++) {
            Integer front = null;
            int sizeBefore = q.len();

            try {
                front = q.getFrontElement();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                q.delete_beg();
            } catch (Exception e) {
                e.printStackTrace();
            }

            assert q.len() == sizeBefore - 1;
        }

        TestUtility.testException(q::delete_beg, Exception.class);
    }

    private static void test_delete_end(MDeque q, Random r) {
        int numInsert = r.nextInt(100) + 20;

        for (int i = 0; i < numInsert; i++)
            q.insert_end(r.nextInt());

        for (int i = 0; i < numInsert; i++) {
            int sizeBefore = q.len();

            try {
                q.delete_end();
            } catch (Exception e) {
                e.printStackTrace();
            }

            assert q.len() == sizeBefore - 1;
        }

        TestUtility.testException(q::delete_end, Exception.class);
    }

    public static void main(String[] args) {
        List<MDeque> mDequeList = new ArrayList<>();
        try {
            mDequeList.add(new StaticArray(10));
        } catch (Exception e) {
            e.printStackTrace();
        }

        mDequeList.add(new SinglyLinkedList());
        try {
            mDequeList.add(new CircularQueue(100));
        } catch (Exception e) {
            e.printStackTrace();
        }

        mDequeList.forEach((q)->{
            new_queue_has_size_zero(q);
            MDeque copy;

            Random r = new Random(10000);

            copy = q.clone();
            test_insert_beg(copy, r);

            copy = q.clone();
            test_insert_end(copy, r);

            copy = q.clone();
            test_delete_beg(copy, r);

            copy = q.clone();
            test_delete_end(copy, r);
        });
    }
}
