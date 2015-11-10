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
        if (node == null) {
            return list;
        }
        list.add(node.getVal());
        preorderTraversal(node.getLeft(), list);
        preorderTraversal(node.getRight(), list);
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
        if (node == null) {
            return list;
        }
        inorderTraversal(node.getLeft(), list);
        list.add(node.getVal());
        inorderTraversal(node.getRight(), list);
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
        if (node == null) {
            return list;
        }
        postorderTraversal(node.getLeft(), list);
        postorderTraversal(node.getRight(), list);
        list.add(node.getVal());
        return list;
    }

    /**
     * Judge A Binary Tree whether is a BST;
     *
     * @param node
     * @param isVaild
     * @return
     */
    public boolean isValidBST(TreeNode node, boolean isVaild) {
        if (node == null) {
            return isVaild;
        }
        if ((node.getLeft() != null && node.getLeft().getVal() > node.getVal()) || (node.getRight() != null && node.getRight().getVal() < node.getVal())) {
            isVaild = false;
        }
        isVaild = isValidBST(node.getLeft(), isVaild);
        isVaild = isValidBST(node.getRight(), isVaild);
        return isVaild;
    }

    /**
     * Find BST mini Depth
     *
     * @param node
     * @param count
     * @return
     */
    public int minDepth(TreeNode node, int count) {
        if (node == null) {
            return count;
        }
        count++;
        int rightcount = count;
        int leftcount = count;
        if (node.getLeft() == null || node.getLeft() == null) {
            return count;
        }
        leftcount = minDepth(node.getLeft(), count);
        rightcount = minDepth(node.getRight(), count);
        return Math.min(leftcount, rightcount);
    }

    /**
     * Find BST max Depth
     *
     * @param node
     * @param count
     * @return
     */
    public int maxDepth(TreeNode node, int count) {
        if (node == null) {
            return count;
        }
        count++;
        int rightcount = count;
        int leftcount = count;
        leftcount = maxDepth(node.getLeft(), count);
        rightcount = maxDepth(node.getRight(), count);
        return Math.max(leftcount, rightcount);
    }

    /**
     * Invert Binary Tree
     *
     * @param node
     */
    public void invertBinaryTree(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode tmpTree = node.getLeft();
        node.setLeft(node.getRight());
        node.setRight(tmpTree);
        invertBinaryTree(node.getLeft());
        invertBinaryTree(node.getRight());
    }

    /**
     * Flatten Binary Tree to Linked List
     * Flatten a binary tree to a fake "linked list" in pre-order traversal.
     * Here we use the right pointer in TreeNode as the next pointer in ListNode.
     *
     * @param node
     * @param lastNode
     */
    public void flatten(TreeNode node, TreeNode lastNode) {
        if (node == null) {
            return;
        }
        if (lastNode != null) {
            lastNode.setLeft(null);
            lastNode.setRight(node);
        }
        lastNode = node;
        flatten(node.getLeft(), lastNode);
        flatten(node.getRight(), lastNode);
    }


}

