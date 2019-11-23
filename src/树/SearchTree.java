package 树;

import com.sun.source.tree.Tree;
import org.jetbrains.annotations.Contract;

/**
 * 查找树ADT----二叉查找树
 * 其左节点大于（小于）等于父节点右节点小于（大于）父节点，
 * 其查找效率为O(logN),在本例中左节点大于父节点，右节点小
 * 于父节点，对于重复值可以将节点的频率加1
 *
 * @param <T>
 */
public class SearchTree<T extends Comparable> {
    private int length = 0;
    private TreeNode root;

    @Contract(pure = true)
    public SearchTree() {
    }

    public SearchTree(T element) {
        insert(element);
    }

    public void insert(T element) {
        length++;
        if (root == null)
            root = new TreeNode(element);
        else
            dfsInsert(root, element);
    }

    //    @Contract(value = "null->")
    private void dfsInsert(TreeNode now, T element) {
        if (now == null) {
            return;
        }
        if (element.compareTo(now.element) == 0) {
            now.frequency++;
        } else if (element.compareTo(now.element) > 0) {
            dfsInsert(now.left, element);
            if (now.left == null)
                now.left = new TreeNode(element);
        } else {
            dfsInsert(now.right, element);
            if (now.right == null)
                now.right = new TreeNode(element);
        }
    }

    public void delete(T element) {
        if(find(element))
            length--;
        root = delete(root, element);
    }

    /**
     * 该函数删除时的值是向右边（最小值）修改
     *
     * @param now
     * @param element
     * @return
     */
    private TreeNode delete(TreeNode now, T element) {
        if (now == null)
            return null;
        else if (element.compareTo(now.element) > 0) {
            now.left = delete(now.left, element);  // 去左边删除，并将左边的删除后的值赋值给当前节点的left
        } else if (element.compareTo(now.element) < 0) {
            now.right = delete(now.right, element);
        } else if (now.right != null && now.left != null) {
            TreeNode node =  findMin(now.left);
            now.element = node.element;
            now.frequency = node.frequency;
            now.left = delete(now.left, now.element);
        } else {
            now = (now.right != null) ? now.right : now.left;
        }
        return now;
    }

    /**
     * element是否存在
     *
     * @param element 查找元素
     * @return true 存在，不存在false
     */
    public boolean find(T element) {
        return find(root, element) != null;
    }

    /**
     * 返回element元素节点
     *
     * @param now     当前节点
     * @param element 查找值
     * @return TreeNode
     */
    private TreeNode find(TreeNode now, T element) {
        if (now == null) {
            return null;
        }
        if (element.compareTo(now.element) == 0) {
            return now;
        } else if (element.compareTo(now.element) > 0) {
            return find(now.left, element);
        } else {
            return find(now.left, element);
        }
    }

    public T findMax() {
        return findMax(root).element;
    }

    private TreeNode findMax(TreeNode now) {
        if (now == null)
            return null;
        if (now.left == null)
            return now;
        return findMax(now.left);
    }

    public T findMin() {
        return findMin(root).element;
    }

    private TreeNode findMin(TreeNode now) {
        if (now == null)
            return null;
        if (now.right == null)
            return now;
        return findMin(now.right);
    }

    private class TreeNode {
        T element;  // 储存元素的值
        int frequency = 1; // 频率
        TreeNode left;  // 左节点
        TreeNode right;  // 右节点

        @Contract(pure = true)
        TreeNode(T element) {
            this.element = element;
        }
    }

    public static void main(String[] args) {
        int[] test = {1, 2, 3, 6, 9, 10, 45, 654, 452, 485, 45, 45, 78, 21, 2, 2415, 21, 2, 45};
        SearchTree<Integer> t = new SearchTree<>();
        for (int i : test
        ) {
            t.insert(i);
        }

        t.delete(2415);
        System.out.print(t.findMin()+" "+t.findMax());
    }
}
