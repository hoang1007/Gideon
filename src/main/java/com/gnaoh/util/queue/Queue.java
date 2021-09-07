package com.gnaoh.util.queue;

public class Queue<T> {
    public class Node {
        T value;
        Node next;
        public Node(T value) {
            this.value = value;
            next = null;
        }
    }

    private Node head, tail;
    int size;
    public Queue() {
        head = tail = null;
        size = 0;
    }

    /**
     * Push value to end of queue
     */
    public void enqueue(T value) {
        Node node = new Node(value);

        // if queue is empty 
        // make head and tail point to node
        // else make node next to tail
        if (tail == null)
            head = node;
        else 
            tail.next = node;

        tail = node;
        size++;
    }

    /**
     * Pop value from head of queue and return its value
     */
    public T dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty");
        T value = head.value;
        head = head.next;
        size--;

        return value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Remove all of the elements from the queue
     */
    public void clear() {
        while (true) {
            try {
                dequeue();
            }
            catch (IllegalArgumentException e) {
                break;
            }
        }
    }

    public T getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty");
        return head.value;
    }

    public T getBack() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty");
        return tail.value;
    }
}
