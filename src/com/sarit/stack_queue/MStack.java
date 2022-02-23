package com.sarit.stack_queue;

public interface MStack {
    Integer len();
    void push(int e);
    Integer pop() throws Exception;
    Integer peek() throws Exception;
}
