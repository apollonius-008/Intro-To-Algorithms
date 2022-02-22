package com.sarit;

import com.sarit.sequence_set.*;
import com.sarit.sequence_set.Set;
import java.util.*;

public class StaticArray implements Sequence, Set {

    private Integer[] arr;
    private Integer size;

    public StaticArray(int initialCapacity) {
        this.arr = new Integer[initialCapacity];
        this.size = 0;
    }

    public boolean isFull() {
        return this.size == this.arr.length;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Integer len() {
        return this.size;
    }

    @Override
    public Integer find(Integer ele) {
        int index = this.indexOf(ele);
        return index == -1 ? null : this.arr[index];
    }

    @Override
    public Integer min() {
        Integer minE = Integer.MAX_VALUE;
        for (Integer e : this.arr) {
            if (e < minE)
                minE = e;
        }

        return minE;
    }

    @Override
    public Integer max() {
        Integer maxE = Integer.MIN_VALUE;
        for (Integer e : this.arr) {
            if (e > maxE)
                maxE = e;
        }

        return maxE;
    }

    @Override
    public Integer find_next(Integer ele) {
        Integer next = Integer.MAX_VALUE;
        for (Integer e : this.arr) {
            if (e > ele && e < next)
                next = e;
        }

        if (next == Integer.MAX_VALUE && this.find(Integer.MAX_VALUE) == null)
            return null;

        return next;
    }

    @Override
    public Integer find_prev(Integer ele) {
        Integer prev = Integer.MIN_VALUE;
        for (Integer e : this.arr) {
            if (e < ele && e > prev)
                prev = e;
        }

        if (prev == Integer.MIN_VALUE && this.find(Integer.MIN_VALUE) == null)
            return null;

        return prev;
    }

    @Override
    public void insert(Integer e) {
        if (this.find(e) == null)
            this.insert_end(e);
    }

    @Override
    public void delete(Integer e) throws Exception {
        int index = this.find(e);
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
        if (index >= this.size)
            throw new IndexOutOfBoundsException(String.format("size=%d, index=%d. 0 <= index < size not satisfied", this.size, index));
        else
            return this.arr[index];
    }

    @Override
    public void set(Integer index, Integer e) throws IndexOutOfBoundsException {
        if (index >= this.size)
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
            System.arraycopy(this.arr, s, this.arr, s + 1, e + 1 - s);
    }

    @Override
    public void insert_beg(Integer e) {
        if (!this.isFull()) {
            try {
                this.shiftElementsToRightBy1(0, this.size - 1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            Integer[] temp = new Integer[this.size + 1];
            for (int i = 0; i < this.size; i++) {
                temp[i + 1] = this.arr[i];
            }
            this.arr = temp;
        }
        this.arr[this.size] = e;
        this.size += 1;
    }

    @Override
    public void insert_end(Integer e) {
        if (this.isFull()) {
            Integer[] temp = new Integer[this.size + 1];
            for (int i = 0; i < this.size; i++) {
                temp[i] = this.arr[i];
            }
            this.arr = temp;
        }

        this.arr[this.size] = e;
        this.size += 1;
    }

    @Override
    public void insert_at(Integer index, Integer e) throws IndexOutOfBoundsException {
        if (index > this.size)
            throw new IndexOutOfBoundsException(String.format("size=%d, index=%d. 0 <= index <= size not satisfied", this.size, index));
        else if (index == 0)
            this.insert_beg(e);
        else if (index.equals(size))
            this.insert_end(e);
        else {
            if (!this.isFull()) {
                for (int i = this.size; i >= index + 1; i--)
                    this.arr[i] = this.arr[i - 1];
            }
            else {
                Integer[] temp = new Integer[this.size + 1];
                for (int i = 0; i <= index - 1; i++)
                    temp[i] = this.arr[i];

                for (int i = this.size; i >= index + 1; i--)
                    temp[i] = this.arr[i - 1];

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
        else {
            for (int i = 1; i < this.size; i++)
                this.arr[i - 1] = this.arr[i];
            this.size -= 1;
        }
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
        else {
            for (int i = index + 1; i < this.size; i++)
                this.arr[i - 1] = this.arr[i];
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
        return null;
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
}
