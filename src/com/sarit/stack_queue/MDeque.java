package com.sarit.stack_queue;

public interface MDeque extends MQueue{

    void insert_beg(Integer e);
    void delete_end() throws Exception;
    MDeque clone();
}
