package Tree.BinaryTree.SegmentTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jz on 2015/11/12.
 */
public class SegmentTreeMin {
    public static void main(String args[]) {
        int[] array = new int[]{1, 2, 7, 8, 5};
        List<Interval> intervalList = new ArrayList<>();
        SegmentTreeMin segmentTreeMin = new SegmentTreeMin();
        intervalList.add(new Interval(1, 2));
        intervalList.add(new Interval(1, 4));
        intervalList.add(new Interval(2, 4));
        intervalList.add(new Interval(4, 5));
        System.out.println(segmentTreeMin.queryIntervalMin(array, intervalList));
    }

    public class SegmentTreeNode {
        int start, end, val;
        SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int val) {
            this.start = start;
            this.val = val;
            this.end = end;
            this.left = this.right = null;
        }
    }

    public static class Interval {
        int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public SegmentTreeNode build(int start, int end, int[] array) {
        if (start > end) {
            return null;
        }
        SegmentTreeNode root = new SegmentTreeNode(start, end, Integer.MAX_VALUE);
        if (start != end) {
            int mid = (start + end) / 2;
            root.left = build(start, mid, array);
            root.right = build(mid + 1, end, array);
            root.val = root.left.val + root.right.val;
            System.out.println(root.val);
        } else {
            root.val = array[start];
        }
        return root;
    }

    public int query(SegmentTreeNode node, int start, int end) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }
        if (node.start == start && node.end == end) {
            return node.val;
        }
        int mid = (node.start + node.end) / 2;
        int leftmin = Integer.MAX_VALUE, rightmin = Integer.MAX_VALUE;
        if (start <= mid) {
            if (end < mid) {
                leftmin = query(node.left, start, end);
            } else {
                leftmin = query(node.left, start, mid);
            }
        }
        if (end > mid) {
            if (start > mid)
                rightmin = query(node.right, start, end);
        } else {
            rightmin = query(node.right, mid + 1, end);
        }
        return Math.min(leftmin, rightmin);
    }

    public int modify(SegmentTreeNode root, int index, int val) {
        if (root == null){
            return Integer.MIN_VALUE;
        }
        int leftval, rightval;
        if (root.left == null && root.right == null) {
            root.val = val;
            return root.val;
        }
        int mid = (root.start + root.end) / 2;
        if (index <= mid) {
            leftval = modify(root.left, index, val);
            root.val = leftval + root.right.val;
        }else {
            rightval = modify(root.right,index,val);
            root.val = rightval + root.left.val;
        }
        return root.val;
    }

    public List<Integer> queryIntervalMin(int[] array, List<Interval> intervalList) {
        List<Integer> resList = new ArrayList<Integer>();
        SegmentTreeNode root = build(0, array.length - 1, array);
        System.out.println(inorderTraversal(root, new ArrayList<Integer>()));
        for (Interval interval : intervalList) {
            resList.add(query(root, interval.start, interval.end));
        }
        return resList;
    }

    public List<Integer> inorderTraversal(SegmentTreeNode node, List<Integer> list) {
        if (node == null) {
            return list;
        }
        list = inorderTraversal(node.left, list);
        list.add(node.val);
        list = inorderTraversal(node.right, list);
        return list;
    }
}

