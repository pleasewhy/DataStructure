package 树;

import 线性结构.QueueByArray;
import 线性结构.Stack;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
     * <p>
     * 首先将当前节点的左子树下的其父节点也是左节点（树每一层最左边的节点）的节点入栈
     * 再取出栈顶元素并输出其值，再判断其是否有右节点，若有右节点则入栈，没有就循环结束，
     * 继续下一次循环
     * <p>
     * 简单点来说就是将栈顶元素的左子树的最左边的节点添加到栈中，再判断添加后栈顶元素是
     * 否有右节点，若有就再将右节点的左子树的最右边的元素添加到栈中........，每取出一个
     * 元素就将其值添加到结果数组中
     *
     * @param now 树的根节点
     */
    public void inOrderTraversalByLoop(TreeNode now) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || now != null) {
            while (now != null) {
                stack.push(now);
                now = now.left;
            }
            now = stack.pop();
            System.out.print(now.element + " ");
            now = now.right;
        }
    }

//    public void inOrderTraversalByLoop(TreeNode now) {
//        Stack<TreeNode> stack = new Stack<>();
//        stack.push(now);
//        boolean flag = true;
//        while (!stack.isEmpty()) {
//            now = stack.pop();
//            while (now != null && flag) {
//                stack.push(now);
//                now = now.left;
//            }
//            if (flag) {
//                now = stack.pop();
//            }
//            System.out.print(now.element + " ");
//            if (now.right == null) {
//                flag = false;
//                continue;
//            }
//            stack.push(now.right);
//            flag = true;
//        }
//    }

    /**
     * 后序序遍历的递归实现
     *
     * @param now 书的根节点
     */
    public void postOrderTraversal(TreeNode now) {
        if (now != null) {
            postOrderTraversal(now.left);
            postOrderTraversal(now.right);
            System.out.print(now.element + " ");
        }
    }

    /**
     * 后序遍历的循环实现
     * 可以先访问根节点，右子树，左子树（类似于先序遍历），再将结果数组反转即可得到正确
     * 的访问结果
     *
     * @param now 二叉树的根节点
     */
    public void postOrderTraversalByLoop(TreeNode now) {
        Stack<TreeNode> stack = new Stack();
        stack.push(now);
        List<Comparable> linkedList = new LinkedList<>();

        while (!stack.isEmpty()) {
            now = stack.pop();
            linkedList.add(now.element);
            if (now.left != null) {
                stack.push(now.left);
            }
            if (now.right != null) {
                stack.push(now.right);
            }
        }
        Collections.reverse(linkedList);
        for (Comparable i : linkedList
        ) {
            System.out.print(i + " ");
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
        int[] test = {7, 4, 9, 3, 5, 8, 10};
        Travere testTraver = new Travere();
        for (int i : test
        ) {
            testTraver.insert(i);
        }

        System.out.print("前序遍历的递归实现：");
        testTraver.preOrderTraversal(testTraver.root);
        System.out.println();

        System.out.print("前序遍历的循环实现：");
        testTraver.preOrderTraversalByLoop(testTraver.root);
        System.out.println();

        System.out.print("中序遍历的递归实现：");
        testTraver.inOrderTraversal(testTraver.root);
        System.out.println();

        System.out.print("中序遍历的循环实现：");
        testTraver.inOrderTraversalByLoop(testTraver.root);
        System.out.println();

        System.out.print("后序遍历的递归实现：");
        testTraver.postOrderTraversal(testTraver.root);
        System.out.println();

        System.out.print("后序遍历的循环实现：");
        testTraver.postOrderTraversalByLoop(testTraver.root);
        System.out.println();

        System.out.print("层次遍历：");
        testTraver.levelTraverse(testTraver.root);
        System.out.println();
    }
}
