package Tree;

/**
 * Created by jz on 2015/11/9.
 */
public class Tree {
    public static void main(String arg[]) {
        TreeNode root = new TreeNode(1);
        TreeNode node0 = new TreeNode(2);
        TreeNode node1 = new TreeNode(3);
        root.addChild(node0);
        root.addChild(node1);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(5);
        node1.addChild(node2);
        node1.addChild(node3);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(7);
        node3.addChild(node4);
        node3.addChild(node5);
        System.out.println(root.getNodeByLayer(2));
    }
}
