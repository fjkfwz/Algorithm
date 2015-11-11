package Tree.BinaryTree;

import java.util.Stack;

/**
 * Created by jz on 2015/11/11.
 */
public class InOrderIterator implements BSTIterator {
    Stack<TreeNode> treeNodeStack = new Stack<>();

    public InOrderIterator(TreeNode root) {
        treeNodeStack.push(root);
        TreeNode temp = root;
        while (temp != null) {
            if (temp.getLeft() != null) {
                treeNodeStack.push(temp.getLeft());
            }
            temp = temp.getLeft();
        }
    }

    @Override
    public boolean hasNext() {
        if (treeNodeStack.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public TreeNode next() {
        TreeNode next = treeNodeStack.pop();
        if (next.getRight() == null) {
            return next;
        }
        treeNodeStack.push(next.getRight());
        TreeNode temp = next.getRight();
        while (temp != null) {
            if (temp.getLeft() != null) {
                treeNodeStack.push(temp.getLeft());
            }
            temp = temp.getLeft();
        }
        return next;
    }
}
