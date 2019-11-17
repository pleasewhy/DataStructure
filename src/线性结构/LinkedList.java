package 线性结构;

import java.util.Iterator;


public class LinkedList<T> implements Iterable<T> {
    int length;  // 用于记录链表的长度
    private int MAXlength = 10000;  // 链表的最大长度
    Node<T> root = new Node<T>();  // 链表的头节点
    Node<T> tail = new Node<T>();

    public LinkedList() {
        tail.setPrevious(root);
    }

    public LinkedList(int MAXlength) {
        this();
        this.MAXlength = MAXlength;
    }

    public int getMAXlength() {
        return MAXlength;
    }

    public void setMAXlength(int MAXlength) {
        this.MAXlength = MAXlength;
    }

    // iterator接口需要实现的方法
    public Iterator<T> iterator() {
        return new LinkedListIterator();//返回了一个迭代器
    }


    public void add(T data) {
        Node<T> node = new Node<>(data, this.tail.getPrevious());
        this.tail.getPrevious().setNext(node);
        tail.setPrevious(node);
    }

    public void addAll(LinkedList<T> linkedList) {
        Node<T> now = this.tail.getPrevious();
        Node<T> tmp = now;
        for (Iterator<T> itor = linkedList.iterator(); itor.hasNext(); ) {
            tmp = new Node<T>(itor.next(), now);
            now.setNext(tmp);
            now = tmp;
        }
        tail.setPrevious(now);
        this.length += linkedList.length;
    }

    public void addAll(T[] array) {
        Node<T> now = this.tail.getPrevious();
        Node<T> tmp = now;
        for (int i = 0; i < array.length; ++i) {
            tmp = new Node<T>(array[i], now);
            now.setNext(tmp);
            now = tmp;
        }
        tail.setPrevious(now);
        this.length += array.length;
    }

    @Override
    public String toString() {
        Node tmp = this.root;
        StringBuffer rev = new StringBuffer();
        for (Iterator itor = this.iterator(); itor.hasNext(); ) {
            rev.append(itor.next());
            if (itor.hasNext())
                rev.append(" <-> ");
        }
        return rev.toString();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> now = root;  //用于迭代器

        // Iterator接口中需要实现的方法
        public boolean hasNext() {
            return now.hasNext();
        }

        // Iterator接口中需要实现的方法
        public T next() {
            T data = now.getNext().getData();
            now = now.getNext();
            return data;
        }
    }

    private class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> previous;

        Node() {

        }

        Node(T data, Node<T> previous) {
            this.data = data;
            this.previous = previous;
        }

        void setNext(Node<T> next) {
            this.next = next;
        }

        T getData() {
            return data;
        }

        Node<T> getNext() {
            return next;
        }

        boolean hasNext() {
            return this.next != null;
        }

        Node<T> getPrevious() {
            return this.previous;
        }

        void setPrevious(Node<T> previous) {
            this.previous = previous;
        }
    }

    public static void main(String[] args) {
        String[] test1 = {"1", "2"};
        Integer[] test2 = {3, 4};
        LinkedList<String> a = new LinkedList<String>();
        LinkedList<Integer> b = new LinkedList<Integer>();
        a.addAll(test1);
        b.addAll(test2);
        System.out.print(a.toString());
    }
}
