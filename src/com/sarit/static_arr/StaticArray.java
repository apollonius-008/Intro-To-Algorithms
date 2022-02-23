package com.sarit.static_arr;

import com.sarit.sequence_set.*;
import com.sarit.stack_queue.*;

import java.util.*;

public class StaticArray implements MSequence, MSet, MStack, MDeque {

    private Integer[] arr;
    private Integer size;

    public StaticArray(int initialCapacity) throws Exception {
        if (initialCapacity <= 0)
            throw new Exception(String.format("initialCapacity is positive. initialCapacity=%d", initialCapacity));
        this.arr = new Integer[initialCapacity];
        this.size = 0;
    }

    public boolean isFull() {
        return this.size == this.arr.length;
    }

    @Override
    public Integer len() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Integer find(Integer ele) {
        int index = this.indexOf(ele);
        return index == -1 ? null : this.arr[index];
    }

    @Override
    public Integer min() {
       Integer minE = null;
       for (int i = 0; i < this.size; i++) {
           if (minE == null || minE > this.arr[i])
               minE = this.arr[i];
       }
       return minE;
    }

    @Override
    public Integer max() {
        Integer maxE = null;
        for (int i = 0; i < this.size; i++) {
            if (maxE == null || maxE < this.arr[i])
                maxE = this.arr[i];
        }
        return maxE;
    }

    @Override
    public Integer find_next(Integer ele) {
        Integer next = null;

        for (int i = 0; i < this.size; i++) {
            if (this.arr[i] > ele && (next == null || next > this.arr[i]))
                next = this.arr[i];
        }

        return next;
    }

    @Override
    public Integer find_prev(Integer ele) {
        Integer prev = null;

        for (int i = 0; i < this.size; i++) {
            if (this.arr[i] < ele && (prev == null || prev < this.arr[i]))
                prev = this.arr[i];
        }

        return prev;
    }

    @Override
    public void insert(Integer e) {
        if (this.find(e) == null)
            this.insert_end(e);
    }

    @Override
    public void delete(Integer e) throws Exception {
        int index = this.indexOf(e);
        if (index == -1)
            throw new Exception(String.format("Element=%d is not present in the sequence.", e));
        else
            this.delete_at(index);
    }

    public int indexOf(Integer e) {
        for (int i = 0; i < this.size; i++) {
            if (this.arr[i].equals(e))
                return i;
        }
        return -1;
    }

    @Override
    public Integer get(Integer index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException(String.format("size=%d, index=%d. 0 <= index < size not satisfied", this.size, index));
        else
            return this.arr[index];
    }

    @Override
    public void set(Integer index, Integer e) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException(String.format("size=%d, index=%d. 0 <= index < size not satisfied", this.size, index));
        else
            this.arr[index] = e;
    }

    public void shiftElementsToRightBy1(int s, int e) throws Exception {
        if (s < 0 || s >= this.size)
            throw new IndexOutOfBoundsException(String.format("size=%d, s=%d. 0 <= index < size not satisfied", this.size, s));
        if (e < 0 || e >= this.size)
            throw new IndexOutOfBoundsException(String.format("size=%d, e=%d. 0 <= index < size not satisfied", this.size, e));
        if (s == this.arr.length - 1)
            throw new Exception(String.format("arr.length=%d, s=%d. Rightmost element cannot be shifted to right", this.arr.length, s));
        if (e == this.arr.length - 1)
            throw new Exception(String.format("arr.length=%d, s=%d. Rightmost element cannot be shifted to right", this.arr.length, e));
        if (e + 1 - s < 0)
            throw new Exception(String.format("s <= e must be true. s=%d, e=%d", s, e));
        else
            System.arraycopy(this.arr, s, this.arr, s + 1, e + 1 - s);
    }

    public void shiftElementsToLeftBy1(int s, int e) throws Exception {
        if (s < 0 || s >= this.size)
            throw new IndexOutOfBoundsException(String.format("size=%d, s=%d. 0 <= index < size not satisfied", this.size, s));
        if (e < 0 || e >= this.size)
            throw new IndexOutOfBoundsException(String.format("size=%d, e=%d. 0 <= index < size not satisfied", this.size, e));
        if (s == 0)
            throw new Exception(String.format("size=%d, s=%d. Leftmost element cannot be shifted to right", this.size, s));
        if (e == 0)
            throw new Exception(String.format("size=%d, s=%d. Leftmost element cannot be shifted to right", this.size, e));
        if (e + 1 - s < 0)
            throw new Exception(String.format("s <= e must be true. s=%d, e=%d", s, e));
        else
            System.arraycopy(this.arr, s, this.arr, s - 1, e + 1 - s);
    }

    @Override
    public void insert_beg(Integer e) {
        if (!this.isFull()) {
            if (!this.isEmpty()) {
                try {
                    this.shiftElementsToRightBy1(0, this.size - 1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        else {
            Integer[] temp = new Integer[this.size + 1];
            if (this.size >= 0) System.arraycopy(this.arr, 0, temp, 1, this.size);
            this.arr = temp;
        }
        this.arr[0] = e;
        this.size += 1;
    }

    @Override
    public void insert_end(Integer e) {
        if (this.isFull()) {
            Integer[] temp = new Integer[this.size + 1];
            if (this.size >= 0) System.arraycopy(this.arr, 0, temp, 0, this.size);
            this.arr = temp;
        }

        this.arr[this.size] = e;
        this.size += 1;
    }

    @Override
    public void insert_at(Integer index, Integer e) throws Exception {
        if (index < 0 || index > this.size)
            throw new IndexOutOfBoundsException(String.format("size=%d, index=%d. 0 <= index <= size not satisfied", this.size, index));
        else if (index == 0)
            this.insert_beg(e);
        else if (index.equals(size))
            this.insert_end(e);
        else {
            if (!this.isFull()) {
                this.shiftElementsToRightBy1(index, this.size - 1);
            }
            else {
                Integer[] temp = new Integer[this.size + 1];
                if (index - 1 + 1 >= 0) System.arraycopy(this.arr, 0, temp, 0, index - 1 + 1);

                if (this.size + 1 - (index + 1) >= 0)
                    System.arraycopy(this.arr, index + 1 - 1, temp, index + 1, this.size + 1 - (index + 1));

                this.arr = temp;
            }
            this.arr[index] = e;
            this.size += 1;
        }
    }

    @Override
    public void delete_beg() throws Exception {
        if (this.isEmpty())
            throw new Exception("Cannot delete from empty array");
        else if (this.len() == 1)
            delete_end();
        else {
            this.shiftElementsToLeftBy1(1, this.size - 1);
            this.size -= 1;
        }
    }

    @Override
    public Integer getFrontElement() throws Exception {
        if (this.size == 0)
            throw new Exception("Cannot get front element from empty queue");
        return this.arr[0];
    }

    @Override
    public Integer getRearElement() throws Exception {
        if (this.size == 0)
            throw new Exception("Cannot get rear element from empty queue");
        return this.arr[this.size - 1];
    }

    @Override
    public void delete_end() throws Exception{
        if (this.isEmpty())
            throw new Exception("Cannot delete from empty array");
        else
            this.size -= 1;
    }

    @Override
    public void delete_at(Integer index) throws Exception {
        if (this.isEmpty())
            throw new Exception("Cannot delete from empty array");
        else if (index == 0)
            delete_beg();
        else if (index == this.size - 1)
            delete_end();
        else {
            this.shiftElementsToLeftBy1(index + 1, this.size - 1);
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
            private Integer currentItem = StaticArray.this.min();

            @Override
            public boolean hasNext() {
                return this.currentIndex < StaticArray.this.size;
            }

            @Override
            public Integer next() {
                Integer item = currentItem;
                currentItem = StaticArray.this.find_next(currentItem);
                currentIndex += 1;
                return item;
            }
        };
    }

    @Override
    public Iterator<Integer> iter_seq() {
        return new Iterator<Integer>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < StaticArray.this.size;
            }

            @Override
            public Integer next() {
                return StaticArray.this.arr[index++];
            }
        };
    }

    @Override
    public void reverse() {
        for (int i = 0; i < this.size / 2; i++) {
            this.swap(i, this.size - 1 - i);
        }
    }

    @Override
    public StaticArray clone() {
        StaticArray array = null;
        try {
            array = new StaticArray(this.arr.length);
            Iterator<Integer> iterator = this.iter_seq();
            while (iterator.hasNext())
                array.insert_end(iterator.next());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public boolean indexInBounds(int index) {
        return index >= 0 && index < this.size;
    }

    public void swap(int index1, int index2) throws IndexOutOfBoundsException {
        if (!this.indexInBounds(index1))
            throw new IndexOutOfBoundsException(String.format("size=%d, index1=%d. 0 <= index1 < size", this.size, index1));
        if (!this.indexInBounds(index2))
            throw new IndexOutOfBoundsException(String.format("size=%d, index2=%d. 0 <= index1 < size", this.size, index2));

        Integer temp = this.arr[index1];
        this.arr[index1] = this.arr[index2];
        this.arr[index2] = temp;
    }

    @Override
    public void push(int e) {
        this.insert_end(e);
    }

    @Override
    public Integer pop() throws Exception {
        int last = this.peek();
        this.delete_end();
        return last;
    }

    @Override
    public Integer peek() throws Exception {
        if (this.size == 0)
            throw new Exception("Cannot perform peek on empty stack");
        return this.arr[this.size - 1];
    }
}
