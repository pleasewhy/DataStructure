package 树;

import 线性结构.QueueByArray;
import 线性结构.Stack;

/**
 * 二叉树遍历的各种方式：前序，中序，后序，层次
 * 前序遍历：先访问本节点，再访问左子树的所有节点，再右子树的所有节点
 * 中序遍历：先访问左子树的所有节点，再本节点，再右子树的所有节点
 * 后序遍历：先访问左子树的所有节点，再右子树的所有节点，在本节点
 * 层次遍历：从第一层访问到最后一层，即宽度优先搜索
 *
 * @author hy
 * @date 2019/11/26
 */
public class Travere extends SearchTree {

    /**
     * 前序序遍历的递归实现*
     *
     * @param now 树的根节点
     */
    public void preOrderTraversal(TreeNode now) {
        if (now != null) {
            System.out.print(now.element + " ");
            preOrderTraversal(now.left);
            preOrderTraversal(now.right);
        }
    }

    /**
     * 前序序遍历的循环实现
     *
     * @param now 树的根节点
     */
    public void preOrderTraversalByLoop(TreeNode now) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        if (now == null) {
            return;
        }
        while (!stack.isEmpty()) {
            now = stack.pop();
            System.out.print(now.element + " ");
            if (now.right != null) {
                stack.push(now.right);
            }
            if (now.left != null) {
                stack.push(now.left);
            }

        }
    }

    /**
     * 中序遍历的递归实现
     *
     * @param now 树的根节点
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
     *
     * @param now 树的根节点
     */
    public void inOrderTraversalByLoop(TreeNode now) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(now);
        boolean flag = true;
        while (!stack.isEmpty()) {
            now = stack.pop();
            while (now != null && flag) {
                stack.push(now);
                now = now.left;
            }
            if (flag) {
                now = stack.pop();
            }
            System.out.print(now.element + " ");
            if (now.right == null) {
                flag = false;
                continue;
            }
            stack.push(now.right);
            flag = true;
        }
    }

    /**
     * 后序序遍历的递归实现
     *
     * @param now 书的根节点
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
     * @param now 书的根节点
     */
    public void levelTraverse(TreeNode now) {
        QueueByArray<TreeNode> queue = new QueueByArray<TreeNode>();
        queue.add(now);
        while (!queue.empty()) {
            TreeNode node = queue.pop();
            System.out.print(node.element + " ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }


    public static void main(String[] args) {
        int[] test = {1, 2, 3, 6, 9, 10, 45, 654, 452, 485, 45, 45, 78, 21, 2, 2415, 21, 2, 45};
        Travere testTravere = new Travere();
        for (int i : test
        ) {
            testTravere.insert(i);
        }

        System.out.print("前序遍历的递归实现：");
        testTravere.preOrderTraversal(testTravere.root);
        System.out.println();

        System.out.print("前序遍历的循环实现：");
        testTravere.preOrderTraversalByLoop(testTravere.root);
        System.out.println();

        System.out.print("中序遍历的递归实现：");
        testTravere.inOrderTraversal(testTravere.root);
        System.out.println();

        System.out.print("中序遍历的循环实现：");
        testTravere.inOrderTraversalByLoop(testTravere.root);
        System.out.println();

        System.out.print("后序遍历的递归实现：");
        testTravere.postOrderTraversal(testTravere.root);
        System.out.println();

        System.out.print("层次遍历：");
        testTravere.levelTraverse(testTravere.root);
        System.out.println();
    }
}
