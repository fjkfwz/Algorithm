package Tree.BinaryTree;

import java.util.List;

/**
 * Created by jz on 2015/11/9.
 */
public class BinaryTree {
    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public void addChild(int val) {
        if (root == null) {
            root = new TreeNode(val);
        } else {
            TreeNode node = root;
            while (node != null) {
                if (val < node.getVal()) {
                    if (node.getLeft() != null) {
                        node = node.getLeft();
                    } else {
                        node.setLeft(new TreeNode(val));
                        break;
                    }
                } else {
                    if (node.getRight() != null) {
                        node = node.getRight();
                    } else {
                        node.setRight(new TreeNode(val));
                        break;
                    }
                }
            }
        }
    }

    public void delChild(int val) {
        TreeNode node = root;
        if (root == null) {
            System.out.println("Can't find BinaryTree");
        } else {
            while (node != null) {
                if (val == node.getVal()) {
                    node = null;
                } else if (val < node.getVal()) {
                    node = node.getLeft();
                } else {
                    node = node.getRight();
                }
            }
        }
    }

    /**
     * Previous Order Traversal Tree Nide
     *
     * @param node
     * @param list
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode node, List<Integer> list) {
        list.add(node.getVal());
        if (node.getLeft() != null) {
            preorderTraversal(node.getLeft(), list);
        }
        if (node.getRight() != null) {
            preorderTraversal(node.getRight(), list);
        }
        return list;
    }

    /**
     * Inter Order Traversal Tree Node
     *
     * @param node
     * @param list
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode node, List<Integer> list) {
        if (node.getLeft() != null) {
            inorderTraversal(node.getLeft(), list);
        }
        list.add(node.getVal());
        if (node.getRight() != null) {
            inorderTraversal(node.getRight(), list);
        }
        return list;
    }

    /**
     * Poster Order Traversal Tree Node
     *
     * @param node
     * @param list
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode node, List<Integer> list) {
        if (node.getLeft() != null) {
            postorderTraversal(node.getLeft(), list);
        }
        if (node.getRight() != null) {
            postorderTraversal(node.getRight(), list);
        }
        list.add(node.getVal());
        return list;
    }

    public boolean isValidBST(TreeNode node, boolean isVaild) {
        if ((node.getLeft() != null && node.getLeft().getVal() > node.getVal()) || (node.getRight() != null && node.getRight().getVal() < node.getVal())) {
            isVaild = false;
        }
        if (node.getLeft() != null) {
            isVaild = isValidBST(node.getLeft(), isVaild);
        }
        if (node.getRight() != null) {
            isVaild = isValidBST(node.getRight(), isVaild);
        }
        return isVaild;
    }

    public int minDepth(TreeNode node, int count) {
        count++;
        int rightcount = count;
        int leftcount = count;
        if (node.getLeft() == null || node.getLeft() == null) {
            return count;
        }
        if (node.getLeft() != null) {
            leftcount = minDepth(node.getLeft(), count);
        }
        if (node.getRight() != null) {
            rightcount = minDepth(node.getRight(), count);
        }
        return Math.min(leftcount, rightcount);
    }

    public int maxDepth(TreeNode node, int count) {
        count++;
        int rightcount = count;
        int leftcount = count;
        if (node.getLeft() != null) {
            leftcount = maxDepth(node.getLeft(), count);
        }
        if (node.getRight() != null) {
            rightcount = maxDepth(node.getRight(), count);
        }
        return Math.max(leftcount, rightcount);
    }

    public void invertBinaryTree(TreeNode node) {
            TreeNode tmpTree = node.getLeft();
            node.setLeft(node.getRight());
            node.setRight(tmpTree);
        if (node.getLeft() != null) {
            invertBinaryTree(node.getLeft());
        }
        if (node.getRight() != null) {
            invertBinaryTree(node.getRight());
        }
    }

    public TreeNode flatten(TreeNode node) {
        TreeNode LeftNode = node;
        TreeNode RightNode = node;
        if (node.getLeft() != null) {
             LeftNode= flatten(node.getLeft());
        }

        if (node.getRight() != null) {
            RightNode = flatten(node.getRight());
        }
        node.setRight(LeftNode);
        node.getRight().setRight(RightNode);
        return node;
    }
}

