package 线性结构;

import java.util.Iterator;

/**
 * 链表一种较为简单的数据结构，其对于删除和添加元素有着较好的表现，
 * 但不支持随机访问，在大量修改和访问指定位置的元素，表现很差，比
 * 数组差了几个量级。
 * 该链表实现了增删改查4个操作，并且支持迭代器
 * @param <T>
 */
public class LinkedList<T> implements Iterable {
    private int length = 0;  // 用于记录链表的长度
    protected Item<T> head = new Item<T>();  // 链表的头节点
    protected Item<T> tail = new Item<T>();  // 链表的尾节点

    public LinkedList() {
    }

    /**
     * 将类型为T的数组转化为链表
     *
     * @param items 数组
     */
    public LinkedList(T[] items) {
        this();
        addAll(items);
    }

    /**
     * @return 链表的长度
     */
    public int getLength() {
        return length;
    }

    /**
     * 将一个元素添加到链表中
     *
     * @param data
     */
    public void add(T data) {
        Item<T> item = new Item<>(data);
        if (length == 0) {
            head.setNext(item);
            tail.setPrevious(item);
        } else {
            tail.getPrevious().setNext(item);
            item.setPrevious(tail.getPrevious());
            tail.setPrevious(item);
        }
        length += 1;
    }

    /**
     * 将链表添加到链表中
     *
     * @param items
     */
    public void addAll(LinkedList<T> items) {
        for (Iterator<T> itor = items.iterator(); itor.hasNext(); ) {
            add(itor.next());
        }
    }

    /**
     * 将类型为T的数组添加到链表中
     * @param items
     */
    public void addAll(T[] items) {
        for (int i = 0; i < items.length; ++i) {
            add(items[i]);
        }
    }

    /**
     * 修改下标index的数据，由于链表不能随机访问，不推荐该方法
     * @param index  要修改的下标
     * @param item  修改元素的新内容
     */
    public void set(int index, T item) {
        Item<T> tmp;
        for (LinkedListIterator itor = new LinkedListIterator(); itor.hasNext(); ) {
            tmp = itor.nextItem();
            if (index == 0) {
                tmp.setData(item);
                return;
            }
            index -= 1;
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     *
     * @param index
     * @return 下标为index的值
     */
    public T get(int index) {
        Item<T> item;
        for (LinkedListIterator itor = new LinkedListIterator(); itor.hasNext(); ) {
            item = itor.nextItem();
            if (index == 0) {

                return item.getData();
            }
            index -= 1;
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * 取出第一个元素
     */
    public T pop() {
        if (empty())
            throw new IndexOutOfBoundsException();
        if (length == 1) {
            T rev = head.next.data;
            clear();
            return rev;
        }
        T rev = head.next.data;
        head.setNext(head.getNext().getNext());
        length -= 1;
        return rev;
    }

    /**
     * 取出最后一个元素
     */
    protected T popLast() {
        if (empty())
            throw new IndexOutOfBoundsException();
        else if (length == 1) {
            T rev = head.next.data;
            clear();
            return rev;
        }
        T rev = tail.previous.data;
        tail.setPrevious(tail.getPrevious().getPrevious());
        length -= 1;
        return rev;
    }

    /**
     * 删除一个元素
     *
     * @param index 删除元素的下标
     */
    public void remove(int index) {
        if (length == 1) {
            clear();
        } else if (index == 0) {
            pop();
        } else if (index == length - 1) {
            popLast();
        } else {
            Item<T> item;
            for (LinkedListIterator itor = new LinkedListIterator(); itor.hasNext(); ) {
                item = itor.nextItem();
                if (index == 0) {
                    item.getPrevious().setNext(item.getNext());
                    item.getNext().setPrevious(item.getPrevious());
                    this.length -= 1;
                    return;
                }
                index -= 1;
            }
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * 清空链表
     */
    public void clear() {
        head = new Item<T>();
        tail = new Item<>();
        this.length = 0;
    }

    /**
     * 判断链表是否为空
     *
     * @return
     */
    public boolean empty() {
        return length == 0;
    }

    /**
     * 将链表反转
     *
     * @return 反转后的链表
     */
    public LinkedList<T> reverse() {
        LinkedList<T> rev = new LinkedList<>();
        Item<T> item;
        int i = 0;
        for (LinkedListIterator itor = new LinkedListIterator(); i < length; ++i) {
            rev.add(itor.previousItem().getData());
        }
        return rev;
    }

    /**
     * iterator接口需要实现的方法
     * @return 返回一个迭代器类
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();//返回了一个迭代器
    }

    @Override
    public String toString() {
        Item tmp = this.head;
        StringBuffer rev = new StringBuffer();
        for (Iterator itor = this.iterator(); itor.hasNext(); ) {
            rev.append(itor.next());
            if (itor.hasNext())
                rev.append(" <-> ");
        }
        return rev.toString();
    }

    /**
     * 链表的迭代器类需要继承Iterator，并完成hasNext()，next()方法
     */
    private class LinkedListIterator implements Iterator<T> {
        private Item<T> now = head;  //用于迭代器
        private Item<T> nowTail = tail;

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

        public Item<T> previousItem() {
            Item<T> item = nowTail.getPrevious();
            nowTail = nowTail.getPrevious();
            return item;
        }

        Item<T> nextItem() {
            Item<T> item = now.getNext();
            now = now.getNext();
            return item;
        }
    }

    protected class Item<t> {
        private t data;
        private Item<t> next;
        private Item<t> previous;

        Item() {

        }

        Item(t data) {
            this.data = data;
        }

        Item(Item<t> previous) {
            this.previous = previous;
        }

        Item(t data, Item<t> previous) {
            this.data = data;
            this.previous = previous;
        }


        t getData() {
            return data;
        }

        Item<t> getNext() {
            return next;
        }

        Item<t> getPrevious() {
            return this.previous;
        }

        void setData(t data) {
            this.data = data;
        }

        void setPrevious(Item<t> previous) {
            this.previous = previous;
        }

        void setNext(Item<t> next) {
            this.next = next;
        }

        boolean hasNext() {
            return this.next != null;
        }


    }

    public static void main(String[] args) {
        String[] test1 = {"1", "2", "3"};
        Integer[] test2 = {3, 4, 5, 6, 7, 8, 9};
        LinkedList<String> a = new LinkedList<String>();
        LinkedList<Integer> b = new LinkedList<Integer>(test2);
        a.addAll(test1);
        a.remove(0);
        System.out.println(a);
        a.remove(0);
        System.out.println(a);
        a.remove(0);
        System.out.println(a);

        if (a.empty())
            System.out.println(b);
        System.out.println(a);
    }
}
