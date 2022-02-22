package com.sarit.sequence_set;

import java.util.Collection;
import java.util.Iterator;

public interface Sequence {

    Integer len();

    Integer get(Integer index) throws IndexOutOfBoundsException;
    void set(Integer index, Integer e) throws IndexOutOfBoundsException;

    void insert_beg(Integer e);
    void insert_end(Integer e);
    void insert_at(Integer index, Integer e) throws IndexOutOfBoundsException;

    void delete_beg() throws Exception;
    void delete_end() throws Exception;
    void delete_at(Integer index) throws Exception;

    void build(Iterator<Integer> it);
    Iterator<Integer> iter_seq();
}
