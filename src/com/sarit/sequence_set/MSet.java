package com.sarit.sequence_set;

import java.util.Iterator;

public interface MSet {

    Integer len();
    Integer find(Integer ele);

    Integer min();
    Integer max();
    Integer find_next(Integer ele);
    Integer find_prev(Integer ele);

    void insert(Integer e);
    void delete(Integer e) throws Exception;

    void build(Iterator<Integer> it);
    Iterator<Integer> iter_set();

    MSet clone();
    
}
