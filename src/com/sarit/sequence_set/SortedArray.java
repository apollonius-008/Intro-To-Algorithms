package com.sarit.sequence_set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class SortedArray implements MSet {

    private final ArrayList<Integer> arr;

    public SortedArray() {
        this.arr = new ArrayList<>();
    }

    @Override
    public Integer len() {
        return this.arr.size();
    }

    @Override
    public Integer find(Integer ele) {
        int index = this.find_index_recursive(ele, 0, this.len() - 1);
        if (index == -1)
            return null;
        else
            return this.arr.get(index);
    }

    private int find_index_recursive(Integer ele, int s, int e) {
        if (s <= e) {
            int mid = (s + e) / 2;
            if (ele < this.arr.get(mid))
                return find_index_recursive(ele, s, mid - 1);
            else if (ele > this.arr.get(mid))
                return find_index_recursive(ele, mid + 1, e);
            else
                return mid;
        }
        return -1;
    }

    @Override
    public Integer min() {
        if (this.arr.isEmpty())
            return null;
        else
            return this.arr.get(0);
    }

    @Override
    public Integer max() {
        if (this.arr.isEmpty())
            return null;
        else
            return this.arr.get(this.len() - 1);
    }

    @Override
    public Integer find_next(Integer ele) {
        int index = this.find_next_recusrive(ele, 0, this.len() - 1);

        if (index == -1)
            return null;
        else
            return this.arr.get(index);
    }

    private int find_next_recusrive(Integer ele, int s, int e) {
        if (s <= e) {
            int mid = (s + e) / 2;

            if (ele < this.arr.get(mid)) {
                if (mid - 1 >= s && this.arr.get(mid - 1) <= ele)
                    return mid;
                else
                    return find_next_recusrive(ele, s, mid - 1);
            } else {
                if (mid + 1 <= e && ele < this.arr.get(mid + 1))
                    return mid + 1;
                else
                    return find_next_recusrive(ele, mid + 1, e);
            }
        }

        return -1;
    }

    @Override
    public Integer find_prev(Integer ele) {
        int index = this.find_prev_recursive(ele, 0, this.len() - 1);
        if (index == -1)
            return null;
        else
            return this.arr.get(index);
    }

    private int find_prev_recursive(Integer ele, int s, int e) {
        if (s <= e) {
            int mid = (s + e) / 2;

            if (ele > this.arr.get(mid)) {
                if (mid + 1 <= e && this.arr.get(mid + 1) >= ele)
                    return mid;
                else
                    return find_prev_recursive(ele, mid + 1, e);
            } else {
                if (mid - 1 >= s && this.arr.get(mid - 1) < ele)
                    return mid - 1;
                else
                    return find_prev_recursive(ele, s, mid - 1);
            }
        }

        return -1;
    }

    @Override
    public void insert(Integer e) {
        if (this.find(e) == null) {
            this.arr.add(e);
            int i;
            for (i = this.len() - 2; i >= 0 && this.arr.get(i) >= e; i--) {
                this.arr.set(i + 1, this.arr.get(i));
            }
            this.arr.set(i + 1, e);
        }
    }

    @Override
    public void delete(Integer e) throws Exception {
        int index = this.find_index_recursive(e, 0, this.len() - 1);
        if (index == -1)
            throw new Exception("Cannot find " + e);
        this.arr.remove(index);
    }

    @Override
    public void build(Iterator<Integer> it) {
        while (it.hasNext())
            this.arr.add(it.next());
        Collections.sort(this.arr);
    }

    @Override
    public Iterator<Integer> iter_set() {
        return new Iterator<Integer>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < SortedArray.this.len();
            }

            @Override
            public Integer next() {
                Integer ele = SortedArray.this.arr.get(index);
                index += 1;
                return ele;
            }
        };
    }

    @Override
    public MSet clone() {
        SortedArray temp = new SortedArray();
        for (Integer ele : this.arr)
            temp.insert(ele);
        return temp;
    }
}
