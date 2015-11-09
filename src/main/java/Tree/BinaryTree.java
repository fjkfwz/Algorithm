package Tree;

/**
 * Created by jz on 2015/11/9.
 */
public class BinaryTree {
    private TreeNode root;

    class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public void addChild(int val) {
        TreeNode node = root;
        if (root == null) {
            root = new TreeNode(val);
        } else {
            while (node != null) {
                if (val < node.val) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            node = new TreeNode(val);
        }
    }

    public void delChild(int val) {
        TreeNode node = root;
        if (root == null) {
            System.out.println("Can't find BinaryTree");
        } else {
            while (node != null) {
                if (val == node.val) {
                    node = null;
                } else if (val < node.val) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }
    }

//    @Override
//    public String toString() {
//        List<TreeNode> list = new ArrayList<>();
//        String string = "";
//        TreeNode rightcursor = root;
//        TreeNode leftcursor = root;
//        list.add(rightcursor);
//        if (root == null) {
//            return string = "";
//        }
//        while (rightcursor != null && leftcursor != null) {
//            if (rightcursor.left != null) {
//                 = node.left;
//            }
//            if (node.right != null) {
//
//            }
//        }
//    }
}

