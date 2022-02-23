package com.sarit.stack_queue;

public interface MQueue {

    Integer len();
    void insert_end(Integer e);
    void delete_beg() throws Exception;
    Integer getFrontElement() throws Exception;
    Integer getRearElement() throws Exception;
}
