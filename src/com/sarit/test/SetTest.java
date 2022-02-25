package com.sarit.test;

import com.sarit.linked_list.SinglyLinkedList;

import com.sarit.sequence_set.MSet;
import com.sarit.sequence_set.SortedArray;
import com.sarit.static_arr.StaticArray;

import java.util.*;
import java.util.logging.Level;

public class SetTest {

    private static void new_set_has_len_0(MSet s) {
        assert s.len() == 0 : "Length of new set is 0";
    }

    private static void test_insert(MSet s, Random r) {
        int numInsert = r.nextInt(100) + 1;
        ArrayList<Integer> elements_added = new ArrayList<>();
        for (int i = 0; i < numInsert; i++)
            assert s.find(r.nextInt()) == null : "find on empty set returns null";

        for (int i = 0; i < numInsert; i++) {
            Integer element = r.nextInt();
            int sizeBefore = s.len();

            while (s.find(element) != null)
                element = r.nextInt();

            s.insert(element);
            elements_added.add(element);

            assert s.len() == sizeBefore + 1: "inserting increases size of set by 1";
            assert s.find(element) != null : "finding the inserted element return non null";

            for (Integer e : elements_added)
                assert s.find(e) != null;

            Iterator<Integer> it = s.iter_set();
            Integer prev = it.next();

            while (it.hasNext()) {
                Integer current = it.next();
                assert prev <= current : "prev <= current";
                prev = current;
            }
        }
    }

    private static void test_min(MSet s, Random r) {
        assert s.min() == null : "Empty set does not have any min";

        int numInsert = r.nextInt(100);
        Integer num = r.nextInt(100);

        s.insert(num);
        assert num.equals(s.min()) : "element inserted to empty set is the min";

        for (int i = 0; i < numInsert; i++) {
            Integer m = s.min();
            s.insert(m - 1);
            assert s.min().equals(m - 1) : "min - 1 is the new min";
        }
    }

    private static void test_max(MSet s, Random r) {
        assert s.max() == null : "Empty set does not have any max";

        int numInsert = r.nextInt(100);
        Integer num = r.nextInt(100);

        s.insert(num);
        assert num.equals(s.max()) : "element inserted to empty set is the max";

        for (int i = 0; i < numInsert; i++) {
            Integer m = s.max();
            s.insert(m + 1);
            assert s.max().equals(m + 1) : "max + 1 is the new max";
        }
    }

    private static void test_next(MSet s, Random r) {
        int numInsert = r.nextInt(100) + 10;
        for (int i = 0; i < numInsert; i++)
            s.insert(r.nextInt(1000));

        assert s.find_next(s.max()) == null : "max has no next";

        for (int i = 0; i < numInsert; i++) {
            Integer num = r.nextInt(s.max());
            Integer next = s.find_next(num);

            assert next != null : "next of ele < max always exist";
            assert s.find(next) != null : "next is in s";
            assert num < next : "ele < next";

            Integer next_prev = s.find_prev(next);
            assert next_prev == null || next_prev <= num;
        }

    }

    private static void test_prev(MSet s, Random r) {
        int numInsert = r.nextInt(100) + 10;
        for (int i = 0; i < numInsert; i++)
            s.insert(r.nextInt(1000));

        assert s.find_prev(s.min()) == null : "min has no prev";

        for (int i = 0; i < numInsert; i++) {
            Integer num = r.nextInt(s.max() - s.min() + 10) + s.min() + 1;
            Integer prev = s.find_prev(num);

            assert prev != null : "prev of ele > min always exist";
            assert s.find(prev) != null : "prev is in s";
            assert num > prev : "ele > prev";

            Integer prev_next = s.find_next(prev);
            assert prev_next == null || prev_next >= num;
        }

    }

    private static void test_delete(MSet s, Random r) {
        assert TestUtility.testException(()-> s.delete(r.nextInt()), Exception.class) : "cannot delete from empty list";

        int numInsert = r.nextInt(100) + 10;
        for (int i = 0; i < numInsert; i++) {
            Integer ele = r.nextInt(1000);
            while (s.find(ele) != null)
                ele = r.nextInt(1000);
            s.insert(ele);
        }

        int numEle = s.len();
        for (int i = 0; i < numEle; i++) {
            Integer num = r.nextInt(s.max() - s.min() + 1) + s.min() + 1;
            Integer ele;
            if (s.find(num) != null)
                ele = num;
            else if (s.find_next(num) != null)
                ele = s.find_next(num);
            else if (s.find_prev(num) != null)
                ele = s.find_prev(num);
            else
                ele = s.max();

            int sizeBefore = s.len();
            try {
                s.delete(ele);
            } catch (Exception e) {
                e.printStackTrace();
            }

            assert s.len() == sizeBefore - 1 : "deleting decreases size by 1";
            assert s.find(ele) == null : "deleted element cannot be found in the list";
        }
    }

    public static void main(String[] args) {
        List<MSet> setList = new ArrayList<>();
        try {
            setList.add(new StaticArray(100));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setList.add(new SinglyLinkedList());
        setList.add(new SortedArray());

        setList.forEach((s) -> {

            Random r = new Random(100000);

            MSet copy = s.clone();
            new_set_has_len_0(copy);

            copy = s.clone();
            test_insert(copy, r);

            copy = s.clone();
            test_min(copy,r);

            copy = s.clone();
            test_max(copy,r);

            copy = s.clone();
            test_next(copy,r);

            copy = s.clone();
            test_prev(copy,r);

            copy = s.clone();
            test_delete(copy, r);
        });
    }
}
