package 线性结构;

/**
 * 链表实现的队列，继承于链表
 *
 * @param <T> 泛型队列储存的数据类型
 * @author hy
 * @version 1.0
 */
public class QueueByLinkedList<T> extends LinkedList<T> {

    public QueueByLinkedList() {
        super();
    }

    public QueueByLinkedList(LinkedList<T> items) {
        addAll(items);
    }

    public QueueByLinkedList(T[] items) {
        addAll(items);
    }

    public void add(T item) {
        super.add(item);
    }

    public void addAll(T[] items) {
        super.addAll(items);
    }

    public void addAll(LinkedList<T> items) {
        super.addAll(items);
    }

    public T pop() {
        T rev = super.get(0);
        super.remove(0);
        return rev;
    }

    public T get() {
        return super.get(0);
    }

    public boolean empty() {
        return super.empty();
    }

    public void clear() {
        super.clear();
    }

    @Override
    public String toString() {
        return "head->" + super.toString() + "<-tail";
    }

    public static void main(String[] args) {
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        QueueByLinkedList<Integer> test = new QueueByLinkedList<>();
        test.addAll(a);
        if (!test.empty())
            System.out.println(test);
    }
}
