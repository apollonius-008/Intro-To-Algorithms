package com.sarit.linked_list;

import com.sarit.sequence_set.MSequence;
import com.sarit.sequence_set.MSet;
import com.sarit.stack_queue.MDeque;
import com.sarit.stack_queue.MStack;

import java.util.Iterator;

public class SinglyLinkedList implements MSequence, MSet, MStack, MDeque {

    @Override
    public void push(int e) {
        this.insert_end(e);
    }

    @Override
    public Integer pop() throws Exception {
        int data = this.peek();
        this.delete_end();
        return data;
    }

    @Override
    public Integer peek() throws Exception {
        if (tail == null)
            throw new Exception("Cannot perform peek on empty stack");
        return this.tail.getData();
    }

    private static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public SinglyLinkedList() {
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Integer len() {
        return this.size;
    }

    @Override
    public Integer find(Integer ele) {
        Node temp = this.head;
        while (temp != null) {
            if (temp.getData() == ele)
                return ele;
            temp = temp.next;
        }
        return null;
    }

    @Override
    public Integer min() {
        Integer minE = null;
        Node temp = this.head;

        while (temp != null) {
            if (minE == null || minE > temp.getData())
                minE = temp.getData();
            temp = temp.next;
        }

        return minE;
    }

    @Override
    public Integer max() {
        Integer maxE = null;
        Node temp = this.head;

        while (temp != null) {
            if (maxE == null || maxE < temp.getData())
                maxE = temp.getData();
            temp = temp.next;
        }

        return maxE;
    }

    @Override
    public Integer find_next(Integer ele) {
        Integer next = null;

        Node temp = this.head;

        while (temp != null) {
            if (temp.data > ele && (next == null || next > temp.getData()))
                next = temp.getData();
            temp = temp.next;
        }

        return next;
    }

    @Override
    public Integer find_prev(Integer ele) {
        Integer prev = null;

        Node temp = this.head;

        while (temp != null) {
            if (temp.data < ele && (prev == null || prev < temp.getData()))
                prev = temp.getData();
            temp = temp.next;
        }

        return prev;
    }

    @Override
    public void insert(Integer e) {
        if (this.find(e) == null)
            this.insert_end(e);
    }

    public int indexOf(Integer e) {
        int count = 0;
        Node temp = this.head;
        while (temp != null) {
            if (temp.getData() == e)
                return count;
            temp = temp.next;
            count += 1;
        }
        return -1;
    }

    @Override
    public void delete(Integer e) throws Exception {
        int index = this.indexOf(e);
        this.delete_at(index);
    }

    private Node getNode(Integer index) throws IndexOutOfBoundsException {
        if (index >= this.size)
            throw new IndexOutOfBoundsException(String.format("size=%d, index=%d. 0 <= index < size", this.size, index));
        Node temp = this.head;
        Integer count = 0;
        while (count < index) {
            temp = temp.getNext();
            count += 1;
        }
        return temp;
    }

    @Override
    public Integer get(Integer index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException(String.format("size=%d, index=%d. 0 <= index < size not satisfied", this.size, index));
        return this.getNode(index).getData();
    }

    @Override
    public void set(Integer index, Integer e) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size)
            throw new IndexOutOfBoundsException(String.format("size=%d, index=%d. 0 <= index < size not satisfied", this.size, index));
        Node indexNode = this.getNode(index);
        indexNode.setData(e);
    }

    @Override
    public void insert_beg(Integer e) {
        if (this.isEmpty()) {
            this.head = new Node(e);
            this.tail = this.head;
        }
        else {
            Node temp = new Node(e, this.head);
            this.head = temp;
        }
        this.size += 1;
    }

    @Override
    public void insert_end(Integer e) {
        if (this.isEmpty()) {
            this.head = new Node(e);
            this.tail = this.head;
        }
        else {
            Node temp = new Node(e);
            this.tail.setNext(temp);
            this.tail = temp;
        }
        this.size += 1;
    }

    @Override
    public void insert_at(Integer index, Integer e) throws Exception {
        if (index < 0 || index > this.size)
            throw new IndexOutOfBoundsException(String.format("size=%d, index=%d. 0 <= index <= size must be satisfied", index, this.size));
        else if (index == 0)
            this.insert_beg(e);
        else if (index == this.size)
            this.insert_end(e);
        else {
            if (this.isEmpty()) {
                this.head = new Node(e);
                this.tail = this.head;
            }
            else {
                Node prev = this.getNode(index - 1);
                Node temp = new Node(e, prev.getNext());
                prev.setNext(temp);
            }
            this.size += 1;
        }
    }

    @Override
    public void delete_beg() throws Exception {
        if (this.isEmpty())
            throw new Exception("Cannot delete from empty linked list");
        else {
            this.head = this.head.getNext();
            this.size -= 1;
        }
    }

    @Override
    public Integer getFrontElement() throws Exception {
        if (this.head == null)
            throw new Exception("Cannot get front element from empty queue");
        return this.head.getData();
    }

    @Override
    public Integer getRearElement() throws Exception {
        if (this.tail == null)
            throw new Exception("Cannot get tail element from empty queue");
        return this.tail.getData();
    }

    @Override
    public void delete_end() throws Exception {
        if (this.isEmpty())
            throw new Exception("Cannot delete from empty linked list");
        else {
            Node prev = this.getNode(this.size - 2);
            this.tail = prev;
            this.tail.setNext(null);
            this.size -= 1;
        }
    }

    @Override
    public void delete_at(Integer index) throws Exception {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException(String.format("size=%d, index=%d. 0 <= index < size must be satisfied", index, this.size));
        else if (index == 0)
            this.delete_beg();
        else if (index == this.size - 1)
            this.delete_end();
        else {
            Node prev = this.getNode(index - 1);
            prev.setNext(prev.getNext().getNext());
            this.size -= 1;
        }
    }

    @Override
    public void build(Iterator<Integer> it) {
        while (it.hasNext())
            this.insert_end(it.next());
    }

    @Override
    public Iterator<Integer> iter_set() {
        return new Iterator<Integer>() {
            private int currentIndex = 0;
            private Integer currentItem = SinglyLinkedList.this.min();

            @Override
            public boolean hasNext() {
                return this.currentIndex < SinglyLinkedList.this.size;
            }

            @Override
            public Integer next() {
                Integer item = currentItem;
                currentItem = SinglyLinkedList.this.find_next(currentItem);
                currentIndex += 1;
                return item;
            }
        };
    }

    @Override
    public Iterator<Integer> iter_seq() {
        return new Iterator<Integer>() {
            private Node current = SinglyLinkedList.this.head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Integer next() {
                Integer data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public void reverse() {
        if (this.size < 2)
            return;
        else {
            Node t1 = null;
            Node t2 = this.head;
            Node t3 = t2.getNext();

            while (t3 != null) {
                t2.setNext(t1);
                t1 = t2;
                t2 = t3;
                t3 = t3.getNext();
            }

            t2.setNext(t1);
            //swap head and tail
            Node temp = this.head;
            this.head = this.tail;
            this.tail = temp;
        }
    }

    @Override
    public SinglyLinkedList clone() {
        SinglyLinkedList link = new SinglyLinkedList();
        Iterator<Integer> iterator = this.iter_seq();
        while (iterator.hasNext())
            link.insert_end(iterator.next());
        return link;
    }
}
