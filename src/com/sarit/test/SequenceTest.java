package com.sarit.test;

import com.sarit.linked_list.SinglyLinkedList;
import com.sarit.sequence_set.*;
import com.sarit.static_arr.StaticArray;

import java.util.*;

public class SequenceTest {

    private static void new_sequence_has_len_0(MSequence s) {
        assert s.len() == 0 : "Length of new sequence is 0";
    }

    private static void test_insert_end(MSequence s, Random r) {
        int numToInsert = r.nextInt(1000) + 1;
        for (int i = 0; i < numToInsert; i++) {
            int lenBefore = s.len();
            Integer element = r.nextInt();
            MSequence cpy = s.clone();
            s.insert_end(element);

            assert s.len() == lenBefore + 1 : "insert_end increases size by 1";
            assert element.equals(s.get(s.len() - 1)) : "Last element is the inserted element";
            Iterator<Integer> it1 = cpy.iter_seq();
            Iterator<Integer> it2 = s.iter_seq();
            while (it1.hasNext())
                assert it1.next().equals(it2.next());
        }
    }

    private static void test_insert_beg(MSequence s, Random r) {
        int numToInsert = r.nextInt(1000) + 1;
        for (int i = 0; i < numToInsert; i++) {
            int lenBefore = s.len();
            Integer element = r.nextInt();
            MSequence cpy = s.clone();
            s.insert_beg(element);

            assert s.len() == lenBefore + 1 : "insert_end increases size by 1";
            assert element.equals(s.get(0)) : "First element is the inserted element";
            Iterator<Integer> it1 = cpy.iter_seq();
            Iterator<Integer> it2 = s.iter_seq();
            it2.next();
            while (it1.hasNext())
                assert it1.next().equals(it2.next());
        }
    }

    private static void test_insert_at(MSequence s, Random r) {
        int numToInsert = r.nextInt(1000) + 1;
        s.insert_end(r.nextInt());
        for (int i = 0; i < numToInsert; i++) {
            int lenBefore = s.len();
            Integer element = r.nextInt();
            int index = r.nextInt(s.len());
            MSequence cpy = s.clone();
            try {
                s.insert_at(index, element);
            } catch (Exception e) {
                e.printStackTrace();
            }

            assert s.len() == lenBefore + 1 : "insert_end increases size by 1";
            assert element.equals(s.get(index));
            Iterator<Integer> it1 = cpy.iter_seq();
            Iterator<Integer> it2 = s.iter_seq();

            for (int j = 0; j < index; j++) {
                assert it1.next().equals(it2.next());
            }
            it2.next();
            while (it1.hasNext())
                assert it1.next().equals(it2.next());


            assert TestUtility.testException(() -> s.insert_at(-(r.nextInt(100) + 1), r.nextInt()), IndexOutOfBoundsException.class) : "get on -ve index throws error";
            assert TestUtility.testException(() -> s.insert_at(s.len() + r.nextInt(100) + 1, r.nextInt()), IndexOutOfBoundsException.class) : "get on index >= len() throws error";

        }
    }

    private static void test_delete_end(MSequence s, Random r) {
        assert TestUtility.testException(s::delete_end, Exception.class) :
                "delete_end on empty sequence throws error";

        int numInsert = r.nextInt(100) + 1;
        for (int i = 0; i < numInsert; i++) s.insert_end(r.nextInt());

        for (int i = 0; i < numInsert; i++) {
            int lenBefore = s.len();
            Integer element = r.nextInt();
            MSequence cpy = s.clone();
            try {
                s.delete_end();
            } catch (Exception e) {
                e.printStackTrace();
            }

            assert s.len() == lenBefore - 1 : "delete_end decreases size by 1";

            Iterator<Integer> it1 = cpy.iter_seq();
            Iterator<Integer> it2 = s.iter_seq();
            while (it2.hasNext())
                assert it1.next().equals(it2.next());
        }

        assert TestUtility.testException(s::delete_end, Exception.class) :
                "delete_end on empty sequence throws error";
    }

    private static void test_delete_beg(MSequence s, Random r) {
        assert TestUtility.testException(s::delete_end, Exception.class) :
                "delete_end on empty sequence throws error";

        int numInsert = r.nextInt(100) + 1;
        for (int i = 0; i < numInsert; i++) s.insert_end(r.nextInt());

        for (int i = 0; i < numInsert; i++) {
            int lenBefore = s.len();
            MSequence cpy = s.clone();
            try {
                s.delete_beg();
            } catch (Exception e) {
                e.printStackTrace();
            }

            assert s.len() == lenBefore - 1 : "delete_end decreases size by 1";

            Iterator<Integer> it1 = cpy.iter_seq();
            Iterator<Integer> it2 = s.iter_seq();
            it1.next();
            while (it2.hasNext())
                assert it1.next().equals(it2.next());
        }

        assert TestUtility.testException(s::delete_end, Exception.class) :
                "delete_end on empty sequence throws error";
    }

    private static void test_delete_at(MSequence s, Random r) {
        assert TestUtility.testException(()->s.delete_at(r.nextInt(100)), Exception.class) :
                "delete_end on empty sequence throws error";

        int numInsert = r.nextInt(100) + 1;
        for (int i = 0; i < numInsert; i++) s.insert_end(r.nextInt());

        for (int i = 0; i < numInsert; i++) {
            int lenBefore = s.len();
            MSequence cpy = s.clone();
            Integer index = r.nextInt(s.len());
            try {
                s.delete_at(index);
            } catch (Exception e) {
                e.printStackTrace();
            }

            assert s.len() == lenBefore - 1 : "delete_at decreases size by 1";

            Iterator<Integer> it1 = cpy.iter_seq();
            Iterator<Integer> it2 = s.iter_seq();
            for (int j = 0; j < index; j++)
                assert it1.next().equals(it2.next());
            it1.next();
            while (it1.hasNext())
                assert it1.next().equals(it2.next());
        }

        assert TestUtility.testException(()->s.delete_at(r.nextInt(100)), Exception.class) :
                "delete_end on empty sequence throws error";
    }

    private static void test_get_set(MSequence s, Random r) {
        assert TestUtility.testException(()->s.get(r.nextInt()), IndexOutOfBoundsException.class) : "get on new sequence throws IndexOutOfBoundsException";
        assert TestUtility.testException(()->s.set(r.nextInt(), r.nextInt()), IndexOutOfBoundsException.class) : "set on new sequence throws IndexOutOfBoundsException";

        int numToInsert = r.nextInt(1000) + 1;
        for (int i = 0; i < numToInsert; i++) {
            s.insert_end(r.nextInt());
            int index = r.nextInt(s.len());
            Integer element = r.nextInt();

            s.set(index, element);
            assert Objects.equals(s.get(index), element);
            assert TestUtility.testException(()->s.get(-(r.nextInt(100) + 1)), IndexOutOfBoundsException.class) : "get on -ve index throws error";
            assert TestUtility.testException(()->s.set(-(r.nextInt(100) + 1), -r.nextInt()), IndexOutOfBoundsException.class) : "set on -ve index throws error";
            assert TestUtility.testException(()->s.get(s.len() + r.nextInt(100)), IndexOutOfBoundsException.class) : "get on index >= len() throws error";
            assert TestUtility.testException(()->s.set(s.len() + r.nextInt(100), r.nextInt()), IndexOutOfBoundsException.class) : "set on index >= len() throws error";
        }
    }

    public static void main(String[] args) {

        List<MSequence> seqList = new ArrayList<>();
        try {
            seqList.add(new StaticArray(100));
        } catch (Exception e) {
            e.printStackTrace();
        }
        seqList.add(new SinglyLinkedList());

        seqList.forEach((seq) -> {
            Random random = new Random(1000);
            MSequence copy = seq.clone();
            new_sequence_has_len_0(copy);
            copy = seq.clone();
            test_insert_end(copy, random);
            copy = seq.clone();
            test_get_set(copy, random);
            copy = seq.clone();
            test_insert_beg(copy, random);
            copy = seq.clone();
            test_insert_at(copy, random);
            copy = seq.clone();
            test_delete_end(copy, random);
            copy = seq.clone();
            test_delete_beg(copy, random);
            copy = seq.clone();
            test_delete_at(copy, random);
        });
    }

}
