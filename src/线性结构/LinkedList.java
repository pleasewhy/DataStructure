package 线性结构;

import java.util.Iterator;


public class LinkedList<T> implements Iterable {
    private int length = 0;  // 用于记录链表的长度
    private int MAXlength = 10000;  // 链表的最大长度
    private Node<T> root = new Node<T>();  // 链表的头节点
    private Node<T> tail = new Node<T>();

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

    public int getLength() {
        return length;
    }

    public void setMAXlength(int MAXlength) {
        this.MAXlength = MAXlength;
    }

    // iterator接口需要实现的方法
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();//返回了一个迭代器
    }

    public void add(T data) {
        Node<T> node = new Node<>(data, this.tail.getPrevious());
        this.tail.getPrevious().setNext(node);
        tail.setPrevious(node);
        this.length += 1;
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

    public void set(int index, T data) {
        Node<T> node;
        for (LinkedListIterator itor = new LinkedListIterator(); itor.hasNext(); ) {
            node = itor.nextNode();
            if (index == 0) {
                node.setData(data);
                return;
            }
            index -= 1;
        }
        throw new IndexOutOfBoundsException();
    }

    public T get(int index) {
        Node<T> node;
        for (LinkedListIterator itor = new LinkedListIterator(); itor.hasNext(); ) {
            node = itor.nextNode();
            if (index == 0) {

                return node.getData();
            }
            index -= 1;
        }
        throw new IndexOutOfBoundsException();
    }

    public void remove(int index) {
        Node<T> node;
        for (LinkedListIterator itor = new LinkedListIterator(); itor.hasNext(); ) {
            node = itor.nextNode();
            if (index == 0) {
                node.getPrevious().setNext(node.getNext());
                node.getNext().setPrevious(node.getPrevious());
                this.length -= 1;
                return;
            }
            index -= 1;
        }
        throw new IndexOutOfBoundsException();
    }

    public void clear() {
        root = new Node<T>();
        tail = new Node<>();
        this.length = 0;
    }

    public LinkedList<T> reverse() {
        LinkedList<T> rev = new LinkedList<>();
        Node<T> node;
        int i = 0;
        for (LinkedListIterator itor = new LinkedListIterator(); i < length; ++i) {
            rev.add(itor.previousNode().getData());
        }
        return rev;
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
        private Node<T> nowTail = tail;

        // Iterator接口中需要实现的方法
        @Override
        public boolean hasNext() {
            return now.hasNext();
        }

        // Iterator接口中需要实现的方法
        @Override
        public T next() {
            T data = now.getNext().getData();
            now = now.getNext();
            return data;
        }

        public Node<T> previousNode() {
            Node<T> node = nowTail.getPrevious();
            nowTail = nowTail.getPrevious();
            return node;
        }

        Node<T> nextNode() {
            Node<T> node = now.getNext();
            now = now.getNext();
            return node;
        }
    }

    private class Node<t> {
        private t data;
        private Node<t> next;
        private Node<t> previous;

        Node() {

        }

        Node(t data, Node<t> previous) {
            this.data = data;
            this.previous = previous;
        }


        t getData() {
            return data;
        }

        Node<t> getNext() {
            return next;
        }

        Node<t> getPrevious() {
            return this.previous;
        }

        void setData(t data) {
            this.data = data;
        }

        void setPrevious(Node<t> previous) {
            this.previous = previous;
        }

        void setNext(Node<t> next) {
            this.next = next;
        }

        boolean hasNext() {
            return this.next != null;
        }


    }

    public static void main(String[] args) {
        String[] test1 = {"1", "2", "3"};
        Integer[] test2 = {3, 4};
        LinkedList<String> a = new LinkedList<String>();
        LinkedList<Integer> b = new LinkedList<Integer>();
        a.addAll(test1);
        b.addAll(test2);
        System.out.print(a.reverse().toString());
    }
}
