package Tree.BinaryTree.SegmentTree;

/**
 * Created by jz on 2015/11/10.
 */
public class TreeNode {
    public int start;
    public int end;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int max;
    public TreeNode left, right;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public TreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.left = this.right = null;
    }

    public TreeNode(int start, int end, int max) {
        this.start = start;
        this.end = end;
        this.max = max;
        this.left = this.right = null;

    }
}
