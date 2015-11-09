package Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jz on 2015/11/9.
 */

public class TreeNode{
    /**
     * This node's parent node.  If this is the root of the tree then
     * the parent will be <code>null</code>.
     */
    private TreeNode parent;

    /**
     * An array of all this node's child nodes.  The array will always
     * exist (i.e. never <code>null</code>) and be of length zero if this is
     * a leaf node.
     * <p/>
     * This is an array instead of a <code>Vector</code> to favor speed of
     * accessing the children.  The array takes longer on adds because
     * of array copying and management.  However to get the array it
     * can just be returned instead of creating a new array from the
     * <code>Vector</code> each time.  The tree is used frequently enough for
     * reading course elements that this difference makes a small impact
     * on rendering.
     */
    private TreeNode[] children = new TreeNode[0];
    /**
     * A handle to the programmer assigned object encapsulated by this
     * node.  This will be <code>null</code> when the user has not assigned
     * any data to this node.
     */
    private Object m_userData;

    /**
     * Constructs a tree node object.  It can become the root of a tree.
     * Or it can become a child of another node by calling the other node's
     * <code>add</code> method.
     * <p/>
     * There is no user object attached to the node.  Call <code>setUserObject</code>
     * to attach one.
     */
    public TreeNode() {
        // Nothing needed.
    }

    /**
     * Constructs a tree node object.  It can become the root of a tree.
     * Or it can become a child of another node by calling the other node's
     * <code>add</code> method.
     *
     * @param userObject is an object this node encapsulates.  It is up to
     *                   the developer to maintain its type.  To get the object back out
     *                   call <code>getUserObject</code>.
     */
    public TreeNode(Object userObject) {
        m_userData = userObject;
    }

    /**
     * Attaches a user defined object to this node.  Only one
     * object can be attached to a node.
     *
     * @param userObject is the programmer defined object to
     *                   attach to this node in the tree.  Set it to <code>null</code>
     *                   to clear any objects.
     */
    public void setUserObject(Object userObject) {
        m_userData = userObject;
    }

    /**
     * Gets the user defined object attached to this node.  It
     * must be cast back to what it was inserted as.  It is up
     * to the developer to make this cast.
     *
     * @return The programmer defined object attached to this
     * node in the tree.  Returns <code>null</code> if no object is
     * attached.
     */
    public Object getUserObject() {
        return m_userData;
    }

    /**
     * Adds the <code>child</code> node to this container making this its parent.
     *
     * @param child is the node to add to the tree as a child of <code>this</code>
     * @param index is the position within the children list to add the
     *              child.  It must be between 0 (the first child) and the
     *              total number of current children (the last child).  If it is
     *              negative the child will become the last child.
     */
    public void addChild(TreeNode child, int index) {
        // Add the child to the list of children.
        if (index < 0 || index == children.length)  // then append
        {
            TreeNode[] newChildren = new TreeNode[children.length + 1];
            System.arraycopy(children, 0, newChildren, 0, children.length);
            newChildren[children.length] = child;
            children = newChildren;
        } else if (index > children.length) {
            throw new IllegalArgumentException("Cannot add child to index " + index + ".  There are only " + children.length + " children.");
        } else  // insert
        {
            TreeNode[] newChildren = new TreeNode[children.length + 1];
            if (index > 0) {
                System.arraycopy(children, 0, newChildren, 0, index);
            }
            newChildren[index] = child;
            System.arraycopy(children, index, newChildren, index + 1, children.length - index);
            children = newChildren;
        }

        // Set the parent of the child.
        child.parent = this;
    }

    /**
     * Adds the <code>child</code> node to this container making this its parent.
     * The child is appended to the list of children as the last child.
     */
    public void addChild(TreeNode child) {
        addChild(child, -1);
    }

    /**
     * Removes the child at position <code>index</code> from the tree.
     *
     * @param index is the position of the child.  It should be between
     *              0 (the first child) and the total number of children minus 1
     *              (the last child).
     * @return The removed child node.  This will be <code>null</code> if
     * no child exists at the specified <code>index</code>.
     */
    public TreeNode removeChild(int index) {
        if (index < 0 || index >= children.length)
            throw new IllegalArgumentException("Cannot remove element with index " + index + " when there are " + children.length + " elements.");

        // Get a handle to the node being removed.
        TreeNode node = children[index];
        node.parent = null;

        // Remove the child from this node.
        TreeNode[] newChildren = new TreeNode[children.length - 1];
        if (index > 0) {
            System.arraycopy(children, 0, newChildren, 0, index);
        }
        if (index != children.length - 1) {
            System.arraycopy(children, index + 1, newChildren, index, children.length - index - 1);
        }
        children = newChildren;

        return node;
    }

    /**
     * Removes this node from its parent.  This node becomes the root
     * of a subtree where all of its children become first level
     * nodes.
     * <p/>
     * Calling this on the root node has no effect.
     */
    public void removeFromParent() {
        if (parent != null) {
            int position = this.index();
            parent.removeChild(position);
            parent = null;
        }
    }

    /**
     * Gets the parent node of this one.
     *
     * @return The parent of this node.  This will return <code>null</code>
     * if this node is the root node in the tree.
     */
    public TreeNode getParent() {
        return parent;
    }

    /**
     * Returns if this node is the root node in the tree or not.
     *
     * @return <code>true</code> if this node is the root of the tree;
     * <code>false</code> if it has a parent.
     */
    public boolean isRoot() {
        if (parent == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns if this node has children or if it is a leaf
     * node.
     *
     * @return <code>true</code> if this node has children; <code>false</code>
     * if it does not have any children.
     */
    public boolean hasChildren() {
        if (children.length == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Gets the position of this node in the list of siblings
     * managed by the parent node.  This node can be obtained
     * by <code>this = parent.children[this.index()]</code>.
     *
     * @return The index of the child array of this node's
     * parent.  If this is the root node it will return -1.
     */
    public int index() {
        if (parent != null) {
            for (int i = 0; ; i++) {
                Object node = parent.children[i];
                if (this == node) {
                    return i;
                }
            }
        }

        // Only ever make it here if this is the root node.
        return -1;
    }

    /**
     * Gets this node's depth in the tree.  The root node will
     * have a depth of 0, first-level nodes will have a depth
     * of 1, and so on.
     *
     * @return The depth of this node in the tree.
     */
    public int getDepth() {
        int depth = recurseDepth(parent, 0);
        return depth;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    /**
     * Gets a list of all the child nodes of this node.
     *
     * @return An array of all the child nodes.  The array will
     * be the size of the number of children.  A leaf node
     * will return an empty array, not <code>null</code>.
     */

    public TreeNode[] getChildren() {
        return children;
    }

    /**
     * Recursive helper method to calculate the depth of a node.
     * The caller should pass its parent and an initial depth of 0.
     * <p/>
     * A recursive approach is used so that when a node that is
     * part of a tree is removed from that tree, we do not need
     * to recalculate the depth of every node in that subtree.
     *
     * @param node  is the node to recursively check for its depth.
     *              This should be set to <code>parent</code> by the caller.
     * @param depth is the depth of the current node (i.e. the
     *              child of <code>node</code>).  This should be set to 0 by the
     *              caller.
     */
    private int recurseDepth(TreeNode node, int depth) {
        if (node == null)  // reached top of tree
        {
            return depth;
        } else {
            return recurseDepth(node.parent, depth + 1);
        }
    }

    /**
     * Output Tree Construct;
     * @return
     */
    @Override
    public String toString() {
        List<TreeNode> branch = new ArrayList<>();
        List<TreeNode> childbranch = new ArrayList<>();
        TreeNode node = this;
        branch.add(node);
        String string = "";
        while (branch.size() > 0) {
            string += "[ ";
            for (int i = 0; i < branch.size(); i++) {
                node = branch.get(i);
                string += node.getUserObject();
                if (node.hasChildren()) {
                    for (int j = 0; j < node.getChildren().length; j++) {
                        childbranch.add(node.getChildren()[j]);
                    }
                }
                string += " ";
            }
            branch.clear();
            for (TreeNode treeNode : childbranch) {
                branch.add(treeNode);
            }
            childbranch.clear();
            string += "]";
        }
        return string;
    }

    /**
     * Get All SubTree Node Count；
     * @return
     */
    public int getTreeCount() {
        List<TreeNode> branch = new ArrayList<>();
        List<TreeNode> childbranch = new ArrayList<>();
        TreeNode node = this;
        branch.add(node);
        int count = 0;
        while (branch.size() > 0) {
            for (int i = 0; i < branch.size(); i++) {
                node = branch.get(i);
                count++;
                if (node.hasChildren()) {
                    for (int j = 0; j < node.getChildren().length; j++) {
                        childbranch.add(node.getChildren()[j]);
                    }
                }
            }
            branch.clear();
            for (TreeNode treeNode : childbranch) {
                branch.add(treeNode);
            }
            childbranch.clear();
        }
        return count;
    }

    /**
     * Caculate Tree Height
     * @return
     */
    public int getTreeHeight() {
        List<TreeNode> branch = new ArrayList<>();
        List<TreeNode> childbranch = new ArrayList<>();
        TreeNode node = this;
        branch.add(node);
        int count = 0;
        while (branch.size() > 0) {
            for (int i = 0; i < branch.size(); i++) {
                node = branch.get(i);
                if (node.hasChildren()) {
                    for (int j = 0; j < node.getChildren().length; j++) {
                        childbranch.add(node.getChildren()[j]);
                    }
                }
            }
            count++;
            branch.clear();
            for (TreeNode treeNode : childbranch) {
                branch.add(treeNode);
            }
            childbranch.clear();
        }
        return count;
    }

    /**
     * Get Appoint layer Nodes;
     * @param count
     * @return
     */
    public List<TreeNode> getNodeByLayer(int count) {
        List<TreeNode> branch = new ArrayList<>();
        List<TreeNode> childbranch = new ArrayList<>();
        TreeNode node = this;
        branch.add(node);
        while (branch.size() > 0) {
            for (int i = 0; i < branch.size(); i++) {
                node = branch.get(i);
                if (node.hasChildren()) {
                    for (int j = 0; j < node.getChildren().length; j++) {
                        childbranch.add(node.getChildren()[j]);
                    }
                }
            }
            branch.clear();
            for (TreeNode treeNode : childbranch) {
                branch.add(treeNode);
            }
            childbranch.clear();
            count--;
            if (count <= 0) {
                break;
            }
        }
        System.out.println(branch.size());
        return branch;
    }

    public int minDepth(){
        List<TreeNode> branch = new ArrayList<>();
        List<TreeNode> childbranch = new ArrayList<>();
        TreeNode node = this;
        branch.add(node);
        int count = 1;
        while (branch.size() > 0) {
            for (int i = 0; i < branch.size(); i++) {
                node = branch.get(i);
                if (node.hasChildren()) {
                    for (int j = 0; j < node.getChildren().length; j++) {
                        childbranch.add(node.getChildren()[j]);
                    }
                }else{
                    return count;
                }
            }
            count++;
            branch.clear();
            for (TreeNode treeNode : childbranch) {
                branch.add(treeNode);
            }
            childbranch.clear();
        }
        return count;
    }
}

