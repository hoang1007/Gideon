package com.gnaoh.util.structures;

import java.util.Iterator;

public abstract class LinkedList<T> implements Iterable<T> {
    public class Node {
        T value;
        Node next;

        public Node(T value) {
            this.value = value;
            next = null;
        }
    }

    private Node head, tail;
    private int size = 0;

    protected LinkedList() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected void addHead(T value) {
        Node node = new Node(value);

        if (isEmpty()) {
            tail = node;
        } else {
            node.next = head;
        }

        head = node;
        size++;
    }

    protected void addLast(T value) {
        Node node = new Node(value);

        if (isEmpty()) {
            head = node;
        } else {
            tail.next = node;
        }

        tail = node;
        size++;
    }

    protected T popHead() {
        if (isEmpty()) {
            return null;
        }

        T value = head.value;
        head = head.next;
        size--;
        return value;
    }

    protected T popLast() {
        if (isEmpty()) {
            return null;
        }

        T value = tail.value;
        Node preLast = head;
        for (; preLast.next != tail; preLast = preLast.next) {
        }

        tail = preLast;
        tail.next = null;

        size--;
        return value;
    }

    protected void insert(T value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addHead(value);
        } else if (index == size - 1) {
            addLast(value);
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++)
                prev = prev.next;
            Node node = new Node(value);
            node.next = prev.next;
            prev.next = node;
            size++;
        }
    }

    protected T get(int position) {
        if (position < 0 || position >= size)
            throw new IndexOutOfBoundsException();
            
        Node itr = head;
        for (int i = 0; i < position; i++) {
            itr = itr.next;
        }

        return itr.value;
    }

    protected T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        if (index == 0)
            return popHead();
        if (index == size - 1)
            return popLast();

        Node prev = head;
        for (int i = 0; i < index - 1; i++)
            prev = prev.next;
        T oldvalue = prev.next.value;
        prev.next = prev.next.next;
        size--;
        return oldvalue;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node itr = head;

            @Override
            public boolean hasNext() {
                return itr != null;
            }

            @Override
            public T next() {
                T value = itr.value;
                itr = itr.next;
                return value;
            }
        };
    }
}
