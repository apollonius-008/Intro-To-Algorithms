package com.sarit.stack_queue;

public class CircularQueue implements MDeque {

    private int front;
    private int rear;
    private int size;
    private Integer[] arr;

    public CircularQueue(int initialCapacity) throws Exception {
        if (initialCapacity <= 0)
            throw new Exception("initialCapacity is positive. initialCapacity=" + initialCapacity);
        this.front = -1;
        this.rear = -1;
        this.arr = new Integer[initialCapacity];
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.front == -1;
    }

    public boolean isFull() {
        return this.size == this.arr.length;
    }

    @Override
    public void insert_beg(Integer e) {
        if (this.isEmpty()) {
            this.front = 0;
            this.rear = 0;
        } else if (this.isFull()) {
            Integer[] temp = new Integer[this.size + 1];
            if (this.front <= this.rear) {
                int count = 1;
                for (int i = this.front; i <= this.rear; i++) {
                    temp[count++] = this.arr[i];
                }
            } else {
                int count = 1;
                for (int i = this.front; i < this.arr.length; i++)
                    temp[count++] = this.arr[i];
                for (int i = 0; i <= this.rear; i++)
                    temp[count++] = this.arr[i];
            }
            this.arr = temp;
            this.front = 0;
            this.rear = this.size - 1;
        } else {
            this.front -= 1;
            if (this.front < 0)
                this.front += this.arr.length;
        }
        this.arr[this.front] = e;
        this.size += 1;
    }

    @Override
    public void delete_end() throws Exception {
        if (this.isEmpty())
            throw new Exception("Cannot delete from an empty queue");
        else if (this.size == 1) {
            this.front = -1;
            this.rear = -1;
            this.size = 0;
        } else {
            this.rear -= 1;
            if (this.rear < 0)
                this.rear -= this.arr.length;

            this.size -= 1;
        }
    }

    @Override
    public CircularQueue clone() {
        CircularQueue q = null;
        try {
            q = new CircularQueue(this.arr.length);
            if (q.isEmpty())
                return q;
            else if (this.front <= this.rear) {
                for (int i = this.front; i <= this.rear; i++) {
                    q.insert_end(this.arr[i]);
                }
            } else {

                for (int i = this.front; i < this.arr.length; i++)
                    q.insert_end(this.arr[i]);
                for (int i = 0; i <= this.rear; i++)
                    q.insert_end(this.arr[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return q;
    }

    @Override
    public Integer len() {
        return this.size;
    }

    @Override
    public void insert_end(Integer e) {
        if (this.isEmpty()) {
            this.front = 0;
            this.rear = 0;
        } else if (this.isFull()) {
            Integer[] temp = new Integer[this.size + 1];
            if (this.front <= this.rear) {
                int count = 0;
                for (int i = this.front; i <= this.rear; i++) {
                    temp[count++] = this.arr[i];
                }
            } else {
                int count = 0;
                for (int i = this.front; i < this.arr.length; i++)
                    temp[count++] = this.arr[i];
                for (int i = 0; i <= this.rear; i++)
                    temp[count++] = this.arr[i];
            }
            this.arr = temp;
            this.front = 0;
            this.rear = this.size - 1;
        }

        this.rear += 1;
        if (this.rear >= this.arr.length)
            this.rear = this.rear - this.arr.length;

        this.size += 1;
        this.arr[this.rear] = e;
    }

    @Override
    public void delete_beg() throws Exception {
        if (this.isEmpty())
            throw new Exception("Cannot delete from an empty queue");
        else if (this.size == 1) {
            this.front = -1;
            this.rear = -1;
            this.size = 0;
        } else {
            this.front += 1;
            if (this.front >= this.arr.length)
                this.front -= this.arr.length;
            this.size -= 1;
        }
    }

    @Override
    public Integer getFrontElement() throws Exception {
        if (this.isEmpty())
            throw new Exception("Cannot get front from empty queue");
        return this.arr[this.front];
    }

    @Override
    public Integer getRearElement() throws Exception {
        if (this.isEmpty())
            throw new Exception("Cannot get rear from empty queue");
        return this.arr[this.rear];
    }
}
