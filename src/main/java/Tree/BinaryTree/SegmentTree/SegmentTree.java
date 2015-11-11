package Tree.BinaryTree.SegmentTree;


/**
 * Created by jz on 2015/11/10.
 */
public class SegmentTree {
    private TreeNode root;

    public TreeNode build(int[] data) {
        int start = 0;
        int end = data.length - 1;
        int max = getMax(data, 0, data.length - 1);
        root = new TreeNode(start, end, max);
        buildTree(root, data);
        return root;
    }

    private int getMax(int[] data, int start, int end) {
        int max = 0;
        for (int i = start; i < end + 1; i++) {
            max = Math.max(data[i], max);
        }
        return max;
    }


    private void buildTree(TreeNode node, int[] data) {
        if (node == null) {
            return;
        }
        if (node.getStart() == node.getEnd()) {
            return;
        }
        int start = node.getStart();
        int end = (node.getStart() + node.getEnd()) / 2;
        TreeNode left = new TreeNode(start, end, getMax(data, start, end));
        node.setLeft(left);
        buildTree(left, data);
        start = (node.getStart() + node.getEnd()) / 2 + 1;
        end = node.getEnd();
        TreeNode right = new TreeNode(start, end, getMax(data, start, end));
        node.setRight(right);
        buildTree(right, data);
        return;
    }

    public String preOrderPrint(TreeNode node, String string) {
        if (node == null) {
            return string;
        }
        string = preOrderPrint(node.getLeft(), string);
        string += "[" + node.getStart() + "," + node.getEnd() + "," + node.getMax() + "] ";
        string = preOrderPrint(node.getRight(), string);
        return string;
    }

    public int query(TreeNode node, int start, int end) {
        if (node.getStart() == start && node.getEnd() == end) {
            return node.getMax();
        }
        if (node.getEnd() == node.getStart()) {
            return -1;
        }
        int max = -1;
        if (node.getRight().getStart() > end) {
            max = query(node.getLeft(), start, end);
        } else {
            max = query(node.getRight(), start, end);
        }
        return max;
    }

    public void modify(TreeNode node, int index, int val) {
        if (node.getEnd() <= index) {
            node.setMax(val);
        }
        if (node.getStart() == node.getEnd()) {
            return;
        }
        if (index >= ((node.getEnd() + node.getStart()) / 2 + 1)){
            modify(node.getRight(), index, val);
        } else {
            modify(node.getLeft(), index, val);
        }
    }
}
