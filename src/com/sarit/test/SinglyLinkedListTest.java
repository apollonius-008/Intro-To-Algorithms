package com.sarit.test;

import com.sarit.linked_list.SinglyLinkedList;

public class SinglyLinkedListTest {

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        System.out.println(list);
        list.insert_end(1);
        list.insert_end(20);
        list.insert_end(5);
        System.out.println(list);
    }
}
