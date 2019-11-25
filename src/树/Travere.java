package 树;

import 线性结构.QueueByArray;

public class Travere extends SearchTree {
    /**
     * 中序遍历的递归实现
     * 中序遍历的顺序左中右，对于查找树中序遍历可排序
     *
     * @param now
     */
    public void inOrderTraversal(TreeNode now) {
        if (now != null) {
            inOrderTraversal(now.left);
            System.out.print(now.element + " ");
            inOrderTraversal(now.right);
        }
    }
    /**
     * 中序遍历的循环实现
     */
    public void inOrderTraversal() {
        QueueByArray<>
    }

    /**
     * 前序序遍历的递归实现
     *
     * @param now
     */
    public void preOrderTraversal(TreeNode now) {
        if (now != null) {
            System.out.print(now.element + " ");
            inOrderTraversal(now.left);
            inOrderTraversal(now.right);
        }
    }

    /**
     * 后序序遍历的递归实现
     *
     * @param now
     */
    public void postOrderTraversal(TreeNode now) {
        if (now != null) {
            inOrderTraversal(now.left);
            inOrderTraversal(now.right);
            System.out.print(now.element + " ");
        }
    }

    /**
     * 层次遍历
     *
     * @param now
     */
    public void levelTraverse(TreeNode now) {
        QueueByArray<TreeNode> queue = new QueueByArray<TreeNode>();
        queue.add(now);
        while (!queue.empty()) {
            TreeNode node = queue.pop();
            System.out.print(node.element + " ");
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
    }

    /**
     * 深度优先遍历
     * @param
     */

    public static void main(String[] args) {
        int[] test = {1, 2, 3, 6, 9, 10, 45, 654, 452, 485, 45, 45, 78, 21, 2, 2415, 21, 2, 45};
        Travere testTravere = new Travere();
        for (int i : test
        ) {
            testTravere.insert(i);
        }
        testTravere.inOrderTraversal(testTravere.root);
        System.out.println();
        testTravere.preOrderTraversal(testTravere.root);
        System.out.println();
        testTravere.postOrderTraversal(testTravere.root);
        System.out.println();
        testTravere.levelTraverse(testTravere.root);
    }
}
