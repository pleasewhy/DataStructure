package 树;

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

    public SearchTree() {
    }

    public SearchTree(T element) {
        insert(element);
    }

    public void insert(T element) {
        if (root == null)
            root = new TreeNode(element);
        else
            dfsInsert(root, element);
    }

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
            dfsInsert(now.left, element);
            if (now.right == null)
                now.right = new TreeNode(element);
        }
    }

    private class TreeNode {
        T element;  // 储存元素的值
        int frequency = 1; // 频率
        TreeNode left;  // 左节点
        TreeNode right;  // 右节点

        TreeNode(T element) {
            this.element = element;
        }
    }
}
