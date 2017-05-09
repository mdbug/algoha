import java.util.ArrayList;
import java.util.Collections;

public class BTree {
    private final int ORDNUNG = 2;
    private BTreeNode root = new BTreeNode();

    public boolean search(int x) {
        return search(x, root);
    }

    private boolean search(int x, BTreeNode node) {
        if (node == null) {
            return false;
        }
        int i = Collections.binarySearch(node.values, x);
        if (i >= 0) {
            return true;
        } else {
            assert -i-1 < node.children.size();
            return search(x, node.children.get(-i - 1));
        }
    }

    public int insert(int x) {
        assert root != null;
        return insert(x, root);
    }

    private int insert(int x, BTreeNode node) {
        assert node != null;
        int i = Collections.binarySearch(node.values, x);
        if (i >= 0) {
            throw new ArithmeticException("Der Wert ist schon vorhanden");
        }
        try {
            BTreeNode child = node.children.get(-i - 1);
            if (child == null) {
                throw new IndexOutOfBoundsException();
            }
            return insert(x, child);
        } catch (IndexOutOfBoundsException e) {
            return insertIntoNode(x, node, -i - 1);
        }
    }

    private int insertIntoNode(int x, BTreeNode node, int index) {
        assert index <= node.values.size();
        assert index <= node.children.size();
        node.values.add(index, x);
        node.children.add(index + 1, null);

        int ret = 0;
        int size = node.values.size();
        if (size > 2 * ORDNUNG) {
            ret = 1;
            BTreeNode current = new BTreeNode();
            // [.1.2.3.4.5.]
            current.values = new ArrayList<>(node.values.subList(size / 2 + 1, size));
            current.children = new ArrayList<>(node.children.subList(size / 2 + 1, size));
            for (int j = size - 1; j > size / 2; --j) {
                node.values.remove(j);
                node.children.remove(j);
            }
            if (node == root) {
                root = new BTreeNode();
                node.parent = root;
                root.children.add(0, node);
                ret = 2;
            }
            int center = node.values.remove(size / 2);
            int i = Collections.binarySearch(node.parent.values, center);
            ret = Math.max(ret, insertIntoNode(center, node.parent, -i-1));
        }
        return ret;
    }
}
