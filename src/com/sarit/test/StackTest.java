package com.sarit.test;

import com.sarit.linked_list.SinglyLinkedList;
import com.sarit.stack_queue.MStack;
import com.sarit.static_arr.StaticArray;

import java.util.*;

public class StackTest {

    private static void test_push(MStack stk, Random r) {
        int numInsert = r.nextInt(1000) + 10;
        TestUtility.testException(()-> stk.peek(), Exception.class);
        TestUtility.testException(()-> stk.pop(), Exception.class);

        for (int i = 0; i < numInsert; i++) {
            int sizeBefore = stk.len();
            Integer ele = r.nextInt();
            stk.push(ele);

            try {
                assert stk.peek().equals(ele);
            } catch (Exception e) {
                e.printStackTrace();
            }

            assert stk.len() == sizeBefore + 1;
        }

        for (int i = 0; i < numInsert; i++) {
            try {
                Integer p = stk.peek();
                int sizeBefore = stk.len();
                assert stk.pop().equals(p);
                assert stk.len() == sizeBefore - 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        TestUtility.testException(()-> stk.peek(), Exception.class);
        TestUtility.testException(()-> stk.pop(), Exception.class);
    }

    public static void main(String[] args) {
        List<MStack> stackList = new ArrayList<>();
        try {
            stackList.add(new StaticArray(100));
        } catch (Exception e) {
            e.printStackTrace();
        }

        stackList.add(new SinglyLinkedList());

        stackList.forEach((stk) -> {
            Random r = new Random(100);
            test_push(stk, r);
        });
    }
}
