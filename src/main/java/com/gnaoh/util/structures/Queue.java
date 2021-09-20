package com.gnaoh.util.structures;

public class Queue<T> extends LinkedList<T> {
    public Queue() {
        super();
    }

    public void push(T value) {
        // push to the last of base list
        // super.addLast(value);
        super.addLast(value);
    }

    public T front() { 
        return super.get(0);
    }

    public T pop() {
        // pop from the head of the base list
        return super.popHead();
    }

    public void insert(T value, int index) {
        // since tail of base list is head of queue
        // the queue is reverse order
        // need to reverse index 
        super.insert(value, size() - index - 1);
    }
}
