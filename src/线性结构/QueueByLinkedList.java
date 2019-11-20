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
        return "head-> " + super.toString() + " <-tail";
    }

    public static void main(String[] args) {
        Integer[] a = {1, 2, 3};
        QueueByLinkedList<Integer> test = new QueueByLinkedList<>();
        test.addAll(a);
        test.pop();
        if (!test.empty())
            System.out.println(test);
    }
}
