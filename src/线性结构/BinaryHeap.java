package 线性结构;

/**
 *优先队列跟队列一样都支持先进先出，但这里是以元素的大小判定出队的顺序
 * 优先级越高越先出队（最大值，或者最小值），若以链表形式，则插入或则
 * 删除会有一个O(N)另一个是O(1)，而对于二叉堆插入删除都是O(logN)。
 * 下列代码实现的是最小值优先，且二叉树是以0为根节点的
 * @param <T>
 */
public class BinaryHeap<T> {
    private static final int DEFAULTSIZE = 50000;  // 默认容量
    Comparable[] array;  // 二叉堆数组
    private int length;  // 元素的长度
    private int size;

    public BinaryHeap() {
        this(DEFAULTSIZE);
    }

    public BinaryHeap(int size) {
        this.size = size;
        array = (new Comparable[size]);
    }

    public boolean isFull() {
        return length == size;
    }

    public void insert(Comparable data) {
        if (isFull())
            throw new OutOfMemoryError();
        int hole = length++;
        for (; hole != 0 && array[(hole - 1) / 2].compareTo(data) > 0; hole = (hole - 1) / 2)  // 上滤
            array[hole] = array[(hole - 1) / 2];
        array[hole] = data;
    }

    public Comparable deleteMin() {
        Comparable minElement = array[0];
        Comparable lastElement = array[--length];
        int i, child;
        for (i = 0; i * 2 + 1 <= length; ++i) {  //下滤
            child = 2 * i + 1;
            // 这里将child的值变为左右子节点较小的哪一个
            if (child != length && array[child].compareTo(array[child + 1]) > 0)
                child++;
            // 若最后一个元素大于当前子节点，则
            if (lastElement.compareTo(array[child]) > 0)
                array[i] = array[child];
            else break;
        }
        array[i] = lastElement;
        return minElement;
    }

    public static void main(String[] args) {
        Integer[] test = {1, 2, 3, 4, 6, 5, 7, 8, 9, 5, 3};
        BinaryHeap<Integer> a = new BinaryHeap<>();

        for (int i : test) {
            a.insert(i);
        }
        for (int i =0;a.length!=0;++i)
            System.out.println(i+"    "+a.deleteMin()+" ");
    }
}