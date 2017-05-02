/**
 * PACKAGE_NAME
 */
public class BinTree {
    private TreeNode root;

    private TreeNode getNode(int x) {
        TreeNode node = root;
        while(node != null) {
            int value = node.getValue();
            if (x == value) {
                return node;
            } if (x < value) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return null;
    }

    private TreeNode getParentNode(int x) {
        TreeNode node = root;
        TreeNode parent = null;
        while(node != null) {
            int value = node.getValue();
            if (x == value) {
                return parent;
            } else {
                parent = node;
                if (x < value) {
                    node = node.getLeft();
                } else {
                    node = node.getRight();
                }
            }
        }
        return null;
    }

    public boolean search(int x) {
        return getNode(x) != null;
    }

    public void insert(int x) {
        TreeNode node = root;
        if (root == null) {
            root = new TreeNode(x);
            return;
        }
        while(true) {
            int value = node.getValue();
            if (x == value) {
                throw new ArithmeticException("Wert ist schon vorhanden");
            } else if (x < value) {
                TreeNode left = node.getLeft();
                if (left == null) {
                    node.setLeft(new TreeNode(value));
                    return;
                }
                node = left;
            } else { // x > value
                TreeNode right = node.getRight();
                if (right == null) {
                    node.setLeft(new TreeNode(value));
                    return;
                }
                node = right;
            }
        }
    }

    public void clear() {
        root = null;
    }

    public void remove(int x) {
        TreeNode parent = getParentNode(x);
        TreeNode node;
        boolean isLeftChild;
        if (parent == null) {
            throw new ArithmeticException("Wert ist nicht vorhanden");
        } else if (x < parent.getValue()) {
            node = parent.getLeft();
            isLeftChild = true;
        } else { // x > parent.getValue()
            node = parent.getRight();
            isLeftChild  = false;
        }

        if (node.isLeaf()) {
            if (isLeftChild) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if (node.isNonBranchingNode()) {
            TreeNode left = node.getLeft();
            TreeNode right = node.getRight();
            if (isLeftChild) {
                if (left == null) {
                    parent.setLeft(right);
                } else {
                    parent.setLeft(right);
                }
            } else {
                if (left == null) {
                    parent.setLeft(right);
                } else {
                    parent.setLeft(right);
                }
            }
        } else {

        }
    }
}