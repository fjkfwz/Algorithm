package Tree;

import Tree.BinaryTree.BinaryTree;

/**
 * Created by jz on 2015/11/9.
 */
public class Tree {

    public static void main(String arg[]) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.addChild(5);
        binaryTree.addChild(2);
        binaryTree.addChild(3);
        binaryTree.addChild(4);
        binaryTree.addChild(1);
        binaryTree.addChild(7);
        binaryTree.addChild(6);
        binaryTree.addChild(10);
//        TreeNode root = new TreeNode(5);
//        TreeNode treeNode0 = new TreeNode(10);
//        TreeNode treeNod1 = new TreeNode(1);
//        root.setLeft(treeNode0);
//        root.setRight(treeNod1);
//        List<Integer> list = new ArrayList<>();
//        System.out.println(binaryTree.preorderTraversal(binaryTree.getRoot(), list).toString());
        System.out.println(binaryTree.maxDepth(binaryTree.getRoot(), 0));
//        System.out.println(binaryTree.postorderTraversal(binaryTree.getRoot(), list).toString());
    }

}
