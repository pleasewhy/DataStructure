package 线性结构;

import java.util.Iterator;

public class QueueByArray<T> implements Iterable<T> {
    private int length = 0;
    private int size = 50000;
    private T[] items;
    private int head = 0;
    private int tail = 0;

    public QueueByArray() {
        items = (T[])(new Object[this.size]);
    }

    public QueueByArray(int size) {
        this.size = size;
        items = (T[])(new Object[size]);
    }

    public QueueByArray(T[] itemArray) {
        items = (T[])(new Object[size]);
        pushAll(itemArray);
    }

    public int length() {
        return length;
    }

    public boolean empty() {
        return this.length == 0;
    }

    public boolean fill() {
        return length == size;
    }

    // 将队列向前移动到head为0
    private void move() {
        int tmpHead = 0;
        for (int i = head; i < tail; ++i) {
            items[tmpHead++] = items[i];
        }
        head = 0;
        tail = length;
    }

    public void push(T item) {
        if (tail != size) {
            items[tail++] = item;
            length += 1;
        } else if (head != 0) {
            this.move();
            items[tail++] = item;
            length += 1;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void pushAll(T[] itemArray) {
        if (size - length < itemArray.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < itemArray.length; ++i) {
            push(itemArray[i]);
        }
    }

    public T get() {
        if (!empty())
            return items[head];
        throw new IndexOutOfBoundsException();
    }

    public T pop() {
        T rev = get();
        head += 1;
        length -= 1;
        return rev;
    }

    @Override
    public Iterator iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {
        private int now = head;

        @Override
        public boolean hasNext() {
            return !(now == tail);
        }

        @Override
        public T next() {
            if (!empty()) {
                return items[now++];
            }
            return null;
        }

    }

    public static void main(String[] args) {
        Integer[] test1 = {1, 2, 3, 4, 5, 6};
        QueueByArray q = new QueueByArray(test1);
        System.out.print(q.pop());
        q.pushAll(test1);
        for (Object i : q) {
            System.out.print(i + " ");
        }
        System.out.print(q.get());
    }
}
