package 线性结构;

import java.util.Iterator;

/**
 * 数组实现的队列
 * @version 1.0
 * @time 2019-11-18
 * @author hy
 */
public class QueueByArray<T> implements Iterable<T> {
    private static final int DEFAULTSIZE = 50000;
    private int length = 0;
    private int size = DEFAULTSIZE;
    private T[] items;
    private int head = 0;
    private int tail = 0;

    /**
     * 初始化一个默认队列的对象
     */
    public QueueByArray() {
        items = (T[])(new Object[this.size]);
    }

    /**
     * 初始化一个指定长度队列
     * @param size 一个队列的最大长度
     */
    public QueueByArray(int size) {
        this.size = size;
        items = (T[])(new Object[size]);
    }

    /**
     * 初始化指定初值的队列
     * @param itemArray 一个数组
     */
    public QueueByArray(T[] itemArray) {
        items = (T[])(new Object[size]);
        addAll(itemArray);
    }

    /**
     * 队列的中包含元素的长度
     * @return 长度
     */
    public int length() {
        return length;
    }

    /**
     * 队列是否为空
     * @return
     */
    public boolean empty() {
        return this.length == 0;
    }

    /**
     * 判断队列是否满元素
     * @return
     */
    public boolean isFull() {
        return length == size;
    }

    /**
     * 移动队列为将队列的head移到0
     */
    private void move() {
        int tmpHead = 0;
        for (int i = head; i < tail; ++i) {
            items[tmpHead++] = items[i];
        }
        head = 0;
        tail = length;
    }

    /**
     * 将item送入队列
     * @param item
     */
    public void add(T item) {
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

    /**
     * 将itemArray送入队列
     * @param itemArray
     */
    public void addAll(T[] itemArray) {
        if (size - length < itemArray.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < itemArray.length; ++i) {
            add(itemArray[i]);
        }
    }

    /**
     * 得到队列的头元素
     * @return
     */
    public T get() {
        if (!empty())
            return items[head];
        throw new IndexOutOfBoundsException();
    }

    /**
     * 取出队列头元素
     * @return
     */
    public T pop() {
        T rev = get();
        head += 1;
        length -= 1;
        return rev;
    }

    /**
     * 用于迭代器，遍历队列的全部元素，不删除
     * @return 队列迭代器
     */
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
        q.addAll(test1);
        for (Object i : q) {
            System.out.print(i + " ");
        }
        System.out.print(q.get());
    }
}
