package 线性结构;

import java.util.Iterator;

public class QueueByArray implements Iterable {
    private int length = 0;
    private int size = 50000;
    private int[] items;
    private int head = 0;
    private int tail = 0;

    public QueueByArray() {
        items = new int[this.size];
    }

    public QueueByArray(int size) {
        this.size = size;
        items = new int[size];
    }

    public QueueByArray(int[] itemArray) {
        pushAll(itemArray);
    }
    public int length(){
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

    public void push(int item) {
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

    public void pushAll(int[] itemArray) {
        if (size - length < itemArray.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < itemArray.length; ++i) {
            push(itemArray[i]);
        }
    }

    public int get() {
        if (!empty())
            return items[head];
        throw new IndexOutOfBoundsException();
    }

    public int pop() {
        int rev = get();
        head -= 1;
        length -= 1;
        return rev;
    }

    @Override
    public Iterator iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Integer> {
        private int now = head;

        @Override
        public boolean hasNext() {
            return !(now == tail);
        }

        @Override
        public Integer next() {
            if (!empty()) {
                return items[now++];
            }
            return null;
        }

    }

    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 4, 5, 6};
        QueueByArray q = new QueueByArray();
        q.pushAll(test1);
        for (Object i : q) {
            System.out.print(i + " ");
        }
    }
}
