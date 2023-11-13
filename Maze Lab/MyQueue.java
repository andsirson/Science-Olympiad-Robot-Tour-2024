import java.util.NoSuchElementException;

public class MyQueue<T> implements QueueADT<T> {
    private Node<T> head;
    private Node<T> tail;

    public MyQueue() {
        this.head = null;
        this.tail = null;
    }

    public T dequeue() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }

        T element = this.head.data;
        this.head = this.head.next;
        if (this.head == null) {
            this.tail = null;
        }
        return element;
    }

    public void enqueue(T element) {
        Node<T> newNode = new Node<T>(element, null);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
    }

    public T front() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        return this.head.data;
    }

    public int size() {
        int count = 0;
        Node<T> tempNode = this.head;
        while (tempNode != null) {
            count++;
            tempNode = tempNode.next;
        }
        return count;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void clear() {
        this.head = null;
        this.tail = null;
    }

    public class Node<U> {
        public U data;
        public Node<U> next;

        public Node(U newData, Node<U> newNext) {
            data = newData;
            next = newNext;
        }
    }
}

