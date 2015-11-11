package Tree.BinaryTree.SegmentTree;

/**
 * Created by jz on 2015/11/11.
 */
public class TreeTest {
    public static void main(String args[]) {
        SegmentTree segmentTree = new SegmentTree();
        int[] data = new int[]{0, 1, 2, 3,4, 5, 6, 7, 8, 9, 10};
        System.out.println(segmentTree.query(segmentTree.build(data), 9, 10));
    }
}
